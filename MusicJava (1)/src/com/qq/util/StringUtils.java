package com.qq.util;

public class StringUtils {
	//判空操作
		public static boolean pach(String ...strs) {
			if(null == strs || strs.length <= 0) {
				return true;
			}

			for(String str : strs) {
				if(null == str || "".equals(str)) {
					return true;
				}
			}


			return false;
		}



}
