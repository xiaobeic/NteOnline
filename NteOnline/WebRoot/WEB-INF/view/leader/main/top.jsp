<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
</head>
	
<body>
<nav class="navbar navbar-inverse navbar-default navbar-fixed-top .visible-lg-*" >
	<div class="navbar-header">
      <a class="navbar-brand" id="navbar-header" href="${pageContext.request.contextPath}/main/leaderIndex_top.action">
        <img alt="Brand" src="${pageContext.request.contextPath}/images/teacher/logo.png">
      </a>
    </div>
	<div class="container">
	    <ul class="nav navbar-nav navbar-left">
			<li><a href="${pageContext.request.contextPath}/main/leaderIndex_top.action">欢迎来到评价系统</a></li>
		</ul>
		<!--<ul class="nav navbar-nav navbar-right">
		    <li><a href="login.html">登录</a></li>
		    <li><a href="register.html">注册</a></li>
		</ul>-->
		<ul class="nav navbar-nav navbar-right">
			<li><a href="${pageContext.request.contextPath}/main/leaderIndex_top.action">欢迎<font color="red">${sessionScope.user.name }&nbsp;&nbsp;&nbsp;&nbsp;</font></a></li>
			<li><a href="${pageContext.request.contextPath}/main/login_loginUI.action" target="_parent">退出</a></li>
		</ul>	
	</div>
</nav>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
