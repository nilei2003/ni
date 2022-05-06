package com.qq.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.bean.User;
import com.qq.biz.MusicBizImpl;
import com.qq.biz.TemporarysdetalsBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.impl.TemporarysdetalsDaoImpl;
import com.qq.util.SessionKeys;

@WebServlet("/TemporarysdetalsServlet/*")
public class TemporarysdetalsServlet extends BaseServlet{


	TemporarysdetalsBizImpl biz = new TemporarysdetalsBizImpl();
	//用户有临时表 吗
	protected void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String uid = (String) request.getSession().getAttribute(SessionKeys.UID);
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);
		System.out.println(uid);

		//String mid = request.getParameter("mid");

		Map<String, String> map = biz.finds(uid);

		if(map == null || map.isEmpty()) {
			this.send(response, 500, 2);
		}

		this.send(response, 200, map);


	}

	//用户有临时表 判断有没有这首歌
	protected void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);

		//		String uid = "101";
		String mid = request.getParameter("mid");

		Map<String, String> map = biz.find(uid, mid);

		if(map == null || map.isEmpty()) {
			this.send(response, 500, 2);
		}

		this.send(response, 200, map);


	}

	// 用户有临时表 没有当前歌
	protected void add02(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);

		//String uid = "101";
		String tid = request.getParameter("tid");
		String mid = request.getParameter("mid");

		int result = biz.add02(tid, mid);

		if(result > 0) {
			this.send(response, 200, result);
		}

		this.send(response, 500, -2);


	}


	// 用户没有临时表 没有当前歌
	protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);

		//String uid = "101";
		String mid = request.getParameter("mid");

		int result = biz.add(mid, uid);

		if(result > 0) {
			this.send(response, 200, result);
		}

		this.send(response, 500, -2);



	}

	protected void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);


		//String uid = "101";

		List<Map<String, String>> list = biz.show(uid);

		if(list == null || list.isEmpty()) {
			this.send(response, 500, null);
		}

		this.send(response, 200, list);



	}


	protected void showlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);


		//String uid = "101";
		String tddid = request.getParameter("tddid");

		int result = biz.delete(tddid);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}

		this.send(response, 500, -1);



	}

	//无表 播放全部
	protected void addUserAndMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);


		//String uid = "101";

		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addUserAndMusic(uid, null, 1);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}



	// 判断用户临时列表有没有数据
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {



		String tid = request.getParameter("tid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();
		List<Map<String, String>> list = dao.fingall(tid);

		if(null == list || list.isEmpty()) {
			this.send(response, 200, 1);
			return;
		}

		this.send(response, 500, -1);



	}

	// 有表无数据
	protected void addMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {



		String tid = request.getParameter("tid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addMusic(tid, null, 1);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}



	// 如果用户有临时播放表， 且里面有数据
	protected void addshow(HttpServletRequest request, HttpServletResponse response) throws IOException {



		String tid = request.getParameter("tid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addshow(tid, null, 1);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}


	/**
	 * 专辑播放全部
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	// 有表无数据
	protected void showlists01(HttpServletRequest request, HttpServletResponse response) throws IOException {


		String tid = request.getParameter("tid");
		String eid = request.getParameter("eid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addMusic(tid, eid, 2);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}

	//无表 播放全部
	protected void showlists01s(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);

		String eid = request.getParameter("eid");
		//String uid = "101";

		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addUserAndMusic(uid, eid, 2);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}

	// 如果用户有临时播放表， 且里面有数据
	protected void showlists01ss(HttpServletRequest request, HttpServletResponse response) throws IOException {



		String tid = request.getParameter("tid");
		String eid =  request.getParameter("eid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addshow(tid, eid, 2);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}

	
	
	/**
	 * 歌单播放全部
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	// 有表无数据
	protected void showlistsplaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {


		String tid = request.getParameter("tid");
		String pid = request.getParameter("pid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addMusic(tid, pid, 3);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}

	//无表 播放全部
	protected void showlistsplaylist1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
		String uid = String.valueOf(obj.uid);

		String pid = request.getParameter("pid");
		//String uid = "101";

		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addUserAndMusic(uid, pid, 3);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}

	// 如果用户有临时播放表， 且里面有数据
	protected void showlistsplaylist2(HttpServletRequest request, HttpServletResponse response) throws IOException {



		String tid = request.getParameter("tid");
		String pid =  request.getParameter("pid");
		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.addshow(tid, pid, 3);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, -1);


	}



	protected void deleteshow(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String tid = request.getParameter("tid");

		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();

		int result = dao.deleteshow(tid);

		if(result > 0) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 500, result);


	}

	protected void deletemusic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		String mid = request.getParameter("mid");

		String [] arrs = mid.split(",");

		TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();
		//
		int result = dao.deletemusic(tid, mid);

		if(result > 0) {
			this.send(response, 200, arrs);
			return;
		}
		this.send(response, 500, -1);


	}





}
