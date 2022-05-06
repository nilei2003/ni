package com.qq.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.qq.bean.playlist;
import com.qq.dao.playlistdetalsDao;
import com.qq.jdbc.DBHelper;

public class playlistdetalsDaoImpl implements playlistdetalsDao {

	DBHelper db = new DBHelper();
	
	@Override
	public int addPlaylistdetals(playlist play, String[] mids) {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();//存储要执行的SQL语句

		List<List<Object>> params = new ArrayList<List<Object>>();//存储每条SQL语句的参数列

		String sql1 = "insert into playlist values(?, ?, ?, ?, 0)";
		
		List<Object> param1 = new ArrayList<Object>();
		
		Collections.addAll(param1, play.getPid(), play.getUid(), play.getPname(), play.getPphoto());//
		
		String sql2 = "insert into playlistdetals select 0, ?, mid from music "
				+ "where mid in ( ";
		List<Object> param2 = new ArrayList<Object>();
		param2.add(play.getPid());
	
		for(String mid : mids) {
			System.out.println(mid);
			sql2 += "?, ";
			param2.add(mid);
		}
		
		sql2 = sql2.substring(0, sql2.lastIndexOf(",")) + ")";
		
		System.out.println(sql2);
		Collections.addAll(sqls, sql1, sql2);// 
		Collections.addAll(params, param1, param2);
		if(db.update(sqls, params) > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public List<Map<String, String>> fingAll() {
		// TODO Auto-generated method stub
		String sql = "select * from playlist";
		return db.finds(sql);
	}

	@Override
	public Map<String, String> show(String pid) {
		// TODO Auto-generated method stub
		String sql = "select * from playlist where pid = ?";
		return db.findSingle(sql, pid);
		
	}

	@Override
	public List<Map<String, String>> showAll(String pid) {
		// TODO Auto-generated method stub
		String sql = "select * from playlist, playlistdetals, siger, (select music.*, musicmv.mvid from music left outer join musicmv on music.mid = musicmv.mid) s where siger.sid = s.sid and playlist.pid = playlistdetals.pid and playlistdetals.mid = s.mid and playlist.pid = ?";
		return db.finds(sql, pid);
	}

}
