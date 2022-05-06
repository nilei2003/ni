package com.qq.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 读取配置文件的类
 * 采用单例模式 -》 再整个程序运行过程中只有一个类的对象存在
 * 1 将构成方法私有化 不允许用户去创建这个类的对象
 * 2 声明一个这个类的静态对象 存放生成的对象
 * 3 提供静态方法 返回这个累的对象
 * @author 夏文龙
 *
 */
public class ReadPro extends Properties{
	private static final long serialVersionUID = -3300047005286395174L;
	private static ReadPro instance = new ReadPro();
	
	private ReadPro() {
		try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("db.properties")){
			this.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static ReadPro getInstance() {
		return instance;
	}
 
}
