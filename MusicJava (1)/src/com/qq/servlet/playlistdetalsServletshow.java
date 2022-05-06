package com.qq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.qq.bean.Music;
import com.qq.bean.Musicmv;
import com.qq.bean.playlist;
import com.qq.biz.MusicBizImpl;
import com.qq.biz.MusicmvBizimpl;
import com.qq.biz.SigerBizImpl;
import com.qq.biz.playlistBizImpl;
import com.qq.biz.playlistdetalsBiaImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.MusicmvDao;
import com.qq.dao.SigerDao;
import com.qq.dao.playlistDao;
import com.qq.dao.playlistdetalsDao;
import com.qq.dao.impl.playlistdetalsDaoImpl;
import com.qq.util.FileUploadUtil;
import com.qq.util.SessionKeys;


/**
 * 
 * @author 夏文龙
 *
 */
@WebServlet("/playlistdetalsServletshow/*")
public class playlistdetalsServletshow extends BaseServlet {


	//首页查询渲染音乐
	protected void showList(HttpServletRequest request, HttpServletResponse response) throws IOException {


		playlistdetalsDao biz = new playlistdetalsBiaImpl();

		List<Map<String, String>> list = biz.fingAll();

		if(null == list || list.isEmpty()) {
			this.send(response, 500, null);
			return;
		}

		this.send(response, 200, list);


	}


	//渲染歌单
	protected void show(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pid = request.getParameter("pid");
		playlistdetalsDao biz = new playlistdetalsBiaImpl();

		Map<String, String> map = biz.show(pid);

		if(null == map || map.isEmpty()) {
			this.send(response, 500, null);
			return;
		}

		this.send(response, 200, map);


	}
	
	

	//渲染歌单
	protected void updateplaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pid = request.getParameter("pid");
        playlistDao biz = new playlistBizImpl();

		int result = biz.updatedisplay(pid);

		if(result > 0) {
			this.send(response, 200);
			return;
		}

		this.send(response, 500);


	}

	

	//渲染歌单
	protected void showAll(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pid = request.getParameter("pid");
		playlistdetalsDao biz = new playlistdetalsBiaImpl();

		List<Map<String, String>> list = biz.showAll(pid);

		if(null == list || list.isEmpty()) {
			this.send(response, 500, null);
			return;
		}

		this.send(response, 200, list);


	}



}
