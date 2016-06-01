<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录</title>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">
<script type="text/javascript">
window.onload=function(){
	$("#submit").click(function(){
		var password1=$("input[name='password1']").val();
		var password2=$("input[name='password2']").val();
		var password3=$("input[name='password3']").val();
		if(password2!=password3){
			alert("两次密码输入不正确...");
			return false;
		}
		var url="${pageContext.request.contextPath}/teacher/teacherAjax_updatePwd.action";
		var args={"password1":password1,"password2":password2,"time":new Date()};
		$.ajax({
			url:url,
			type:"post",
			data:args,
			dataType:"json",
			success:function(data){
				if(data!=null){
					alert("修改成功...");
					$("input[name='password1']").val('');
					$("input[name='password2']").val('');
					$("input[name='password3']").val('');
				}else{
					alert("修改失败，请确认原密码是否正确...");
				}
			},
			error:function(){
				alert("服务器错误");
			}
		});
		return false;	
	});
}
</script>
</head>
	
<body>
<div class="separte">
	<div id="right" class="container-fluid">
		<div class="update_pwd">
			<form method="post" class="form-horizontal">
				<span class="span_pwd">密码修改</span>
                <div class="form-group">
                    <label class="col-sm-2 control-label">原密码</label>
                    <div class="col-sm-4">
                        <input name="password1" type="password" class="form-control" required />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">新密码</label>
                    <div class="col-sm-4">
                        <input name="password2" type="password" class="form-control" required />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-4">
                        <input name="password3" type="password" class="form-control" required />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button id="submit" class="btn modify-btn">提交</button>
                    </div>
                </div>
            </form>
		</div>
	</div>
</div>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

</body>
</html>