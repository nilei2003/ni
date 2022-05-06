package com.qq.biz.impl;

import java.util.List;
import java.util.Map;

import com.qq.dao.SearchDao;
import com.qq.dao.impl.SearchDaoImpl;
import com.qq.util.StringUtils;

public class SearchBizImpl implements SearchDao{
	SearchDao dao =new SearchDaoImpl();
	@Override
	public List<List<Map<String, Object>>> search(String Sname) {
		if(StringUtils.pach(Sname)) {
			return null;
		}
		return dao.search(Sname);
	}
	@Override
	public List<List<Map<String, Object>>> search_detail(String Sname) {
		if(StringUtils.pach(Sname)) {
			return null;
		}
		return dao.search_detail(Sname);
	}
	
}
