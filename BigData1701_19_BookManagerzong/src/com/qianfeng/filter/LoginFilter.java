package com.qianfeng.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.qianfeng.domain.User;
import com.qianfeng.service.UserService;
import com.qianfeng.service.impl.UserException;
import com.qianfeng.service.impl.UserServiceImpl;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hsr = (HttpServletRequest)request;
//		//获取账号,密码
//		Cookie[] cookies = hsr.getCookies();
//		
//		
//		
//		if (cookies != null) {
//		    for (Cookie cookie : cookies) {
//		    	if (cookie.getName().equals("user")) {
//		    		String name ="";
//		    		String pwd ="";
//		    		String value = cookie.getValue();
//		    		String[] ss = value.split("&");
//				
//		    		name = ss[0];
//					pwd = ss[1];
//					
//					//名字是中文时的处理:解码
//					name = URLDecoder.decode(name,"utf-8");
//				
//					System.out.println("name="+name+"   "+"pwd="+pwd);
//					
//					//使用当前 账号,密码去数据库查询
//					UserService userService = new UserServiceImpl();
//					User user = null;
//					try {
//						user = userService.userLogin(name,pwd);
//						
//						hsr.getSession().setAttribute("user", user);
//					} catch (UserException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					}
//		    	}	
//		    }
		
		

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
