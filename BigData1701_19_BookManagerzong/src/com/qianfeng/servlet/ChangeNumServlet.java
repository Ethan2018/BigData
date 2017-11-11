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
		
		//����һ������,Ŀ��:Ϊ�˽���ǰ��idֵ����map
		//Book book = new Book();
		//book.setId(id);
		BookService bService = new BookServiceImpl();
		Book book = bService.findBookById(id);
		
		//��ȡ���ﳵ����
		Map<Book, String> map = (Map<Book, String>)request.getSession().getAttribute("cart");
		
		//��num=0��ʱ��,Ӧ�ý���ǰ�����map��ɾ��
		if ("0".equals(num)) {
			map.remove(book);
		}
		
		//��num>0��ʱ��,��ȥִ�и��Ĳ���
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
