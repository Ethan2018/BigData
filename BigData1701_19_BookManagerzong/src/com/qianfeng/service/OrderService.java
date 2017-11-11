package com.qianfeng.service;

import java.util.List;

import com.qianfeng.domain.Order;
import com.qianfeng.service.impl.UserException;

public interface OrderService {
	public void addOrder(Order order) throws UserException;

	public List<Order> findOrders(int id) throws UserException;

	public Order findOrderInfo(String orderid) throws UserException;

	public void deleteOrderInfo(String orderid) throws UserException;

	public void modifyOrderState(String r6_Order)throws UserException;
}
