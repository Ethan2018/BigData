package com.qianfeng.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qianfeng.dao.OrderDao;
import com.qianfeng.domain.Book;
import com.qianfeng.domain.Order;
import com.qianfeng.domain.OrderItem;
import com.qianfeng.util.C3P0Util;
import com.qianfeng.util.ManagerThreadLocal;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) throws SQLException {
		QueryRunner qRunner = new QueryRunner();

		qRunner.update(ManagerThreadLocal.getConnection(),"insert into orders values(?,?,?,?,?,?,?,?)",order.getId(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName(),
				order.getReceiverPhone(),order.getPaystate(),order.getOrdertime(),order.getUser().getId());
	}
	
	//��ѯ��ǰ�û������ж���
    @Override
    public List<Order> findOrders(int id) throws SQLException {
    	QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
    	
    	List<Order> list =  qRunner.query("select * from orders where user_id=?", new BeanListHandler<Order>(Order.class),id);
    	
    	return list;
    }
    
    //��ѯ��ǰ����������
    @Override
    public Order findOrderInfo(String orderid) throws SQLException {
    	System.out.println("OrderDaoImpl--findOrderInfo--begin");
    	QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
    	//��ѯ��������Ϣ
    	Order order = qRunner.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),orderid);
    	
    	//��ѯ������������Ϣ
    	//String sql = "select * from books where id in (select book_id from orderitem where order_id=?)";
    	
    	//���������������
    	String sql = "select * from books,orderitem where books.id=orderitem.book_id and orderitem.order_id=?";
        List<OrderItem>list = qRunner.query(sql, new ResultSetHandler<List<OrderItem>>(){
    		@Override
    		public List<OrderItem> handle(ResultSet rs) throws SQLException {
    			List<OrderItem> list = new ArrayList<OrderItem>();
    			while (rs.next()) {
					//�������������
    				OrderItem item = new OrderItem();
    				
    				item.setBuynum(rs.getInt("buynum"));
    				Book book = new Book();
    				book.setName(rs.getString("name"));
    				book.setPrice(rs.getInt("price"));
    				
    				//ͬʱ��ȡbook��id,ɾ��ʱʹ��
    				book.setId(rs.getString("id"));
    				
    				item.setBook(book);
    				
    				list.add(item);
    				
				}
    			
    			return list;
    		}
    	},orderid);
       
    	order.setList(list);
    	 System.out.println("OrderDaoImpl--findOrderInfo--end---order="+order);
    	return order;
    }
    
    //ɾ����ǰ�Ķ���
    @Override
    public void deleteOrderInfo(String orderid) throws SQLException {
    	QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
    	
    	
    	//�������
    	//ע��:��findOrderInfo������Ҫͬʱ��ȡbook��id
    	Order order = findOrderInfo(orderid);
    	List<OrderItem> list = order.getList();
    	
    	Object[][] params1 = new Object[list.size()][];
    	for (int i = 0; i < params1.length; i++) {
			params1[i] = new Object[]{list.get(i).getBuynum(),list.get(i).getBook().getId()};
		
			System.out.println("buynum="+params1[i][0]+"     "+"id="+params1[i][1] );
		
		}
    	
    	String sql1 = "update books set pnum=pnum+? where id =?";
    	
    	int[] num = qRunner.batch(sql1, params1);
    	
    	for (int i = 0; i < num.length; i++) {
    		System.out.println("num["+i+"]="+num[i] );
		}
    	
    	//ɾ��������
    	String sql2 = "delete from orderitem where  order_id=?";
    	qRunner.update(sql2,orderid);
    	
    	//ɾ������
    	String sql3 = "delete from orders where id=?";
    	qRunner.update(sql3,orderid);
    	
    	
    	
    }
    
    //�޸Ķ�����״̬
    @Override
	public void modifystate(String r6_Order) throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		qRunner.update("update orders set paystate=? where id=?",r6_Order);
		
	}

}
