package com.sunnyinfo.ShoppingTrolley;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunnyinfo.Mould.Book;
import com.sunnyinfo.Utils.DBUtil;

/**
 * Servlet implementation class ShowAllBooksServlet
 */
@WebServlet("/ShowAllBooksServlet")
public class ShowAllBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().write("<h2>下面是我们要出售的图书</h2>");
		for (Map.Entry<String, Book> entry : DBUtil.getAllBooks().entrySet()) {
			Book book = entry.getValue();
			String bookid = entry.getKey();
			String url = request.getContextPath() + "/AddBookServlet?bookid=" + bookid;
			response.getWriter().write("<a href='"+url+"'>" + book.getName() + "</a><br/>");		
		}
		
		String url2 = request.getContextPath() + "/ShowCarBooks";
		response.getWriter().write("<a href='"+url2+"'>查看购物车</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
