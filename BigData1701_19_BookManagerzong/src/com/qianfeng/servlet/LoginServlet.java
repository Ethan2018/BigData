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
		
		//��ȡ�û���,����
		String name = request.getParameter("username");
		//������������ʱ,��Ҫ�ȱ���,��Ϊcookie�����ܴ�����
		String newname = URLEncoder.encode(name,"utf-8");
		String password = request.getParameter("password");
	System.out.println(newname+"  "+password);
	
//		UserService uService = new UserServiceImpl();
//		User user = null;
//		try {
//			user = uService.userLogin(newname,password);
//			
//			//�����Զ���¼
//			
//			String auto = request.getParameter("auto");
//			//����cookie����
//			Cookie cookie = new Cookie("user", user.getUsername()+"&"+user.getPassword());
//			//����·��
//			cookie.setPath("/");
//			if (auto!=null) {
//				cookie.setMaxAge(60*60*24*3);
//			}else {
//				cookie.setMaxAge(0);
//			}
//			
//			response.addCookie(cookie);
//			
//			//��ס�û���
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
//			//��¼�ɹ���,���û���Ϣ�洢��session����
//			String path = "/index.jsp";//��ͨ�û�
//			if (user.getRole().equals("admin")) {
//				path = "/admin/login/home.jsp";//����Ա
//			}
//			
//			request.getSession().setAttribute("user", user);
//			
//			request.getRequestDispatcher(path).forward(request, response);
//		} catch (UserException e) {//��¼ʧ��
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
