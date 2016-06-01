<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,oa.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本信息</title>

<link
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<td>姓名</td>
			<td>性别</td>
			<td>年龄</td>
			<td>用户名</td>
			<td>电话</td>
			<td>所属班级</td>
		</tr>
		<%
			Student e = (Student) request.getAttribute("student_info");//取出数据
			if (e != null) {
		%>
		<tr>
			<td><%=e.getName()%></td>
			<td><%=e.getSex()%></td>
			<td><%=e.getAge()%></td>
			<td><%=e.getUsername()%></td>
			<td><%=e.getTelephone()%></td>
			<td><%=e.getClasses().getClassName()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<center>
		<a 	href="${pageContext.request.contextPath}/student/studentModify_Info.action">修改个人信息</a>
	</center>
</body>
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>