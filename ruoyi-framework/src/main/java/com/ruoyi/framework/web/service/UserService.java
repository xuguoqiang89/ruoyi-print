package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Administrator
 * @Date: 2020\3\3 0003
 */
@Service("user")
public class UserService {

    @Autowired
    private ISysUserService userService;

    /**
     * 获取所有的系统用户
     * @return
     */
    public List<SysUser> getAllUsers(){
        return userService.selectAllUserList();
    }
}
