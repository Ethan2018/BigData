package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qianfeng.domain.Book;
import com.qianfeng.domain.SearchBook;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class SearchBooksServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		SearchBook sBook = new SearchBook();
		
		try {
			BeanUtils.populate(sBook, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将传过来的值，如果是空，就赋值成空字符串，这样便于数据的处理
		if (sBook.getId()==null) {
			sBook.setId("");
		}
		if (sBook.getName()==null) {
			sBook.setName("");
		}
		
		if (sBook.getCategory()==null) {
			sBook.setCategory("");
		}
		
		if (sBook.getMinprice()==null) {
			sBook.setMinprice("");
		}
		
		if (sBook.getMaxprice()==null) {
			sBook.setMaxprice("");
		}
		
		//到数据库查询
		BookService bService = new BookServiceImpl();
		List<Book> list = bService.searchBooks(sBook);
		
		
		//跳转到list.jsp界面
		request.setAttribute("books", list);
		request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
