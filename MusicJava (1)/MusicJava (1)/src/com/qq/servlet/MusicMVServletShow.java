package com.qq.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.qq.dao.MusicDao;
import com.qq.dao.MusicmvDao;
import com.qq.dao.impl.MusicmvDaoImpl;

@WebServlet("/MusicMVServletShow/*")
public class MusicMVServletShow extends BaseServlet{
	
	
	//首页查询渲染音乐
	protected void showList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		
		MusicmvDao dao = new MusicmvDaoImpl();
		 
		List<Map<String, String>> list = dao.showlist(page, rows);
		
		if(null == list || list.isEmpty()) {
			this.send(response, 500, null);
			return;
		}
		
		this.send(response, 200, list);

		
	}
	
	
protected void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String mvid = request.getParameter("mvid");
		
		MusicmvDao dao = new MusicmvDaoImpl();
		 
		Map<String, String> map = dao.show(mvid);
		
		if(null == map || map.isEmpty()) {
			this.send(response, 500, null);
			return;
		}
		
		this.send(response, 200, map);

		
	}
}
