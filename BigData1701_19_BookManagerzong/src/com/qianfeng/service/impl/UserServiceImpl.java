package com.qianfeng.service.impl;

import java.sql.SQLException;

import com.qianfeng.dao.UserDao;
import com.qianfeng.dao.impl.UserDaoImpl;
import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import com.qianfeng.util.SendJMail;

public class UserServiceImpl implements UserService {
	UserDao uDao = new UserDaoImpl();

	@Override
	public int registerUser(User user) throws UserException {
		int num = 0;
	
		try {
			
			num = uDao.registerUser(user);
			
			//ִ������Ĵ���,˵���Ѿ�ע��ɹ�
			//������ʵ�ּ�����ķ���
			
			//��д��������
			String emailMsg = "ע��ɹ�,��<a href='http://http://10.0.181.19:8081/BigData1701_19_BookManager/servlet/ActiveServlet?activeCode="+user.getActiveCode()+"'>�������</a>";
			//�����ʼ�
			SendJMail.sendMail(user.getEmail(), emailMsg);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			throw new UserException("ע��ʧ��");
			
		}
		
		return num;
	}
	
	@Override
	//�û�����
	public void activeUser(String activecode) throws UserException {
		User user = null;
		
		try {
			user = uDao.findUserWithActiveCode(activecode);
			
		    if (user!=null) {
				uDao.modifystate(activecode);
				return;
			}
		    
		    throw new UserException("����ʧ��");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("����ʧ��");
		}
	}
	
	//�û���¼
	@Override
	public User userLogin(String name, String password) throws UserException {
		User user = null;
		
		try {
			user = uDao.findUserWithNameAndPwd(name,password);
			
			
			if (user==null) {
				throw new UserException("�û������������");
			}
			
			if (user!=null && user.getState()==0) {
				throw new UserException("���ȼ���");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new UserException("�û������������");
		}
		
		return user;
	}
	
	
	//�޸��û���Ϣ
	@Override
	public void modifyUser(User user) throws UserException {
		try {
			uDao.modifyUser(user);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("�޸�ʧ��");
		}
	}

}
