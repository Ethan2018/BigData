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
			
			//执行这里的代码,说明已经注册成功
			//在这里实现激活码的发送
			
			//编写发送内容
			String emailMsg = "注册成功,请<a href='http://http://10.0.181.19:8081/BigData1701_19_BookManager/servlet/ActiveServlet?activeCode="+user.getActiveCode()+"'>点击激活</a>";
			//发送邮件
			SendJMail.sendMail(user.getEmail(), emailMsg);
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			throw new UserException("注册失败");
			
		}
		
		return num;
	}
	
	@Override
	//用户激活
	public void activeUser(String activecode) throws UserException {
		User user = null;
		
		try {
			user = uDao.findUserWithActiveCode(activecode);
			
		    if (user!=null) {
				uDao.modifystate(activecode);
				return;
			}
		    
		    throw new UserException("激活失败");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("激活失败");
		}
	}
	
	//用户登录
	@Override
	public User userLogin(String name, String password) throws UserException {
		User user = null;
		
		try {
			user = uDao.findUserWithNameAndPwd(name,password);
			
			
			if (user==null) {
				throw new UserException("用户名和密码错误");
			}
			
			if (user!=null && user.getState()==0) {
				throw new UserException("请先激活");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new UserException("用户名和密码错误");
		}
		
		return user;
	}
	
	
	//修改用户信息
	@Override
	public void modifyUser(User user) throws UserException {
		try {
			uDao.modifyUser(user);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserException("修改失败");
		}
	}

}
