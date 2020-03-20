package com.ruoyi.system.service;

import com.ruoyi.system.domain.TbPrintTemplateContent;
import java.util.List;

/**
 * 打印模板-套打内容Service接口
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public interface ITbPrintTemplateContentService 
{
    /**
     * 查询打印模板-套打内容
     * 
     * @param id 打印模板-套打内容ID
     * @return 打印模板-套打内容
     */
    public TbPrintTemplateContent selectTbPrintTemplateContentById(Long id);

    /**
     * 查询打印模板-套打内容列表
     * 
     * @param tbPrintTemplateContent 打印模板-套打内容
     * @return 打印模板-套打内容集合
     */
    public List<TbPrintTemplateContent> selectTbPrintTemplateContentList(TbPrintTemplateContent tbPrintTemplateContent);

    /**
     * 新增打印模板-套打内容
     * 
     * @param tbPrintTemplateContent 打印模板-套打内容
     * @return 结果
     */
    public int insertTbPrintTemplateContent(TbPrintTemplateContent tbPrintTemplateContent);

    /**
     * 修改打印模板-套打内容
     * 
     * @param tbPrintTemplateContent 打印模板-套打内容
     * @return 结果
     */
    public int updateTbPrintTemplateContent(TbPrintTemplateContent tbPrintTemplateContent);

    /**
     * 批量删除打印模板-套打内容
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbPrintTemplateContentByIds(String ids);

    /**
     * 删除打印模板-套打内容信息
     * 
     * @param id 打印模板-套打内容ID
     * @return 结果
     */
    public int deleteTbPrintTemplateContentById(Long id);

    /**
     * 根据tempId获取模板内容
     * @param tempId
     * @return
     */
    List<TbPrintTemplateContent> selectTbPrintTemplateContentListByTempId(Integer tempId);

    /**
     * 根据tempId删除模板内容
     * @param tempId
     * @return
     */
    public int deleteTbPrintTemplateContentByTempId(Integer tempId);
}
