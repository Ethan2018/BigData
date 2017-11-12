package com.qianfeng.util;

import java.util.UUID;

public class UUIDUtil {
	//可以获取一个随机的长字符串
	public  static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
