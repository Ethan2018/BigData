package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.User;

public class MyAccountServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		request.setCharacterEncoding("utf-8");
		
		//����account����
		//��ȡ�û�����Ϣ
		User user = (User)request.getSession().getAttribute("user");
		
		if (user==null) {
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/login.jsp");//�ض���
		}else {
			String path = "/myAccount.jsp";
			if ("admin".equals(user.getRole())) {
				path = "/admin/login/home.jsp";
			}
			
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
