package com.qq.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.qq.bean.MoneySepcial;
import com.qq.dao.MoneySepcialDao;
import com.qq.jdbc.DBHelper;

public class MoneySepcialDaoImpl implements MoneySepcialDao {

	DBHelper db = new DBHelper();
	
	@Override
	public Map<String, String> show(MoneySepcial moneySepcial) {
		// TODO Auto-generated method stub
		String sql = "select * from moneysepcial where eid = ? and uid = ?";
		return db.findSingle(sql, moneySepcial.getEid(), moneySepcial.getUid());
	}

	@Override
	public String addMoneySepcial(String msid, String eid, String uid) {
		// TODO Auto-generated method stub
		
		String sql = "insert into moneysepcial values(?, ?, ?, 1)";
		int  result = db.update(sql, msid, eid, uid);
		if(result > 0) {
			return "1";
		}
		return "-1";
	}
	@Override
	public int UptatdMoneySepcial(String msid) {
		String sql = "update moneysepcial set statis = 1 where msid = ?";
		return db.update(sql, msid);
	}

	@Override
	public List<Map<String, String>> find(MoneySepcial moneySepcial) {
		// TODO Auto-generated method stub
		String sql = "select * from moneysepcial, sepcial where moneysepcial.eid = sepcial.eid and moneysepcial.uid = ?";
		return db.finds(sql, moneySepcial.getUid());
	}

}
