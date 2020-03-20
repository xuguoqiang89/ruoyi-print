package com.ruoyi.system.service;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.system.domain.TbPrintTemplate;
import java.util.List;

/**
 * 打印模板Service接口
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public interface ITbPrintTemplateService 
{
    /**
     * 查询打印模板
     * 
     * @param id 打印模板ID
     * @return 打印模板
     */
    public TbPrintTemplate selectTbPrintTemplateById(Integer id);

    /**
     * 查询打印模板列表
     * 
     * @param tbPrintTemplate 打印模板
     * @return 打印模板集合
     */
    public List<TbPrintTemplate> selectTbPrintTemplateList(TbPrintTemplate tbPrintTemplate);

    /**
     * 新增打印模板
     * 
     * @param tbPrintTemplate 打印模板
     * @return 结果
     */
    public int insertTbPrintTemplate(TbPrintTemplate tbPrintTemplate);

    public int insertTbPrintTemplate(JSONArray param);

    /**
     * 修改打印模板
     * 
     * @param tbPrintTemplate 打印模板
     * @return 结果
     */
    public int updateTbPrintTemplate(TbPrintTemplate tbPrintTemplate);

    /**
     * 批量删除打印模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbPrintTemplateByIds(String ids);

    /**
     * 删除打印模板信息
     * 
     * @param id 打印模板ID
     * @return 结果
     */
    public int deleteTbPrintTemplateById(Integer id);

    List<TbPrintTemplate> selectTbPrintTemplateListByType(String orderType, String projectId);
}
