<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,oa.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选课界面</title> 
<link
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css"
	rel="stylesheet">
	<script type="text/javascript">
		function ff(){
			
		}
	</script>
</head>

<body onload=ff()>
	<form
		action="${pageContext.request.contextPath}/student/studentXuanKe_Insert.action">
		<%
			List<Integer> course_NotSelected=(List<Integer>)request.getAttribute("Course_NotSelected");
			int course_size=course_NotSelected.size();
			if(course_size!=0){
		 %>
		<table class="table table-hover">
			<tr>
				<td>选课</td>
				<td>课程名</td>
				<td>老师名</td>
			</tr>
			<%
				List<Course> list_map = (List<Course>) request
						.getAttribute("XuanKe_info");
				
				if (list_map != null||course_NotSelected!=null) {
					for (Course c : list_map) {
			%>
			<tr>
			<% 
				for(int a:course_NotSelected)
				if(c.getId()==a) {%>
				<td>
				<input type="checkbox" name="xuanke" value="<%=c.getId() %>" id="<%=c.getId() %>" />
				</td>
				<td><%=c.getCourseName()%></td>
				<td><%=c.getTeacher().getTeacherName() %></td>
				<%
				
				} 
				 %>
			</tr>
			<%
				}
			}
			%>
		</table>
		<center>
			<button class="btn btn-default" type="submit">提交</button>
		</center>
		<%
		}
		else{
		 %>
		 <strong>
		 暂时没有未选课程
		 </strong>
		 <%
		 }
		  %>
	</form>
	<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>

</html>