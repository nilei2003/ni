package com.qq.dao.impl;

import java.util.List;
import java.util.Map;

import com.qq.bean.Music;
import com.qq.dao.MusicDao;
import com.qq.jdbc.DBHelper;
import com.qq.jdbc.DBHelper1;

public class MusicDaoImpl implements MusicDao{

	DBHelper db = new DBHelper();
	//音乐上传
	@Override
	public int musicadd(Music music) {
		// TODO Auto-generated method stub
		String sql = "insert into music values(0, ?, ?, ?, ?, ?, 0, 0, 0, null, ?, ?, 0)";
		return db.update(sql, music.getMusics(), music.getMphoto(), music.getLyrlce(),
				            music.getMname(), music.getSid(), music.getMdate(), music.getMbj());
	}
	
	
	@Override
	public Map<String, String> show(String mname) {
		String sql = "select mid from music where mname = ?";
		return db.findSingle(sql, mname);
	}
	@Override
	public List<Map<String, String>> showlist(int page, int rows) {
		// TODO Auto-generated method stub
		String sql = "select * from music, siger where yesnopay = 0 and music.sid = siger.sid order by mid desc limit ?,?";
		return db.finds(sql, ( page - 1 ) * rows, rows);
	}

	@Override
	public List<Map<String, String>> showlists(int page, int rows) {
		// TODO Auto-generated method stub
		String sql = "select mid from music where yesnopay = 0 order by mid desc limit ?,?";
		return db.finds(sql, ( page - 1 ) * rows, rows);
	}
	@Override
	public List<Map<String, String>> showlists01(String eid) {
		// TODO Auto-generated method stub
		String sql = "select mid from music where eid = ? order by mid desc";
		return db.finds(sql, eid);
	}

	
	// 以歌单查询

	@Override
	public List<Map<String, String>> showlist(String pid) {
		// TODO Auto-generated method stub
		String sql = "select music.mid from music, playlistdetals where music.mid = playlistdetals.mid and playlistdetals.pid = ?";
		return db.finds(sql, pid);
	}



	@Override
	public List<Map<String, String>> showfind(String eid) {
		// TODO Auto-generated method stub
		String sql = "select * from (select music.*, musicmv.mvid from music left outer join musicmv on music.mid = musicmv.mid) s, siger where s.sid = siger.sid and s.eid = ? order by mid desc";
		return db.finds(sql, eid);
	}


	@Override
	public Map<String, String> showeid(String mid) {
		// TODO Auto-generated method stub
		String sql = "select eid from music where mid = ?";

		return db.findSingle(sql, mid);

	}
	
	
	public Map<String, String> showeids(String mid) {
		// TODO Auto-generated method stub
		String sql = "select * from music where mid = ?";

		return db.findSingle(sql, mid);

	}

	@Override
	public List<Map<String,Object>> findMusic(String sid) {
		DBHelper1 db1 = new DBHelper1();
		String sql = "select @rownum:=@rownum+1 as num ,m.* from music m,(select @rownum := 0) t where m.sid = ?";
		return db1.findMultiple(sql, sid);
	}

	//查找排名
	@Override
	public List<Map<String, Object>> findByMusic() {
		
		String sql = "select temp.*, @rownum:=@rownum+1 as num from (select s.*, siger.sname from (select music.*, musicmv.mvid from music left outer join musicmv on music.mid = musicmv.mid) s, siger where s.sid = siger.sid order by s.playvolume desc limit 0,20) temp ,(select @rownum := 0) t";
		return db.find(sql);
	}
	// 更改播放量
	@Override
	public int updatemusiclplayvloume(String mid) {
		// TODO Auto-generated method stub
		String sql = "update music set playvolume = playvolume + 1 where mid = ?";
		return db.update(sql, mid);
	}
}
