package com.qianfeng.domain;

import java.util.List;

public class PageBook {
	private int totalPage;//总页数
	private int currentPage;//当前页数
	private int totalBooks;//总的书籍的数量
	private int pageSize;//当前页的数据数量
	private List<Book> books;//当前页的书籍信息
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
