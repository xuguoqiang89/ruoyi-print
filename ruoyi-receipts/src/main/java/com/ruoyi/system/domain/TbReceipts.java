package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 收据管理对象 tb_receipts
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
public class TbReceipts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 交款单位(或姓名) */
    @Excel(name = "交款单位(或姓名)")
    private String companyName;

    /** 收据日期 */
    @Excel(name = "收据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 收据号 */
    @Excel(name = "收据号")
    private String receiptNo;

    /** 收款方式 */
    @Excel(name = "收款方式")
    private String receiptMode;

    /** 人民币 */
    @Excel(name = "人民币")
    private Double receiptRmb;

    /** 人民币大写 */
    @Excel(name = "人民币大写")
    private String receiptRmbUpp;

    /** 备注事项 */
    @Excel(name = "备注事项")
    private String remarkType;

    /** 出纳 */
    @Excel(name = "出纳")
    private String cashier;

    /** 经办人 */
    @Excel(name = "经办人")
    private String operator;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setReceiptDate(Date receiptDate) 
    {
        this.receiptDate = receiptDate;
    }

    public Date getReceiptDate() 
    {
        return receiptDate;
    }
    public void setReceiptNo(String receiptNo) 
    {
        this.receiptNo = receiptNo;
    }

    public String getReceiptNo() 
    {
        return receiptNo;
    }
    public void setReceiptMode(String receiptMode) 
    {
        this.receiptMode = receiptMode;
    }

    public String getReceiptMode() 
    {
        return receiptMode;
    }
    public void setReceiptRmb(Double receiptRmb) 
    {
        this.receiptRmb = receiptRmb;
    }

    public Double getReceiptRmb() 
    {
        return receiptRmb;
    }
    public void setReceiptRmbUpp(String receiptRmbUpp) 
    {
        this.receiptRmbUpp = receiptRmbUpp;
    }

    public String getReceiptRmbUpp() 
    {
        return receiptRmbUpp;
    }
    public void setRemarkType(String remarkType) 
    {
        this.remarkType = remarkType;
    }

    public String getRemarkType() 
    {
        return remarkType;
    }
    public void setCashier(String cashier) 
    {
        this.cashier = cashier;
    }

    public String getCashier() 
    {
        return cashier;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyName", getCompanyName())
            .append("receiptDate", getReceiptDate())
            .append("receiptNo", getReceiptNo())
            .append("receiptMode", getReceiptMode())
            .append("receiptRmb", getReceiptRmb())
            .append("receiptRmbUpp", getReceiptRmbUpp())
            .append("remarkType", getRemarkType())
            .append("remark", getRemark())
            .append("cashier", getCashier())
            .append("operator", getOperator())
            .toString();
    }
}
