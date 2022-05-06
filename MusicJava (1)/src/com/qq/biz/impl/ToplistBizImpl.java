package com.qq.biz.impl;

import java.util.List;
import java.util.Map;

import com.qq.dao.ToplistDao;
import com.qq.dao.impl.ToplistDaoImpl;
import com.qq.util.StringUtils;

public class ToplistBizImpl implements ToplistDao{
	ToplistDao dao =new ToplistDaoImpl(); 
	//查询评论
	@Override
	public List<Map<String, Object>> findComment() {
		return dao.findComment();
	}
	
	//点赞
	@Override
	public int addZan(String uid, String did) {
		if(StringUtils.pach(uid,did)) {
			return 0;
		}

		return dao.addZan(uid, did);
	}


	@Override
	public List<Map<String, Object>> findReComment(String did) {
		if(StringUtils.pach(did)) {
			return null;
		}
		return dao.findReComment(did);
	}

	@Override
	public int findPin() {
		
		return dao.findPin();
	}

	@Override
	public List<Map<String,Object>> findReback() {
		return dao.findReback();
	}

	@Override
	public List<Map<String, Object>> findCountComent() {
		return dao.findCountComent();
	}

	@Override
	public Map<String, Object> Checkgood(String uid, String did) {
		if(StringUtils.pach(uid,did)) {
			return null;
		}

		return dao.Checkgood(uid, did);
	}

	@Override
	public int addRPin(String uid, String did, String text, String time) {
		
		return dao.addRPin(uid, did, text, time);
	}

	@Override
	public List<Map<String, Object>> findRCpin() {
		
		return dao.findRCpin();
	}

	@Override
	public int addCZan(String uid, String rid) {
		if(StringUtils.pach(uid,rid)) {
			return 0;
		}

		return dao.addZan(uid, rid);
	}

	@Override
	public List<Map<String, Object>> findMV() {
		return dao.findMV();
	}

	@Override
	public List<Map<String, Object>> findPinSin(String code) {
		if(StringUtils.pach(code)) {
			return null;
		}
		return dao.findPinSin(code);
	}

	@Override
	public List<List<Map<String, Object>>> inite() {
		return dao.inite();
	}
		
}
