package com.qq.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.qq.dao.ToplistDao;
import com.qq.jdbc.DBHelper;
import com.qq.jdbc.DBHelper1;

public class ToplistDaoImpl implements ToplistDao{
	
	DBHelper1 db = new DBHelper1();
	//查找评论
	@Override
	public List<Map<String, Object>> findComment() {
		String sql = "select d.did,d.uid,date_format( d.ddate,'%Y-%m-%d %H:%i:%S') as date,d.dtext,d.discusss, u.* from discuss d, user u where d.discusss = 5 and d.uid = u.uid";
		return db.findMultiple(sql);
	}
	//点赞
	@Override
	public int addZan(String uid, String did) {
		String sql = "insert into detail_dis values(0,?,?)";
		return db.update(sql, uid,did);
	}
	
	@Override
	public List<Map<String, Object>> findReComment(String did) {
		String sql = "select u.*,rd.* ,date_format( rd.ddate,'%Y-%m-%d %H:%i:%S') as date,count(dd.dd_id) as count from r_discuss rd  right join user u on rd.uid = u.uid left join detail_dis dd on dd.did = rd.rid where rd.did = ? group by rd.rid;";
		return db.findMultiple(sql, did);
	}
	
	@Override
	public int findPin() {
		String sql = "select count(did) from discuss where discusss = 5";
		return db.getTotal(sql);
	}
	
	//查询回复数量
	@Override
	public List<Map<String,Object>> findReback() {
		String sql = "select count(rid) as count,did from r_discuss group by did ";
		return db.findMultiple(sql);
	}
	
	/**
	 * 查询点赞数量
	 */
	@Override
	public List<Map<String, Object>> findCountComent() {
		String sql = "select count(ds.did) as count,d.* from discuss d left join detail_dis ds on ds.did = d.did group by did";
		return db.findMultiple(sql);
	}
	/**
	 * 查询用户对评论是否赞过
	 */
	@Override
	public Map<String, Object> Checkgood(String uid,String did) {
		String sql = "select * from detail_dis where uid = ? and did = ?";
		return db.findSingle(sql, uid,did);
	}
	
	@Override
	public int addRPin(String uid, String did, String text, String time) {
		String sql = "insert into r_discuss values(0,?,?,?,?)";
		return db.update(sql, uid,time,text,did);
	}
	
	@Override
	public List<Map<String, Object>> findRCpin() {
		String sql = "select u.uname, rd.dtext,rd.did,rd.rid ,count(dd.dd_id) as count from r_discuss rd right join discuss d on rd.did = d.did right join user u on rd.uid = u.uid left join detail_dis dd on dd.did = rd.rid group by rd.did";
		return db.findMultiple(sql);
	}
	@Override
	public int addCZan(String uid, String rid) {
		String sql = "insert into detail_dis values(0,?,?)";
		return db.update(sql, uid,rid);
	}
	@Override
	public List<Map<String, Object>> findMV() {
		String sql = "select * from musicmv";
		return db.findMultiple(sql);
	}
	@Override
	public List<Map<String, Object>> findPinSin(String code) {
		String sql = "select * from siger s where fristPinyin(s.sname) = ?";
		return db.findMultiple(sql, code);
	}
	@Override
	public List<List<Map<String, Object>>> inite() {
		DBHelper db1 = new DBHelper();
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();//存储参数
		
		String sql1 = "select temp.*, @rownum:=@rownum+1 as num from (select s.*, siger.sname from (select music.*, musicmv.mvid from music left outer join musicmv on music.mid = musicmv.mid) s, siger where s.sid = siger.sid order by s.playvolume desc limit 0,20) temp ,(select @rownum := 0) t";
		String sql2 = "select count(did) as count from discuss where discusss = 5";
		String sql3 = "select count(ds.did) as count,d.* from discuss d left join detail_dis ds on ds.did = d.did group by did";
		String sql4 = "select u.uname, rd.dtext,rd.did,rd.rid ,count(dd.dd_id) as count from r_discuss rd right join discuss d on rd.did = d.did right join user u on rd.uid = u.uid left join detail_dis dd on dd.did = rd.rid group by rd.did";
		String sql5 = "select m.playvolume,m.mname,@rownum:=@rownum+1 as num  from music m,(select @rownum := 0) t order by m.playvolume  desc limit 0,5";

		Collections.addAll(sqls, sql1,sql2,sql3,sql4,sql5);
		System.out.println(db1.findMoreDate(sqls,params));
		return db1.findMoreDate(sqls,params);
	}
	
}
