package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.dao.TemporarysdetalsDao;
import com.qq.dao.impl.TemporarysdetalsDaoImpl;

public class TemporarysdetalsBizImpl implements TemporarysdetalsDao {

	TemporarysdetalsDaoImpl dao = new TemporarysdetalsDaoImpl();
	
	@Override
	public Map<String, String> find(String uid, String mid) {
		// TODO Auto-generated method stub
		return dao.find(uid, mid);
	}

	@Override
	public Map<String, String> finds(String uid) {
		// TODO Auto-generated method stub
		return dao.finds(uid);
	}

	@Override
	public int add(String mid, String uid) {
		// TODO Auto-generated method stub
		return dao.add(mid, uid);
	}

	@Override
	public int add02(String tid, String mid) {
		// TODO Auto-generated method stub
		return dao.add02(tid, mid);
	}

	@Override
	public List<Map<String, String>> show(String uid) {
		// TODO Auto-generated method stub
		return dao.show(uid);
	}

	@Override
	public int delete(String tddid) {
		// TODO Auto-generated method stub
		return dao.delete(tddid);
	}

	@Override
	public int addshow(String tid, String eid, Integer sum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUserAndMusic(String uid, String eid, Integer sum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMusic(String tid, String eid, Integer sum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
