<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
<script type="text/javascript">
	window.onload=function(){
	$("li[id=sc]").css("backgroundColor","#002248");
		$("li").click(function(){
			$("li[id=sc]").css("backgroundColor","#002248");
			$(this).css("backgroundColor","white");
		});
	}
</script>
</head>
	
<body style="overflow:-Scroll;overflow-x:hidden;background-color:#002248;">
	<ul id="clk" class="nav nav-pills nav-stacked af_option">
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/teacher/teacherOperate_class_evaluate.action" target="right">班级评价查看</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/teacher/teacherOperate_evaluate_comment.action" target="right">评级及评语</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/teacher/teacherOperate_teacherInfo.action" target="right">教师信息查看与修改</a></li>
	</ul>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>