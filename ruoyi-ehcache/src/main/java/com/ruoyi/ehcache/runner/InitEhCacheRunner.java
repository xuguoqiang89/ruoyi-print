package com.ruoyi.ehcache.runner;

import com.ruoyi.ehcache.util.EhCacheUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * springboot 允许实现CommandLineRunner接口的类程序启动后run方法中做一些事情，比如加载缓存.
 */
/*@Component
@Order(value = 1)*/
public class InitEhCacheRunner implements CommandLineRunner {
    protected   final Logger log= LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDictDataService dictDataService;

    @Override
    public void run(String... args) throws Exception {
        log.info("InitEhCacheRunner加载Ehcache缓存信息Start>>>>>>>>>");
        //可以从数据库加载配置信息到缓存
        initSysUser();

        initDicData();

        log.info("InitEhCacheRunner加载Ehcache缓存信息End<<<<<<<<");
    }

    private void initSysUser(){
        List<SysUser> list = sysUserService.selectAllUserList();
        for(SysUser user:list){
            EhCacheUtils.putUserInfo(user.getUserId().toString(), user.getUserName());
        }
    }

    private void initDicData(){
        List<SysDictData> dataList = dictDataService.selectDictDataList(new SysDictData());
        for(SysDictData data:dataList){
            EhCacheUtils.putDictInfo(data.getDictType(), data.getDictValue(), data.getDictLabel());
        }
    }
}
