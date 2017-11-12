package com.sunnyinfo.Utils;


import java.util.HashMap;
import java.util.Map;

import com.sunnyinfo.Mould.Book;

public class DBUtil {
	static private Map<String, Book> booksMap = new HashMap<String, Book>();
	static {
		booksMap.put("01", new Book("01", "��¥��", 40, "��ѩ��"));
		booksMap.put("02", new Book("02", "ˮ䰴�", 40, "ʩ����"));
		booksMap.put("03", new Book("03", "����", 40, "�޹���"));
		booksMap.put("04", new Book("04", "���μ�", 40, "��ж�"));
	}

	public static Map<String, Book> getAllBooks() {
		return booksMap;
	}

	public static Book searchBook(String id) {
		return booksMap.get(id);
	}
}
