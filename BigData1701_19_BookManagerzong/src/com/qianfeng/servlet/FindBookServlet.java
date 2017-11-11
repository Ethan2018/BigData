package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class FindBookServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//��ȡid
		String id = request.getParameter("id");
		
		System.out.println("id+"+id);
		
		//ȥ���ݿ�鵱ǰ���鼮
		BookService bService = new BookServiceImpl();
		Book book = bService.fineBook(id);
		
		System.out.println("book+"+book);
		
		//��ת���༭����
		request.setAttribute("book", book);
		request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
