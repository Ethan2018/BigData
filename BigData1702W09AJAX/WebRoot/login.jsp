<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="czfjs/ajax.js"></script>
<script type="text/javascript">
	function checkName() {
		var user = document.getElementsByName("user")[0];
		var uservalue = user.value;
		var req = getXMLHttpRequest();
		//接收服务器的数据 函数回调  监听者的角色,随时接收从服务器传回的信息
		req.onreadystatechange = function() {
			if(req.status == 200 && req.readyState == 4) {
				var value = req.responseText;
				//alert(value);
				var span = document.getElementById("span");
				span.innerHTML = value;
			}
		}
		req.open("get", "${pageContext.request.contextPath}/CheckEmailNameServlet?name=" + uservalue);
		req.send(null);
	}
</script>
</head>
<body>
	<form action="" method="get">
		邮箱:<input type="text" name="user" onblur="checkName()"><span id="span"></span><br>
		密码:<input type="password" name="pwd"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>