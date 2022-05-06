package com.qq.biz;

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
		if(StringUtils.pach(sname, sphoto, sbrief)) {
			return 4;
		}
		
		int result = dao.add(sname, sphoto, sbrief);
		
		return result;
	}

	//歌手查询 以名族
	@Override
	public Map<String, String> show(String sname) {
		if(StringUtils.pach(sname)) {
			return null;
		}
		Map<String, String> map = dao.show(sname);
		return map;
	}

	@Override
	public List<Map<String, Object>> findHot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Siger> findSinger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> find(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addFans(String sid, String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String> findFans(String uid, String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int descFans(String sid, String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> findConSin(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findMv(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Map<String, Object>>> findFirst(String sid) {
		
		return null;
	}

}
