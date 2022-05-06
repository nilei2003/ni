package com.qq.servlet;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{uname}")
public class WebSocketServer {

    private static String userName;
    private Session session;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();
    
    //连接时执行
    @OnOpen
    public void onOpen(Session session, @PathParam(value="uname")String userName) {
        this.session = session;
        webSocketSet.add(this);
        sessionPool.put(userName, session);
        System.out.println("【websocket消息】有新用户" + userName +"接入，当前在线总数为:"+webSocketSet.size());
    }

    //关闭时执行
    @OnClose
    public void onClose() {
        System.out.println("连接：" + this.userName + " 关闭");
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
    	
        session.getBasicRemote().sendText("收到 " + this.userName + " 的消息 "); //回复用户
    }
    
    // 此为广播消息-集体通知
    public static void sendAllMessage(String message) {
        for(WebSocketServer webSocket : webSocketSet) {
            System.out.println("【websocket消息】广播消息:"+message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户id为：" + this.userName + "的连接发送错误");
        error.printStackTrace();
    }
}
