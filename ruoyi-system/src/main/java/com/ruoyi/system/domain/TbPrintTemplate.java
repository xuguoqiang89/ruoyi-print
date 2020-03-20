package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 打印模板对象 tb_print_template
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public class TbPrintTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 模板类别：10认筹、01预订、03合同，02认购单、04收款信息、05换房、06付款方式变更、07退单 08客户更名 09折扣报批 11收据 */
    @Excel(name = "模板类别：10认筹、01预订、03合同，02认购单、04收款信息、05换房、06付款方式变更、07退单 08客户更名 09折扣报批 11收据")
    private String type;

    /** $column.columnComment */
    private Long projectId;

    /** $column.columnComment */
    private String content;

    /** $column.columnComment */
    private String operator;

    /** 状态：0启用 1停用 */
    @Excel(name = "状态：0启用 1停用")
    private String state;

    /** 打印方式 01空白纸打印  02套打 */
    @Excel(name = "打印方式 01空白纸打印  02套打")
    private String printMode;

    private List<TbPrintTemplateContent> contentList;//空白纸打印模板列表
    private List<String> contentStrList;//空白纸打印模板列表

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setPrintMode(String printMode) 
    {
        this.printMode = printMode;
    }

    public String getPrintMode() 
    {
        return printMode;
    }

    public List<TbPrintTemplateContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<TbPrintTemplateContent> contentList) {
        this.contentList = contentList;
    }

    public List<String> getContentStrList() {
        return contentStrList;
    }

    public void setContentStrList(List<String> contentStrList) {
        this.contentStrList = contentStrList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("projectId", getProjectId())
            .append("createTime", getCreateTime())
            .append("content", getContent())
            .append("operator", getOperator())
            .append("state", getState())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("printMode", getPrintMode())
            .append("contentList", getContentList())
            .append("contentStrList", getContentStrList())
            .toString();
    }
}
