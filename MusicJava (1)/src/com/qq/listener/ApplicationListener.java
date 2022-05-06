package com.qq.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebListener;

import com.qq.util.FileUtil;
import com.qq.util.StringUtils;




 

@WebListener
public class ApplicationListener implements ServletContextListener{
	
	private String path = "../pics";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("监听器初始化");
		
		//获取初始化参数
		String temp = sce.getServletContext().getInitParameter("uploadPath");
		System.out.println(temp);
		//判断
		if( !StringUtils.pach(temp)) {
			path = temp;
		}
		//获取保存文件的文件夹的绝对路径-> Tomcat服务器的绝对路径   c...\wabapps\项目名\
		String basePath = sce.getServletContext().getRealPath("/");
		File file = new File(basePath, path);
		//判断路径是否存在  如果存在则不管  不存在则需要创建
		if( !file.exists()) {
			file.mkdir();//创建目录
		}
		
		FileUtil.uploadPath = path;//修改文件上传工具  FileUtil uploadpath 的默认值
	}
	
	

}
