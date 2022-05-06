  package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.bean.Collect;
import com.qq.dao.CollectDao;
import com.qq.dao.impl.CollectDaoImpl;

public class CollectBizImpl implements CollectDao {

	CollectDao dao = new CollectDaoImpl();
	
	@Override
	public int add(String uid, String mid) {
		// TODO Auto-generated method stub
		
		return dao.add(uid, mid);
	}

	@Override
	public List<Map<String, String>> show(int page, int rows, String uid) {
		// TODO Auto-generated method stub
		return dao.show(page, rows, uid);
	}

	@Override
	public int tatol(String uid, String likecusss) {
		// TODO Auto-generated method stub
		return dao.tatol(uid, likecusss);
	}

	@Override
	public int delete(String uid, String lid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String> music(String mid, String uid) {
		// TODO Auto-generated method stub
		return dao.music(mid, uid);
	}

	@Override
	public List<Map<String, String>> findAll(String uid) {
		// TODO Auto-generated method stub
		return dao.findAll(uid);
	}

}
