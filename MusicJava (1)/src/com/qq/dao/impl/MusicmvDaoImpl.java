package com.qq.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.qq.bean.Musicmv;
import com.qq.dao.MusicmvDao;
import com.qq.jdbc.DBHelper;

public class MusicmvDaoImpl implements MusicmvDao {
	
	DBHelper db = new DBHelper();
	
	

	// 上上传MV
	@Override
	public int musicmvAdd(Musicmv musicmv, String mname) {
		
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();//存储要执行的SQL语句

		List<List<Object>> params = new ArrayList<List<Object>>();//存储每条SQL语句的参数列表

		
		
		String sql1 = "insert into musicmv values(0, ?, ?, ?, 0, ?, ?)";
		
		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, musicmv.getMvs(), musicmv.getMvname(), musicmv.getMvphoto(),
				            musicmv.getMid(), musicmv.getMvbj());// 
		
		String sql2 = "update music set statusmv = 1 where mname = ?";
		List<Object> param2 = new ArrayList<Object>();
		Collections.addAll(param2, mname);// 
		
		
		Collections.addAll(sqls, sql1, sql2);// 
		Collections.addAll(params, param1, param2);// 
		return db.update(sqls, params);
	}



	// 查询MV
	@Override
	public List<Map<String, String>> showlist(int page, int rows) {
		// TODO Auto-generated method stub
		
		String sql = "select * from musicmv order by mvid desc limit ?,?";
		return db.finds(sql, ( page - 1 ) * rows, rows);
	}



	@Override
	public Map<String, String> show(String mvid) {
		// TODO Auto-generated method stub
		String sql = "select * from musicmv where mvid = ?";
		return db.findSingle(sql, mvid);

	}


	
}
