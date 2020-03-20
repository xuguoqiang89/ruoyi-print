package com.ruoyi.redis.runner;

import com.ruoyi.redis.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * springboot 允许实现CommandLineRunner接口的类程序启动后run方法中做一些事情，比如加载缓存.
 */
/*@Component
@Order(value = 2)*/
public class InitRedisCacheRunner implements CommandLineRunner {
    protected   final Logger log= LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        log.info("InitRedisCacheRunner加载缓存信息start>>>>>>>");
        //可以从数据库加载配置信息到缓存
        RedisUtil.setStringValue("test_key","test_value");
        RedisUtil.setStringValue("test_user_key","test_user_value",18000);
        for(int i=1;i<=20;i++){
            RedisUtil.putDefaultInfo("key_"+i,"value"+i);
            RedisUtil.putSysInfo("key_"+i,"value"+i);
            RedisUtil.putUserInfo("key_",i+"","value"+i);
        }

        log.info("InitRedisCacheRunner加载缓存信息End<<<<<<<<");
    }
}
