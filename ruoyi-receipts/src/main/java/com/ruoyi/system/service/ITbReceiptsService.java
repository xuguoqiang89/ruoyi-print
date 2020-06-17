package com.ruoyi.system.service;

import com.ruoyi.system.domain.TbReceipts;
import java.util.List;
import java.util.Map;

/**
 * 收据管理Service接口
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
public interface ITbReceiptsService 
{
    /**
     * 查询收据管理
     * 
     * @param id 收据管理ID
     * @return 收据管理
     */
    public TbReceipts selectTbReceiptsById(Long id);

    /**
     * 查询收据管理列表
     * 
     * @param tbReceipts 收据管理
     * @return 收据管理集合
     */
    public List<TbReceipts> selectTbReceiptsList(TbReceipts tbReceipts);

    /**
     * 新增收据管理
     * 
     * @param tbReceipts 收据管理
     * @return 结果
     */
    public int insertTbReceipts(TbReceipts tbReceipts);

    /**
     * 修改收据管理
     * 
     * @param tbReceipts 收据管理
     * @return 结果
     */
    public int updateTbReceipts(TbReceipts tbReceipts);

    /**
     * 批量删除收据管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbReceiptsByIds(String ids);

    /**
     * 删除收据管理信息
     * 
     * @param id 收据管理ID
     * @return 结果
     */
    public int deleteTbReceiptsById(Long id);

    public String initPrintData(String tempContent, String receiptId);

    public Map<String, Object> getPrintDataMap(String receiptId);

    String initPdfPrintData(String receiptId, String type, String pdfTemplatePath);
}
