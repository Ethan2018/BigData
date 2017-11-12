<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath }/ServletDemo1" method="get">
    	用户名:<input type="text" name="name"><br/>
    	密码:<input type="password" name="pwd"><br/>
    	<input type="submit" value="自动登录"> 
    </form>
</body>
</html>