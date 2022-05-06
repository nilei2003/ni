package com.qq.fliter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.util.StringUtils;





@WebFilter(filterName = "MyLiveFilter2", value="/*",
           initParams = @WebInitParam(name="encoding", value="utf-8"))
public class MyLiveFilter2 implements Filter{
	
	private String encoding = "utf-8";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter...DOFilter...");
		
		//转换
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		chain.doFilter(req, resp);

	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter...init...");
		
		String temp = filterConfig.getInitParameter("encoding");
		
		if(!StringUtils.pach(temp)) {
			encoding = temp;
		}
	}

}
