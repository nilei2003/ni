package com.qq.dao.impl;

import java.util.List;
import java.util.Map;

import com.qq.bean.Collect;
import com.qq.dao.CollectDao;
import com.qq.jdbc.DBHelper;

public class CollectDaoImpl implements CollectDao{

	DBHelper db = new DBHelper();
	
	//添加收藏
	@Override
	public int add(String uid, String mid) {
		// TODO Auto-generated method stub
		String sql = "insert into collect values(0, ?, 1, ?, null)";
		return db.update(sql, uid, mid);
	}
	// 查询音乐收藏列表
	@Override
	public List<Map<String, String>> show(int page, int rows, String uid) {
		// TODO Auto-generated method stub
		//select * from music, siger where music.sid = siger.sid order by mid desc limit ?,?
		String sql = "select * from collect where uid = ? and likecusss = 1 order by cid limit ?,?";
		return null;
	}
	// 查询音乐收藏总数
	@Override
	public int tatol(String uid, String likecusss) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from collect where uid = ? and likecusss = ?";
		return db.show(sql, uid, likecusss);
	}
	
	
	
	@Override
	public int delete(String uid, String lid) {
		// TODO Auto-generated method stub
		String sql = "delete from collect and cid = ?";
		return db.update(sql, lid);
	}
	@Override
	public Map<String, String> music(String mid, String uid) {
		// TODO Auto-generated method stub
		String sql = "select * from collect where mid = ? and uid = ?";
		return db.findSingle(sql, mid, uid);
	}
	@Override
	public List<Map<String, String>> findAll(String uid) {
		// TODO Auto-generated method stub
		String sql = "select * from siger, (select music.*, collect.cid from collect left outer join music on collect.mid = music.mid where uid = ?) s left join sepcial on s.eid = sepcial.eid where s.sid = siger.sid";
		return db.finds(sql, uid);
	}
	
	

}
