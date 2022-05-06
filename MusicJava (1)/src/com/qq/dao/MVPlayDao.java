package com.qq.dao;

import java.util.List;
import java.util.Map;

public interface MVPlayDao {
	/**
	 * 查询弹幕
	 * @return
	 */
	public List<Map<String,Object>> findBullet(String mvid);
	/**
	 * 以mvid添加弹幕
	 * @param text
	 * @param time
	 * @param ismove
	 * @param color
	 * @param mvid
	 * @return
	 */
	public int addBullet(String text,String time,String ismove,String color,String mvid);
}
