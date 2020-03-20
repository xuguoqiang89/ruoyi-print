package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysConfig;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: Administrator
 * @Date: 2020\3\11 0011
 */
public interface AsyncService {
    /**
     * 执行异步任务
     * 可以根据需求，自己加参数拟定，此处为测试演示
     */
    void executeAsync();

    CompletableFuture<Integer> saveConfig(SysConfig config);
}
