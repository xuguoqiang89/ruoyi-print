package com.ruoyi.websocket.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.websocket.server.WebSocketServer;
import com.ruoyi.websocket.util.WebSocketUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private WebSocketServer websocketServer;

    @RequiresPermissions("notice")
    @GetMapping("/notice")
    public String notice(Model model) {
        model.addAttribute("online", websocketServer.getOnlineUsers().size());
        return "/websocket/notification";
    }
    /**
     * 发送消息通知
     *
     * @return
     */
    @RequiresPermissions("notice")
    @PostMapping("/notice")
    @Log(title = "通过websocket向前台用户发送通知",businessType = BusinessType.OTHER)
    @ResponseBody
    public AjaxResult notice(String msg) throws IOException {
//        WebSocketUtil.sendNotificationMsg(msg, websocketServer.getOnlineUsers());
        websocketServer.sendMessageToAll(msg);
        return AjaxResult.success("消息发送成功");
    }
}
