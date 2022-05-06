package com.qq.dao.impl;

import java.util.List;
import java.util.Map;

import com.qq.bean.Sepcial;
import com.qq.dao.SepcialDao;
import com.qq.jdbc.DBHelper;

public class SepcialDaoImpl implements SepcialDao {

	DBHelper db = new DBHelper();
	
	@Override
	public int add(Sepcial sepcial) {
		// TODO Auto-generated method stub
		String sql = "insert into sepcial values(?, ?, ?, ?, ?)";
		return db.update(sql, sepcial.getEid(), sepcial.getEphoto(), sepcial.getEname(), sepcial.getEbrief(), sepcial.getEmoney());
	}

	@Override
	public Map<String, String> find(String eid) {
		// TODO Auto-generated method stub
//		
		String sql = "select sepcial.*,siger.sid,siger.sname from sepcial,siger, music where sepcial.eid = ? and sepcial.eid = music.eid and music.sid = siger.sid GROUP BY sepcial.ename";
		return db.findSingle(sql, eid);
	}
	@Override
	public Map<String, String> count(String eid) {
		// TODO Auto-generated method stub
//		
		String sql = "select count(*) as count from moneysepcial where eid = ?";
		return db.findSingle(sql, eid);
	}
	
	public Map<String, String> finds(String ename){
		
		String sql = "select eid from sepcial where ename = ?";
		return db.findSingle(sql, ename);
		
	}

	@Override
	public List<Map<String, String>> show() {
		// TODO Auto-generated method stub
		String sql = "select sepcial.*,siger.sid,siger.sname from sepcial,siger, music WHERE sepcial.eid = music.eid and music.sid = siger.sid GROUP BY sepcial.ename";
		
		return db.finds(sql);
	}
	

	public List<Map<String, String>> findAll(String uid){
		String sql = "select * from sepcial, moneysepcial where sepcial.eid = moneysepcial.eid and moneysepcial.uid = ?";
		return db.finds(sql, uid);
	}

}
