package com.qq.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequesToObject {
	public static <T> T getParamesToObject(Class<T> cls, HttpServletRequest request) {
		T t = null;//返回实体类对象
		
		try {
			t = cls.newInstance();//实例化对象
			
			//获取请求中的参数 及表单控件中 name 属性的属性值 或ajaxd定义的建
			Enumeration<String> names = request.getParameterNames();
			//获取传入类中的所有方法名
			Method[] methods = cls.getMethods();
			//存储set方法的map
			Map<String, Method> setters = new HashMap<String, Method>();
			
			String methodname = null;//方法名
			
			for(Method md : methods) {//循环所有的方法的方法名
				methodname = md.getName();//获取数组中方法的方法名
				
				if(methodname.startsWith("set")) {//筛选出set方法
					System.out.println(methodname);
					setters.put(methodname, md);
				}
			}
			
			String name = null;//表单元素name
			String value = null;//请求参数的具体的值
			Method method = null;//匹配的方法
			
			
			Class<?> [] types = null;//形参的参数列表
			Class<?> type = null;//单个参数的类型
			
			
			
			//循环枚举对象
			while(names.hasMoreElements()) {
				name = names.nextElement();//获取请求的参数  的  -》建
				methodname = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);//setXxx
				System.out.println(methodname);
				method = setters.getOrDefault(methodname, null);//根据方法名查询对应的方法 无则返回
				if(null == method) {//如果没有对应的方法 说明这个类中的属性不需要阻值 跳过
					continue;//
				}
				
				types = method.getParameterTypes();//有对应方法 则获取方法的新参的参数类型列表
				if(null == types || types.length <= 0) {//没有形参 无法住值
					continue;//
				}//
				
				type = types[0];//set方法默认只有一个新参 所有取第一个新参的类型
				value = request.getParameter(name);//获取请求中的形参
				
				//类型匹配开始住值
				if(Integer.TYPE == type) {//说明要整形值
					method.invoke(t, Integer.parseInt(value));//反向激活 对象  参数值
				}else if(Integer.class == type) {
					System.out.println("--------------------------");
					method.invoke(t, Integer.valueOf(value));
				}else if(Long.TYPE == type) {
					System.out.println("--------------------------");
					method.invoke(t, Long.parseLong(value));
				}else if(Long.class == type) {
					System.out.println("--------------------------");
					method.invoke(t, Long.valueOf(value));
				}else if(Short.TYPE == type) {
					System.out.println("--------------------------");
					method.invoke(t, Short.parseShort(value));
				}else if(Short.class == type) {
					System.out.println("--------------------------");
					method.invoke(t, Short.valueOf(value));
				}else if(Boolean.TYPE == type) {
					System.out.println("--------------------------");
					method.invoke(t, Boolean.parseBoolean(value));
				}else if(Boolean.class == type) {
					System.out.println("--------------------------");
					method.invoke(t, Boolean.valueOf(value));
				}else if(Float.TYPE == type) {
					System.out.println("--------------------------");
					method.invoke(t, Float.parseFloat(value));
				}else if(Float.class == type) {
					System.out.println("--------------------------");
					method.invoke(t, Float.valueOf(value));
				}else if(Double.TYPE == type) {
					System.out.println("--------------------------");
					method.invoke(t, Double.parseDouble(value));
				}else if(Double.class == type) {
					System.out.println("--------------------------");
					method.invoke(t, Double.valueOf(value));
				}else if(String.class == type) {
					System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
					method.invoke(t, value);
				}else if(Character.TYPE == type || Character.class == type) {//单个字符
					char [] cs = value.toCharArray();
					if(cs.length > 1) {
						throw new IllegalArgumentException("参数长度太大");
					}
					
					method.invoke(t, cs[0]);
				}
				
			}


		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("+++++++++++++++");
		System.out.println(t);
		
		return t;
	}

}
