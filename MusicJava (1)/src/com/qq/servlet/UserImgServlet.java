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
import com.qq.bean.Music;
import com.qq.bean.User;
import com.qq.biz.MusicBizImpl;
import com.qq.biz.SigerBizImpl;
import com.qq.biz.UserBiz;
import com.qq.biz.impl.UserBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.SigerDao;
import com.qq.dao.UserDao;
import com.qq.dao.impl.SepcialDaoImpl;
import com.qq.util.FileUploadUtil;
import com.qq.util.SessionKeys;


/**
 * 上传音乐  已完成
 * @author 夏文龙
 *
 */

@WebServlet("/UserImgServlet")
public class UserImgServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====");
		
			add(request,response);
	
	}


	private static final long serialVersionUID = 1L;

	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("上传头像");
		FileUploadUtil uploagUtil = new FileUploadUtil();
		
		response.setContentType("text/html;charset=utf-8");//防止页面乱码
		
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		
		int result = 0;
		
		try {
			Map<String, String> map = uploagUtil.uploads(pageContext);
			
			System.out.println("==========="+map);
			User obj =(User) request.getSession().getAttribute(SessionKeys.CURRENTBACKLOGIN);
			String uid = String.valueOf(obj.uid);
			UserBiz biz = new UserBizImpl();
			int result1 = biz.updateuphoto(map.get("file"), uid);
			
			
			if(result1 == 4) {
				PrintWriter out = response.getWriter();
	         	out.print("<script>alert('请输入完整的音乐信息噢~~~');location.href ='userindex.html';</script>");
				out.flush();
			}
			
			if(result1 > 0) {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('添加成功~~~'); location.href = 'userindex.html'</script>");
				out.flush();
			}else {
				PrintWriter out = response.getWriter();
	         	out.print("<script>alert('音乐添加失败噢~~~');location.href ='userindex.html';</script>");
				out.flush();
			}
			
			
			//lyrlce=../MusicJava_vue/1647952816922_起风了.lrc, mbj=古风, mdate=地区, sname=买辣椒也用卷, musics=../MusicJava_vue/1647952816923_买辣椒也用券 - 起风了.mp3, mname=起风了, mphoto=../MusicJava_vue/1647952816930_01.jpg}

			
			
			
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
