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
		//�ʺ���post
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		
		//get
		//����request����MyHttpRequest�Ķ���
		MyHttpRequest mRequest = new MyHttpRequest((HttpServletRequest)request);
		
		chain.doFilter(mRequest, response);

	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	

}

//ʹ��װ�����ģʽ-������ģʽ���Ӹ�Ч�Ľ��get��ʽ��������������
//HttpServletRequestWrapper����������
//MyHttpRequest:װ����
class MyHttpRequest extends HttpServletRequestWrapper{
	private HttpServletRequest hsr = null;

	//������û�в��������Ĺ��췽��,����Ҫ�Լ����ֶ����ø���Ĺ��췽��
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
	
	//��֤���еı�ǩ����ֻ����һ���ٱ����ٽ���
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
