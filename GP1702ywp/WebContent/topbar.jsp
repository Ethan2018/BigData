
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<html>
<head>
<title>Hadoop云盘系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/js/my.css" rel="stylesheet" media="screen">
<link href="<%=request.getContextPath()%>/js/bootstrap-3.2.0-dist/css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<body>
      <!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Hadoop模拟网盘功能</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">云存储 <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="listfile.action">我的文件</a></li>
                  <li><a href="goto_upload_page.action">上传文件</a></li>
                </ul>
              </li>
              <li><a href="#">搜索</a></li>
              <li><a href="#">Link</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Action <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action1</a></li>
                  <li><a href="#">Action2</a></li>
                  <li><a href="#">Action3</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Action4</li>
                  <li><a href="#">Action5</a></li>
                  <li><a href="#">Action6</a></li>
                </ul>
              </li>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
            
              <div class="navbar-collapse collapse">

				<%
					String username = (String)session.getAttribute("username");
					if(username == null) {
				%>
          		<form class="navbar-form navbar-right" role="form" action="login.action" method="post">
          		    
            		<div class="form-group">
             			 <input name="name" type="text" placeholder="用户名" class="form-control">
            		</div>
            		<div class="form-group">
             			 <input name="pwd" type="password" placeholder="密码" class="form-control">
            		</div>
            		<button type="submit" class="btn btn-success">登录</button>
            		<button formaction="register.action" type="submit" class="btn btn-success">注册</button>
          		</form>
          		<%
					} else {
          		%>
					<form class="navbar-form navbar-right" role="form" action="logout.action" method="get">
						<div class="form-group">
             				 <label>你好，${username }</label>
            			</div>
          		 		<button type="submit" class="btn btn-success">注销</button>
          		 	</form>
				<%
					}
				%>
        		</div>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
  </body>
</html>