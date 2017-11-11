package com.qianfeng.service;

import java.util.List;

import com.qianfeng.domain.Book;
import com.qianfeng.domain.PageBook;
import com.qianfeng.domain.SearchBook;

public interface BookService {
	//查找所有的书籍
	public List<Book> findAllBooks();
	
	//添加数据到数据库
	public int AddBook(Book book);
	
	//查找当前的一本书
	public Book fineBook(String id);
	
	//更新一本书
	public int updateBook(Book book);
	
	//删除一本书
	public int delBook(String id) ;
	
	//删除多本书
	public int[] delBooks(String[] ids);
	
	//按照条件查找书
	public List<Book> searchBooks(SearchBook sBook);
	
	
	//查询当页搜有的信息
	public PageBook  findBooksByPage(int currentPage,int pageSize);
	
	//按照关键字查询数据
	public List<Object> findBooksByName(String name);
	
	//按照id查找一本书
	public Book findBookById(String id);
}
