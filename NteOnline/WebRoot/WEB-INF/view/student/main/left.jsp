<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentControl_selectedCourse.action" target="right"><span>当前已选课程</span></a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentXuanKe_Info.action" target="right"><span>学生选课</span></a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentControl_evaluate_comment.action" target="right"><span>学生评教</span></a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentGridInfo_type.action" target="right">查看原始成绩</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentGridInfo_sum.action" target="right">查看总成绩</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentGridInfo_pingyu.action" target="right">查看评语</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/student/studentControl_modify.action" target="right">修改密码</a></li>
	</ul>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>