package com.qianfeng.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.qianfeng.dao.BookDao;
import com.qianfeng.dao.impl.BookDaoImpl;
import com.qianfeng.domain.Book;
import com.qianfeng.domain.PageBook;
import com.qianfeng.domain.SearchBook;
import com.qianfeng.service.BookService;

public class BookServiceImpl implements BookService {

	private static BookDao bDao = new BookDaoImpl();
	public List<Book> findAllBooks() {
		List<Book> list = null;
		try {
			list =  bDao.findAllBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public int AddBook(Book book) {
		int num = 0;
		try {
			num = bDao.addBook(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	@Override
	public Book fineBook(String id) {
		Book book = null;
		try {
			book = bDao.findOneBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
     @Override
    public int updateBook(Book book) {
    	int num = 0;
    	
    	try {
			num = bDao.updateBook(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return num;
    }
     
     @Override
    public int delBook(String id) {
    	int num = 0;
    	
    	try {
			num = bDao.delBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return num;
    }
     
     @Override
    public int[] delBooks(String[] ids) {
    	int[] num = null;
    	
    	try {
			num = bDao.delBooks(ids);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return num;
    }
     
     @Override
    public List<Book> searchBooks(SearchBook sBook) {
    	List<Book> list = null;
    	
    	try {
			list = bDao.searchBooks(sBook);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return list;
    }
     
     @Override
    public PageBook findBooksByPage(int currentPage, int pageSize) {
    	 PageBook pBook = null;
    	 try {
			
		
    	 //获取所有的书籍数量
    	 int total = bDao.getTotalCount();
    	
    	 //获取搜有的页数
    	 int totalPage =  (int)Math.ceil(total*1.0/pageSize);
    	 System.out.println("total="+total+"  "+"totalPage="+totalPage);
    	 //查找当前页所有书籍的信息
    	 List<Book> books = bDao.findBooksByPage(currentPage,pageSize);
    	 
    	 pBook =  new PageBook();
    	  
    	 pBook.setTotalBooks(total);
    	 pBook.setTotalPage(totalPage);
    	 pBook.setCurrentPage(currentPage);
    	 pBook.setBooks(books);
    	 pBook.setPageSize(pageSize);
    	 
    	 System.out.println("total="+total+" "+"pBook="+pBook);
    
    	 } catch (SQLException e) {
 			// TODO: handle exception
    		 e.printStackTrace();
 		}
    	 
    	 return pBook;
    }
     
     @Override
    public List<Object> findBooksByName(String name) {
    	List<Object> list = null;
    	
    	try {
			list = bDao.findBooksByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
     
     @Override
    public Book findBookById(String id) {
    	Book book = null;
    	
    	try {
			book = bDao.findBookById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return book;
    }

}
