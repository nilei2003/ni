package com.qq.biz.impl;

import java.util.List;
import java.util.Map;

import com.qq.biz.MVPlayBiz;
import com.qq.dao.MVPlayDao;
import com.qq.dao.impl.MVPlayDaoImpl;
import com.qq.util.StringUtils;

public class MVPlayBizImpl implements MVPlayBiz{
	MVPlayDao dao = new MVPlayDaoImpl();
	
	@Override
	public List<Map<String, Object>> findBullet(String mvid) {
		return dao.findBullet(mvid);
	}
	
	@Override
	public int addBullet(String text, String time, String ismove, String color, String mvid) {
		if(StringUtils.pach(time,ismove)) {
			return 0;
		}
		
		return dao.addBullet(text, time, ismove, color, mvid);
	}

}
