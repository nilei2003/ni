package com.qq.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import com.qq.biz.MVPlayBiz;
import com.qq.biz.impl.MVPlayBizImpl;

@WebServlet("/mvplay/*")
public class MVPlayController extends BaseServlet{
	MVPlayBiz biz = new MVPlayBizImpl();
	/**
	 * 查询弹幕
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findBullet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mvid = req.getParameter("mvid");
		System.out.println(mvid);
		List<Map<String,Object>> list = biz.findBullet(mvid);
		if(list != null) {
			this.send(resp,200,list);
			return;
		}
		this.send(resp, 500);
	}
	/**
	 * 添加弹幕
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBullet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("text");
		String time = req.getParameter("time");
		String ismove = req.getParameter("ismove");
		String color = req.getParameter("color");
		String mvid = req.getParameter("mvid");
		System.out.println(text+"+" +time+"+" +ismove+"+" +color+"+" +mvid );
		int result = biz.addBullet(text, time, ismove, color, mvid);
		if(result <= 0) {
			this.send(resp, 500);
			return;
		}
		this.send(resp, 200);
	}
	
	protected void addDm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map map = req.getParameterMap();
		String message = null;
	
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
        	String[] values = (String[]) map.get(key);
        	for (String value : values) {
        		message = value;
        	}
        }
        System.out.println(message);
        WebSocketServer.sendAllMessage(message);
        
	}
}
