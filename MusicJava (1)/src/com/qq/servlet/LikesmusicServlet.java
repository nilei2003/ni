package com.qq.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.bean.User;
import com.qq.biz.LikesmusicBizImpl;
import com.qq.dao.LikesmusicDao;
import com.qq.util.SessionKeys;

@WebServlet("/LikesmusicServlet/*")
public class LikesmusicServlet extends BaseServlet{

	// 点赞
	protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mid = request.getParameter("mid");
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		
		LikesmusicDao biz = new LikesmusicBizImpl();
		
		int result = biz.addLikesmusic(uid, mid);
		if(result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
		

	}
	
	// 查询点赞
	protected void showLikeMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mid = request.getParameter("mid");
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		
		LikesmusicDao biz = new LikesmusicBizImpl();
		
		Map<String, String> map = biz.showLikesmusic(uid, mid);
		if(map == null || map.isEmpty()) {
			this.send(response, 500, null);
			return;
		}
		this.send(response, 200, map);
		

	}
	
	// 取消点赞
	protected void deleteLikeMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mid = request.getParameter("mid");
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		
		LikesmusicDao biz = new LikesmusicBizImpl();
		
		int result = biz.delete(uid, mid);
		if(result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
		

	}
	
	
	

}
