package com.ruoyi.web.controller.system;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.TbPrintTemplateContent;
import com.ruoyi.system.domain.TbReceipts;
import com.ruoyi.system.service.ITbPrintTemplateContentService;
import com.ruoyi.system.service.ITbReceiptsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TbPrintTemplate;
import com.ruoyi.system.service.ITbPrintTemplateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印模板Controller
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/system/print")
public class TbPrintTemplateController extends BaseController
{
    private String prefix = "system/print";

    @Autowired
    private ITbPrintTemplateService tbPrintTemplateService;

    @Autowired
    private ITbPrintTemplateContentService contentService;

    @Autowired
    private ITbReceiptsService receiptsService;

    @RequiresPermissions("system:print:view")
    @GetMapping()
    public String template()
    {
        return prefix + "/template";
    }

    /**
     * 查询打印模板列表
     */
    @RequiresPermissions("system:print:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbPrintTemplate tbPrintTemplate)
    {
        startPage();
        List<TbPrintTemplate> list = tbPrintTemplateService.selectTbPrintTemplateList(tbPrintTemplate);
        return getDataTable(list);
    }

    /**
     * 导出打印模板列表
     */
    @RequiresPermissions("system:print:export")
    @Log(title = "打印模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbPrintTemplate tbPrintTemplate)
    {
        List<TbPrintTemplate> list = tbPrintTemplateService.selectTbPrintTemplateList(tbPrintTemplate);
        ExcelUtil<TbPrintTemplate> util = new ExcelUtil<TbPrintTemplate>(TbPrintTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 新增打印模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存打印模板
     */
    @RequiresPermissions("system:print:add")
    @Log(title = "打印模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody JSONArray params)
    {
        return toAjax(tbPrintTemplateService.insertTbPrintTemplate(params));
    }

    /**
     * 修改打印模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbPrintTemplate tbPrintTemplate = tbPrintTemplateService.selectTbPrintTemplateById(id);
        List<TbPrintTemplateContent> contentList = contentService.selectTbPrintTemplateContentListByTempId(id);
        tbPrintTemplate.setContentList(contentList);
        mmap.put("tbPrintTemplate", tbPrintTemplate);
        return prefix + "/edit";
    }

    @PostMapping("/view/{id}")
    @ResponseBody
    public AjaxResult view(@PathVariable("id") Integer id, ModelMap mmap)
    {
        TbPrintTemplate tbPrintTemplate = tbPrintTemplateService.selectTbPrintTemplateById(id);
        return new AjaxResult(AjaxResult.Type.SUCCESS,"",tbPrintTemplate);
    }

    /**
     * 修改保存打印模板
     */
    @RequiresPermissions("system:print:edit")
    @Log(title = "打印模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbPrintTemplate tbPrintTemplate)
    {
        return toAjax(tbPrintTemplateService.updateTbPrintTemplate(tbPrintTemplate));
    }

    /**
     * 删除打印模板
     */
    @RequiresPermissions("system:print:remove")
    @Log(title = "打印模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbPrintTemplateService.deleteTbPrintTemplateByIds(ids));
    }

    /**
     * 打印
     * @param id:业务单据ID
     * @param orderType 单据类别：对应模板类别：预订、认购等
     */
    @GetMapping("/print/{id}/{tempId}/{orderType}")
    @Log(title="执行打印", businessType = BusinessType.PRINT)
    public String print(HttpServletRequest request, @PathVariable("id") Long id, @PathVariable("tempId") String tempId, @PathVariable("orderType") String orderType, ModelMap mmap)
    {
        String projectId = "";
        mmap.put("projectId", projectId);
        mmap.put("orderType", orderType);
        mmap.put("id", id);
        String req_url = request.getRequestURL().toString();
        String req_uri = request.getRequestURI();
        req_url = req_url.substring(0, req_url.indexOf(req_uri));
        if(StringUtils.isEmpty(tempId) || "0".equals(tempId)){
            List<TbPrintTemplate> list = tbPrintTemplateService.selectTbPrintTemplateListByType(orderType, projectId);
            if(list.size()==0){
                //无模板
                mmap.put("msg", "未设置打印模板");
                return "/error/500.html";
            }else if(list.size()>1){
                //若包含多个有效的模板，则返回列表进行选择
                return prefix + "/printTempSel";
            }else if(list.size()==1){
                //直接到打印预览界面
                TbPrintTemplate tbPrintTemplate = list.get(0);
                StringBuffer sf = new StringBuffer();
                if("02".equals(tbPrintTemplate.getPrintMode())){//套打
                    List<TbPrintTemplateContent> contentList = contentService.selectTbPrintTemplateContentListByTempId(tbPrintTemplate.getId());
                    for(int i=0;i<contentList.size();i++){
                        TbPrintTemplateContent content = contentList.get(i);
                        String content_str = content.getContent();
                        if(i>0){
                            content_str = content_str.substring(content_str.indexOf("LODOP.ADD_PRINT"), content_str.length());
                        }
                        if(i==contentList.size()-1){
                            sf.append(content_str);
                        }else{
                            sf.append(content_str).append("LODOP.NewPage();");
                        }
                    }
                    tbPrintTemplate.setContent(receiptsService.initPrintData(sf.toString(), id.toString()));
                }else if("03".equals(tbPrintTemplate.getPrintMode())){
                    //PDF套打：生成带数据的文件，并将路径返回
                    String pdfUrl = receiptsService.initPdfPrintData(id.toString(), tbPrintTemplate.getType(), tbPrintTemplate.getContent());
                    tbPrintTemplate.setContent(req_url+pdfUrl);
                }else{
                    //空白纸打印
                    sf.append(tbPrintTemplate.getContent());
                    tbPrintTemplate.setContent(receiptsService.initPrintData(sf.toString(), id.toString()));
                }
                mmap.put("tbPrintTemplate", tbPrintTemplate);
                return prefix + "/printview";
            }
        }else {
            TbPrintTemplate tbPrintTemplate = tbPrintTemplateService.selectTbPrintTemplateById(Integer.parseInt(tempId));
            StringBuffer sf = new StringBuffer();
            if("02".equals(tbPrintTemplate.getPrintMode())){//套打
                List<TbPrintTemplateContent> contentList = contentService.selectTbPrintTemplateContentListByTempId(tbPrintTemplate.getId());
                for(int i=0;i<contentList.size();i++){
                    TbPrintTemplateContent content = contentList.get(i);
                    String content_str = content.getContent();
                    if(i>0){
                        content_str = content_str.substring(content_str.indexOf("LODOP.ADD_PRINT"), content_str.length());
                    }
                    if(i==contentList.size()-1){
                        sf.append(content_str);
                    }else{
                        sf.append(content_str).append("LODOP.NewPage();");
                    }
                }
                tbPrintTemplate.setContent(receiptsService.initPrintData(sf.toString(), id.toString()));
            }else if("03".equals(tbPrintTemplate.getPrintMode())){
                //PDF套打：生成带数据的文件，并将路径返回
                String pdfUrl = receiptsService.initPdfPrintData(id.toString(), tbPrintTemplate.getType(), tbPrintTemplate.getContent());
                tbPrintTemplate.setContent(req_url+pdfUrl);
            }else{
                //空白纸打印
                sf.append(tbPrintTemplate.getContent());
                tbPrintTemplate.setContent(receiptsService.initPrintData(sf.toString(), id.toString()));
            }
            mmap.put("tbPrintTemplate", tbPrintTemplate);
            return prefix + "/printview";
        }

        return "/error/500.html";
    }


    /**
     * 查询打印模板列表
     */
    @RequiresPermissions("system:print:list")
    @PostMapping("/listByType")
    @ResponseBody
    public TableDataInfo listByType(TbPrintTemplate tbPrintTemplate)
    {
        startPage();
        String projectId = tbPrintTemplate.getProjectId()==null?"":tbPrintTemplate.getProjectId().toString();
        List<TbPrintTemplate> list = tbPrintTemplateService.selectTbPrintTemplateListByType(tbPrintTemplate.getType(), projectId);
        return getDataTable(list);
    }
}
