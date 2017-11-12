package com.sunnyinfo.Utils;


import java.util.HashMap;
import java.util.Map;

import com.sunnyinfo.Mould.Book;

public class DBUtil {
	static private Map<String, Book> booksMap = new HashMap<String, Book>();
	static {
		booksMap.put("01", new Book("01", "红楼梦", 40, "曹雪芹"));
		booksMap.put("02", new Book("02", "水浒传", 40, "施耐庵"));
		booksMap.put("03", new Book("03", "三国", 40, "罗贯中"));
		booksMap.put("04", new Book("04", "西游记", 40, "吴承恩"));
	}

	public static Map<String, Book> getAllBooks() {
		return booksMap;
	}

	public static Book searchBook(String id) {
		return booksMap.get(id);
	}
}
