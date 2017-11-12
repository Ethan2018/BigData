package com.sunnyinfo.ShoppingTrolley;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunnyinfo.Mould.Book;

/**
 * Servlet implementation class ShowCarBooks
 */
@WebServlet("/ShowCarBooks")
public class ShowCarBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCarBooks() {
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
		HttpSession session = request.getSession();
		List<Book> list = (List<Book>)session.getAttribute("car");
		if (list == null) {
			response.getWriter().write("抱歉,您还没有买书,请回到主页买书");
			String url = request.getContextPath() + "/ShowAllBooksServlet";
			response.setHeader("refresh", "3;'"+url+"'");
		} else {
			for (Book book : list) {
				response.getWriter().write("<h4>" + book.getName() + " " + book.getPrice() + " " + book.getAuthor() + "</h4><br/>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
