package com.qq.dao;

import java.util.Map;

import com.qq.bean.User;

public interface UserDao {
	
	public User login(String uname,String upwd);
	
	public int add(String uname,String pwd,String email);
	
	public int addComment(String uid,String date,String text,int discuss,int mid,int mvid,String eid,String pid,int sid);
	
	public int updateuphoto(String uphoto,String uid);
	
	public User getUser(String uid);
}
