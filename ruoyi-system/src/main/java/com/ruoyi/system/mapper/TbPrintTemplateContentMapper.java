package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TbPrintTemplateContent;
import java.util.List;

/**
 * 打印模板-套打内容Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public interface TbPrintTemplateContentMapper 
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
     * 删除打印模板-套打内容
     * 
     * @param id 打印模板-套打内容ID
     * @return 结果
     */
    public int deleteTbPrintTemplateContentById(Long id);

    /**
     * 批量删除打印模板-套打内容
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbPrintTemplateContentByIds(String[] ids);

    public int deleteTbPrintTemplateContentByTempIds(String[] ids);

    public List<TbPrintTemplateContent> selectTbPrintTemplateContentListByTempId(Integer tempId);

    public int deleteTbPrintTemplateContentByTempId(Integer tempId);
}
