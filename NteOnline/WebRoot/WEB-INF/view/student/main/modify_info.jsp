<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,oa.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改基本信息</title>
<link
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/student/studentUpdate_Info.action">
		<table class="table table-bordered">
			<tr>
				<td>姓名</td>
				<td>性别</td>
				<td>年龄</td>
				<td>用户名</td>
				<td>电话</td>
			</tr>
			<%
				Student e = (Student) request.getAttribute("student_info");//取出数据
				if (e != null) {
			%>
			<tr>
				<td>
				<input id="id" value="<%=e.getId() %>" type="hidden" />
				<input type="text" style="width:220px;" name="name"
					class="form-control" id="exampleInputUserName" placeholder="text"
					value="<%=e.getName()%>"></td>

				<td><input type="text" style="width:220px;" name="sex"
					class="form-control" id="exampleInputUserName" placeholder="text"
					value="<%=e.getSex()%>"></td>
				<td><input type="text" style="width:220px;" name="age"
					class="form-control" id="exampleInputUserName" placeholder="text"
					value="<%=e.getAge()%>"></td>
				<td><input type="text" style="width:220px;" name="username"
					class="form-control" id="exampleInputUserName" placeholder="text"
					value="<%=e.getUsername()%>"></td>
				<td><input type="text" style="width:220px;" name="telephone"
					class="form-control" id="exampleInputUserName" placeholder="text"
					value="<%=e.getTelephone()%>">
				</td>
			</tr>
			<%
				}
			%>

		</table>
		<center>
			<button type="submit" class="btn btn-default">保存个人信息</button>
		</center>
	</form>
</body>
<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</html>