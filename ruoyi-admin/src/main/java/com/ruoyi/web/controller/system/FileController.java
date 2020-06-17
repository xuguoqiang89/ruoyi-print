package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/system/fileupload")
public class FileController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    private String prefix = "system/fileupload";

    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam("picUrl") MultipartFile file)
    {
        JSONObject result = new JSONObject();
        try
        {
            String filePath = FileUploadUtils.upload(Global.getUploadPath(), file);
            result.put("file_path", filePath);
            return success(result.toString());
        }
        catch (Exception e)
        {
            log.error("上传文件失败！", e);
            return error(e.getMessage());
        }
    }

    @Log(title = "删除文件-本地", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(@RequestParam("filepath") String filepath)
    {
        boolean b = FileUploadUtils.deleteFile(Global.getUploadPath(), filepath);
        if(!b){
            return toAjax(0);
        }
        return toAjax(1);
    }
}
