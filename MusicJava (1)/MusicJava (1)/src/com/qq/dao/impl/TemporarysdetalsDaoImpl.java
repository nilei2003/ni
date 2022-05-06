package com.qq.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.qq.bean.temporarysdetals;
import com.qq.biz.MusicBizImpl;
import com.qq.dao.MusicDao;
import com.qq.dao.TemporarysdetalsDao;
import com.qq.jdbc.DBHelper;

public class TemporarysdetalsDaoImpl implements TemporarysdetalsDao {

	DBHelper db = new DBHelper();

	//当前用户的临时播放表有这首歌吗 单曲情况
	@Override
	public Map<String, String> find(String uid, String mid) {
		// TODO Auto-generated method stub
		String sql = "select * from temporarys, temporarysdetals where temporarys.tid = temporarysdetals.tid and uid = ? and mid = ?";
		return db.findSingle(sql, uid, mid);
	}

	//当前用户有临时播放表吗
	@Override
	public Map<String, String> finds(String uid) {
		// TODO Auto-generated method stub
		String sql = "select * from temporarys where uid = ?";
		return db.findSingle(sql, uid);
	}

	// 如果当前用户没有临时播放表（第一次登录本网站）单曲情况
	@Override
	public int add(String mid, String uid) {


		List<String> sqls = new ArrayList<String>();//存储要执行的SQL语句

		List<List<Object>> params = new ArrayList<List<Object>>();//存储每条SQL语句的参数列表

		// TODO Auto-generated method stub
		String sql1 = "insert into temporarys values(?, ?)";

		String tid = new Date().getTime() + "" + new Random().nextInt(1000);// 时间戳 + 随机数   订单编号
		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, tid, uid);// 

		String sql2 = "insert into temporarysdetals values(0, ?, ?)";
		List<Object> param2 = new ArrayList<Object>();
		Collections.addAll(param2, tid, mid);// 

		Collections.addAll(sqls, sql1, sql2);// 
		Collections.addAll(params, param1, param2);// 

