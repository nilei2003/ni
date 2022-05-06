package com.qq.util;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javafx.print.Collation;

/**
 * 文件上传工具
 * @author 夏文龙
 *
 */
public class FileUtil {
	public static String uploadPath = "../images";//默认上传路径
	public static final String ALLOWEDFILESLIST = "gif,jpg,jpeg,png";//默认上传路径
	public static final String DENIEDFILESLIST = "exe,bat,jsp";//不允许上传的文件
	public static final int TOTALMAXFILESIZE = 100 * 1024 * 1024;//最大总上传文件的最大值
	public static final int MAXFILESIZE = 10 * 1024 * 1024;//单个文件上传的最大值
	
	
	
	public Map<String, String> uploads(PageContext pageContext) throws ServletException, IOException, SQLException, SmartUploadException{
		Map<String, String> map = new HashMap<String, String>();
		
		//上传工具
		SmartUpload su = new SmartUpload();
		//初始化工具
		su.initialize(pageContext);
		//配置参数
		su.setAllowedFilesList(ALLOWEDFILESLIST);//默认上传路径
		su.setDeniedFilesList(DENIEDFILESLIST);//默认上传路径
		su.setTotalMaxFileSize(TOTALMAXFILESIZE);//最大总上传文件的最大值
		su.setMaxFileSize(MAXFILESIZE);//单个文件上传的最大值
		su.setCharset("UTF-8");//上传数据编码集
		
		su.upload();//开始上传
		
		//获取上传的参数  非文件参数  注意 此时的 requset是com.jspsmart.upload.Request
		Request requset = su.getRequest();//
		System.out.println(requset);//
		
		//获取前端网页控件名
		Enumeration<String> enums = requset.getParameterNames();
		
		//单个控件名 name属性
		String name = null;
		
		//获取信息
		while(enums.hasMoreElements()) {
			name = enums.nextElement();//建
			System.out.println("-------------------------");
			map.put(name, requset.getParameter(name));//值
		}
		
		
		//TODO :
		/*
		 * 1.如何上传文件
		 * 2.文件存储服务器的那个位置
		 * 3.文件如何存储到指定位置
		 * 4.获取的文件存储路径如何处理
		 * 5.如果服务器指定的位置有多张同名图片如何处理
		 */
		
		System.out.println("=========");
		System.out.println(map);
		
		//处理上传图片
		Files files = su.getFiles();//获取所有的文件
		System.out.println(files);
		
		
		//如果无文件  就直接返回 普通文本信息 说明用户未上传文件
		if(null == files || files.getCount() <= 0 || files.getSize() <= 0 || files.getFile(0).getSize() <= 0) {
			return map;//
		}
		
		//有 则循环取出用户上传的文件
		Collection<File> fls = files.getCollection();
		
		//TODO 文件存储服务器的详细位置
		//获取保存文件的文件夹的绝对路径-> Tomcat服务器的绝对路径  
		
		//String basePath = pageContext.request().getRealPath("/");
		String basePath = pageContext.getServletContext().getRealPath("/");
		//
		System.out.println(basePath);
		
		String fileName = null;//上传后的文件名称
		String filedName = null;//上传文件域的控件name属性
		String filePath = null;//文本保存路径
		
		
		String temp = null;//网页文件中上传的控件名称  文件域
		String pateStr = null;//多拼接路径
		//循环处理文件
		for(File fl : fls) {
			if(!fl.isMissing()) {//文件未丢失  打印相关信息
				System.out.println(fl.getFieldName());//控件名
				System.out.println(fl.getFileName());//
				System.out.println(fl.getContentType());//
				System.out.println(fl.getSize());//
				
				
				temp = fl.getFieldName();//files photo  页面多个文件域控件
				
				if(StringUtils.pach(filedName)) {//如果这个文件为空  说明第一个文本域 要上传的文件
					filedName = temp;
				}else {//否则是其他文件域控件
					if(!temp.equals(filedName)) {//是其他文件域控件
						map.put(filedName, pateStr);////首先需要其他文件域的内容
						pateStr = "";//初始化  清空上一个文件域的路径  准备存放下一个路径
						filedName = temp;//新的文件域控件
					}
				}
				
				//为了避免 文件名相同的文件出现覆盖问题 所有自定义上传文件的名字 注意  fileName  |  filedName
				filedName = fl.getFieldName();
				fileName = uploadPath + "/" + new Date().getTime() + "_" + fl.getFileName();//../images/123_1.png
				
				//如果是第一个上传的文件
				if(StringUtils.pach(pateStr)) {
					pateStr = fileName;//第一个文件的相对路径  ../images/123_1.png;../images/123_3.png
				}else {
					pateStr += ";" + fileName;//多图片  字符串拼接的相对路径  
				}
				
				
				
				//将上传的问价存储服务器的指定目录 获取上传的服务器路径  + 自定义路径 + 自定义名
				filePath = basePath + fileName;
				
				
				//开始存储文件
				fl.saveAs(filePath, SmartUpload.SAVE_PHYSICAL);
				
				
				//想象 ：保存到服务器项目目录下images文件下  不能永久存储文件
				
				//TODO 如何永久存储文件
				//方案 ：
				
				//TODO 1 如果是一个文件域有多个文件如何处理？ --> 提升 字符串拼接  1.png;2.png
				//TODO 2 如果是多个文件域  每一个文件域可能出现多个文件  又该如何处理
			}
		}
		
		map.put(filedName, pateStr);//控件名 自定义的控件名存入map
		
		
		return map;
	}

}
