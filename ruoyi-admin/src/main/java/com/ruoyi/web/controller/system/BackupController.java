package com.ruoyi.web.controller.system;


import com.google.common.collect.Lists;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.ReadDirector;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.quartz.task.RyTask;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库备份与恢复操作
 *
 * @author wujiyue
 * @date 2019-11-04
 */
@Controller
@RequestMapping("/system/backup")
public class BackupController extends BaseController {
    private String prefix = "system/backup";
    @Autowired
    private ISysJobService jobService;

    @Autowired
    private RyTask ryTask;

    @RequiresPermissions("system:backup")
    @GetMapping()
    public String backup(ModelMap modelMap) {
        return prefix + "/backup";
    }
    @PostMapping("/files")
    @ResponseBody
    public TableDataInfo files() throws Exception
    {
        String folder= Global.getDbBackupPath();
        List<String> fs= ReadDirector.getFiles(folder);

        List<Map<String,Object>> files= Lists.newArrayList();
        Map<String,Object> temp=null;
        int i=0;
        String name="";
        String hourSeconds="";
        if(fs!=null){
            for(String s:fs){
                if(s.endsWith(".sql")){
                    i++;
                    temp = new HashMap<>();
                    temp.put("rowNum", i);
                    name = s;
                    temp.put("name", name);
                    name = name.replace("ry_plus_", "");
                    hourSeconds = name.substring(11, 19);
                    hourSeconds = hourSeconds.replace("_", ":");
                    name = name.substring(0, 10);

                    name = name.replace("_", "-");
                    name = name + " " + hourSeconds;
                    temp.put("time", name);
                    files.add(temp);
                }
            }
        }
        return getDataTable(files);
    }

    @RequiresPermissions("system:backup:backup")
    @Log(title = "数据库备份", businessType = BusinessType.DB_BACKUP)
    @PostMapping("/backup")
    @ResponseBody
    public AjaxResult backup() throws SchedulerException {
        SysJob job=new SysJob();
        //查看sys_job表的数据库备份任务的id
        job.setJobId(4L);
        job.setJobGroup("SYSTEM");
        jobService.run(job);
        return success();
    }

    /**
     * 数据库恢复
     */
    @RequiresPermissions("system:backup:recovery")
    @PostMapping("/recovery")
    @Log(title = "还原数据库", businessType = BusinessType.DB_RECOVERY)
    @ResponseBody
    public AjaxResult recovery(String name) throws SchedulerException {
        //在考虑这个功能有没有必要实现
        try{
            ryTask.recoverDBFile(name);
            return success("数据库还原成功");
        }catch (Exception e){
            return error("该功能尚在研发中!");
        }
    }

    /**
     * 删除备份文件
     */
    @RequiresPermissions("system:backup:delete")
    @Log(title = "删除备份文件", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String name) {
        String dir= Global.getDbBackupPath();
        String filePath=dir+File.separator+name;
        File file=new File(filePath);
        if(file.exists()){
            file.delete();
        }
        return success();
    }
}
