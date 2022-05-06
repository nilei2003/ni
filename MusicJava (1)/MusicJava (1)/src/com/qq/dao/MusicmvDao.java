package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.Musicmv;

public interface MusicmvDao {
	
	
	// 上上传MV
	public int musicmvAdd(Musicmv musicmv, String mname);
	
	public List<Map<String, String>> showlist(int page, int rows);
	
	public Map<String, String> show(String mvid);

}
