package com.qq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;


import com.jspsmart.upload.SmartUploadException;
import com.qq.biz.impl.SigerBizImpl;
import com.qq.dao.SigerDao;
import com.qq.util.FileUploadUtil;


/**
 * 添加歌手 已完成
 * @author 夏文龙
 *
 */

@WebServlet("/siger")
public class SigerServlet extends HttpServlet {

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


	private static final long serialVersionUID = 1L;

	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("添加歌手");
		FileUploadUtil uploagUtil = new FileUploadUtil();
		
		response.setContentType("text/html;charset=utf-8");//防止页面乱码
		
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		
		int result = 0;
		
		try {
			Map<String, String> map = uploagUtil.uploads(pageContext);
			
			System.out.println("==========="+map);
			
			SigerDao biz = new SigerBizImpl();
			
			
			int result1 = biz.add(map.get("sname"), map.get("sphoto"), map.get("sbrief"));
			
			System.out.println(result1);
			
			if(result1 == 4) {
				PrintWriter out = response.getWriter();
	         	out.print("<script>alert('请输入完整的歌手信息噢~~~');location.href ='userindex.html';</script>");
				out.flush();
			}
			
			if(result1 > 0) {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('添加成功~~~'); location.href = 'userindex.html'</script>");
			}else {
				PrintWriter out = response.getWriter();
	         	out.print("<script>alert('歌手添加失败噢~~~');location.href ='userindex.html';</script>");
				out.flush();
			}
			
//			FabuBizImpl fb = new FabuBizImpl();
//			if(fb.add(map.get("gname"), map.get("g_image")) < 0) {
//				PrintWriter out = response.getWriter();
//				out.print("<script>alert('请输入完整的商品信息噢~~~');location.href ='font/fabu.html';</script>");
////				out.flush();
//			}else {
//				FabuDaoImpl dao = new FabuDaoImpl();
//			
//			
//				result = dao.add(map.get("selection"), map.get("gname"),map.get("price"),map.get("stock"),map.get("g_image"),map.get("pnote"));
//				PrintWriter out = response.getWriter();
//				out.print("<script>alert('添加成功~~~'); location.href = 'font/fabu.html'</script>");
//				//out.flush();
//			}
			
//			System.out.println(map.get("gname")+map.get("price")+map.get("stock")+map.get("pnote"));
//			System.out.println(map.get("gname"));
//		System.out.println(map.get("g_image"));
//			System.out.println(map.get("g_image"));
//			System.out.println(result);
			//p判断
//			if(result > 0) {
//				PrintWriter out = response.getWriter();
//				out.print("<script>alert('商品信息添加失败...')</script>");
//				out.flush();
//				
//			}else {
//				response.sendRedirect("show.html");
//			}
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
