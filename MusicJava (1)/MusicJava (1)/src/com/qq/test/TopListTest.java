package com.qq.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.qq.biz.MVPlayBiz;
import com.qq.biz.impl.MVPlayBizImpl;
import com.qq.biz.impl.SearchBizImpl;
import com.qq.biz.impl.ToplistBizImpl;
import com.qq.dao.SearchDao;
import com.qq.dao.ToplistDao;

public class TopListTest {
	@Test
	public void testAdd() {
		ToplistDao biz = new ToplistBizImpl(); 
		System.out.println(biz.addRPin("102", "1001", "<img src=\"http://localhost:8080/Music/image/smile/embarrassed_smile.png\" style='height:20px;'>", "2022-04-07 16:39:59"));
	}
	@Test
	public void testfind() {
		SearchDao biz = new SearchBizImpl();
		System.out.println(biz.search("风"));
	}
	

}
