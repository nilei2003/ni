package com.qq.biz.impl;

import java.util.List;
import java.util.Map;

import com.qq.bean.Music;
import com.qq.dao.MusicDao;
import com.qq.dao.impl.MusicDaoImpl;
import com.qq.util.StringUtils;

public class MusicBizImpl implements MusicDao{

	MusicDao dao = new MusicDaoImpl();
	
	//音乐上传
	@Override
	public int musicadd(Music music) {
		
		// TODO Auto-generated method stub
		
		if(StringUtils.pach(music.getMusics(), music.getMphoto(), music.getLyrlce(), music.getMname(), music.getMdate(), music.getMbj())){
			return 4;
		}
		
		int result =  dao.musicadd(music);
		return result;
	}

	@Override
	public Map<String, String> show(String mname) {
		// TODO Auto-generated method stub
		return dao.show(mname);
	}

	@Override
	public List<Map<String, String>> showlist(int page, int rows) {
		// TODO Auto-generated method stub
		return dao.showlist(page, rows);
	}

	@Override
	public List<Map<String, String>> showlist(String pid) {
		// TODO Auto-generated method stub
		return dao.showlist(pid);
	}

	@Override
	public List<Map<String, String>> showfind(String eid) {
		// TODO Auto-generated method stub
		return dao.showfind(eid);
	}

	@Override
	public List<Map<String, String>> showlists(int page, int rows) {
		// TODO Auto-generated method stub
		return dao.showlists(page, rows);
	}

	@Override
	public Map<String, String> showeid(String mid) {
		// TODO Auto-generated method stub
		return dao.showeid(mid);
	}
	
	@Override
	public List<Map<String,Object>> findMusic(String sid) {
		if(StringUtils.pach(sid)) {
			return null;
		}
		
		return dao.findMusic(sid);
	}
	//排行榜
	@Override
	public List<Map<String, Object>> findByMusic() {
		
		return dao.findByMusic();
	}

	@Override
	public List<Map<String, String>> showlists01(String eid) {
		// TODO Auto-generated method stub
		return dao.showlists01(eid);
	}

	@Override
	public int updatemusiclplayvloume(String mid) {
		// TODO Auto-generated method stub
		return dao.updatemusiclplayvloume(mid);
	}

}
