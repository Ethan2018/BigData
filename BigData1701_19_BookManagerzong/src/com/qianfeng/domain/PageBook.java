package com.qianfeng.domain;

import java.util.List;

public class PageBook {
	private int totalPage;//��ҳ��
	private int currentPage;//��ǰҳ��
	private int totalBooks;//�ܵ��鼮������
	private int pageSize;//��ǰҳ����������
	private List<Book> books;//��ǰҳ���鼮��Ϣ
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalBooks() {
		return totalBooks;
	}
	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "PageBook [totalPage=" + totalPage + ", currentPage=" + currentPage + ", totalBooks=" + totalBooks
				+ ", pageSize=" + pageSize + ", books=" + books + "]";
	}
	
	
}
