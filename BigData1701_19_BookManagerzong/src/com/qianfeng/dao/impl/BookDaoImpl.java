package com.qianfeng.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qianfeng.dao.BookDao;
import com.qianfeng.domain.Book;
import com.qianfeng.domain.Order;
import com.qianfeng.domain.OrderItem;
import com.qianfeng.domain.SearchBook;
import com.qianfeng.util.C3P0Util;
import com.qianfeng.util.ManagerThreadLocal;

public class BookDaoImpl implements BookDao {
	//查找全部的书籍
	
	public List<Book> findAllBooks() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.query("select * from books", new BeanListHandler<Book>(Book.class));
	}
	
	
	public int addBook(Book book) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		return qRunner.update("insert into books values(?,?,?,?,?,?,?)",book.getId(),book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription(),book.getImg_url());	
	}
	
	@Override
	public Book findOneBook(String id) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.query("select * from books where id=?", new BeanHandler<Book>(Book.class),id);
	}
	
	@Override
	public int updateBook(Book book) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.update("update books set name=?,price=?,pnum=?,category=?,description=? where id=?",book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription(),book.getId());
		
	}
	
	@Override
	public int delBook(String id) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.update("delete from books where id=?",id);
	}
	
	@Override
	public int[] delBooks(String[] ids) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		Object[][] params = new Object[ids.length][];
		
		for (int i = 0; i < params.length; i++) {
			
			params[i] = new Object[]{ids[i]};
		}
		
		return qRunner.batch("delete from books where id=?", params);
		
	}
	
	@Override
	public List<Book> searchBooks(SearchBook sBook) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		//默认写成1=1，目的：为了拼接sql的时候出现的语法错误
		String sql = "select * from books where 1=1 ";
		
		//用来存储存在的查询条件
		List<Object> list = new ArrayList<Object>();
		
		if (!"".equals(sBook.getId().trim())) {
			list.add("%"+sBook.getId().trim()+"%");
			sql+=" and id like ?";
		}
		
		if (!"".equals(sBook.getName().trim())) {
			list.add("%"+sBook.getName().trim()+"%");
			sql+=" and name like ?";
		}
		
		if (!"".equals(sBook.getCategory().trim())) {
			list.add(sBook.getCategory().trim());
			sql+=" and category=?";
		}
		
		if (!"".equals(sBook.getMinprice().trim())) {
			list.add(Integer.parseInt(sBook.getMinprice().trim()));
			sql+=" and price >?";
		}
		
		if (!"".equals(sBook.getMaxprice().trim())) {
			list.add(Integer.parseInt(sBook.getMaxprice().trim()));
			sql+=" and price <?";
		}
		
		//第三个参数接收的是数组，所以要将集合转化成数组
		return qRunner.query(sql, new BeanListHandler<Book>(Book.class),list.toArray());
		
	}
	
	@Override
	public int getTotalCount() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		long total = (Long)qRunner.query("select count(*) from books", new ScalarHandler(1));
		return (int)total;
	}
	
	@Override
	//注意:默认是从第一页开始
	public List<Book> findBooksByPage(int currentPage,int pageSize) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.query("select * from books limit ?,?", new BeanListHandler<Book>(Book.class),(currentPage-1)*pageSize,pageSize);
	}
	
	@Override
	public List<Object> findBooksByName(String name) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.query("select * from books where name like ?", new ColumnListHandler(2),"%"+name+"%");
	}

	@Override
	public Book findBookById(String id) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		return qRunner.query("select * from books where id=?", new BeanHandler<Book>(Book.class),id);
	
	}
	
	//根据订单的情况更改库存
	@Override
	public void updateBookNum(Order order) throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		
		//获取订单项数据
		List<OrderItem> list = order.getList();
		
		Object[][] params = new Object[list.size()][];
		
		for (int i = 0; i < params.length; i++) {
			
			params[i] = new Object[]{list.get(i).getBuynum(),list.get(i).getBook().getId()};
		}
		
		//进行批量更新
		qRunner.batch(ManagerThreadLocal.getConnection(),"update books set pnum=pnum-? where id=?", params);
		
	}
}
