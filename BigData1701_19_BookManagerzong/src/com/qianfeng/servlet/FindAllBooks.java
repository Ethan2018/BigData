package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class FindAllBooks extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//�����ݿ�ȡ�����е��鼮
		BookService bService  = new BookServiceImpl();
		List< Book> list = bService.findAllBooks();
		
		//��ת
		//�������鼮��list����request����
		request.setAttribute("books", list);
		//��ת��list.jsp����
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}