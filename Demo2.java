package com.qianfeng.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.qianfeng.javaBean.User;
import com.qianfeng.util.C3P0Util;

public class Demo2 {
	//@Test   
	//ʹ��ResultSetHandlerֱ�Ӵ����ݿ��ȡֵ
	//��ȡ���еļ�¼
	public void test1() throws SQLException{
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		
		List<User> list = qRunner.query("select * from user ", new ResultSetHandler<List<User>>() {

			//������ResultsetHandler�����������ݵĵط�
			@Override
			public List<User> handle(ResultSet set) throws SQLException {
				List<User> list = new ArrayList<User>();
				User user = null;
				
				while (set.next()) {
					user = new User();
					user.setName(set.getString("name"));
					user.setPwd(set.getInt("pwd"));
					
					list.add(user);
				}
				
				return list;
			}
		});
		
		System.out.println(list);
	}
	
	//ͨ��BeanListHandlerʵ�ֻ�ȡ���еļ�¼--�����ݷ���ģ��,�ٷ���list
	//@Test
	public void test2() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
																//������list��Ԫ�ض�Ӧ����,����������ֽ����ļ�
		List<User> list = qRunner.query("select * from user ", new BeanListHandler<User>(User.class));
		System.out.println(list);
	}
	
	//ͨ��BeanHandler��ȡһ������,�����ݷ���ģ��
	//ע��:����ѯ��������ͬ�����ʱ,Ĭ�ϻ᷵�ز鵽�ĵ�һ������
	@Test
	public void test3() throws SQLException {
		QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
		User user = qRunner.query("select * from user where birthday=?", new BeanHandler<User>(User.class),"1990-12-23");
		
		System.out.println(user);
	}
	
	//ȡһ����¼
			//ArrayHandler:ֻ�ܷ���һ����¼,����û�д洢��javabean��,Ĭ�Ϸ��ص��ǵ�һ����¼
			//@Test
			public void test13() throws SQLException{
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				//
				Object[] objects = qRunner.query("select * from user", new ArrayHandler());
				
				for (Object object : objects) {
					System.out.println(object);
				}
			}
			
			
			//ȡ������¼
			//ArrayListHandler:���Է��ز鵽�Ķ��м�¼,��һ����¼��ÿ���ֶδ洢��һ��������,�ٽ���Щ�������һ��������,������
			//@Test
			public void test4() throws SQLException{
				
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				
				List<Object[]> list = qRunner.query("select * from user", new ArrayListHandler());
				
				for (Object[] objects : list) {
					for (Object object : objects) {
						System.out.print(object+"  ");
					}
					System.out.println();
				}
				
			}
			
			//ColumnListHandler:����ָ��������,ȡĳһ�е�����,��װ��list�з���
			//@Test
			public void test5() throws SQLException{
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				//��������˼��:ָ����ѯ���к�---��1��ʼ����
				List<Object> list = qRunner.query("select * from user", new ColumnListHandler(2));
				
				for (Object object : list) {
					System.out.print(object+"  ");
				}
			}
			
			//KeyedHandler:����ȡ������¼,ÿһ����¼����װ����map��,Ȼ���ٽ����е�map�������һ�����map��,��ÿ����¼�е�ĳ���ֶγ䵱���map��key
			//ע��:����䵱key���ֶ����ظ�,����ļ�¼�Ὣǰ�����еļ�¼����
			//@Test
			public void test6() throws SQLException{
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				
				Map<Object, Map<String, Object>> map=qRunner.query("select * from user", new KeyedHandler(5));
				
				for(Map.Entry<Object, Map<String, Object>> en:map.entrySet()){
					Object key = en.getKey();
					Map<String, Object> subMap = en.getValue();
					
					for(Map.Entry<String, Object> suben:subMap.entrySet()){
						String subkey = suben.getKey();
						Object value = suben.getValue();
						
						System.out.print(subkey+"="+value+"   ");
					}
					System.out.println("  key="+key);
				}
			}
			
			//ֻ��ȥһ����¼
			//MapHandler:��һ����¼���ֶκͶ�Ӧ��ֵ��װ��һ��map��,����,Ĭ��ȡ���ǵ�һ��
			//@Test
			public void test7() throws SQLException{
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				
				Map<String, Object> map = qRunner.query("select * from user", new MapHandler());
				
				for(String key:map.keySet()){
					System.out.print(key+"="+map.get(key)+"   ");
				}
			}
			
			
			//MapHandler:��һ����¼���ֶκͶ�Ӧ��ֵ��װ��һ��map��,�ٽ����е�map�ŵ�һ��list�в�����
			//@Test
			public void test8() throws SQLException{
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				List<Map<String, Object>> list = qRunner.query("select * from user", new MapListHandler());
				
				for (Map<String, Object> map : list) {
					for(String key:map.keySet()){
						System.out.print(key+"="+map.get(key)+"   ");
					}
					
					System.out.println();
				}
			}
			
			//ȡ��һ�е�ĳһ�е�ֵ
			//ScalarHandler:�ʺ�ȡ���е��е�����
			@Test
			public void test9() throws SQLException{
				QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
				//����:�����һ�ж�Ӧ���к�,ȡ������µ�����,������д,Ĭ��ȡ��һ��
				//Object object = qRunner.query("select * from user", new ScalarHandler(3));
				//��ۺϺ�������ʹ��,��������ȡ������select������Ի�ȡ������
				Object object1 = qRunner.query("select *,count(*) from user", new ScalarHandler(6));
				System.out.println(object1);
			}
}
