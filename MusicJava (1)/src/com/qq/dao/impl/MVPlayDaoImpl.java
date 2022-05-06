package com.qq.dao.impl;

import java.util.List;
import java.util.Map;

import com.qq.dao.MVPlayDao;
import com.qq.jdbc.DBHelper1;

public class MVPlayDaoImpl implements MVPlayDao{
	DBHelper1 db = new DBHelper1();
	@Override
	public List<Map<String, Object>> findBullet(String mvid) {
		String sql = "select * from bullet where mvid = ?";
		return db.findMultiple(sql,mvid);
	}
	
	
	@Override
	public int addBullet(String text, String time, String ismove, String color, String mvid) {
		String sql = "insert into bullet values(0,?,?,?,?,?)";
		return db.update(sql, text,time,ismove,color,mvid);
	}
	
}
