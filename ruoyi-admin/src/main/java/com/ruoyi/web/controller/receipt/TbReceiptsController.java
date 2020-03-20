package com.ruoyi.web.controller.receipt;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.TbReceipts;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ITbReceiptsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收据管理Controller
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/system/receipts")
public class TbReceiptsController extends BaseController
{
    private String prefix = "system/receipts";

    @Autowired
    private ITbReceiptsService tbReceiptsService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:receipts:view")
    @GetMapping()
    public String receipts()
    {
        return prefix + "/receipts";
    }

    /**
     * 查询收据管理列表
     */
    @RequiresPermissions("system:receipts:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbReceipts tbReceipts)
    {
        startPage();
        List<TbReceipts> list = tbReceiptsService.selectTbReceiptsList(tbReceipts);
        return getDataTable(list);
    }

    /**
     * 导出收据管理列表
     */
    @RequiresPermissions("system:receipts:export")
    @Log(title = "收据管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbReceipts tbReceipts)
    {
        List<TbReceipts> list = tbReceiptsService.selectTbReceiptsList(tbReceipts);
        ExcelUtil<TbReceipts> util = new ExcelUtil<TbReceipts>(TbReceipts.class);
        return util.exportExcel(list, "receipts");
    }

    /**
     * 新增收据管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser loginUser = ShiroUtils.getSysUser();
        mmap.put("loginUser", loginUser);
        return prefix + "/add";
    }

    /**
     * 新增保存收据管理
     */
    @RequiresPermissions("system:receipts:add")
    @Log(title = "收据管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbReceipts tbReceipts)
    {
        return toAjax(tbReceiptsService.insertTbReceipts(tbReceipts));
    }

    /**
     * 修改收据管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbReceipts tbReceipts = tbReceiptsService.selectTbReceiptsById(id);
        SysUser operateUser = userService.selectUserById(Long.parseLong(tbReceipts.getOperator()));
        mmap.put("operateUser", operateUser);
        mmap.put("tbReceipts", tbReceipts);
        return prefix + "/edit";
    }

    /**
     * 修改保存收据管理
     */
    @RequiresPermissions("system:receipts:edit")
    @Log(title = "收据管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbReceipts tbReceipts)
    {
        return toAjax(tbReceiptsService.updateTbReceipts(tbReceipts));
    }

    /**
     * 删除收据管理
     */
    @RequiresPermissions("system:receipts:remove")
    @Log(title = "收据管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbReceiptsService.deleteTbReceiptsByIds(ids));
    }

}
