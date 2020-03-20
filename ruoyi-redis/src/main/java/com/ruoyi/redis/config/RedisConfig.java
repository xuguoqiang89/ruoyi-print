package com.ruoyi.redis.config;
import com.ruoyi.redis.util.InitRedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * 读取代码生成相关配置
 * 
 * @author wujiyue
 */
@Configuration
@PropertySource(value = { "classpath:redis.properties" })
public class RedisConfig {
    protected   final Logger log= LoggerFactory.getLogger(getClass());
    @Value("${redis.address}")
    protected String redisAddress;

    @PostConstruct
    public void initRedis(){
        log.info(">>>>>>>redisAddress:"+redisAddress);
        InitRedisUtil.init(redisAddress);
        if(InitRedisUtil.initFlag){
            log.info(">>>>>>>redis init success!");
        }else{
            log.warn(">>>>>>>redis init fail!");
        }
    }
}
