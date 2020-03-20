package com.ruoyi.quartz.task;

import com.ruoyi.common.config.Global;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.quartz.config.DbParamConfig;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.quartz.util.BatCommandUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    private static final Logger log = LoggerFactory.getLogger(RyTask.class);
    @Autowired
    DbParamConfig dbParamConfig;
    @Autowired
    ISysJobService jobService;
    private static String mysqlBasedir = null;

    private void initMysqlDir(){
        if(mysqlBasedir==null){
            mysqlBasedir = jobService.getMysqlBasedir();
        }
    }

    public void recoverDBFile(String fileId) {
        log.info("------------------执行定时任务------------ryTask.recoverDBFile，参数:"+fileId);
        if(fileId.indexOf(".sql")==-1){
            fileId = fileId+".sql";
        }
        try {
            File sqlFile = new File(Global.getDbBackupPath()+"\\"+fileId);
            if(!sqlFile.exists()){
                log.error("数据库恢复时录入的文件id值不存在："+fileId);
                return;
            }
            initMysqlDir();
            importDBFile(sqlFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("执行数数据库恢复时发生异常："+e.getMessage());
        }
    }
    public void backupDB() {
        log.info("------------------执行定时任务------------ryTask.backupDB");
        try {
            initMysqlDir();
            //备份数据库
            backup(Global.getDbBackupPath());

            //默认清理操作30天的数据库备份文件
            final Integer days=30;
            final String folder= Global.getDbBackupPath();
            AsyncManager.me().execute(AsyncFactory.cleanOutDateBackupFile(days,folder));

        } catch (Exception e) {
            log.error("执行数据库备份时发生异常："+e.getMessage());
        }
    }
    //===============================================内部方法=========================================
    private synchronized void importDBFile(String filePath) throws IOException {
        StringBuilder cmdStr = new StringBuilder();
        cmdStr.append(" -u").append(dbParamConfig.getUsername())
                .append(" -p").append(dbParamConfig.getPassword())
                .append(" -h").append(dbParamConfig.getHost())
                .append(" -P").append(dbParamConfig.getPort())
                .append(" ").append(dbParamConfig.getDatabaseName())
                .append("<").append(filePath);
        if (isWindows()) {
            BatCommandUtil.writeAndExcute(Global.getDbBackupPath()+"recoverMysqlDb.bat",new StringBuilder()
                    .append("cd /d ").append(mysqlBasedir).append("bin").append("\r\n")
                    .append("mysql").append(cmdStr.toString()).toString());
        } else {
            throw new RuntimeException("暂时只支持windows系统！");
        }
    }
    /**
     * 备份数据库操作
     *
     * @param fileBasePath 文保存件路径
     * @return
     */
    private void backup(String fileBasePath) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        String name = dbParamConfig.getDatabaseName()+"_"+sdf.format(new Date())+".sql";

        File sqlFile = BatCommandUtil.createFile(fileBasePath+File.separator+ name);
        StringBuilder cmdStr = new StringBuilder();
        // 构建数据库备份参数
        cmdStr.append(" -u").append(dbParamConfig.getUsername())
                //注意：如果数据库密码为空，导不出来
                .append(" -p").append(dbParamConfig.getPassword())
                .append(" -h").append(dbParamConfig.getHost())
                .append(" -P").append(dbParamConfig.getPort())
                .append(" ").append(dbParamConfig.getDatabaseName())
                .append(" ").append("--hex-blob")//使用十六进制符号转储二进制字符序列，防止乱码
                .append(" > ").append(sqlFile.getPath());
        if (isWindows()) {
            BatCommandUtil.writeAndExcute(fileBasePath+File.separator+"backupmysql.bat",new StringBuilder()
                    .append("cd /d ").append(mysqlBasedir).append("bin").append("\r\n")
                    .append("mysqldump").append(cmdStr.toString()).toString());
        } else {
            throw new RuntimeException("暂时只支持windows系统！");
        }
    }


    private static boolean isWindows() {
        return System.getProperty("os.name").indexOf("Windows") != -1;
    }

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }


}
