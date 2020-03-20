package com.ruoyi.system.domain;


import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统响应时间统计记录（方便对响应时间长的进行优化）表 sys_cost_time
 *
 * @author lws
 * @date 2018-10-25
 */
public class CostTime extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 消耗的时间
     */
    private Long spendTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("className", getClassName())
                .append("methodName", getMethodName())
                .append("spendTime", getSpendTime())
                .toString();
    }
}
