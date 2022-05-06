package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.bean.MoneySepcial;
import com.qq.dao.MoneySepcialDao;
import com.qq.dao.impl.MoneySepcialDaoImpl;

public class MoneySepcialBizImpl implements MoneySepcialDao {

	MoneySepcialDao dao = new MoneySepcialDaoImpl();
	
	@Override
	public Map<String, String> show(MoneySepcial moneySepcial) {
		// TODO Auto-generated method stub
		return dao.show(moneySepcial);
	}

	@Override
	public String addMoneySepcial(String msid, String eid, String uid) {
		// TODO Auto-generated method stub
		return dao.addMoneySepcial(msid, eid, uid);
	}

	@Override
	public int UptatdMoneySepcial(String msid) {
		return dao.UptatdMoneySepcial(msid);
	}
	
	@Override
	public List<Map<String, String>> find(MoneySepcial moneySepcial) {
		// TODO Auto-generated method stub
		return dao.find(moneySepcial);
	}

}
