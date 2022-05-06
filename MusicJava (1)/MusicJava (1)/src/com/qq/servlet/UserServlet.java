package com.qq.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;
import com.qq.bean.User;
import com.qq.biz.UserBiz;
import com.qq.biz.impl.UserBizImpl;
import com.qq.dao.impl.UserDaoImpl;
import com.qq.util.SessionKeys;
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
	UserBiz biz = new UserBizImpl();

	/**
	 * 退出登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void quit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		User user = biz.getUser(uid);
		req.getSession().removeAttribute(SessionKeys.CURRENTBACKLOGIN);
		this.send(resp, 200);
	}
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uname = req.getParameter("account");
		String upwd = req.getParameter("pwd");
		User user = biz.Login(uname, upwd);
		
		if(user == null) {
			this.send(resp, 500,null);
			return;
		}
		req.getSession().setAttribute(SessionKeys.CURRENTBACKLOGIN, user);
		this.send(resp, 200,user);
	}
	protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		int result = biz.add(uname, pwd, email);
		
		if(result <= 0) {
			this.send(resp, 500);
		}else {
			this.send(resp, 200);
		}
	}
	protected void info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object obj =req.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		if(obj == null) {
			this.send(resp, 500,null);
		}
		this.send(resp, 200,obj);
	}
	
	protected void infoto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User obj =(User) req.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		int uid = obj.uid;
		UserDaoImpl dao = new UserDaoImpl();
		User user = dao.show(uid);
		if(user == null) {
			this.send(resp, 500,null);
			return;
		}
		req.getSession().setAttribute(SessionKeys.CURRENTBACKLOGIN, user);
		this.send(resp, 200,user);
	}
	protected void addComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String text = req.getParameter("comment");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());
		System.out.println("当前时间"+time);
		
		int result = biz.addComment(uid, time, text, 5, 0, 0, null, null, 0);
		if(result <= 0) {
			this.send(resp, 500);
		}else {
			this.send(resp, 200);
		}
	}
	

}
