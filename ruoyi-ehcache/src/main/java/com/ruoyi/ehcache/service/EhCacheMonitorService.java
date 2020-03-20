package com.ruoyi.ehcache.service;

import com.alibaba.fastjson.JSON;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ehcache.util.EhCacheUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wujiyue
 * @date 2015/10/30
 */
@Service
public class EhCacheMonitorService {

    private final String cacheValueKey = "key";
    private final String cacheNameKey = "cachename";
    private final String cacheSizeKey = "size";
    private final String cacheMsSizeKey = "memorystoresize";
    private final String cacheDsSizeKey = "diskstoresize";
    private final String cachesImKey = "sizeinmemory";
    private final String cacheExplainKey = "explain";
    //系统缓存
    public Map<String, Object> sysCacheState(){
        //获得系统缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put(cacheNameKey, cache.getName());
        m.put(cacheSizeKey, Integer.valueOf(cache.getSize()));
        m.put(cacheMsSizeKey, Long.valueOf(cache.getMemoryStoreSize()));
        m.put(cacheDsSizeKey, Integer.valueOf(cache.getDiskStoreSize()));
        m.put(cachesImKey, Long.valueOf(cache.calculateInMemorySize()));
        return m;
    }
    //用户缓存
    public Map<String, Object> userCacheState(){
        //获得用户缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.USER_CACHE);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put(cacheNameKey, cache.getName());
        m.put(cacheSizeKey, Integer.valueOf(cache.getSize()));
        m.put(cacheMsSizeKey, Long.valueOf(cache.getMemoryStoreSize()));
        m.put(cacheDsSizeKey, Integer.valueOf(cache.getDiskStoreSize()));
        m.put(cachesImKey, Long.valueOf(cache.calculateInMemorySize()));
        return m;
    }

