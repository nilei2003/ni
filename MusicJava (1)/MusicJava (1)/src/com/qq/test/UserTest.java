package com.qq.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.qq.jdbc.DBHelper1;

public class UserTest {

	public static void main(String[] args) {
		List<String> destList = Collections.synchronizedList(new ArrayList<>(Arrays.asList("foo")));

		List<String> newList = Arrays.asList("0", "1", "2", "3", "4", "5");

		newList.stream().sequential().collect(Collectors.toCollection(() -> destList));

		//newList + destList 的内容
		//结果 [foo, 0, 1, 2, 3, 4, 5]
		System.out.println(destList);

		System.out.println("==========================================");

		List<String> listOne = new ArrayList<String>();
		listOne.add("333");
		listOne.add("666");
		listOne.add("999");

		List<String> listTwo = new ArrayList<String>();
		listTwo.add("A");
		listTwo.add("B");
		listTwo.add("C");

		//addAll  添加另一集合里面的元素
		//结果[333, 666, 999, A, B, C]
		listOne.addAll(listTwo);
		System.out.println(listOne);


		//add 添加整个集合包括 []
		//结果 [A, B, C, [333, 666, 999, A, B, C]]
		listTwo.add(listOne.toString());
		System.out.println(listTwo);

	}
}
