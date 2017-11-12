package com.sunnyinfo.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class RequestDemo
 */
@WebServlet("/RequestDemo")
public class RequestDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		System.out.println(request.getMethod());
//		System.out.println(request.getContextPath());
//		System.out.println(request.getRequestURL());
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getQueryString());
		request.setCharacterEncoding("utf-8");
		test(request);
		test2(request);
		
	}
	public static void test(HttpServletRequest request) {
		String name = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		String[] hobbys = request.getParameterValues("hobby");
		String area = request.getParameter("area");
		System.out.println(name + " " + pwd + " " + sex + " " + area);
		for (String string : hobbys) {
			System.out.println(string);
		}
	}
	private static void test2(HttpServletRequest request) {
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String string = (String) names.nextElement();
				String[] values = request.getParameterValues(string);
				for (String str : values) {
					System.out.println(str);
				}
			}
	}
	
	public static void test3(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(user);
	}
	public static String fun(String name) throws UnsupportedEncodingException {
		byte[] bytes = name.getBytes("ISO8859-1");
		String newname = new String(bytes, "utf-8");
		return newname;
	}
	
	public static void testDirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/RequestDemo2");
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
