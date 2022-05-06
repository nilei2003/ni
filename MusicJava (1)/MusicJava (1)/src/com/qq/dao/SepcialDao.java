package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.Sepcial;

public interface SepcialDao {
	
	public int add(Sepcial sepcial);
	
	public Map<String, String> find(String eid);
	public Map<String, String> count(String eid);
	public List<Map<String, String>> show();
	
	

}
