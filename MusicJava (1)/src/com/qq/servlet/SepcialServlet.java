package com.qq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import com.qq.bean.Sepcial;
import com.qq.biz.MusicBizImpl;
import com.qq.biz.SepcialBizImpl;
import com.qq.biz.SigerBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.SepcialDao;
import com.qq.dao.SigerDao;
import com.qq.dao.impl.SepcialDaoImpl;
import com.qq.util.FileUploadUtil;

@WebServlet("/SepcialServlet")
public class SepcialServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====");
		String op = request.getParameter("op");
		switch(op) {
		case "add":
			add(request,response);
			break;
//		case "finds":
//			finds(request,response);
		}
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("上传专辑");
		FileUploadUtil uploagUtil = new FileUploadUtil();
		
		response.setContentType("text/html;charset=utf-8");//防止页面乱码
		
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		
		int result = 0;
		
		try {
			Map<String, String> map = uploagUtil.uploads(pageContext);
			
			System.out.println("==========="+map);
			
			
			
			SepcialDao dao = new SepcialBizImpl();
			
			String mid = new Date().getTime() + "" + new Random().nextInt(1000);// 时间戳 + 随机数   订单编号
			//根据歌手名字获取歌手id
			Integer emoney = Integer.valueOf(map.get("emoney"));
			Sepcial sepcial = new Sepcial();
			sepcial.setEid(mid);
			sepcial.setEbrief(map.get("ebrief"));
			sepcial.setEmoney(emoney);
			sepcial.setEphoto(map.get("ephoto"));
			sepcial.setEname(map.get("ename"));
		    
			
			
		
			int result1 = dao.add(sepcial);
			

			
			if(result1 > 0) {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('添加成功~~~'); location.href = 'userindex.html'</script>");
			}else {
				PrintWriter out = response.getWriter();
	         	out.print("<script>alert('专辑添加失败噢~~~');location.href ='userindex.html';</script>");
				out.flush();
			}
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		super.service(request, response);
	}

}
