package com.qq.biz;

import java.util.List;
import java.util.Map;

import com.qq.dao.LikesmusicDao;
import com.qq.dao.impl.LikesmusicDaoImpl;

public class LikesmusicBizImpl implements LikesmusicDao {

	LikesmusicDao dao = new LikesmusicDaoImpl();
	
	@Override
	public int addLikesmusic(String uid, String mid) {
		// TODO Auto-generated method stub
		
		return dao.addLikesmusic(uid, mid);
	}

	@Override
	public Map<String, String> showLikesmusic(String uid, String mid) {
		// TODO Auto-generated method stub
		return dao.showLikesmusic(uid, mid);
	}

	@Override
	public int delete(String uid, String mid) {
		// TODO Auto-generated method stub
		return dao.delete(uid, mid);
	}


}
