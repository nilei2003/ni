package com.qq.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.qq.dao.SearchDao;
import com.qq.jdbc.DBHelper;
import com.qq.jdbc.DBHelper1;

public class SearchDaoImpl implements SearchDao{
	DBHelper db = new DBHelper();
	@Override
	public List<List<Map<String, Object>>> search(String Sname) {
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();//存储参数
		
		List<Object> param1 = new ArrayList<Object>(); 
		List<Object> param2 = new ArrayList<Object>(); 
		List<Object> param3 = new ArrayList<Object>(); 
		List<Object> param4 = new ArrayList<Object>(); 
		String sql1 = "select m.* from music m,siger s where m.mname like concat('%',?,'%') and s.sid = m.sid limit 0,5";
		String sql2 = "select * from siger where sname like concat('%',?,'%') limit 0,2";
		String sql3 = "select * from sepcial where ename like concat('%',?,'%') limit 0,2 ";
		String sql4 = "select * from musicmv mv,music m where mv.mvname like concat('%',?,'%') and m.mid = mv.mid limit 0,2";
		
		Collections.addAll(param1, Sname);
		Collections.addAll(param2, Sname);
		Collections.addAll(param3, Sname);
		Collections.addAll(param4, Sname);

		Collections.addAll(params, param1,param2,param3,param4);

		Collections.addAll(sqls, sql1,sql2,sql3,sql4);
		
		return db.findMoreDate(sqls, params);
	}
	@Override
	public List<List<Map<String, Object>>> search_detail(String Sname) {
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();//存储参数
		
		List<Object> param1 = new ArrayList<Object>(); 
		List<Object> param2 = new ArrayList<Object>(); 
		List<Object> param3 = new ArrayList<Object>(); 
		List<Object> param4 = new ArrayList<Object>(); 
		List<Object> param5 = new ArrayList<Object>(); 
		
		String sql1 = "select m.*,s.sname,se.ename from music m inner join siger s on s.sid = m.sid left join sepcial se on se.eid = m.eid where m.mname like concat('%',?,'%')";
		String sql2 = "select se.*,s.sname  from sepcial se, music m,siger s where se.eid = m.eid and m.sid = s.sid and se.ename like concat('%',?,'%') group by se.eid";
		String sql3 = "select mv.*,s.sname from musicmv mv,music m,siger s where mv.mvname like concat('%',?,'%') and m.mid = mv.mid and m.sid = s.sid ";
		String sql4 = "select p.*,u.uname from playlist p,user u where pname like concat('%',?,'%') and u.uid = p.pid";
		String sql5 = "select s.*,count(p.sid) as count from siger s left join paysinger p on s.sid = p.sid where s.sname like concat('%',?,'%') group by p.sid";
		
		Collections.addAll(param1, Sname);
		Collections.addAll(param2, Sname);
		Collections.addAll(param3, Sname);
		Collections.addAll(param4, Sname);
		Collections.addAll(param5, Sname);

		Collections.addAll(params, param1,param2,param3,param4,param5);

		Collections.addAll(sqls, sql1,sql2,sql3,sql4,sql5);
		return db.findMoreDate(sqls, params);
	}

}
