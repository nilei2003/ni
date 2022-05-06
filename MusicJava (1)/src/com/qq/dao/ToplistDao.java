package com.qq.dao;

import java.util.List;
import java.util.Map;

public interface ToplistDao {
	
	public List<Map<String,Object>> findComment();
	
	public int addZan(String uid,String did);
	
	public int addCZan(String uid,String rid);
	
	public List<Map<String,Object>> findReComment(String did);
	//查询总共的评论数量
	public int findPin();
	//查询评论回复
	public List<Map<String,Object>> findReback();
	
	public List<Map<String,Object>> findCountComent();
	
	//查询是否赞过
	public Map<String,Object> Checkgood(String uid,String did);
	//添加评论回复
	public int addRPin(String uid,String did,String text,String time);
	
	public List<Map<String,Object>> findRCpin();
	
	//查询mv
	public List<Map<String,Object>> findMV();
	
	public List<Map<String,Object>> findPinSin(String code);
	
	public List<List<Map<String,Object>>> inite();
}
