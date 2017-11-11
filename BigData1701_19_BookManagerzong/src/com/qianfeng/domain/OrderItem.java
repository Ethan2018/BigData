package com.qianfeng.domain;
import com.qianfeng.domain.Book;
import com.qianfeng.domain.Order;

public class OrderItem {
	private Order order;
	private Book book;
	private int buynum;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	@Override
	public String toString() {
		return "OrderItem [order=" + order + ", book=" + book + ", buynum=" + buynum + "]";
	}
	
	
}
