package com.qianfeng.util;

import java.util.UUID;

public class UUIDUtil {
	//���Ի�ȡһ������ĳ��ַ���
	public  static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
