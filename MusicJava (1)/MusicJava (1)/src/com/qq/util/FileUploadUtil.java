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

/**
 * 文件上传的工具类
 * company 源辰信息
 * @author 38929
 * @date 2021年7月26日
 * @version 1.0
 * Email 1198865589@qq.com
 */
public class FileUploadUtil {

	public static String uploadPath = "../MusicJava_vue";//默认的上传路径
		
	private static final String ALLOWEDFILESLIST = "gif,jpg,jpeg,png,mp3,lrc,mp4";//允许上传的文件类型
	private static final String DENIEDFILESLIST = "exe,bat,jsp";//不允许上传的文件类型
	private static final int TOTALMAXFILESIZE= 1000 * 1024 * 1024;//最大总上传文件的最大值
	private static final int MAXFILESIZE = 500 * 1024 * 1024;//单个上传文件的最大值
	
	
	/**
	 * 文件上传    普通文本 ->  单个文件 -> 多个文件 -> 多文件域 多文件  
	 * @param pageContext 页面上下文
	 * @return map 网页数据 包含文件信息
	 * @throws ServletException  
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws SmartUploadException 
	 */
	public Map<String, String> uploads(PageContext pageContext) throws ServletException, IOException, SQLException, SmartUploadException{
		
		Map<String, String> map = new HashMap<String, String>();
		
		//上传工具 
		SmartUpload su = new SmartUpload();
		//初始化组件
		su.initialize(pageContext);
		
		//配置参数 
		su.setAllowedFilesList( ALLOWEDFILESLIST ); //允许上传的文件类型
		su.setDeniedFilesList( DENIEDFILESLIST );  //不允许上传的文件类型
		su.setTotalMaxFileSize( TOTALMAXFILESIZE );//最大总上传文件的最大值
		su.setMaxFileSize( MAXFILESIZE );//单个上传文件的最大值
		
		su.setCharset("UTF-8");//上传数据的编码集
		su.upload();//开始上传
		
		//获取上传的参数信息   非文件参数   注意 此时 的request是com.jspsmart.upload.Request而不是HttpServletRequest中
		Request request = su.getRequest();
		System.out.println( request );
		
		//获取前端网页控件的名称  枚举
		Enumeration<String> enums = request.getParameterNames();
		System.out.println("++++++++++++++++==");
		System.out.println(enums);
		//单个控件名称  name属性
		String name = null;
		//获取信息
		while( enums.hasMoreElements() ){
			name = enums.nextElement(); //键
			System.out.println(name);
			map.put(name, request.getParameter(name)); //值
		}
		
		//return map;//此时获取的所有的普通文本信息
		
		//TODO : 思考问题
		/*
		 * 1、如何上传文件
		 * 2、文件存储服务器的那个位置？
		 * 3、文件如何存储到指定的位置？
		 * 4、获取存储的文件路径 如何处理？
		 * 5、如果服务器指定的位置有多张同名图片 如何处理？
		 */		
		
		//TODO 处理上传文件
		Files files = su.getFiles();//获取的所有的文件

		//如果无文件 就直接返回 普通文本信息   说明用户未上传文件
		if(null == files || files.getCount() <= 0 || files.getSize() <=0 || files.getFile(0).getSize() <= 0) {
			return map;//此时获取的所有的普通文本信息
		}
		//有 则循环取出用户上传的文件
		Collection<File> fls = files.getCollection();
		
		//TODO  文件存储服务器的详细位置
		//获取保存文件的文件夹的绝对路径-> Tomcat服务器的绝对路径   C...\webapps\项目名\
		//String basePath = pageContext.getRequest().getRealPath("/");
		String basePath = pageContext.getServletContext().getRealPath("/");//C:\Tomcat\apache-tomcat-9.0.50\webapps\Goods\
		//pageContext.getServletContext() 对应的实例名就是jsp中内置对象的application

		String fileName = null;//上传后的文件名称
		String fieldName = null;//上传文件域的控件name属性名
		String filePath = null;//文件保存路径
		
		String temp  = null;//网页文件中上传的控件名称  文件域
		String pathStr = null;//多文件拼接路径
		
		//循环处理文件
		for( File fl : fls) {
			
			if( !fl.isMissing()) { //文本未丢失 打印相关信息
			
				temp =  fl.getFieldName();// files  photo 页面多个文件域控件
				if( StringUtils.pach( fieldName )) {//如果这个变量是空  说明这是第一个文件域 要上传的文件
					fieldName = temp;
				}else { //否则是其他文件域控件 
					if( !temp.equals( fieldName ) ) {//说明是其他文件域控件 
						map.put( fieldName, pathStr);//首先需要存储第一个文件域的内容 
						pathStr = "";//初始化 清空上一个文件域的路径  准备存放下一个文件域上传路径
						fieldName = temp; //新的文件域控件
					}
				}
				
				//为了避免 文件名相同出现的文件覆盖问题 所以自定义上传文件的名字 注意   fileName  |   fieldName
				fieldName = fl.getFieldName();
				fileName = uploadPath + "/" + new Date().getTime() + "_" + fl.getFileName();//images/123_1.jpg
				
				
				//如果是第一个上传的文件
				if( StringUtils.pach( pathStr )) {
					pathStr = fileName;//第一个文件的相对路径
				}else {  
					pathStr += ";"  + fileName; //多图片 字符串拼接的相对路径   ../images/123_1.jpg;../images/456_2.jpg
				}
				
				//将上传的文件存储服务器的指定目录 获取上传的服务器路径 + 自定义路径 + 自定义名
				filePath = basePath + fileName; 
				//开始存储文件
				fl.saveAs(filePath, SmartUpload.SAVE_PHYSICAL);
				
				//现象： 保存服务器项目目录下images文件下   不能永久性存储   图片在服务器重启消失 
				//TODO 如何永久性存储 文件信息
				//方案： webapps\项目名\images  -> webapps\images ->  在默认路径前面加 ../
				
				//TODO 1、 如果是一个文件域中有多个文件如何处理？  --> 提示 ： 字符串的拼接 1.png;2.png
				//TODO 2、如果是多文件域  每一个文件域也可能出现多个文件 又该如何处理？
				
			}
		}
		//map.put(fieldName, filePath);
		map.put(fieldName, pathStr);//控件名 -> 自定义的文件名 存入map ->多图拼接路径  数据需要存储的相对路径
		return map;//带有图片的相对路径信息
		
		//TODO  如何以对象的方式返回  而不是map
	}
	
