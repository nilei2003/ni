package com.qq.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
	public static void main(String[] args) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		List<List<Map<String, Object>>> lists = new ArrayList<List<Map<String,Object>>>();
		Map<String,Object> map =new HashMap<String, Object>();;
		map.put("eid", "0呀");
		list.add(map);
		list1.add(map);
		
		lists.add(list1);
		lists.add(list);
		System.out.println(lists);
	}
}
