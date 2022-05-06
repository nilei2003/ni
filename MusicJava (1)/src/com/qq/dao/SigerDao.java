package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.Siger;

public interface SigerDao {
	
	//歌手添加
	public int add(String sname, String sphoto, String sbrief);
	//歌手查询 以名族
	public Map<String, String> show(String sname);
	
	//查询热门音乐
	public List<Map<String,Object>> findHot();
	
	//首页第一次进行查询
	public List<Siger> findSinger();
	
	
	//歌手详情界面第一次查询渲染
	public Map<String ,String> find(String sid);
	//关注
	public int addFans(String sid,String uid);
	//查询是否关注
	public Map<String,String> findFans(String uid,String sid);
	
	public int descFans(String sid,String uid);
	//查询关注的歌手信息
	public List<Map<String,Object>> findConSin(String uid);
	
	public List<Map<String,Object>> findMv(String sid);
	
	public List<List<Map<String,Object>>> findFirst(String sid);
}
