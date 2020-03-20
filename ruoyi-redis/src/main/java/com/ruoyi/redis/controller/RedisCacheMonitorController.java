package com.ruoyi.redis.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.redis.service.RedisCacheMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/monitor/redis")
public class RedisCacheMonitorController extends BaseController {

    @Autowired
    RedisCacheMonitorService jedisUtilCacheService;

    private String prefix = "monitor/redis";

    /**
     * 跳转页面
     * @return
     */
    @RequestMapping(value={"","/"})
    public String index(){
        return prefix+"/list";
    }
    @RequestMapping("/viewCacheInfoByKey")
    public String viewCacheInfo(String key, Model model){

        if(key.contains("$")){
            key=key.replace("$","#") ;
        }
        Map<String,Object> info= jedisUtilCacheService.viewCacheInfoByKey(key);
        model.addAttribute("info",info);
        return prefix+"/viewInfo";
    }

    /**
     * redis缓存信息列表
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(HttpServletRequest req){
        Map map= ServletUtils.getMap(req);
        return jedisUtilCacheService.listPg(map);
    }

    /**
     * 清除缓存信息
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("/removeCacheByKey")
    public Object removeCacheByKey(String key){
        if(key.contains("$")){
            key=key.replace("$","#") ;
        }
        return jedisUtilCacheService.removeCacheByKey(key);
    }
}
