package com.qianfeng.dao;

import java.sql.SQLException;
import java.util.List;

import com.qianfeng.domain.Order;

public interface OrderDao {
	public void addOrder(Order order)throws SQLException;

	public List<Order> findOrders(int id) throws SQLException;

	public Order findOrderInfo(String orderid)throws SQLException;

	public void deleteOrderInfo(String orderid)throws SQLException;

	public void modifystate(String r6_Order)throws SQLException;
}
