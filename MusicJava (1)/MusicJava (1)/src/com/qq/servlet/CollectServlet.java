package com.qq.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.bean.Collect;
import com.qq.bean.User;
import com.qq.biz.CollectBizImpl;
import com.qq.dao.CollectDao;
import com.qq.util.SessionKeys;

@WebServlet("/CollectServlet/*")
public class CollectServlet extends BaseServlet{

	CollectDao biz = new CollectBizImpl();

	// 添加收藏
	protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("=======================");

		String mid = request.getParameter("mid");

		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);

		System.out.println(mid + "===" + uid);

		int result = biz.add(uid, mid);

		if(result > 0) {
			this.send(response, 200);
			return;
		}

		this.send(response, 500);

	}

	// 查询收藏表是否已经有了该音乐
	protected void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mid = request.getParameter("mid");

		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		Map<String, String> map = biz.music(mid, uid);

		if(map == null || map.isEmpty()) {
			this.send(response, 500);
			return;
		}

		this.send(response, 501, map);

	}


	// 查询当前用户的收藏记录 
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		

		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		List<Map<String, String>> list = biz.findAll(uid);
	
		if(list == null || list.isEmpty()) {
			this.send(response, 500);
			return;
		}

		this.send(response, 200, list);

	}


}
