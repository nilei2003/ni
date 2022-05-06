package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.Collect;

public interface CollectDao {
	
	//添加收藏
	public int add(String uid, String mid);
	
	// 查询音乐收藏列表
	public List<Map<String, String>> show(int page, int rows, String uid);
	
	// 查询音乐收藏总数
	public int tatol(String uid, String likecusss);
	
	public int delete(String uid, String lid);
	
	public Map<String, String> music(String mid, String uid);
	
	// 查询当前用户的音乐收藏表   
		//select * from siger, (select music.*, collect.cid from collect left outer join music on collect.mid = music.mid where uid = 103) s left join sepcial on s.eid = sepcial.eid where s.sid = siger.sid

		public List<Map<String, String>> findAll(String uid);

}
