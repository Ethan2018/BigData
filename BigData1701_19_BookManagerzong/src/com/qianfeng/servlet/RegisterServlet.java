package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import com.qianfeng.service.impl.UserException;
import com.qianfeng.service.impl.UserServiceImpl;

public class RegisterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//先对验证码进行验证
		String code = request.getParameter("ckcode");
		String code1 = (String)request.getSession().getAttribute("checkcode_session");
		
		if (!code.equals(code1)) {
			request.setAttribute("errmsg", "验证码输入错误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
		//验证所有信息
		User user = new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserService uService = new UserServiceImpl();
		int num = 0;
		
		//注册请求的两种方式之通过自定义异常
		try {
			num = uService.registerUser(user);
			out.println("注册成功");
			//这里不需要存储用户的数据,在用户完成登录后,再存
//			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "注册失败");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
		
		//通过返回值
//		if (num>0) {
//			out.println("注册成功");
//			request.getSession().setAttribute("user", user);
//			request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
//			
//		}else {
//			request.setAttribute("msg", "注册失败");
//			request.getRequestDispatcher("/register.jsp").forward(request, response);
//		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
