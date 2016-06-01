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
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/teacherPingJiaInfo_teacherPingJiaMain.action" target="right">教师评价信息</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/studentPingJiaInfo_studentPingJiaMain.action" target="right">学生评价信息</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/dudaoZhiBiao_list.action" target="right"><span style="font-size: 12px;">督导员对课程评价指标体系管理</span></a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/studentZhiBiao_list.action" target="right"><span style="font-size: 13px;">教师对学生评价指标体系管理</span></a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/courseZhiBiao_list.action" target="right"><span style="font-size: 13px;">学生对课程评价指标体系管理</span></a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/course_list.action" target="right">课程管理</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/dudaocourse_list.action" target="right">督导课程管理</a></li>
		<li id="sc" role="presentation"><a href="${pageContext.request.contextPath}/admin/dudaocourse_updatepasswordUI.action" target="right">修改密码</a></li>
	</ul>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>