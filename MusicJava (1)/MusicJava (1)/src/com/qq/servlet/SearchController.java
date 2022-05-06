package com.qq.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.biz.impl.SearchBizImpl;
import com.qq.dao.SearchDao;
import com.qq.util.StringUtils;
@WebServlet("/search/*")
public class SearchController extends BaseServlet{
	SearchDao biz = new SearchBizImpl();
	
	protected void findData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Sname = request.getParameter("sname");//获取搜索框内容
		if(StringUtils.pach(Sname)) {
			this.send(response, 500);
		}
		List<List<Map<String, Object>>> list = biz.search(Sname);
		this.send(response, 200,list);
	}
	protected void findDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Sname = request.getParameter("sname");//获取搜索框内容
		if(StringUtils.pach(Sname)) {
			this.send(response, 500);
		}
		List<List<Map<String, Object>>> list = biz.search_detail(Sname);
		System.out.println(list);
		this.send(response, 200,list);
	}
}
