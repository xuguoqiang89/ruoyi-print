package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: Administrator
 * @Date: 2020\3\11 0011
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Autowired
    private SysConfigMapper configMapper;

    @Override
    @Async("threadPoolTaskExecutor")
    public void executeAsync() {
        /*logger.info("start executeAsync");

        System.out.println("异步线程要做的事情");
        System.out.println("可以在这里执行批量插入等耗时的事情");

        logger.info("end executeAsync");*/
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Integer> saveConfig(SysConfig config) {
        logger.info("插入数据："+config.getConfigKey());
        int result = configMapper.insertConfig(config);
        return CompletableFuture.completedFuture(result);
    }
}
