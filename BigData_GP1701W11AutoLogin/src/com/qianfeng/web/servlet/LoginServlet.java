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
		
		//��ȡ�˺�,����,�Զ���¼��ǩ
		String name = request.getParameter("name");
		String password = request.getParameter("pwd");
		String auto = request.getParameter("box");
		
		//�Ȳ�ѯ���ݿ������֤
		UserService uService = new UserServiceImpl();
		User user = uService.findUserByNameAndPwd(name,password);
		
		//���user������,˵������,�˺Ŵ���,����û�������
		if (user != null) {
			//��cookie�б������ĵ�ʱ��----����
			String ss = URLEncoder.encode(user.getName()+"&"+user.getPwd(), "utf-8");
			//����cookie����
			Cookie cookie = new Cookie("user", ss);
			System.out.println("user="+user);
			cookie.setPath("/");
			if (auto != null) {//auto��ֵ��on  û��ֵ��null
				System.out.println("auto!=null");
				cookie.setMaxAge(Integer.MAX_VALUE);
			}else {
				cookie.setMaxAge(0);
			}
			
			//��cookie���ؿͻ���
			response.addCookie(cookie);
			
			//��ת��������
			
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("msg", "�˺�,������֤ʧ��,�����µ�¼");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
