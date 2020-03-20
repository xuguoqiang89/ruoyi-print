package com.ruoyi.framework.aspectj;


import com.ruoyi.common.utils.TimeUuidUtil;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.domain.CostTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 计算统计服务器响应时间
 * @author lws
 *
 */
@Aspect
@Component
public class CountResponseTimeAspect {

    public static final int CONDITION_TIME = 1000;
	ThreadLocal<Long> startTime = new ThreadLocal<>();
	String className = null;
	String methodName = null;

    // 配置织入点
	//第一个*代表任意返回值；controller..代表controller包下及子包下；第二个*代表所有类；*(..)代表任意参数的所有方法
    @Pointcut("execution(* com.ruoyi..controller..*.*(..))")
    public void webLog(){}
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        className = joinPoint.getTarget().getClass().getName();
        methodName = joinPoint.getSignature().getName()+"()";
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        long spendTime = System.currentTimeMillis() - startTime.get();
        if(spendTime>CONDITION_TIME){
            CostTime costTime = new CostTime();
            costTime.setId(TimeUuidUtil.get16UUID());
            costTime.setClassName(className);
            costTime.setMethodName(methodName);
            costTime.setSpendTime(spendTime);
            AsyncManager.me().execute(AsyncFactory.recordCostTime(costTime));
        }
    }
}
