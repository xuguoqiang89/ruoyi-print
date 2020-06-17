package com.ruoyi.system.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.DateFormatUtil;
import com.ruoyi.common.utils.NumberFormatUtil;
import com.ruoyi.common.utils.PdfUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.ehcache.util.EhCacheUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TbReceiptsMapper;
import com.ruoyi.system.domain.TbReceipts;
import com.ruoyi.system.service.ITbReceiptsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 收据管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
@Service
public class TbReceiptsServiceImpl implements ITbReceiptsService 
{
    @Autowired
    private TbReceiptsMapper tbReceiptsMapper;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 查询收据管理
     * 
     * @param id 收据管理ID
     * @return 收据管理
     */
    @Override
    public TbReceipts selectTbReceiptsById(Long id)
    {
        return tbReceiptsMapper.selectTbReceiptsById(id);
    }

    /**
     * 查询收据管理列表
     * 
     * @param tbReceipts 收据管理
     * @return 收据管理
     */
    @Override
    public List<TbReceipts> selectTbReceiptsList(TbReceipts tbReceipts)
    {
        return tbReceiptsMapper.selectTbReceiptsList(tbReceipts);
    }

    /**
     * 新增收据管理
     * 
     * @param tbReceipts 收据管理
     * @return 结果
     */
    @Override
    public int insertTbReceipts(TbReceipts tbReceipts)
    {
        //若收据号为空，则自动生成
        if(StringUtils.isEmpty(tbReceipts.getReceiptNo())){
            String receiptDate = DateFormatUtil.formatDate(tbReceipts.getReceiptDate(), DateFormatUtil.FORMAT_DATE);
            Long userId = ShiroUtils.getUserId();
            String receiptNo = initReceiptNo(receiptDate, userId);
            tbReceipts.setReceiptNo(receiptNo);
        }
        return tbReceiptsMapper.insertTbReceipts(tbReceipts);
    }

    /**
     * 修改收据管理
     * 
     * @param tbReceipts 收据管理
     * @return 结果
     */
    @Override
    public int updateTbReceipts(TbReceipts tbReceipts)
    {
        return tbReceiptsMapper.updateTbReceipts(tbReceipts);
    }

    /**
     * 删除收据管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbReceiptsByIds(String ids)
    {
        return tbReceiptsMapper.deleteTbReceiptsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收据管理信息
     * 
     * @param id 收据管理ID
     * @return 结果
     */
    @Override
    public int deleteTbReceiptsById(Long id)
    {
        return tbReceiptsMapper.deleteTbReceiptsById(id);
    }

    @Override
    public String initPrintData(String tempContent, String receiptId) {
        TbReceipts receipts = tbReceiptsMapper.selectTbReceiptsById(Long.parseLong(receiptId));
        Map<String, Object> map = this.getPrintDataMap(receiptId);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            tempContent = tempContent.replace(entry.getKey(),entry.getValue()==null?"":entry.getValue().toString());
        }
        return tempContent;
    }

    @Override
    public Map<String, Object> getPrintDataMap(String receiptId) {
        Map<String, Object> map = new HashMap<>();
        TbReceipts receipts = tbReceiptsMapper.selectTbReceiptsById(Long.parseLong(receiptId));
        String operator = (String) EhCacheUtils.getUserInfo(receipts.getOperator());
        String receiptMode = (String) EhCacheUtils.getDictInfo("receipt_type", receipts.getReceiptMode());//收款方式
        String remarkType = (String) EhCacheUtils.getDictInfo("receipt_remark_type", receipts.getRemarkType());//备注事项
        map.put("${companyName}",receipts.getCompanyName());
        map.put("${receiptNo}", StringUtils.isEmpty(receipts.getReceiptNo())?"":receipts.getReceiptNo());
        map.put("${rmb}", NumberFormatUtil.format2(receipts.getReceiptRmb()));
        map.put("${rmbUpper}", receipts.getReceiptRmbUpp());
        map.put("${remarkType}", remarkType);
        map.put("${remark}", receipts.getRemark());
        map.put("${receiptMode}", receiptMode);
        map.put("${operator}", operator);
        map.put("${cashier}", StringUtils.isEmpty(receipts.getCashier())?"":receipts.getCashier());
        map.put("${receiptDate}", DateFormatUtil.format(receipts.getReceiptDate(), DateFormatUtil.FORMAT_DATE_STR));
        return map;
    }

    /**
     * 返回生成的附件
     * @param receiptId
     * @param type
     * @param pdfTemplatePath
     * @return /profile/upload/2020/
     */
    @Override
    public String initPdfPrintData(String receiptId, String type, String pdfTemplatePath) {
        Map<String, Object> dataMap = this.getPrintDataMap(receiptId);
        Map<String, Object> map = new HashMap<>();
        map.put("datamap", dataMap);

        String filePath = Global.getUploadPath();

        pdfTemplatePath = pdfTemplatePath.replace("/profile/upload", filePath);

        File tempFile = new File(pdfTemplatePath);

        String outName = type+"-"+receiptId+"-"+tempFile.getName();//03-11-*******.pdf
        String outPath = tempFile.getParent()+"/"+outName;//C:/ruoyi/uploadPath/upload/2020/06/17/******.pdf

        PdfUtils.pdfout(pdfTemplatePath, outPath, map, "C:\\Windows\\Fonts\\msyh.ttf");

        File outFile = new File(outPath);
        String out_path = outFile.getParent();
        String url = out_path.substring(out_path.indexOf("upload\\")+6, out_path.length());
        url = "/profile/upload"+url+"/"+outName;
        url = url.replaceAll("\\\\","/");
        return url;
    }

    /**
     * 自动生成收据号：年（4位）月（2位）用户ID(2位)+流水号（4位）
     * @param receiptDate
     * @param userId
     * @return
     */
    private String initReceiptNo(String receiptDate, Long userId){
        String receiptNo = receiptDate.substring(0,4)+receiptDate.substring(5,7)+String.format("%02d", userId);
        List<TbReceipts> list = tbReceiptsMapper.selectTbReceiptByReceiptNo(receiptNo);
        if(list.size()==0){
            receiptNo += String.format("%04d", 1);
        }else {
            String no = list.get(0).getReceiptNo().substring(8, 12);
            receiptNo += String.format("%04d", Integer.parseInt(no)+1);
        }
        return receiptNo;
    }
}
