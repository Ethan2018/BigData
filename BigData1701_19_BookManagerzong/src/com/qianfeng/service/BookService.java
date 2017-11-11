package com.qianfeng.service;

import java.util.List;

import com.qianfeng.domain.Book;
import com.qianfeng.domain.PageBook;
import com.qianfeng.domain.SearchBook;

public interface BookService {
	//�������е��鼮
	public List<Book> findAllBooks();
	
	//������ݵ����ݿ�
	public int AddBook(Book book);
	
	//���ҵ�ǰ��һ����
	public Book fineBook(String id);
	
	//����һ����
	public int updateBook(Book book);
	
	//ɾ��һ����
	public int delBook(String id) ;
	
	//ɾ���౾��
	public int[] delBooks(String[] ids);
	
	//��������������
	public List<Book> searchBooks(SearchBook sBook);
	
	
	//��ѯ��ҳ���е���Ϣ
	public PageBook  findBooksByPage(int currentPage,int pageSize);
	
	//���չؼ��ֲ�ѯ����
	public List<Object> findBooksByName(String name);
	
	//����id����һ����
	public Book findBookById(String id);
}
