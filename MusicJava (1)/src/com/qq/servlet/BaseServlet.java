package com.qq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);

		System.out.println(uri + "--" + path);

		Method[] method = this.getClass().getDeclaredMethods();

		for(Method m : method) {
			if(path.equals(m.getName())) {
				try {
					m.invoke(this, req, resp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
				return;
			}
		}
		this.error(req, resp);
	}


	private void error(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/";

		out.print("<script>alert('该请求不被支持...'); location.href ='" +basePath +"indet.html';</script>"); 
	}
	
	
	public void send(HttpServletResponse resp, int code) throws IOException {
		// TODO Auto-generated method stub
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
 
	    out.print(code);
	    out.flush();
	    out.close();
	
	}
	public void send(HttpServletResponse resp, String code) throws IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(code);
		out.flush();
		out.close();
		
	}
	public void send(HttpServletResponse resp, Object obj) throws IOException {
		// TODO Auto-generated method stub
		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		resp.setContentType("text/html;charset=utf-8");
		Gson gson = new GsonBuilder().serializeNulls().create();
		PrintWriter out = resp.getWriter();

		out.print(gson.toJson(obj));
		out.flush();
		out.close();
		
	}

	public void send(HttpServletResponse resp, int code, Object data) throws IOException {
		// TODO Auto-generated method stub
		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		resp.setContentType("application/jaon;charset=utf-8");
		Gson gson = new GsonBuilder().serializeNulls().create();
		PrintWriter out = resp.getWriter();

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		
		out.print(gson.toJson(map));
		out.flush();
		out.close();
		
	}



}