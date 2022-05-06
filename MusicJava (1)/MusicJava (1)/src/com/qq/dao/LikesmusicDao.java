package com.qq.dao;

import java.util.List;
import java.util.Map;

public interface LikesmusicDao {
	
	// 为喜欢的音乐点赞
	public int addLikesmusic(String uid, String mid);
	
	// 查询当前登录用户有没有美当前播放的音乐点赞
	public Map<String, String> showLikesmusic(String uid, String mid);
	
	//取消点赞删除数据
	public int delete(String uid, String mid);
	
	

}
