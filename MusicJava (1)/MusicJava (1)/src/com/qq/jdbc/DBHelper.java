package com.qq.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class DBHelper {

	/**
	 * 步骤
	 * 1.导包-> 将对应数据库的驱动包拷贝到项目中， 然后再驱动包上右击 -》 Build Path -> Add to Build Path
	 * 2.加载驱动 -》 Class.forName("要加载的驱动全路径")
	 * 3.建立连接 -》 Connention con = DriverManager.getConnection(url, uaername, password)
	 * 4.创建语句块或预编译执行语句块
	 *     Statement stmt = con.createStatement();
	 *     PreparedStatement pstmt = con.prepareStatement();
	 * 5.给预编译执行语句块中的占位符? 赋值
	 * 6.执行语句块获得结果或结果集
	 * 7.处理结果或结果集
	 * 8.关闭资源 close();
	 */
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static {
		try {
			Class.forName(ReadPro.getInstance().getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConn() {
		try {
			con = DriverManager.getConnection(ReadPro.getInstance().getProperty("url"), ReadPro.getInstance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}


	public void close() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} 

	/**
	 * 给预编译语句块的?注入值
	 * @param pstmt
	 * @param params
	 */
	public void setParams(PreparedStatement pstmt, List<Object> params) {
		if(params == null || params == null || params.isEmpty()) {
			return;
		}
		for(int i = 0, len = params.size(); i < len; ++i) {
			try {
				pstmt.setObject(i + 1, params.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("第" + (i + 1) + "个参数注册失败");
			}
		}
	}

	public void setParams(PreparedStatement pstmt, Object ...params) {
		if(params == null || params.length <= 0) {
			return;
		}
		for(int i = 0, len = params.length; i < len; ++i) {
			try {
				pstmt.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("第" + (i + 1) + "个参数注册失败");
			}
		}
	}

	public int update(String sql, Object ...params) {
		int result = -1;//定义默认的范围值
		
		try {
			con = this.getConn();//获取连接
			pstmt = con.prepareStatement(sql);//预编译对象装载SQL
			this.setParams(pstmt, params);//需要给占位符注入参数
			result = pstmt.executeUpdate();//执行更新语句
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 多条件组合查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, List<Object> params) {
		if(params == null || params.isEmpty()) {

			return this.update(sql);
		}
		return this.update(sql, params.toArray()); //将集合转为数组
	}


	public int update(List<String> sqls, List<List<Object>> params) {
		int result = -1;
		try {
			con = this.getConn();
			con.setAutoCommit(false);
			for(int i = 0, len = sqls.size(); i < len; ++ i) {
				pstmt = con.prepareStatement(sqls.get(i));//预编译对象装载SQL-list
				this.setParams(pstmt, params.get(i).toArray());//需要给占位符注入参数
				result = pstmt.executeUpdate();//执行更新语句
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取总数的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int show(String sql, Object ...params) {
		int result = 0;

		try {
			Connection con = this.getConn(); // 连接数据库
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("++++++++++++++++++++++");
				System.out.println(rs.getObject(1));
				result = Integer.valueOf(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 创建预编译
		finally {
			this.close();
		}
		return result;
	}

	/**
	 * 多条件组合查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public int show(String sql, List<Object> params) {
		if(params == null || params.isEmpty()) {

			return this.show(sql);
		}
		return this.show(sql, params.toArray()); //将集合转为数组
	}


	/**
	 * 获取结果集中的列名
	 * @param rs 结果集
	 * @return 返回列名
	 * @throws SQLException
	 */
	private String[] getColumnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmt = rs.getMetaData(); //获取结果中的属性

		int columnCount = rsmt.getColumnCount(); // 获取列的长度

		String[] columnCounts = new String[columnCount];

		for(int i = 0; i < columnCount; ++ i) {
			columnCounts[i] = rsmt.getColumnName(i + 1).toLowerCase(); //赋值忽略大小写
		}
		return columnCounts;

	}



	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> finds(String sql, Object ...params){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try {
			Connection con = this.getConn(); // 连接数据库
			PreparedStatement pstmt = con.prepareStatement(sql); // 创建预编译
			this.setParams(pstmt, params); //设置参数
			ResultSet rs = pstmt.executeQuery(); // 执行sql语句
			Map<String, String> map = null;
			String[] cloumnCounts = this.getColumnNames(rs); //获取结果集中的列名
			while(rs.next()) {
				map = new HashMap<String, String>();
				for(String cloumnCount : cloumnCounts) { //循环将结果集中每一行的值赋值给map
					map.put(cloumnCount, rs.getString(cloumnCount));
				}
				list.add(map);// 将每一个MAP添加到list中

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return list;
	}

	/**
	 * 多条件组合查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> finds(String sql, List<Object> params) {
		if(params == null || params.isEmpty()) {

			return this.finds(sql);
		}
		return this.finds(sql, params.toArray()); //将集合转为数组
	}


	/**
	 * 一对象的形式
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> find(String sql, Object ...params){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			Connection con = this.getConn(); // 连接数据库
			PreparedStatement pstmt = con.prepareStatement(sql); // 创建预编译
			this.setParams(pstmt, params); //设置参数
			ResultSet rs = pstmt.executeQuery(); // 执行sql语句
			Map<String, Object> map = null;
			
			String[] cloumnCounts = this.getColumnNames(rs); //获取结果集中的列名
			Object obj = null; // 当前获取的这一列的值
			String columnType = null; // 这一列的类型
			Blob blob = null;
			byte[] bt = null; // 获取Blob类型列的值

			while(rs.next()) {
				map = new HashMap<String, Object>();
				for(String cloumnCount : cloumnCounts) { //循环将结果集中每一行的值赋值给map

					obj = rs.getObject(cloumnCount);
					if(obj == null) {
						map.put(cloumnCount, null);
						continue;
					}


					columnType = obj.getClass().getSimpleName();
					if("BLOB".contentEquals(columnType)) {
						blob = rs.getBlob(cloumnCount);
						bt = blob.getBytes(1, (int)blob.length());
						map.put(cloumnCount, bt);
					}else {
						map.put(cloumnCount, obj);
					}


				}
				list.add(map);// 将每一个MAP添加到list中
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return list;
	}

	/**
	 * 多条件组合查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> find(String sql, List<Object> params) {
		if(params == null || params.isEmpty()) {

			return this.find(sql);
		}
		return this.find(sql, params.toArray());  //将集合转为数组
	}

	/**
	 * 获取单条数据 一般市登录的时候
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, String> findSingle(String sql, Object ...params){
		Map<String, String> map = new HashMap<String, String>();

		try {
			Connection con = this.getConn(); // 连接数据库
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params); //设置参数
			ResultSet rs = pstmt.executeQuery(); // 执行sql语句
			String[] columnCounts = this.getColumnNames(rs);
			if(rs.next()) {
				for(String columnCount : columnCounts) {
					map.put(columnCount, rs.getString(columnCount));
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 创建预编译


		return map;		
	}

	/**
	 * 多条件组合查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, String> findSingle(String sql, List<Object> params) {
		if(params == null || params.isEmpty()) {

			return this.findSingle(sql);
		}
		return this.findSingle(sql, params.toArray());  //将集合转为数组
	}

	/**
	 * 获取单条数据 一般市登录的时候
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, Object> findSingles(String sql, Object ...params){
		Map<String, Object> map = null;

		try {
			Connection con = this.getConn(); // 连接数据库
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(sql);
			this.setParams(pstmt, params); //设置参数
			ResultSet rs = pstmt.executeQuery(); // 执行sql语句
			String[] columnCounts = this.getColumnNames(rs);

			Object obj = null; // 当前获取的这一列的值
			String columnType = null; // 这一列的类型
			Blob blob = null;
			byte[] bt = null; // 获取Blob类型列的值
			if(rs.next()) {
				map = new HashMap<String, Object>();
				for(String cloumnCount : columnCounts) { //循环将结果集中每一行的值赋值给map

					obj = rs.getObject(cloumnCount);
					if(obj == null) {
						map.put(cloumnCount, null);
						continue;
					}


					columnType = obj.getClass().getSimpleName();
					if("BLOB".contentEquals(columnType)) {
						blob = rs.getBlob(cloumnCount);
						bt = blob.getBytes(1, (int)blob.length());
						map.put(cloumnCount, bt);
					}else {
						map.put(cloumnCount, obj);
					}

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 创建预编译


		return map;		
	}

	/**
	 * 多条件组合查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, Object> findSingles(String sql, List<Object> params) {
		if(params == null || params.isEmpty()) {

			return this.findSingles(sql);
		}
		return this.findSingles(sql, params.toArray());  //将集合转为数组
	}

	public List<List<Map<String, Object>>> findMoreDate(List<String> sqls,List<List<Object>> params){
		List<List<Map<String, Object>>> lists = new ArrayList<List<Map<String,Object>>>();
			try {
				
				Connection con = this.getConn(); // 连接数据库
				con.setAutoCommit(false);
				for(int i = 0, len = sqls.size(); i < len;  i++) {
					
					PreparedStatement pstmt = con.prepareStatement(sqls.get(i)); // 创建预编译
					if(params != null && !params.isEmpty()) {
						this.setParams(pstmt,  params.get(i).toArray()); //设置参数
					}
					ResultSet rs = pstmt.executeQuery(); // 执行sql语句
					List<Map<String,Object>> list =  new ArrayList<Map<String,Object>>();
					Map<String, Object> map = null;
					
					String[] cloumnCounts = this.getColumnNames(rs); //获取结果集中的列名
					Object obj = null; // 当前获取的这一列的值
					String columnType = null; // 这一列的类型
					Blob blob = null;
					byte[] bt = null; // 获取Blob类型列的值

					while(rs.next()) {
						map = new HashMap<String, Object>();
						for(String cloumnCount : cloumnCounts) { //循环将结果集中每一行的值赋值给map

							obj = rs.getObject(cloumnCount);
							if(obj == null) {
								map.put(cloumnCount, null);
								continue;
							}
							columnType = obj.getClass().getSimpleName();
							if("BLOB".contentEquals(columnType)) {
								blob = rs.getBlob(cloumnCount);
								bt = blob.getBytes(1, (int)blob.length());
								map.put(cloumnCount, bt);
							}else {
								map.put(cloumnCount, obj);
							}
						}
						if(map != null) {
							list.add(map);// 将每一个MAP添加到list中
						}
					}
					
					if(list != null && !list.isEmpty()) {
						lists.add(i,list);
					}else {
						lists.add(i,null);
					}
				}
				
				con.commit();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					con.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println(lists);
		return lists;
	}
	
}
