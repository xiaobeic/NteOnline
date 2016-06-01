<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,oa.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人成绩</title>
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
	<div style="text-align: center;">
		<form>
			<table class="table table-hover">
				<tr>
					<td>课程名</td>
					<td>课程环节名</td>
					<td>总成绩</td>
				</tr>
				<%
					List<StudentPingJiaScore> list_score = (List<StudentPingJiaScore>) request
							.getAttribute("SumGridProject");
					for (StudentPingJiaScore score : list_score) {
				%>
				<tr>
					<td><%=score.getCourse().getCourseName()%></td>
					<td><%=score.getStudentPingJiaProject().getName()%></td>
					<td><%=score.getScore()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>