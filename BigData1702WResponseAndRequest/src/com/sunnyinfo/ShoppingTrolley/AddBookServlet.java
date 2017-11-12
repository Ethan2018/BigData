package com.sunnyinfo.ShoppingTrolley;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunnyinfo.Mould.Book;
import com.sunnyinfo.Utils.DBUtil;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
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
		String id = request.getParameter("bookid");
		Book book = DBUtil.searchBook(id);
		HttpSession session = request.getSession();
		//获取购物车
		List<Book> list = (List<Book>)session.getAttribute("car");
		if (list == null) {
			list = new ArrayList<Book>();	
		}
		list.add(book);
		session.setAttribute("car", list);
		response.getWriter().write("恭喜你添加"+book.getName()+"成功,3秒后返回主页,请继续购物");
		String url = request.getContextPath() + "/ShowAllBooksServlet";
		response.setHeader("refresh", "3;'"+url+"'");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
