package com.qq.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.bean.Music;
import com.qq.biz.impl.MusicBizImpl;
import com.qq.dao.MusicDao;
import com.qq.util.StringUtils;

@WebServlet("/music/*")
public class MusicController extends BaseServlet{


	protected void findmusic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		if(StringUtils.pach(sid)) {
			return;
		}
		MusicDao biz = new MusicBizImpl();
		List<Map<String,Object>> list = biz.findMusic(sid);
		System.out.println(list);
		if(list == null) {
			this.send(resp, 500,null);
		}else {
			this.send(resp, 200,list);
		}
	}
	//查找排行榜
	protected void findBymusic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MusicDao biz = new MusicBizImpl();
		List<Map<String,Object>> list = biz.findByMusic();
		
		System.out.println(list);
		if(list == null) {
			this.send(resp, 500,null);
		}else {
			this.send(resp, 200,list);
		}
	}
}
