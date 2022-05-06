package com.qq.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.qq.bean.Siger;
import com.qq.dao.SigerDao;
import com.qq.jdbc.DBHelper;
import com.qq.jdbc.DBHelper1;

public class SigerDaoImpl implements SigerDao {

	DBHelper db = new DBHelper();
	
	// 歌手添加
	@Override
	public int add(String sname, String sphoto, String sbrief) {
		//insert into admin values(0, ?, ?, ?, ?, null, 1)
		// TODO Auto-generated method stub
		System.out.println("+++++++++++");
		String sql = "insert into siger values(0, ?, ?, ?)";
		int result = db.update(sql, sphoto, sname, sbrief);
		return result;
	}

	//歌手查询 以名族
	@Override
	public Map<String, String> show(String sname) {
		// TODO Auto-generated method stub
		String sql = "select sid from siger where sname = ?";
		return db.findSingle(sql, sname);
	}

	@Override
	public List<Siger> findSinger() {
		DBHelper1 db1 = new DBHelper1();
		String sql = "select * from siger limit 0,10";
		return db1.findMultiple(Siger.class, sql);
	}

	@Override
	public Map<String, String> find(String sid) {
		String sql = "select s.*,count(m.mid) as c_song from siger s, music m where s.sid = ? and m.sid = s.sid";
		return db.findSingle(sql, sid);
	}

	@Override
	public int addFans(String sid, String uid) {
		String sql = "insert into paysinger value(0,?,?)";
		return db.update(sql, uid,sid);
	}
	//查询关注与否
	@Override
	public Map<String, String> findFans(String uid,String sid) {
		String sql = "select * from paysinger where uid = ? and sid = ?";
		return db.findSingle(sql, uid, sid);
	}

	@Override
	public int descFans(String sid, String uid) {
		String sql = "delete from paysinger where uid = ? and sid = ?";
		return db.update(sql, uid,sid);
	}

	@Override
	public List<Map<String, Object>> findHot() {
		String sql = "select m.playvolume,m.mname,@rownum:=@rownum+1 as num  from music m,(select @rownum := 0) t order by m.playvolume  desc limit 0,5";
		return db.find(sql);
	}

	@Override
	public List<Map<String, Object>> findConSin(String uid) {
		String sql = "select s.sphoto,s.sname,s.sid from paysinger p ,siger s where p.uid = ? and p.sid = s.sid limit 0,5";
		return db.find(sql, uid);
	}

	@Override
	public List<Map<String, Object>> findMv(String sid) {
		String sql = "select mv.* from musicmv mv, music m where m.sid = ? and m.mid = mv.mid";
		return db.find(sql, sid);
	}

	@Override
	public List<List<Map<String, Object>>> findFirst(String sid) {
		DBHelper db1 = new DBHelper();
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();//存储参数
		
		List<Object> param1 = new ArrayList<Object>(); 
		List<Object> param2 = new ArrayList<Object>(); 
		List<Object> param3 = new ArrayList<Object>(); 
		List<Object> param4 = new ArrayList<Object>(); 
		List<Object> param5 = new ArrayList<Object>(); 
		
		String sql1 = "select m.playvolume,m.mname,@rownum:=@rownum+1 as num  from music m,(select @rownum := 0) t order by m.playvolume  desc limit 0,5";
		String sql2 = "select @rownum:=@rownum+1 as num ,s.* from (select music.*, sepcial.ename from music left outer join sepcial on music.eid = sepcial.eid) s, (select @rownum := 0) t where s.sid = ?";
		String sql3 = "select s.*,count(m.mid) as c_song from siger s, music m where s.sid = ? and m.sid = s.sid";
		String sql4 = "select mv.* from musicmv mv, music m where m.sid = ? and m.mid = mv.mid";
		String sql5 = "select se.*, s.sid from music m inner join siger s on s.sid = m.sid right join sepcial se on se.eid = m.eid where s.sid = ?  GROUP BY se.eid";
		Collections.addAll(param2, sid);
		Collections.addAll(param3, sid);
		Collections.addAll(param4, sid);
		Collections.addAll(param5, sid);

		Collections.addAll(params, param1,param2,param3,param4,param5);
		Collections.addAll(sqls, sql1,sql2,sql3,sql4,sql5);
		
		return db.findMoreDate(sqls, params);
	}

}
