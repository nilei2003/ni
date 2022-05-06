package com.qq.dao.impl;

import java.util.Map;

import com.qq.bean.User;
import com.qq.dao.UserDao;
import com.qq.jdbc.DBHelper;
import com.qq.jdbc.DBHelper1;

public class UserDaoImpl implements UserDao{

	//登录
	@Override
	public User login(String uname, String upwd) {
		DBHelper1 db = new DBHelper1();
		String sql = "select * from user where uname = ? and upwd = ?";
		return db.findSingle(User.class, sql, uname,upwd);
	}
	//注册
	@Override
	public int add(String uname, String pwd, String email) {
		DBHelper1 db = new DBHelper1();
		String sql = "insert into user values(0,null,?,?,?,0)";
		return db.update(sql, uname,pwd,email);
	}
	//添加评论
	@Override
	public int addComment(String uid, String date, String text, int discuss, int mid, int mvid, String eid, String pid,
			int sid) {
		DBHelper1 db = new DBHelper1();
		String sql = "insert into discuss values(0,?,?,?,?,?,?,?,?,?)";
		return db.update(sql, uid,date,text,discuss,mid,mvid,eid,pid,sid);
	}
	
	@Override
	public int updateuphoto(String uphoto, String uid) {
		DBHelper db = new DBHelper(); 
		String sql ="update user set uphoto = ? where uid = ?";
		return db.update(sql, uphoto, uid);
	}
	
	public User show(int uid){
		DBHelper1 db = new DBHelper1();
		String sql = "select * from user where uid = ?";
		return db.findSingle(User.class, sql, uid);
	}
	@Override
	public User getUser(String uid) {
		DBHelper1 db = new DBHelper1();
		String sql = "select * from user where uid = ?";
		return db.findSingle(User.class, sql, uid);
	}
}
