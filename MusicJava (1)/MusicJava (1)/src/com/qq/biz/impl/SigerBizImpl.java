package com.qq.biz.impl;

import java.util.List;
import java.util.Map;

import com.qq.bean.Siger;
import com.qq.dao.SigerDao;
import com.qq.dao.impl.SigerDaoImpl;
import com.qq.util.StringUtils;

public class SigerBizImpl implements SigerDao {

	SigerDao dao = new SigerDaoImpl();
	//歌手添加
	@Override
	public int add(String sname, String sphoto, String sbrief) {
		// TODO Auto-generated method stub
		
		if(StringUtils.pach(sname, sphoto, sbrief)) {
			return 4;
		}
		
		int result = dao.add(sname, sphoto, sbrief);
		
		return result;
	}

	//歌手查询 以名族
	@Override
	public Map<String, String> show(String sname) {
		// TODO Auto-generated method stub
		if(StringUtils.pach(sname)) {
			return null;
		}
		Map<String, String> map = dao.show(sname);
		return map;
	}

	@Override
	public List<Siger> findSinger() {
		
		return dao.findSinger();
	}

	@Override
	public Map<String, String> find(String sid) {
		if(StringUtils.pach(sid)) {
			return null;
		}
		return dao.find(sid);
	}

	@Override
	public int addFans(String sid, String uid) {
		if(StringUtils.pach(sid,uid)) {
			return 0;
		}
		return dao.addFans(sid, uid);
	}

	@Override
	public Map<String, String> findFans(String uid,String sid) {
		if(StringUtils.pach(uid,sid)) {
			return null;
		}
		return dao.findFans(uid,sid);
	}

	@Override
	public int descFans(String sid, String uid) {
		if(StringUtils.pach(uid,sid)) {
			return 0;
		}
		return dao.descFans(sid, uid);
	}

	@Override
	public List<Map<String, Object>> findHot() {
		return dao.findHot();
	}

	@Override
	public List<Map<String, Object>> findConSin(String uid) {
		if(StringUtils.pach(uid)) {
			return null;
		}
		return dao.findConSin(uid);
	}

	@Override
	public List<Map<String, Object>> findMv(String sid) {
		if(StringUtils.pach(sid)) {
			return null;
		}
		return dao.findMv(sid);
	}

	@Override
	public List<List<Map<String, Object>>> findFirst(String sid) {
		if(StringUtils.pach(sid)) {
			return null;
		}
		return dao.findFirst(sid);
	}

}
