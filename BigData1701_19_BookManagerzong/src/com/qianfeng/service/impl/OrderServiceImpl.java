package com.qianfeng.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.qianfeng.dao.BookDao;
import com.qianfeng.dao.OrderDao;
import com.qianfeng.dao.OrderItemDao;
import com.qianfeng.dao.impl.BookDaoImpl;
import com.qianfeng.dao.impl.OrderDaoImpl;
import com.qianfeng.dao.impl.OrderItemDaoImpl;
import com.qianfeng.domain.Order;
import com.qianfeng.service.impl.UserException;
import com.qianfeng.service.OrderService;
import com.qianfeng.util.ManagerThreadLocal;

public class OrderServiceImpl implements OrderService {
	private BookDao bDao = new BookDaoImpl();
	private OrderDao oDao = new OrderDaoImpl();
	private OrderItemDao iDao = new OrderItemDaoImpl();

	@Override
	public void addOrder(Order order) throws UserException {
		
		try{
			ManagerThreadLocal.startTransaction();
			oDao.addOrder(order);
			iDao.addOrderItem(order);
			
			bDao.updateBookNum(order);
			ManagerThreadLocal.commitTransaction();
		}catch(SQLException e){
			e.printStackTrace();
			ManagerThreadLocal.rollbackTransaction();
			throw new UserException("添加订单失败");
		}
	
	}
	
	//查询当前用户的所有订单
	@Override
	public List<Order> findOrders(int id) throws UserException {
		List<Order> list = null;
		
		try {
			list = oDao.findOrders(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("订单查询失败");
		}
		
		return list;
	}

	@Override
	public Order findOrderInfo(String orderid) throws UserException {
		Order order = null;
		
		try {
			order = oDao.findOrderInfo(orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new UserException("订单查询失败");
		}
		
		return order;
	}
	
	@Override
	public void deleteOrderInfo(String orderid) throws UserException {
		
		try {
			oDao.deleteOrderInfo(orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new UserException();
		}
		
	}
	
	@Override
	public void modifyOrderState(String r6_Order) throws UserException {
		try {
			oDao.modifystate(r6_Order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException();
		}
		
	}
	
}

