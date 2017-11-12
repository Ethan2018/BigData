package com.qianfeng.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.Flags.Flag;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Filter1 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//适合于post
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		
		//get
		//利用request构造MyHttpRequest的对象
		MyHttpRequest mRequest = new MyHttpRequest((HttpServletRequest)request);
		
		chain.doFilter(mRequest, response);

	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	

}

//使用装饰设计模式-适配器模式更加高效的解决get方式的中文乱码问题
//HttpServletRequestWrapper就是适配器
//MyHttpRequest:装饰类
class MyHttpRequest extends HttpServletRequestWrapper{
	private HttpServletRequest hsr = null;

	//父类中没有不带参数的构造方法,所以要自己先手动调用父类的构造方法
	public MyHttpRequest(HttpServletRequest request) {
		super(request);
		this.hsr = request;
		// TODO Auto-generated constructor stub
	}
	
	@Override
//	public String getParameter(String name) {
//		
//		String temp = hsr.getParameter(name);
//		
//		try {
//			temp = new String(temp.getBytes("ISO8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return temp;
//	}
	public String getParameter(String name) {
			
		return getParameterMap().get(name)[0];
	};
	
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return getParameterMap().get(name);
	}
	
	//保证所有的标签对象只进行一次再编码再解码
	boolean flag = true;
	 Map<String, String[]> map = null;
	@Override
	public Map<String, String[]> getParameterMap() {
		
		if (flag) {
		map = hsr.getParameterMap();
		
		
		for (Map.Entry<String, String[]> en : map.entrySet()) {
			String[] values =  en.getValue();
			
			for (int i = 0; i < values.length; i++) {
				try {
					values[i] = new String(values[i].getBytes("ISO8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 flag = false;
		}
		
		return map;
	}
}
