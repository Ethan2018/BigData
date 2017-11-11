package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import com.qianfeng.service.impl.UserException;
import com.qianfeng.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//获取用户名,密码
		String name = request.getParameter("username");
		//当名字是中文时,需要先编码,因为cookie本身不能存中文
		String newname = URLEncoder.encode(name,"utf-8");
		String password = request.getParameter("password");
	System.out.println(newname+"  "+password);
	
//		UserService uService = new UserServiceImpl();
//		User user = null;
//		try {
//			user = uService.userLogin(newname,password);
//			
//			//设置自动登录
//			
//			String auto = request.getParameter("auto");
//			//创建cookie对象
//			Cookie cookie = new Cookie("user", user.getUsername()+"&"+user.getPassword());
//			//设置路径
//			cookie.setPath("/");
//			if (auto!=null) {
//				cookie.setMaxAge(60*60*24*3);
//			}else {
//				cookie.setMaxAge(0);
//			}
//			
//			response.addCookie(cookie);
//			
//			//记住用户名
//			String remember = request.getParameter("remember");
//			
//			Cookie cookie1 = new Cookie("user1", newname);
//			cookie1.setPath("/");
//			if (remember!=null) {
//				cookie1.setMaxAge(60*60*24*3);
//			}else {
//				cookie1.setMaxAge(0);
//			}
//			response.addCookie(cookie1);
//			
//			//登录成功后,将用户信息存储到session里面
//			String path = "/index.jsp";//普通用户
//			if (user.getRole().equals("admin")) {
//				path = "/admin/login/home.jsp";//管理员
//			}
//			
//			request.getSession().setAttribute("user", user);
//			
//			request.getRequestDispatcher(path).forward(request, response);
//		} catch (UserException e) {//登录失败
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			out.print(e.getMessage());
//			request.setAttribute("errmsg", e.getMessage());
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
//		}
//		
//		
//
//		
//		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
