package com.qianfeng.dao;

import java.sql.SQLException;
import java.util.List;

import com.qianfeng.domain.Book;
import com.qianfeng.domain.Order;
import com.qianfeng.domain.SearchBook;

public interface BookDao {
	//�������е��鼮
	public List<Book> findAllBooks() throws SQLException;
	
	//�����ݿ��������
	public int addBook(Book book)throws SQLException;
	
	//��һ����
	public Book findOneBook(String id) throws SQLException;
	
	//����һ����
	public int updateBook(Book book) throws SQLException;
	
	//ɾ��һ����
	public int delBook(String id)throws SQLException;
	
	//ɾ���౾��
	public int[] delBooks(String[] ids)throws SQLException;
	
	//����������ѯ��
	public List<Book> searchBooks(SearchBook sBook)throws SQLException;
	
	//��ѯ���е��鼮����
	public int getTotalCount() throws SQLException;
	
	//��ѯ��ǰҳ���鼮����Ϣ
	public List<Book> findBooksByPage(int currentPage,int pageSize) throws SQLException;
	
	//���չؼ��ֲ�ѯ�鼮
	public List<Object> findBooksByName(String name)throws SQLException;
	
	//����id�����鼮
	public Book findBookById(String id)throws SQLException;

	//���ݶ�����������Ŀ��
	public void updateBookNum(Order order) throws SQLException;
}