    /**
     * 获得特定key的系统缓存详情
     * @param map
     * @return
     */
    public Map<String, Object> getSysCacheByKey(Map<String, Object> map) {
        //获得系统缓存对象
        Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
        Map<String, Object> m = new HashMap<String, Object>();
        Object key = map.get(cacheValueKey);
        if (key==null||"".equals(key.toString())) {
            m.put(cacheExplainKey, "");
        } else {
            m.put(cacheValueKey, key.toString());
            Element element = cache.getQuiet(key.toString());
            if (element != null) {
                m.put(cacheExplainKey, element.toString());
            } else {
                m.put(cacheExplainKey, "");
            }
        }
        return m;
    }
    //获得指定key的缓存信息
    public Map<String, Object> viewCacheInfoByKey(String key){
        if(StringUtils.isNotEmpty(key)){
            Map<String, Object> res=getCacheInfoByKey(key,EhCacheUtils.SYS_CACHE);
            if(res!=null){
                return res;
            }else{
                res=getCacheInfoByKey(key,EhCacheUtils.USER_CACHE);
                if(res!=null){
                    return res;
                }else{
                    res=getCacheInfoByKey(key,EhCacheUtils.DEFAULT_CACHE);
                    if(res!=null){
                        return res;
                    }
                }
            }
        }
        return null;
    }
    private Map<String, Object> getCacheInfoByKey(String key,String cacheType){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        Cache cache =null;
        if(cacheType.equals(EhCacheUtils.SYS_CACHE)){
            cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
        }else if(cacheType.equals(EhCacheUtils.USER_CACHE)){
            cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.USER_CACHE);
        }else{
            //return null;
            cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.DEFAULT_CACHE);
        }
        List<String> keysList = cache.getKeys();
        keysList=keysList.stream().filter(s->{return s.contains(key);}).collect(Collectors.toList());
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> returnMap=new HashMap<String, Object>();
        if(CollectionUtils.isNotEmpty(keysList)){
            int  keysCount=keysList.size();

            for (int i = 0; i < keysCount; i++) {
                Element element = cache.getQuiet(keysList.get(i).toString());

                long l = element.getSerializedSize();
                Map<String, Object> elMap = new HashMap<String, Object>();
                elMap.put(cacheValueKey, keysList.get(i).toString());
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(element.getCreationTime());
                elMap.put("value",  JSON.toJSONString(element.getValue()));
                elMap.put("creattime", formatter.format(calendar.getTime()));
                calendar.setTimeInMillis(element.getLatestOfCreationAndUpdateTime());
                elMap.put("lastupdatetime", formatter.format(calendar.getTime()));
                calendar.setTimeInMillis(element.getLastAccessTime());
                elMap.put("lastaccesstime", formatter.format(calendar.getTime()));
                elMap.put("hittimes", Long.valueOf(element.getHitCount()));

                mapList.add(elMap);
            }
            returnMap.put("total",keysCount);
            returnMap.put("rows",mapList);
            return returnMap;
        }else{
            return null;
        }
    }
    public Object removeCacheByKey(String key){

        if(StringUtils.isNotEmpty(key)){
            Cache cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
            Element element = cache.getQuiet(key);
            if(element!=null){
                cache.remove(key);
                return AjaxResult.success("清除缓存成功!");
            }else{
                Cache cacheUser = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.USER_CACHE);
                Element elementUser = cacheUser.getQuiet(key);
                if(elementUser!=null){
                    cacheUser.remove(key);
                    return AjaxResult.success("清除缓存成功!");
                }else{
                    //return AjaxResult.error("不存在key="+key+"的缓存!");
                    Cache cacheDefault = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.DEFAULT_CACHE);
                    Element elementDefault = cacheDefault.getQuiet(key);
                    if(elementDefault!=null){
                        cacheDefault.remove(key);
                        return AjaxResult.success("清除缓存成功!");
                    }else{
                        return AjaxResult.error("不存在key="+key+"的缓存!");
                    }
                }
            }
        }else{
            return AjaxResult.error("请指定要清除的缓存的key!");
        }

    }
    /**
     * 获得缓存详情 0系统缓存，1用户缓存
     * @param map
     * @return
     */
    public Object getCacheInfo(Map<String, Object> map) {
        //获得系统缓存对象
        String cachetype=String.valueOf(map.get("cachetype"));
        Cache cache =null;
        if("1".equals(cachetype)){
             cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.USER_CACHE);
        }else if("0".equals(cachetype)){
            cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.SYS_CACHE);
        }else{
           cache = EhCacheUtils.getCacheManager().getCache(EhCacheUtils.DEFAULT_CACHE);
        }
        String keyContent=String.valueOf(map.get("keyContent"));
        try {
            String pageStr=(String)map.get("pageNum");//当前页
            String limitStr=(String)map.get("pageSize");//每页几条数据
            pageStr= StringUtils.assertNotNullOrEmpty(pageStr,"1");
            limitStr= StringUtils.assertNotNullOrEmpty(limitStr,"10");
            int page=Integer.parseInt(pageStr);
            int limit = Integer.parseInt(limitStr);
            int start = page-1;
            if(page == 1 || page == 0){
                start = 0;
            }
            List<String> keysList = cache.getKeys();

            int keysCount=keysList.size();
            if(StringUtils.isNotEmpty(keyContent)&&!"null".equals(keyContent)){
                keysList=keysList.stream().filter(s->{return s.contains(keyContent);}).collect(Collectors.toList());

                keysCount=keysList.size();
            }
            long serializeLong = 0L;
            for (int i = 0; i < keysList.size(); i++) {
                Element e = cache.getQuiet(keysList.get(i).toString());
                serializeLong += e.getSerializedSize();
            }
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            for (int i = start * limit; i < keysCount && i < (start+1) * limit; i++) {
                Element element = cache.getQuiet(keysList.get(i).toString());
                //JSONObject jsonValue=JSONObject.fromObject(element.getValue());

                long l = element.getSerializedSize();
                Map<String, Object> elMap = new HashMap<String, Object>();
                elMap.put(cacheValueKey, keysList.get(i).toString());
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(element.getCreationTime());
                elMap.put("value",  JSON.toJSONString(element.getValue()));
                elMap.put("creattime", formatter.format(calendar.getTime()));
                calendar.setTimeInMillis(element.getLatestOfCreationAndUpdateTime());
                elMap.put("lastupdatetime", formatter.format(calendar.getTime()));
                calendar.setTimeInMillis(element.getLastAccessTime());
                elMap.put("lastaccesstime", formatter.format(calendar.getTime()));
                elMap.put("hittimes", Long.valueOf(element.getHitCount()));
                if (serializeLong == 0L) {
                    elMap.put(cacheSizeKey, "0");
                    elMap.put("proprotion", "0%");
                } else {
                    double d = l * 100.0D / serializeLong;
                    elMap.put(cacheSizeKey, new DecimalFormat("####,###").format(l));
                    elMap.put("proprotion", new DecimalFormat("####.00").format(d) + "%");
                }
                mapList.add(elMap);
            }

            TableDataInfo tableDataInfo=new TableDataInfo();
            tableDataInfo.setTotal(keysList.size());
            tableDataInfo.setRows(mapList);
            tableDataInfo.setCode(0);
            tableDataInfo.setMsg(0);

            return tableDataInfo;
        }catch (Exception e) {
            return AjaxResult.error("获得系统缓存详情失败！");
        }

    }
}
