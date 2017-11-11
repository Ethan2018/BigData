package com.qianfeng.dao;

import java.sql.SQLException;

import com.qianfeng.domain.Order;
import com.qianfeng.service.impl.UserException;

public interface OrderItemDao {
	public void addOrderItem(Order order)throws SQLException;
}
