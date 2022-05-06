package com.qq.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.bean.Music;
import com.qq.bean.User;
import com.qq.biz.MusicBizImpl;
import com.qq.biz.SepcialBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.SepcialDao;
import com.qq.dao.impl.SepcialDaoImpl;
import com.qq.util.SessionKeys;



@WebServlet("/SepcialServletShow/*")
public class SepcialServletShow extends BaseServlet{


	//根据mid拿到专辑ID 根据id查询专辑信息
	protected void showList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mid = request.getParameter("mid");

		MusicDao dao = new MusicBizImpl();

		Map<String, String> map = dao.showeid(mid);

		if(map == null || map.isEmpty()) {
			this.send(response, 500, null);
			return;
		}


		String eid = map.get("eid");

		SepcialDao dao1 = new SepcialDaoImpl();
		Map<String, String> map1 = dao1.find(eid);

		if(map1 == null || map1.isEmpty()) {
			this.send(response, 501, null);
			return;
		}

		this.send(response, 200, map1);


	}
	
	
	protected void showLists(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String eid = request.getParameter("eid");

		SepcialDao dao1 = new SepcialDaoImpl();
		Map<String, String> map1 = dao1.find(eid);

		if(map1 == null || map1.isEmpty()) {
			this.send(response, 501, null);
			return;
		}

		this.send(response, 200, map1);


	}


	protected void show(HttpServletRequest request, HttpServletResponse response) throws IOException {

		SepcialDao dao = new SepcialDaoImpl();
		List<Map<String, String>> list = dao.show();
		
		if(list == null || list.isEmpty()) {
			this.send(response, 501, null);
			return;
		}

		this.send(response, 200, list);


	}
	
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		SepcialDaoImpl dao = new SepcialDaoImpl();
		List<Map<String, String>> list = dao.findAll(uid);
		
		if(list == null || list.isEmpty()) {
			this.send(response, 501, null);
			return;
		}

		this.send(response, 200, list);


	}
	
	protected void find(HttpServletRequest request, HttpServletResponse response) throws IOException {

		SepcialDao dao = new SepcialBizImpl();
		
		String eid = request.getParameter("eid");
		
		Map<String, String> map = dao.find(eid);
		
		if(map == null || map.isEmpty()) {
			this.send(response, 501, null);
			return;
		}

		this.send(response, 200, map);


	}
	
	
	protected void showfind(HttpServletRequest request, HttpServletResponse response) throws IOException {

		MusicDao dao = new MusicBizImpl();
		
		String eid = request.getParameter("eid");
		
		List<Map<String, String>> list = dao.showfind(eid);
		
		if(list == null || list.isEmpty()) {
			this.send(response, 501, null);
			return;
		}

		this.send(response, 200, list);


	}

}