		return db.update(sqls, params);
	}


	// 若有临时播放表 没有该音乐
	@Override
	public int add02(String tid, String mid) {


		String sql = "insert into temporarysdetals values(0, ?, ?)";

		return db.update(sql, tid, mid);
	}

	@Override
	public List<Map<String, String>> show(String uid) {
		// TODO Auto-generated method stub
		String sql = "select * from temporarysdetals, siger, temporarys,(select music.*, musicmv.mvid from music left outer join musicmv on music.mid = musicmv.mid) s where s.sid = siger.sid and temporarysdetals.tid = temporarys.tid and s.mid = temporarysdetals.mid and temporarys.uid = ?";
		return db.finds(sql, uid);
	}
	
	// 情空列表
	public int deleteshow(String tid) {
		String sql = "delete from temporarysdetals where tid = ?";
		return db.update(sql, tid);
	}
	
	// 情空列表
	public int deletemusic(String tid, String mid) {
		String [] arrs = mid.split(",");
		String sql = "delete from temporarysdetals where tid = ? and mid in ( ";
		List<Object> param = new ArrayList<Object>();
		param.add(tid);
		for(String mids : arrs) {

			System.out.println(mids);
			sql += "?, ";
			param.add(mids);

		}
		sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
		return db.update(sql, param);
	}
	
	

	@Override
	public int delete(String tddid) {
		// TODO Auto-generated method stub
		String sql = "delete from temporarysdetals where tddid = ?";
		return db.update(sql, tddid);
	}

	// 如果用户有临时播放表， 且里面有数据
	@Override
	public int addshow(String tid,String eid, Integer sum) {
		// TODO Auto-generated method stub
		MusicDao biz = new MusicBizImpl();
		List<String> sqls = new ArrayList<String>();//存储要执行的SQL语句

		List<List<Object>> params = new ArrayList<List<Object>>();//存储每条SQL语句的参数列


		//清空原临时播放详情数据
		String sql1 = "delete from temporarysdetals where tid = ?";

		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, tid);// 

		int page = 1;

		int rows = 18;
		
		List<Map<String, String>> list = null;
		
		switch (sum) {
		case 1:
			// 获取音乐ID
			list = biz.showlists(page, rows);
			break;

		case 2:
			// 获取音乐ID 根据专辑id
			list = biz.showlists01(eid);
			break;

		case 3:
			// 获取音乐ID 根据专辑id
			list = biz.showlist(eid);
			break;

		default:
			break;
		}

		//添加临时播放详情表
		String sql2 = "insert into temporarysdetals select 0, ?, mid from music "
				+ "where mid in ( ";
		List<Object> param2 = new ArrayList<Object>();
		param2.add(tid);
		for(Map<String, String> map : list) {

			System.out.println(map);
			sql2 += "?, ";
			String mid = map.get("mid");


			param2.add(mid);

		}

		sql2 = sql2.substring(0, sql2.lastIndexOf(",")) + ")";
		System.out.println(sql2);
		Collections.addAll(sqls, sql1, sql2);// 
		Collections.addAll(params, param1, param2);// 

		if(db.update(sqls, params) > 0) {
			return 1;
		}
		return -1;
	}

	//无表
	@Override
	public int addUserAndMusic(String uid, String eid, Integer sum) {
		// TODO Auto-generated method stub

		MusicDao biz = new MusicBizImpl();

		List<String> sqls = new ArrayList<String>();//存储要执行的SQL语句

		List<List<Object>> params = new ArrayList<List<Object>>();//存储每条SQL语句的参数列


		// TODO Auto-generated method stub
		String sql1 = "insert into temporarys values(?, ?)";

		String tid = new Date().getTime() + "" + new Random().nextInt(1000);// 时间戳 + 随机数   订单编号

		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, tid, uid);// 

		int page = 1;

		int rows = 18;
		
		List<Map<String, String>> list = null;
		
		switch (sum) {
		case 1:
			// 获取音乐ID
			list = biz.showlists(page, rows);
			break;

		case 2:
			// 获取音乐ID 根据专辑id
			list = biz.showlists01(eid);
			break;
			
		case 3:
			// 获取音乐ID 根据专辑id
			list = biz.showlist(eid);
			break;


		default:
			break;
		}

		
		// 获取音乐ID
		

		//添加临时播放详情表
		String sql2 = "insert into temporarysdetals select 0, ?, mid from music "
				+ "where mid in ( ";
		List<Object> param2 = new ArrayList<Object>();
		param2.add(tid);
		for(Map<String, String> map : list) {

			System.out.println(map);

			String mid = map.get("mid");

			sql2 += "?, ";
			param2.add(mid);

		}

		sql2 = sql2.substring(0, sql2.lastIndexOf(",")) + ")";
		System.out.println(sql2);
		Collections.addAll(sqls, sql1, sql2);// 
		Collections.addAll(params, param1, param2);// 

		if(db.update(sqls, params) > 0) {
			return 1;
		}
		return -1;

	}

	// 有表无数据
	@Override
	public int addMusic(String tid,String eid, Integer sum) {
		// TODO Auto-generated method stub
		MusicDao biz = new MusicBizImpl();

		int page = 1;

		int rows = 18;
		
		List<Map<String, String>> list = null;
		
		switch (sum) {
		case 1:
			// 获取音乐ID
			list = biz.showlists(page, rows);
			break;

		case 2:
			// 获取音乐ID 根据专辑id
			list = biz.showlists01(eid);
			break;
		case 3:
			// 获取音乐ID 根据专辑id
			list = biz.showlist(eid);
			break;


		default:
			break;
		}

		//添加临时播放详情表
		String sql = "insert into temporarysdetals select 0, ?, mid from music "
				+ "where mid in ( ";
		List<Object> params = new ArrayList<Object>();
		params.add(tid);
		for(Map<String, String> map : list) {

			System.out.println(map);

			String mid = map.get("mid");

			sql += "?, ";
			params.add(mid);

		}

		sql = sql.substring(0, sql.lastIndexOf(",")) + ")";
		System.out.println(sql);
		return db.update(sql, params);
	}



	public List<Map<String, String>> fingall(String tid){
		String sql = "select * from temporarysdetals where tid = ?";
		return db.finds(sql, tid);
	}




}
