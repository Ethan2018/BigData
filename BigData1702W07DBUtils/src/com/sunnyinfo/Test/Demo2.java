package com.sunnyinfo.Test;



import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.sunnyinfo.javaBean.Users;
import com.sunnyinfo.util.C3P0Util;

public class Demo2 {
	//@Test
	//ʹ��ResultSetHandlerֱ�Ӵ����ݿ��ȡֵ
	//��ȡ���еļ�¼
	public void test() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getComboPooledDataSource());
		List<Users> list = qRunner.query("select * from users", new ResultSetHandler<List<Users>>(){
			//ResultSetHandler�����������ݵĵط�
			@Override
			public List<Users> handle(ResultSet set) throws SQLException {
				// TODO Auto-generated method stub
				List<Users> list = new ArrayList<Users>();
				Users user = null;
				while (set.next()) {
					user = new Users();
					user.setName(set.getString("name"));
					user.setPassword(set.getString("password"));
					list.add(user);
				}
				return list;
			}
			
		});
		System.out.println(list);
	}
	
	
	//ͨ��BeanListHandlerʵ�ֻ�ȡ���еļ�¼
	//@Test
	public void test2() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getComboPooledDataSource());
		List<Users> list = qRunner.query("select * from users", new BeanListHandler<Users>(Users.class));
		System.out.println(list);
	}
	@Test
	//����ѯ��������ͬ�����ʱ,Ĭ�ϻ᷵�ز鵽�ĵ�һ������
	public void test3() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getComboPooledDataSource());
		Users user = qRunner.query("select * from users where id=?", new BeanHandler<Users>(Users.class), 3);
		System.out.println(user);
	}
}
