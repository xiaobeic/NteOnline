<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>评价系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery1.42.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.SuperSlide.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Validform_v5.3.2_min.js"></script>

</head>
<frameset name="main" cols="*" rows="120, *" id="frame_main" border="0">
	<frame src="${pageContext.request.contextPath}/student/studentIndex_top.action"
		noresize="noresize" name="header">
	<frameset cols="181, *">
		<frame src="${pageContext.request.contextPath}/student/studentIndex_left.action"
			name="left" noresize="noresize" />
		<frameset rows="820,*" border="0">
			<frame name="right"
				src="${pageContext.request.contextPath}/student/studentIndex_right.action" noresize />
		</frameset>
	</frameset>
</frameset>
<noframes>

	<body>

	</body>
</noframes>
</html>
