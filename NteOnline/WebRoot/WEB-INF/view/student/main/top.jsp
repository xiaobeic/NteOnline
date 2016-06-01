<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录</title>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">
</head>
	
<body>
<nav class="navbar navbar-inverse navbar-default navbar-fixed-top .visible-lg-* mynavbar" >
	<div class="navbar-header">
      <a class="navbar-brand" href="teacherIndex_main.action" target="_parent">
        <img alt="Brand" src="${pageContext.request.contextPath}/images/teacher/logo.png">
      </a>
    </div>
	<div class="container">
		<!--<ul class="nav navbar-nav navbar-right">
		    <li><a href="login.html">登录</a></li>
		    <li><a href="register.html">注册</a></li>
		</ul>-->
		<ul class="nav navbar-nav navbar-right login_info">
			<li><a href="#">欢迎<font color="red">${name }&nbsp;&nbsp;&nbsp;&nbsp;</font></a></li>
			<li><a href="${pageContext.request.contextPath }/main/login_loginUI.action" target="_parent">退出</a></li>
		</ul>	
	</div>
</nav>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

</body>
</html>