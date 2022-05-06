package com.qq.biz;

import com.qq.bean.playlist;
import com.qq.dao.playlistDao;
import com.qq.dao.impl.playlistDaoImpl;

public class playlistBizImpl implements playlistDao {

	playlistDao dao = new playlistDaoImpl();
	
	@Override
	public int addPlaylist(playlist play) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatedisplay(String pid) {
		// TODO Auto-generated method stub
		return dao.updatedisplay(pid);
	}

}
