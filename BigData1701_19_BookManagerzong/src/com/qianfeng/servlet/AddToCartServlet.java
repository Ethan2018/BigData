package com.qianfeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qianfeng.domain.Book;
import com.qianfeng.service.BookService;
import com.qianfeng.service.impl.BookServiceImpl;

public class AddToCartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//���ղ���
		String id = request.getParameter("id");
		
		//��ȡ��Ӧ������
		BookService bService =  new BookServiceImpl();
		Book book = bService.findBookById(id);
		
		HttpSession hSession = request.getSession();
		Map<Book, String> map = (Map<Book, String>)hSession.getAttribute("cart");
		
		int num = 1;
		
		if (map == null) {
			map = new HashMap<Book, String>();
		}
		
		//����ͬʱ��౾ͬ����������
		//�жϳ����Ƿ������ǰ����
		if (map.containsKey(book)) {
			num = Integer.parseInt(map.get(book))+1;
		}
		
		map.put(book, num+"");
		
		//�����ﳵ����session
		hSession.setAttribute("cart", map);
		
		
		out.println("<a href='"+request.getContextPath()+"/PageServlet?currentPage=1'>��������</a>");
		out.println("<a href='"+request.getContextPath()+"/cart.jsp'>�����ﳵ</a>");
		
		out.close();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
