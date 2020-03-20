package com.ruoyi.ehcache.config;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class EhCacheConfig {

    @Bean("ehCacheManager")
    public EhCacheManagerFactoryBean getEhCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean=new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache-local.xml"));
        return ehCacheManagerFactoryBean;
    }

}
