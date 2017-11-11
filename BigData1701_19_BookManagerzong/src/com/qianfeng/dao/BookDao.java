package com.qianfeng.dao;

import java.sql.SQLException;
import java.util.List;

import com.qianfeng.domain.Book;
import com.qianfeng.domain.Order;
import com.qianfeng.domain.SearchBook;

public interface BookDao {
	//查找所有的书籍
	public List<Book> findAllBooks() throws SQLException;
	
	//往数据库添加数据
	public int addBook(Book book)throws SQLException;
	
	//找一本书
	public Book findOneBook(String id) throws SQLException;
	
	//更新一本书
	public int updateBook(Book book) throws SQLException;
	
	//删除一本书
	public int delBook(String id)throws SQLException;
	
	//删除多本书
	public int[] delBooks(String[] ids)throws SQLException;
	
	//根据条件查询书
	public List<Book> searchBooks(SearchBook sBook)throws SQLException;
	
	//查询搜有的书籍数量
	public int getTotalCount() throws SQLException;
	
	//查询当前页的书籍的信息
	public List<Book> findBooksByPage(int currentPage,int pageSize) throws SQLException;
	
	//按照关键字查询书籍
	public List<Object> findBooksByName(String name)throws SQLException;
	
	//按照id查找书籍
	public Book findBookById(String id)throws SQLException;

	//根据订单的情况更改库存
	public void updateBookNum(Order order) throws SQLException;
}
