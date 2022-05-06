package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.temporarys;
import com.qq.bean.temporarysdetals;

public interface TemporarysdetalsDao {
	
	public Map<String, String> find(String uid, String mid);
	
	public Map<String, String> finds(String uid);
	
	public List<Map<String, String>> show(String uid);
	
	public int add(String mid, String uid);
	public int add02(String tid, String mid);
	
	public int delete(String tddid);
	
	// 有标有数据
	public int addshow(String tid, String eid, Integer sum);
	
	//无表
	public int addUserAndMusic(String uid, String eid, Integer sum);
	
	// 有表无数据
	public int addMusic(String tid, String eid, Integer sum);

}
