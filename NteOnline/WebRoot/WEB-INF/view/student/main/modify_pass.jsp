<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改个人密码</title>
<link
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/student/studentModify_Pass.action">

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">修改密码</h3>
			</div>
			<div class="panel-body">
				<label for="exampleInputUserName">请输入原密码 </label> <input type="password"
					style="width:220px;" name="oldpass" class="form-control"
					id="exampleInputUserName" placeholder="text"> <label
					for="exampleInputPassword1">请输入新密码 </label> <input type="password"
					name="password1" style="width:220px;" class="form-control"
					id="exampleInputPassword1" placeholder="Password"> <label
					for="exampleInputPassword2">请再次输入新密码</label> <input type="password"
					style="width: 220px;" class="form-control" name="password2"
					id="exampleInputPassword2" placeholder="Password">
			</div>
		</div>
		<button type="submit" class="btn btn-default">确认更改</button>
	</form>
</body>
</html>