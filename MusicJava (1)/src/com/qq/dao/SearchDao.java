package com.qq.dao;

import java.util.List;
import java.util.Map;

public interface SearchDao {
	//搜索框模糊搜索
	public List<List<Map<String,Object>>>  search(String Sname);
	
	public List<List<Map<String,Object>>>  search_detail(String Sname);
}
