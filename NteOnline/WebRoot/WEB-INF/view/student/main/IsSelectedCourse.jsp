<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,oa.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评教页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.css">
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/app.css">

</head>

<body>
	<form>
		<table width="100%" class="table table-hover">
			<tr>
				<td>课程名</td>
				<td>教师名</td>
			</tr>
			<%
				List<Course> list_map = (List<Course>) request
						.getAttribute("PingJiao_info");
				List<Integer> course_Selected = (List<Integer>) request
						.getAttribute("Course_IsSelected");
				if (list_map != null || course_Selected != null) {
					for (Course c : list_map) {
			%>
			<tr>
				<%
					for (int a : course_Selected) {
								if (c.getId() == a) {
				%>
				<td><%=c.getCourseName()%></td>
				<td><%=c.getTeacher().getTeacherName()%></td>
			</tr>
			<%
				}
						}
					}
				}
			%>
		</table>
	</form>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

</html>