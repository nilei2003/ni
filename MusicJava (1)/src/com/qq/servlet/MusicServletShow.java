package com.qq.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.bean.Music;
import com.qq.biz.MusicBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.impl.MusicDaoImpl;



@WebServlet("/MusicServletShow/*")
public class MusicServletShow extends BaseServlet{


	//首页查询渲染音乐
	protected void showList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));

		MusicDao dao = new MusicBizImpl();

		List<Map<String, String>> list = dao.showlist(page, rows);

		if(null == list || list.isEmpty()) {
			this.send(response, 500, null);
			return;
		}

		this.send(response, 200, list);


	}

	protected void showLists(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mid = request.getParameter("mid");
		MusicDaoImpl dao = new MusicDaoImpl();

		Map<String, String> map = dao.showeids(mid);

		if(null == map || map.isEmpty()) {
			this.send(response, 500, null);
			return;
		}

		this.send(response, 200, map);


	}

	//首页查询渲染音乐
	protected void updatemusiclplayvloume(HttpServletRequest request, HttpServletResponse response) throws IOException {

	    String mid = request.getParameter("mid");

		MusicDao dao = new MusicBizImpl();

	    int result = dao.updatemusiclplayvloume(mid);

		if(result > 0) {
			this.send(response, 200);
			return;
		}

		this.send(response, 500);


	}

}
