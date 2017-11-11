package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class ChangeNumServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String num =  request.getParameter("num");
		
		//创建一个对象,目的:为了将当前的id值传给map
		//Book book = new Book();
		//book.setId(id);
		BookService bService = new BookServiceImpl();
		Book book = bService.findBookById(id);
		
		//获取购物车对象
		Map<Book, String> map = (Map<Book, String>)request.getSession().getAttribute("cart");
		
		//当num=0的时候,应该将当前的书从map中删除
		if ("0".equals(num)) {
			map.remove(book);
		}
		
		//当num>0的时候,再去执行更改操作
		if (map.containsKey(book)) {
			map.put(book, num);
		}
		
		
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		
		out.close();
		
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
