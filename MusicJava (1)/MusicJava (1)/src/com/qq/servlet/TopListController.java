package com.qq.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.biz.UserBiz;
import com.qq.biz.impl.ToplistBizImpl;
import com.qq.biz.impl.UserBizImpl;
import com.qq.dao.ToplistDao;

@WebServlet("/top/*")
public class TopListController extends BaseServlet{
	ToplistDao biz = new ToplistBizImpl();
	//查询评论
	protected void findComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String,Object>> list = biz.findComment();
		
		if(list == null) {
			this.send(resp, 500,null);
		}else {
			this.send(resp, 200,list);
		}
	}
	
	//查询当前歌曲是否有MV
	protected void findMV(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.send(resp, 200,biz.findMV());
	}
	
	//根据拼音首字母查询
	protected void findPin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		List<Map<String,Object>> list = biz.findPinSin(code);
		if(list == null) {
			this.send(resp, 500,null);
		}else {
			this.send(resp, 200,list);
		}
	}
	//评论点赞
	protected void addZan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String did = req.getParameter("did");
		String uid = req.getParameter("uid");
		Map<String,Object> map = biz.Checkgood(uid, did);
		if(map != null) {
			this.send(resp, 502);
			return;
		}
		int result = biz.addZan(uid, did);
		if(result <= 0) {
			this.send(resp, 500);
		}else {
			this.send(resp, 200);
		}
	}
	//评论回复点赞
	protected void addCZan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rid = req.getParameter("rid");
		String uid = req.getParameter("uid");
		Map<String,Object> map = biz.Checkgood(uid, rid);
		if(map != null) {
			this.send(resp, 502);
			return;
		}
		int result = biz.addCZan(uid, rid);
		if(result <= 0) {
			this.send(resp, 500);
		}else {
			this.send(resp, 200);
		}
	}
	//查询评论回复
	protected void findReComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String did = req.getParameter("did");
		List<Map<String, Object>> list = biz.findReComment(did);
		if(list == null) {
			this.send(resp, 500,null);
			return;
		}
		this.send(resp, 200,list);
	}
	
	protected void finPin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = biz.findPin();
		if(result >0) {
			this.send(resp, 200,result);
			return;
		}
		this.send(resp, 500,null);
	}
	//查看评论回复数
	protected void finCPin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String,Object>> list = biz.findReback();
		this.send(resp, 200,list);
	}
	/**
	 * 添加评论回复
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addRPin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String did = req.getParameter("did");
		
		String text = req.getParameter("rcomment");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());
		System.out.println(time + text + uid);
		int result = biz.addRPin(uid, did, text, time);
		System.out.println("添加回复结果"+result);
		if(result <= 0) {
			this.send(resp, 500);
			return;
		}
		this.send(resp, 200);
	}
	
	/**
	 * 查询点赞数
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String,Object>> list  = biz.findCountComent();
		if(list != null) {
			this.send(resp, 200,list);
			return;
		}
		this.send(resp, 500);
	}
	/**
	 * 查询单条回复评论
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findRPin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String,Object>> list = biz.findRCpin();
		
		if(list != null) {
			this.send(resp, 200,list);
			return;
		}
		this.send(resp, 500);
	}
	
	/**
	 * 初始化查询数据
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void inits(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<List<Map<String, Object>>> list = biz.inite();
		
		this.send(resp, 200,list);
	}
}
