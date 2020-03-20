package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TbReceipts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收据管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
public interface TbReceiptsMapper 
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
     * 删除收据管理
     * 
     * @param id 收据管理ID
     * @return 结果
     */
    public int deleteTbReceiptsById(Long id);

    /**
     * 批量删除收据管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbReceiptsByIds(String[] ids);

    public List<TbReceipts> selectTbReceiptByReceiptNo(@Param("receiptNo") String receiptNo);
}
