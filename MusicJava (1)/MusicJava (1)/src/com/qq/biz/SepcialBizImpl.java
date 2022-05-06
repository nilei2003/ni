package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.bean.Sepcial;
import com.qq.dao.SepcialDao;
import com.qq.dao.impl.SepcialDaoImpl;
import com.qq.jdbc.DBHelper;

public class SepcialBizImpl implements SepcialDao {

	SepcialDao dao = new SepcialDaoImpl();
	
	@Override
	public int add(Sepcial sepcial) {
		// TODO Auto-generated method stub
		
		return dao.add(sepcial);
	}

	@Override
	public Map<String, String> find(String eid) {
		// TODO Auto-generated method stub
        
	    Map<String, String> map = dao.find(eid);
	    Map<String, String> count = dao.count(eid);
	    String count1 = count.get("count");
	    map.put("count", count1);
		return map;
	}

	@Override
	public List<Map<String, String>> show() {
		// TODO Auto-generated method stub
		
		
		return dao.show();
	}

	@Override
	public Map<String, String> count(String eid) {
		// TODO Auto-generated method stub
		return null;
	}

}
