package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.CostTime;

import java.util.List;

/**
 * 系统响应时间统计记录（方便对响应时间长的进行优化） 数据层
 *
 * @author lws
 * @date 2018-10-25
 */
public interface CostTimeMapper {
    /**
     * 查询系统响应时间统计记录（方便对响应时间长的进行优化）信息
     *
     * @param id 系统响应时间统计记录（方便对响应时间长的进行优化）ID
     * @return 系统响应时间统计记录（方便对响应时间长的进行优化）信息
     */
    public CostTime selectCostTimeById(Integer id);

    /**
     * 查询系统响应时间统计记录（方便对响应时间长的进行优化）列表
     *
     * @param costTime 系统响应时间统计记录（方便对响应时间长的进行优化）信息
     * @return 系统响应时间统计记录（方便对响应时间长的进行优化）集合
     */
    public List<CostTime> selectCostTimeList(CostTime costTime);

    /**
     * 新增系统响应时间统计记录（方便对响应时间长的进行优化）
     *
     * @param costTime 系统响应时间统计记录（方便对响应时间长的进行优化）信息
     * @return 结果
     */
    public int insertCostTime(CostTime costTime);

    /**
     * 删除系统响应时间统计记录（方便对响应时间长的进行优化）
     *
     * @param id 系统响应时间统计记录（方便对响应时间长的进行优化）ID
     * @return 结果
     */
    public int deleteCostTimeById(Integer id);

    /**
     * 批量删除系统响应时间统计记录（方便对响应时间长的进行优化）
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCostTimeByIds(String[] ids);

}