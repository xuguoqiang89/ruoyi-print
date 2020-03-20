package com.ruoyi.ehcache.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ehcache.service.EhCacheMonitorService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Ehcache缓存监控
 * @author wujiyue
 * @date 2015/10/30
 */
@Controller
@RequestMapping("/monitor/ehcache")
public class EhCacheMonitorController extends BaseController {
    private String prefix = "monitor/ehcache";

    @Autowired
    EhCacheMonitorService cacheMonitorService;
    @RequestMapping(value={"","/"})
    public String index(){
        return prefix+"/list";
    }

    @ResponseBody
    @RequestMapping("/json/sysCacheState")
    public Object sysCacheState(){
        return cacheMonitorService.sysCacheState();
    }
    @ResponseBody
    @RequestMapping("/json/userCacheState")
    public Object userCacheState(){
        return cacheMonitorService.userCacheState();
    }

    @ResponseBody
    @RequestMapping("/json/getSysCacheByKey")
    public Object getSysCacheByKey(HttpServletRequest req){
        Map map= ServletUtils.getMap(req);
        return cacheMonitorService.getSysCacheByKey(map);
    }

    @ResponseBody
    @RequestMapping("/list")
    public Object getCacheInfo(HttpServletRequest req){
        Map map= ServletUtils.getMap(req);
        return cacheMonitorService.getCacheInfo(map);
    }

    /**
     * 清除缓存信息
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("/json/removeCacheByKey")
    public Object removeCacheByKey(String key){
        return cacheMonitorService.removeCacheByKey(key);
    }

    /**
     * 获得指定key的缓存信息
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("/json/viewCacheInfoByKey")
    public Object viewCacheInfoByKey(String key){
        return cacheMonitorService.viewCacheInfoByKey(key);
    }


    @RequestMapping("/viewCacheInfoByKey")
    public String viewCacheInfo(String key,Model model){
        if(StringUtils.isEmpty(key)){
            throw new BusinessException("参数key不能为空!");
        }
        Map<String,Object> info= cacheMonitorService.viewCacheInfoByKey(key);
        Map<String, Object> o=null;
        if(info!=null){
            List<Map<String, Object>> list=(List<Map<String, Object>>)info.get("rows");
            if(CollectionUtils.isNotEmpty(list)){
                o= list.get(0);
            }
        }
        model.addAttribute("info",o);
        return prefix+"/viewInfo";
    }

}
