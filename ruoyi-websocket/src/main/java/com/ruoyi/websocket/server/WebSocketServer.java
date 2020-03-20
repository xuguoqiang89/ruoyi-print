package com.ruoyi.websocket.server;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.websocket.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    public static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 线程安全的socket集合
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    /**
     * 链接数
     */
    private static int onlineCount = 0;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("当前在线人数为" + getOnlineCount());
        this.sid=sid;
        try {
            sendMessageToAll("新用户刚才登陆了系统");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
 　　 * 发生错误时触发的方法
 　　*/
    @OnError
    public void onError(Session session,Throwable error){
        log.info(session.getId()+"连接发生错误"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //log.info("收到来自窗口"+sid+"的信息:"+message);
        if("heart".equals(message)){
            try {
                sendMessage("heartOk");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(int status,String message,Object datas) throws IOException {
        JSONObject result = new JSONObject();
        result.put("status", status);
        result.put("message", message);
        result.put("datas", datas);
        this.session.getBasicRemote().sendText(result.toString());
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * */
    public void sendMessageToAll(String message) throws IOException {
        message = WebSocketUtil.sendNotificationMsg(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
//                if(sid==null) {
                    item.sendMessage(message);
                    log.info("推送消息到窗口"+item.sid+"，推送内容:"+message);
                /*}else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }*/
            } catch (IOException e) {
                continue;
            }
        }
    }
    /**
     * 获取在线用户数量
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    /**
     * 获取在线用户的会话信息
     *
     * @return
     */
    public CopyOnWriteArraySet<WebSocketServer> getOnlineUsers() {
        return webSocketSet;
    }
}