	//富文本单图片上传
	public Map<String, String> upload(PageContext pageContext) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		SmartUpload su = new SmartUpload();
		//初始组件
		su.initialize(pageContext);
		
		//设置参数
		su.setAllowedFilesList(ALLOWEDFILESLIST);//允许上传的文件类型
		su.setDeniedFilesList(DENIEDFILESLIST);//不允许上传的文件类型
		su.setTotalMaxFileSize(MAXFILESIZE);//单个上传文件的最大值
		su.setMaxFileSize(TOTALMAXFILESIZE);//最大总上传文件的最大值
		
		su.setCharset("UTF-8");//上传数据的编码集
		su.upload();//开始上传
		//TODO  处理上传文件 
		Files files = su.getFiles(); //获取所有的文件
		//如果无文件 就直接 普通文本信息    说明用户未上传文件
		if( files == null || files.getCount() <= 0 || files.getSize() <= 0 || files.getFile(0).getSize() <= 0 ) {
			return map;//此时获取所有的普通文本信息
		}
		
		//有 则循环取出用户上传的文件
		Collection<File> fls = files.getCollection();
		
		//获取保存文件的文件夹的绝对路径 -> Tomcat服务器的绝对路径   C...\webapps\项目名\
		String basePath = pageContext.getServletContext().getRealPath("/");
				
		String fileName = null;//上传后的文件名称
	    String fieldName = null;///上传文件域的控件name属性
		String filePath = null;//文件保存的路径		
			
		//循环处理 文件 
		for(File fl : fls) {
			if( !fl.isMissing()) { //文件未丢失 打印相关信息
				//为了避免 文件名相同出现的文件覆盖问题  所以自定义上传文件的名称   注意   fileName  fieldName
				fieldName = fl.getFieldName();
				fileName = uploadPath + "/" + new Date().getTime() + "_" + fl.getFileName();// images/123_head19.jpg 
	
				//将上传的文件存储到服务器的指定目录    获取上传的服务器路径 + 自定义路径 + 自定义名称   
				filePath =  basePath + fileName;  //绝对路径 存储文件
				
				//开始存储文件
				fl.saveAs(filePath, SmartUpload.SAVE_PHYSICAL); 
			}
		}
		map.put(fieldName, fileName);//富文本上传的控件
		return map;
	}
}
