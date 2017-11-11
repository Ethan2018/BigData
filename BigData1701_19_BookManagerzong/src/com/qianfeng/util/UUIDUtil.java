package com.qianfeng.util;

import java.util.UUID;
//获取随机的一个长字符串
public class UUIDUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.print(getUUID());
	}
}
