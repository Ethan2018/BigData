package com.qianfeng.util;

import java.util.UUID;
//��ȡ�����һ�����ַ���
public class UUIDUtil {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.print(getUUID());
	}
}
