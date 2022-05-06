package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.bean.playlist;
import com.qq.dao.playlistdetalsDao;
import com.qq.dao.impl.playlistdetalsDaoImpl;

public class playlistdetalsBiaImpl implements playlistdetalsDao {

	playlistdetalsDao dao = new playlistdetalsDaoImpl();
	
	@Override
	public int addPlaylistdetals(playlist play, String[] mids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, String>> fingAll() {
		// TODO Auto-generated method stub
		return dao.fingAll();
	}

	@Override
	public Map<String, String> show(String pid) {
		// TODO Auto-generated method stub
		return dao.show(pid);
	}

	@Override
	public List<Map<String, String>> showAll(String pid) {
		// TODO Auto-generated method stub
		return dao.showAll(pid);
	}

}
