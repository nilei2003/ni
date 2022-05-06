package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.playlist;

public interface playlistdetalsDao {
	
	public int addPlaylistdetals(playlist play, String [] mids);
	
	public List<Map<String, String>> fingAll();
	
	public Map<String, String> show(String pid);
	
	public List<Map<String, String>> showAll(String pid);

}
