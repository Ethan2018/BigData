package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class DelBookServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		//É¾³ýÒ»±¾Êé
		BookService bService = new BookServiceImpl();
		int num = bService.delBook(id);
		
		if (num>0) {
			request.getRequestDispatcher("/FindAllBooks").forward(request, response);
		}
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
