package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TbPrintTemplateContent;
import com.ruoyi.system.mapper.TbPrintTemplateContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TbPrintTemplateMapper;
import com.ruoyi.system.domain.TbPrintTemplate;
import com.ruoyi.system.service.ITbPrintTemplateService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 打印模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
@Service
public class TbPrintTemplateServiceImpl implements ITbPrintTemplateService 
{
    @Autowired
    private TbPrintTemplateMapper tbPrintTemplateMapper;

    @Autowired
    private TbPrintTemplateContentMapper contentMapper;

    /**
     * 查询打印模板
     * 
     * @param id 打印模板ID
     * @return 打印模板
     */
    @Override
    public TbPrintTemplate selectTbPrintTemplateById(Integer id)
    {
        return tbPrintTemplateMapper.selectTbPrintTemplateById(id);
    }

    /**
     * 查询打印模板列表
     * 
     * @param tbPrintTemplate 打印模板
     * @return 打印模板
     */
    @Override
    public List<TbPrintTemplate> selectTbPrintTemplateList(TbPrintTemplate tbPrintTemplate)
    {
        return tbPrintTemplateMapper.selectTbPrintTemplateList(tbPrintTemplate);
    }

    /**
     * 新增打印模板
     * 
     * @param tbPrintTemplate 打印模板
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTbPrintTemplate(TbPrintTemplate tbPrintTemplate)
    {
        tbPrintTemplate.setCreateTime(DateUtils.getNowDate());
        List<TbPrintTemplateContent> contentList = tbPrintTemplate.getContentList();
        if(contentList!=null && contentList.size()>0){
            //插入内容
            for(TbPrintTemplateContent content:contentList){
                contentMapper.insertTbPrintTemplateContent(content);
            }
        }
        return tbPrintTemplateMapper.insertTbPrintTemplate(tbPrintTemplate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTbPrintTemplate(JSONArray params) {
        JSONObject paramObj = JSONArray.parseObject(params.getString(0));
        JSONArray contentArray = params.getJSONArray(1);
        Integer tempId = 0;
        int rows = 0;
        if(StringUtils.isEmpty(paramObj.getString("id")) || "0".equals(paramObj.getString("id"))){
            TbPrintTemplate tbPrintTemplate = new TbPrintTemplate();
            tbPrintTemplate.setCreateTime(new Date());
            tbPrintTemplate.setUpdateTime(new Date());
            tbPrintTemplate.setPrintMode(paramObj.getString("printMode"));
            tbPrintTemplate.setType(paramObj.getString("printType"));
            tbPrintTemplate.setState(paramObj.getString("state"));
            tbPrintTemplate.setProjectId(StringUtils.isNull(paramObj.getString("projectId"))?0:paramObj.getLong("projectId"));
            tbPrintTemplate.setRemark(paramObj.getString("remark"));
            if("01".equals(paramObj.getString("printMode")) || "03".equals(paramObj.getString("printMode"))){
                //空白纸打印
                tbPrintTemplate.setContent(paramObj.getString("content"));
            }else {
                tbPrintTemplate.setContent("");
            }
            rows = tbPrintTemplateMapper.insertTbPrintTemplate(tbPrintTemplate);

            tempId = tbPrintTemplate.getId();
        }else {
            TbPrintTemplate tbPrintTemplate = tbPrintTemplateMapper.selectTbPrintTemplateById(paramObj.getInteger("id"));
            tbPrintTemplate.setUpdateTime(new Date());
//            tbPrintTemplate.setContent(paramObj.getString("content"));
            tbPrintTemplate.setPrintMode(paramObj.getString("printMode"));
            tbPrintTemplate.setType(paramObj.getString("printType"));
            tbPrintTemplate.setState(paramObj.getString("state"));
            tbPrintTemplate.setRemark(paramObj.getString("remark"));

            if("01".equals(paramObj.getString("printMode")) || "03".equals(paramObj.getString("printMode"))){
                //空白纸打印
                tbPrintTemplate.setContent(paramObj.getString("content"));
            }else {
                tbPrintTemplate.setContent("");
            }
            rows = tbPrintTemplateMapper.updateTbPrintTemplate(tbPrintTemplate);

            //子表 先删除在新增
            contentMapper.deleteTbPrintTemplateContentByTempId(tbPrintTemplate.getId());

            tempId = tbPrintTemplate.getId();
        }
        //子表
        for(int i=0;i<contentArray.size();i++){
            String content = contentArray.getString(i);
            TbPrintTemplateContent contentEntity = new TbPrintTemplateContent();
            contentEntity.setTempId(tempId);
            contentEntity.setContent(content);
            rows = contentMapper.insertTbPrintTemplateContent(contentEntity);
        }

        return rows;
    }

    /**
     * 修改打印模板
     * 
     * @param tbPrintTemplate 打印模板
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTbPrintTemplate(TbPrintTemplate tbPrintTemplate)
    {
        return 0;
    }

    /**
     * 删除打印模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteTbPrintTemplateByIds(String ids)
    {
        //删除子表
        contentMapper.deleteTbPrintTemplateContentByTempIds(Convert.toStrArray(ids));
        //删除主表
        return tbPrintTemplateMapper.deleteTbPrintTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除打印模板信息
     * 
     * @param id 打印模板ID
     * @return 结果
     */
    @Override
    public int deleteTbPrintTemplateById(Integer id)
    {
        return tbPrintTemplateMapper.deleteTbPrintTemplateById(id);
    }

    /**
     * 根据模板类型获取模板列表
     * @param orderType
     * @return
     */
    @Override
    public List<TbPrintTemplate> selectTbPrintTemplateListByType(String orderType, String projectId) {
        return tbPrintTemplateMapper.selectTbPrintTemplateListByType(orderType, projectId);
    }
}
