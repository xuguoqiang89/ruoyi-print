package com.ruoyi.redis.service;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.redis.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RedisCacheMonitorService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public Object removeCacheByKey(String key){
        if(RedisUtil.exists(key)){
            RedisUtil.del(key);
            return AjaxResult.success("清除缓存成功!");
        }
        return AjaxResult.error("不存在key为["+key+"]的缓存!");
    }
    public Map<String,Object> viewCacheInfoByKey(String key){
        Map<String,Object> res=new HashMap();
        if(RedisUtil.exists(key)){
            res.put("key",key);
            String value= RedisUtil.getStringValue(key);
            res.put("value",value);
        }
        return res;
    }
    /**
     * 获取缓存分页信息
     * @param map
     * @return
     */
    public Object listPg(Map<String, Object> map) {
        try {
            String keyContent = String.valueOf(map.get("keyContent"));

            String page_str = (String) map.get("pageNum");//当前页
            String limit_str = (String) map.get("pageSize");//每页几条数据
            page_str = StringUtils.assertNotNullOrEmpty(page_str, "1");
            limit_str = StringUtils.assertNotNullOrEmpty(limit_str, "10");
            int page = Integer.parseInt(page_str);
            int limit = Integer.parseInt(limit_str);
            int start = page - 1;
            if (page == 1 || page == 0) {
                start = 0;
            }
            if (StringUtils.isEmpty(keyContent)) {
                keyContent = "*";
            } else {
                keyContent = "*" + keyContent + "*";
            }
            List<String> keysList = RedisUtil.keys(keyContent);
            int keysCount = keysList.size();
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            Map<String, Object> temp = null;
            for (int i = start * limit; i < keysCount && i < (start + 1) * limit; i++) {
                temp = new HashMap<>();
                String key = keysList.get(i);
                String type = RedisUtil.type(key);
                long ttl = RedisUtil.ttl(key);
                temp.put("key", key);
                temp.put("type", type);
                temp.put("ttl", ttl);
                mapList.add(temp);
            }
            TableDataInfo tableDataInfo=new TableDataInfo();
            tableDataInfo.setTotal(keysCount);
            tableDataInfo.setRows(mapList);
            tableDataInfo.setCode(0);
            tableDataInfo.setMsg(0);

            return tableDataInfo;
        }catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("获取缓存分页信息失败!");
        }
    }


}
