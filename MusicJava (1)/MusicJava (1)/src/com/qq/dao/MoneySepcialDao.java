package com.qq.dao;

import java.util.List;
import java.util.Map;

import com.qq.bean.MoneySepcial;

public interface MoneySepcialDao {
	
	// 根据用户id 专辑id 查询购买表 判断当前用户有没有购买当前专辑
	public Map<String, String> show(MoneySepcial moneySepcial);
	
	// 添加购买
	public String addMoneySepcial(String msid, String eid, String uid);
	
    // 查询当前用户购买的所有专辑
	public List<Map<String, String>> find(MoneySepcial moneySepcial);
	
	public int UptatdMoneySepcial(String msid);

}
