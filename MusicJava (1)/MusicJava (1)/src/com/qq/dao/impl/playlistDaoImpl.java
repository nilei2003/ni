package com.qq.dao.impl;

import com.qq.bean.playlist;
import com.qq.dao.playlistDao;
import com.qq.jdbc.DBHelper;

public class playlistDaoImpl implements playlistDao {

	DBHelper db = new DBHelper();
	
	@Override
	public int addPlaylist(playlist play) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatedisplay(String pid) {
		// TODO Auto-generated method stub
		String sql = "update playlist set display = display + 1 where pid = ?";
		return db.update(sql, pid);
	}

}
