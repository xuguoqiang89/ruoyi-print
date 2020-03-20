package com.ruoyi.websocket.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

/**
 * websocket工具类，支持单条发送和批量发送
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class WebSocketUtil {
    public static final Logger log = LoggerFactory.getLogger(WebSocketUtil.class);
    private static final String ONLINE_MSG_KEY = "online";//在线用户
    private static final String NOTIFICATION_MSG_KEY = "notification";//通知消息
    private static final String TODO_MSG_KEY = "todo";//待办

    private WebSocketUtil() {
        // 私有化构造方法，禁止new
    }

    /**
     * 根据消息类型，生成发送到客户端的最终消息内容
     *
     * @param type
     *         消息类型
     * @param content
     *         消息正文
     * @return
     */
    private static String generateMsg(String type, String content) {
        return String.format("{\"fun\": \"%s\", \"msg\":\"%s\"}", type, content);
    }

    /**
     * 发送在线用户的消息
     *
     * @param msg
     */
    public static String sendOnlineMsg(String msg) {
        return generateMsg(ONLINE_MSG_KEY, msg);
    }

    /**
     * 发送通知的消息
     *
     * @param msg
     */
    public static String sendNotificationMsg(String msg) throws UnsupportedEncodingException {
        // 为了防止消息中存在特殊字符（比如换行符）等造成前台解析错误，此处编码一次。前台对应的需要解码
        return generateMsg(NOTIFICATION_MSG_KEY, URLEncoder.encode(msg, Charsets.UTF_8.displayName()));
    }
}
