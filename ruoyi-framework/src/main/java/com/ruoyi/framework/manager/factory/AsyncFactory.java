package com.ruoyi.framework.manager.factory;

import java.io.File;
import java.util.Date;
import java.util.TimerTask;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.CostTime;
import com.ruoyi.system.service.ICostTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.AddressUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.shiro.session.OnlineSession;
import com.ruoyi.framework.util.LogUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.domain.SysUserOnline;
import com.ruoyi.system.service.ISysOperLogService;
import com.ruoyi.system.service.ISysUserOnlineService;
import com.ruoyi.system.service.impl.SysLogininforServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 异步工厂（产生任务用）
 * 
 * @author liuhulu
 *
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 同步session到数据库
     * 
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                SysUserOnline online = new SysUserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                SpringUtils.getBean(ISysUserOnlineService.class).saveOnline(online);

            }
        };
    }

    /**
     * 操作日志记录
     * 
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

    /**
     * 记录登陆信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setLoginName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status))
                {
                    logininfor.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLogininforServiceImpl.class).insertLogininfor(logininfor);
            }
        };
    }

    /**
     * 记录响应时间
     *
     * @param costTime 消耗时间
     * @return 任务task
     */
    public static TimerTask recordCostTime(final CostTime costTime) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                SpringUtils.getBean(ICostTimeService.class).insertCostTime(costTime);
            }
        };
    }

    /**
     * 清理过期的数据库备份文件
     * @param days  如果传入30，表示清理30天前的文件
     * @param folder 存放备份文件的文件夹路径
     * @return
     */
    public static TimerTask cleanOutDateBackupFile(final Integer days,final String folder) {
        return new TimerTask() {
            @Override
            public void run() {
                File file=new File(folder);
                File[] files =  file.listFiles();
                Date now=new Date();
                Date daysAgo= DateUtils.addDays(now,days>0?-days:days);
                String name="";
                for(File f:files){
                    //通过文件名称判断日期如 ry_2019_10_19_11_34_943.sql
                    name=f.getName();
                    name=name.replace("ry_","");
                    name=name.substring(0,10);
                    name=name.replace("_","-");
                    Date date=DateUtils.parseDate(name);
                    if(date!=null && date.before(daysAgo)){
                        if(f.exists()){
                            f.delete();
                        }
                    }
                }
            }
        };
    }
}
