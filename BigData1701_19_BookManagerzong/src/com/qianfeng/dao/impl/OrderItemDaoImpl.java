package com.qianfeng.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.qianfeng.dao.OrderItemDao;
import com.qianfeng.domain.Order;
import com.qianfeng.domain.OrderItem;
import com.qianfeng.util.C3P0Util;
import com.qianfeng.util.ManagerThreadLocal;

public class OrderItemDaoImpl implements OrderItemDao {

	@Override
	//��Ӷ���������
	public void addOrderItem(Order order) throws SQLException {
		QueryRunner qRunner = new QueryRunner();
		
		//��ȡ���������ж�����
		List<OrderItem> list = order.getList();
		
		//ƴװ��ά����
		Object[][] params = new Object[list.size()][];
		
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{order.getId(),list.get(i).getBook().getId(),list.get(i).getBuynum()};
		}
		
		qRunner.batch(ManagerThreadLocal.getConnection(),"insert into orderitem values(?,?,?)", params);

	}

}
