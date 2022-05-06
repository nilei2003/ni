package com.qq.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * 封装  数据库操作的工具类
 * @author 38929
 * @date  2021年5月29日 下午4:43:06
 */
public class DBHelper1 {
	//相关对象
	private Connection conn = null;//连接对象
	private PreparedStatement pstmt = null;//预编译
	private ResultSet rs = null;//结果集
		
	//只在类中第一次加载才执行 且 执行一次 
	static {
		try {
			Class.forName(ReadPro.getInstance().getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//建立连接
	private Connection getConn() {
		//3、建立连接     url  uname  pwd
		//url 统一资源定位符   jdbc:oracle:thin:@数据库的IP地址:1521:实例名    127.0.0.1 | localhost:1521:ORCL 
		try {
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "a");
			conn = DriverManager.getConnection(ReadPro.getInstance().getProperty("url"), ReadPro.getInstance());
			//从数据库连接地获取一个空闲的连接-> 上下文获取DataSource
//			Context context = new InitialContext();
//
//			//从命名目录接口中根据资源名查询 括号里面类似于协议
//			DataSource dataSourse = (DataSource)context.lookup("java:comp/env/fresh");
//			//从数据库连接池获取一个空闲的连接
//		    conn = dataSourse.getConnection();
//			
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭资源
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	@SuppressWarnings("unused")
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		//7、关闭资源  先开启的后关闭  后开启的先关闭  结果集 -> 语句块 -> 连接
		if( rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if( pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if( conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 更新操作   Object ... params  不定参数   类似于数组  万物皆对象
	 * @param sql  要执行的更新语句   可以 insert update delete 
	 * @param params 要执行的SQL语句中的占位符？ 所对应参数的值 
	 * @return
	 */
	public int update(String sql, Object ... params) {
		int result = -1;//定义默认的范围值
		
		try {
			conn = this.getConn();//获取连接
			pstmt = conn.prepareStatement(sql);//预编译对象装载SQL
			this.setParams(pstmt, params);//需要给占位符注入参数
			result = pstmt.executeUpdate();//执行更新语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 给预编译语句块中占位符 赋值 设置参数
	 * @param pstmt   预编译对象
	 * @param params  要执行的SQL语句中的占位符？ 所对应参数的值 
	 */
	private void setParams(PreparedStatement pstmt, Object ... params) {
		//去空判断  说明没有参数给我   也就是执行的SQL语句中没有占位符
		if( null == params || params.length <= 0) {
			return;
		}
		//有参数 则循环参数  给预编译语句块中占位符 赋值
		//先获取参数的长度  节省资源 提高性能
		for(int i = 0, len = params.length; i < len; i++) {
			//我不清楚占位符 对应的详细数据类型    万物皆对象
			try {
				pstmt.setObject( i + 1, params[i]);// % +v+ %
			} catch (SQLException e) {
				e.printStackTrace();
				//项目可以使用日志来记录
				System.out.println("第" + (i+1) + "个参数注值失败...");
			}
		}
	}
	
	/**
	 * 单条查询 返回一条记录   select * from userinfo where user_name = ? and user_pwd = ?
	 * @param sql  查询SQL  
	 * @param params 查询参数
	 * @return map  一条记录
	 */
	public Map<String, Object> findSingle(String sql, Object ... params){
		Map<String, Object> map = null;
		try {
			conn = this.getConn();//获取连接
			pstmt = conn.prepareStatement(sql);//预编译对象装载SQL
			this.setParams(pstmt, params);//需要给占位符注入参数
			rs = pstmt.executeQuery();//执行更新语句
			
			//现获取所有的列名
			List<String> columnNames = this.getAllColumnNames(rs);
			if( rs.next()) { //处理结果集
				map = new HashMap<String, Object>();
				//map.put("user_id", rs.getInt("user_id"));
				//map.put("user_name", rs.getString("user_name"));
				//map.put("user_pwd", rs.getString("user_pwd"));
				//如果换了查询的表  还会有这些列吗？  select * from emp; 
				//如何查询表所对应各个列的名称？  请去API 先行查看 rs.getMetaData()
				
				Object value = null;//列所对应的值
				String type = "";//列所对应的值的类型
				
				//增强for
				for(String columnName : columnNames) {
					//列对应的类型不确定
					//map.put(columnName, rs.getObject(columnName));
					value = rs.getObject(columnName);
					//判空
					if( null == value) {
						map.put(columnName, value);
						continue;
					}
					type = value.getClass().getName(); //获取类型
					System.out.println(type); //oracle.sql.BLOB   java.math.BigDecimal
					//判断类型
					//TODO 如果是Blob类型 该怎么办 ?  图片的处理？？? 想办法获取对应的数据类型？
					if("oracle.sql.BLOB".equals(type)) {
						//获取对应类型数据
						Blob blob = rs.getBlob(columnName);
						//获取对应二进制流操作
						try(InputStream is = blob.getBinaryStream()){
							byte [] bt = new byte[ (int)blob.length()];
							is.read(bt);
							map.put(columnName, bt);//存入字节数组
						}catch(IOException e) {
							e.printStackTrace();
						}
					}else {
						map.put(columnName, value);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * JDBC2.0 获取所有的列名
	 * @param rs
	 * @return
	 */
	public List<String> getAllColumnNames(ResultSet rs){
		//存储列的集合
		List<String> list = new ArrayList<String>();
		try {
			//getMetaData() 获取此 ResultSet 对象的列的编号、类型和属性
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取列的数量
			int count = rsmd.getColumnCount();
			//列的范围确定 循环
			for(int i = 1; i <= count; i++){
				//获取对应列的列名
				list.add(rsmd.getColumnName(i).toLowerCase());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 多行查询 返回多条  select * from emp; 
	 * @param sql  查询SQL  
	 * @param params 查询参数
	 * @return list 
	 */
	public List<Map<String, Object>> findMultiple(String sql, Object ... params){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		try {
			conn = this.getConn();//获取连接
			pstmt = conn.prepareStatement(sql);//预编译对象装载SQL
			this.setParams(pstmt, params);//需要给占位符注入参数
			rs = pstmt.executeQuery();//执行更新语句
			
			
			
			//现获取所有的列名
			List<String> columnNames = this.getAllColumnNames(rs);
			
			while( rs.next()) { //处理结果集
				map = new HashMap<String, Object>();
				Object value = null;//列所对应的值
				String type = "";//列所对应的值的类型
				
				//增强for
				for(String columnName : columnNames) {
					//列对应的类型不确定
					//map.put(columnName, rs.getObject(columnName));
					value = rs.getObject(columnName);
					//判空
					if( null == value) {
						map.put(columnName, value);
						continue;
					}
					type = value.getClass().getName(); //获取类型
					System.out.println(type); //oracle.sql.BLOB   java.math.BigDecimal
					//判断类型
					//TODO 如果是Blob类型 该怎么办 ?  图片的处理？？? 想办法获取对应的数据类型？
					if("oracle.sql.BLOB".equals(type)) {
						//获取对应类型数据
						Blob blob = rs.getBlob(columnName);
						//获取对应二进制流操作
						try(InputStream is = blob.getBinaryStream()){
							byte [] bt = new byte[ (int)blob.length()];
							is.read(bt);
							map.put(columnName, bt);//存入字节数组
						}catch(IOException e) {
							e.printStackTrace();
						}
					}else {
						map.put(columnName, value);
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 以对象的方式返回数据
	 * @param <T>限定类型
	 * @param c 获取当前的calss实例中所有的属性和方法
	 * @param sql 查询sql
	 * @param params 注入的参数
	 * @return
	 */
	public <T>List<T> findMultiple(Class c, String sql, Object ...params){
		List<T> list = new ArrayList<T>();
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();

			List<String> columnName = this.getAllColumnNames(rs);

			T t = null;
			Object obj = null;
			String typeName = null;

			Method[] methods = c.getDeclaredMethods();



			while (rs.next()) {
				t = (T)c.newInstance();

				for(String columnNames : columnName) {
					obj = rs.getObject(columnNames);

					for(Method method : methods) {
						String name = "set" + columnNames;

						if(name.equalsIgnoreCase(method.getName())) {
							if(null == obj) {
								continue;
							}

							typeName = obj.getClass().getName();

							if("java.math.BigDecimal".equals(typeName)) {
								try {
									method.invoke(t, rs.getDouble(columnNames));
								} catch (Exception e) {
									// TODO Auto-generated catch block
									method.invoke(t, rs.getString(columnNames));
								} 
							}else if("java.lang.Integer".equals(typeName)) {
								method.invoke(t, rs.getInt(columnNames));

							}else if("java.lang.Double".equals(typeName)) {
								method.invoke(t, rs.getDouble(columnNames));
							}else if("java.lang.String".equals(typeName)) {
								method.invoke(t, rs.getString(columnNames));
							}else if("java.lang.Date".equals(typeName)) {
								method.invoke(t, rs.getString(columnNames));
							}else if("oracle.sql.CLOB".equals(typeName)) {
								Reader in = rs.getCharacterStream(columnNames);
								BufferedReader br = new BufferedReader(in);

								StringBuffer sb = new StringBuffer();

								try {
									String str = br.readLine();
									while(null != str) {
										sb.append(sb);
										str = br.readLine();
									}
									method.invoke(t, sb.toString());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}else {

							}
						}
					}
				}
				list.add(t);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeAll(rs, pstmt, conn);
		}
		
		System.out.println(list);


		return list;
	}
	
	public <T> T findSingle(Class c, String sql, Object...params) {
		T t = null;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();

			List<String> columnName = this.getAllColumnNames(rs);
			Object obj = null;
			String typeName = null;
			Method[] methods = c.getDeclaredMethods();
			while (rs.next()) {
				t = (T)c.newInstance();

				for(String columnNames : columnName) {
					obj = rs.getObject(columnNames);

					for(Method method : methods) {
						String name = "set" + columnNames;

						if(name.equalsIgnoreCase(method.getName())) {
							if(null == obj) {
								continue;
							}
							typeName = obj.getClass().getName();
							if("java.math.BigDecimal".equals(typeName)) {
								try {
									method.invoke(t, rs.getDouble(columnNames));
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
							}else if("java.lang.Integer".equals(typeName)) {
								method.invoke(t, rs.getInt(columnNames));

							}else if("java.lang.Double".equals(typeName)) {
								method.invoke(t, rs.getDouble(columnNames));
							}else if("java.lang.String".equals(typeName)) {
								method.invoke(t, rs.getString(columnNames));
							}else if("java.lang.Date".equals(typeName)) {
								method.invoke(t, rs.getString(columnNames));
							}else if("oracle.sql.CLOB".equals(typeName)) {
								Reader in = rs.getCharacterStream(columnNames);
								BufferedReader br = new BufferedReader(in);

								StringBuffer sb = new StringBuffer();

								try {
									String str = br.readLine();
									while(null != str) {
										sb.append(sb);
										str = br.readLine();
									}
									method.invoke(t, sb.toString());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}else {

							}
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.closeAll(rs, pstmt, conn);
		}


		return t;

	}
	/**
	 * 获取总记录的方法 为分页做准备
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getTotal(String sql,Object ... params) {
		int result = 0;
		try {
			conn = this.getConn();
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(rs, pstmt, conn);
		}
		return result;
	}
	
	
}
