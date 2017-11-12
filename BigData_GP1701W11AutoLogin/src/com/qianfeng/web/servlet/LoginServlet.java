package com.qianfeng.web.servlet;

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
import com.qianfeng.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//获取账号,密码,自动登录标签
		String name = request.getParameter("name");
		String password = request.getParameter("pwd");
		String auto = request.getParameter("box");
		
		//先查询数据库进行验证
		UserService uService = new UserServiceImpl();
		User user = uService.findUserByNameAndPwd(name,password);
		
		//如果user不存在,说明密码,账号错误,或者没有这个人
		if (user != null) {
			//在cookie中保存中文的时候----编码
			String ss = URLEncoder.encode(user.getName()+"&"+user.getPwd(), "utf-8");
			//创建cookie对象
			Cookie cookie = new Cookie("user", ss);
			System.out.println("user="+user);
			cookie.setPath("/");
			if (auto != null) {//auto有值是on  没有值是null
				System.out.println("auto!=null");
				cookie.setMaxAge(Integer.MAX_VALUE);
			}else {
				cookie.setMaxAge(0);
			}
			
			//将cookie发回客户端
			response.addCookie(cookie);
			
			//调转到主界面
			
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("msg", "账号,密码验证失败,请重新登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
