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

import com.qq.bean.MoneySepcial;
import com.qq.bean.User;
import com.qq.biz.MoneySepcialBizImpl;
import com.qq.dao.MoneySepcialDao;
import com.qq.dao.MusicmvDao;
import com.qq.dao.impl.MusicmvDaoImpl;
import com.qq.util.SessionKeys;

@WebServlet("/MoneySepcialSelvlet/*")
public class MoneySepcialSelvlet extends BaseServlet{
	
	MoneySepcialDao biz = new MoneySepcialBizImpl();
	
	//添加购买
	   protected void showList(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			String eid = request.getParameter("eid");
			User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
			String uid = String.valueOf(obj.uid);
			String msid = new Date().getTime() + "" + new Random().nextInt(1000);// 时间戳 + 随机数   订单编号
			
			Map<String , String> map = new HashMap<String, String>();
			map.put("eid", eid);
			map.put("uid", uid);
			map.put("msid", msid);
		    
		    if(map == null || map.isEmpty()) {
		    	this.send(response, 500, null);
		    }
		    
			
			this.send(response, 200, map);

			
		}
		//查询购买记录 用于判断用户有没有购买音乐
		protected void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			String eid = request.getParameter("eid");
			User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
			String uids = String.valueOf(obj.uid);
			
			int uid = Integer.valueOf(uids);
			MoneySepcial moneySepcial = new MoneySepcial();
			moneySepcial.setEid(eid);
			moneySepcial.setUid(uid);
		    Map<String , String> map = biz.show(moneySepcial);
		    
		    if(map == null || map.isEmpty()) {
		    	this.send(response, 500, null);
		    	return;
		    }
		    
		    this.send(response, 200, map);

			
		}

}
