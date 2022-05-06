package com.qq.dao.impl;

import java.util.List;
import java.util.Map;

import com.qq.dao.LikesmusicDao;
import com.qq.jdbc.DBHelper;

public class LikesmusicDaoImpl implements LikesmusicDao {

	DBHelper db = new DBHelper();
	
	@Override
	public int addLikesmusic(String uid, String mid) {
		// TODO Auto-generated method stub
		String sql = "insert into likesmusic values(0, ?, ?)";
		return db.update(sql, uid, mid);
	}

	@Override
	public Map<String, String> showLikesmusic(String uid, String mid) {
		// TODO Auto-generated method stub
		String sql = "select * from likesmusic where uid = ? and mid = ?";
		return db.findSingle(sql, uid, mid);
	}

	@Override
	public int delete(String uid, String mid) {
		// TODO Auto-generated method stub
		String sql = "delete from likesmusic where uid = ? and mid = ?";
		return db.update(sql, uid, mid);
	}


}
