package com.qq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.biz.impl.MusicBizImpl;
import com.qq.biz.impl.SigerBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.SigerDao;

@WebServlet("/singer/*")
public class SingerController extends BaseServlet{
	SigerDao dao = new SigerBizImpl();

	//第一次进行查询
	protected void findFirst(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.send(resp,dao.findSinger());
	}
	//第一次进行查询
	protected void findHot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.send(resp,dao.findHot());
	}
	protected void findFirsts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		List<List<Map<String, Object>>> list = dao.findFirst(sid);
		this.send(resp,200,list);
	}
	
	//查询关注的歌手
	protected void findConSin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if(uid=="") {
			return;
		}
		List<Map<String,Object>> list = dao.findConSin(uid);
		this.send(resp, 200,list);
	}
	//查询当前歌手的mv
	protected void findMV(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		List<Map<String,Object>> list = dao.findMv(sid);
		System.out.println(sid);
		System.out.println("mv"+ list);
		this.send(resp, 200,list);
	}
	
	//歌手详情页面通过sid进行查询歌手信息
	protected void findSinger(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		if(sid == "") {
			return;
		}
		Map<String,String> map = dao.find(sid);
		if(map == null) {
			this.send(resp, 500,null);
		}else {
			this.send(resp, 200,map);
		}
		
	}
	
	protected void addFans(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		String uid = req.getParameter("uid");
		if(sid == "" || uid == "") {
			return;
		}
		int result = dao.addFans(sid, uid);
		if(result <= 0) {
			this.send(resp, 500);
			return;
		}
		this.send(resp, 200);
	}
	protected void findFans(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String sid = req.getParameter("sid");
		if(uid == "") {
			return;
		}
		Map<String,String> map = dao.findFans(uid,sid);

		if(map == null || map.isEmpty()) {
			this.send(resp, 500,null);
			
		}else {
			this.send(resp, 200,map);
		}
		
	}
	
	//取消关注
	protected void descfans(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String sid = req.getParameter("sid");
		if(uid == "") {
			return ;
		}
		int result = dao.descFans(sid, uid);
		System.out.println(result);
		if(result <= 0) {
			this.send(resp, 500);
		}else {
			this.send(resp, 200);
		}
	}
		
}
