<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>评价系统</title>
</head>
	<frameset rows="13%,*" frameborder="0">
    	<frame src="${pageContext.request.contextPath}/main/teacherIndex_top.action" name="top" src="top.jsp" noresize></frame>
        <frameset cols="15%,*" cols="30%">
        	<frame src="${pageContext.request.contextPath}/main/teacherIndex_left.action" name="left" src="left.jsp" frameborder="0" noresize></frame>
            <frame src="${pageContext.request.contextPath}/main/teacherIndex_right.action" name="right" src="right.jsp" frameborder="0" noresize></frame>
        </frameset>
    </frameset>
<body>
</body>
</html>
