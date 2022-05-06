package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.Music;

public interface MusicDao {
	
	//音乐上传
	public int musicadd(Music music);
	
    // 根据歌名查询歌信息
	public Map<String, String> show(String mname);

	//查询歌曲
	public List<Map<String, Object>> findMusic(String sid);
	
	//排行榜
	public List<Map<String,Object>> findByMusic();
	
	public List<Map<String, String>> showlist(int page, int rows);
	
	public List<Map<String, String>> showlists(int page, int rows);
	
	public List<Map<String, String>> showlist(String pid);
	
	public List<Map<String, String>> showfind(String eid);
	
	public List<Map<String, String>> showlists01(String eid);
	
	public Map<String, String> showeid(String mid);
	
	// 音乐播放量
	public int updatemusiclplayvloume(String mid);
}
