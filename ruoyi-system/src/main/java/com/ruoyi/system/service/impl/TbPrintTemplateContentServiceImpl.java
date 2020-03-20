package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TbPrintTemplateContentMapper;
import com.ruoyi.system.domain.TbPrintTemplateContent;
import com.ruoyi.system.service.ITbPrintTemplateContentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 打印模板-套打内容Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
@Service
public class TbPrintTemplateContentServiceImpl implements ITbPrintTemplateContentService 
{
    @Autowired
    private TbPrintTemplateContentMapper tbPrintTemplateContentMapper;

    /**
     * 查询打印模板-套打内容
     * 
     * @param id 打印模板-套打内容ID
     * @return 打印模板-套打内容
     */
    @Override
    public TbPrintTemplateContent selectTbPrintTemplateContentById(Long id)
    {
        return tbPrintTemplateContentMapper.selectTbPrintTemplateContentById(id);
    }

    /**
     * 查询打印模板-套打内容列表
     * 
     * @param tbPrintTemplateContent 打印模板-套打内容
     * @return 打印模板-套打内容
     */
    @Override
    public List<TbPrintTemplateContent> selectTbPrintTemplateContentList(TbPrintTemplateContent tbPrintTemplateContent)
    {
        return tbPrintTemplateContentMapper.selectTbPrintTemplateContentList(tbPrintTemplateContent);
    }

    /**
     * 新增打印模板-套打内容
     * 
     * @param tbPrintTemplateContent 打印模板-套打内容
     * @return 结果
     */
    @Override
    public int insertTbPrintTemplateContent(TbPrintTemplateContent tbPrintTemplateContent)
    {
        return tbPrintTemplateContentMapper.insertTbPrintTemplateContent(tbPrintTemplateContent);
    }

    /**
     * 修改打印模板-套打内容
     * 
     * @param tbPrintTemplateContent 打印模板-套打内容
     * @return 结果
     */
    @Override
    public int updateTbPrintTemplateContent(TbPrintTemplateContent tbPrintTemplateContent)
    {
        return tbPrintTemplateContentMapper.updateTbPrintTemplateContent(tbPrintTemplateContent);
    }

    /**
     * 删除打印模板-套打内容对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbPrintTemplateContentByIds(String ids)
    {
        return tbPrintTemplateContentMapper.deleteTbPrintTemplateContentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除打印模板-套打内容信息
     * 
     * @param id 打印模板-套打内容ID
     * @return 结果
     */
    @Override
    public int deleteTbPrintTemplateContentById(Long id)
    {
        return tbPrintTemplateContentMapper.deleteTbPrintTemplateContentById(id);
    }

    @Override
    public List<TbPrintTemplateContent> selectTbPrintTemplateContentListByTempId(Integer tempId) {
        return tbPrintTemplateContentMapper.selectTbPrintTemplateContentListByTempId(tempId);
    }

    @Override
    public int deleteTbPrintTemplateContentByTempId(Integer tempId) {
        return tbPrintTemplateContentMapper.deleteTbPrintTemplateContentByTempId(tempId);
    }
}
