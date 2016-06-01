<%@page import="java.util.List,oa.bean.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录</title>
<link
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/non-responsive.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css"
	rel="stylesheet">

<script type="text/javascript">
	window.onload = function() {
		$("table button")
				.click(
						function() {
							//将学生id、课程id、和班级id嵌入到模态框中
							var userId = $(this).parent().siblings(
									"#userColumn").children(
									"input[name='userId']").val();
							var courseId = $(this).parent().siblings(
									"#CourseColumn").children("input").attr(
									"value");
							$("input[name='courseId']").val(courseId);
							var projectId = $(this).attr("id");
							$("input[name='stuId']").val(userId);
							$("input[name='projectId']").val(projectId);
							if (projectId != null) {
								var url = "${pageContext.request.contextPath}/student/studentAjax_listAspects.action";
								var args = {
									"linkId" : projectId,
									"time" : new Date()
								};
								$
										.ajax({
											url : url,
											type : "post",
											data : args,
											dataType : "json",
											success : function(data) {
												$(
														"#table_detail2 tr:not(:first)")
														.remove();
												//$("#evaDetails").show();
												//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
												var data = eval("(" + data
														+ ")");
												if (data.length == 0) {
													alert("当前班级没有环节可选...");
												} else {
													for ( var i = 0; i < data.length; i++) {
														var aspectId = data[i].id;
														var aspectName = data[i].name;
														var fenzhi = data[i].score;
														/*$("#aspect").append("<option value='"+aspectId+"'>"+aspectName+"</option>");*/
														var table_content = "<tr><td>"
																+ aspectName
																+ "<input style='display:none;' name='aspectId"+i+"' value='"+aspectId+"'/></td><td id='fenzhi'>"
																+ fenzhi
																+ "</td><td><input required class='tf_score' type='textfield' name='score"+i+"' />分</td></tr>";
														$("#table_detail2")
																.append(
																		table_content);
													}
													$("#table_detail2")
															.append(
																	"<input name='aspectCount' style='display:none;' value='"+data.length+"'/>");
												}
												/*//向服务器请求，将已经评价过的信息显示出来
												var url="${pageContext.request.contextPath}/teacher/teacherAjax_backEvalteData.action";
												//alert(url);
												var args={"projectId":projectId,"studentId":userId,"time":new Date()};
												$.ajax({
													url:url,
													type:"post",
													data:args,
													dataType:"json",
													success:function(data){
														
													},
													error:function(data){
														alert("服务器异常...");
													}
												});*/
											},
											error : function() {
												alert("出现错误，- -！");
											}

										});
							}
						});
		$("#submit").click(
				function() {
					var count = $("input[name='aspectCount']").val();
					var score;
					var edit_score;
					var pattern = /^[0-9]/;
					for ( var i = 0; i < count; i++) {
						edit_score = $("input[name='score" + i + "']");
						score = parseInt(edit_score.parent()
								.siblings("#fenzhi").text());
						//alert(edit_score.val()+":"+score);
						if (!pattern.test(edit_score.val())) {
							alert("分数填写不正常");
							return false;
						}
						if (score < edit_score.val()) {
							alert("分数填写不正常");
							return false;
						}
						if (edit_score.val() < 0) {
							alert("分数填写不正常");
							return false;
						}
					}
					return true;
				});
	}
</script>
</head>

<body>

	<div style="text-align: center;">
		<span class="span_pwd">对老师和课程进行评级和写评语</span> <br> <br> <br>
		<br>
		<div id="evaDetails" style="display: none1;">
			<div class="panel panel-default tab_panel">
				<!-- Default panel contents -->
				<div class="panel-heading">查询结果</div>
				<!-- Table -->
				<table class="table table-bordered table-hover table-condensed">
					<tbody>
						<th>课程名称</th>
						<th>教师名称</th>
						<th>评价状态</th>
					</tbody>

					<%
						List<Course> list_course = (List<Course>) request
								.getAttribute("courseList");
						List<CoursePingYu> pingyu = (List<CoursePingYu>) request
								.getAttribute("pingyuList");
						List<CoursePingJiaProject> list_project = (List<CoursePingJiaProject>) request
								.getAttribute("projectlist");
						for (Course c : list_course) {
							int temp = 0, p=0;
					%>
					<tr>
						<td id="CourseColumn"><input name="courseId"
							value="<%=c.getId()%>" style="display:none;" /> <%=c.getCourseName()%></td>
						<td id="teacher"><input name="teacher"
							value="<%=c.getId()%>" style="display:none;" /> <%=c.getTeacher().getTeacherName()%></td>
						<%
							for (CoursePingJiaProject project : list_project) {
									for (CoursePingYu pro : pingyu) {
										if (pro.getCourse().getId() == c.getId()) {
											if (pro.getCoursePingJiaProject().getId() == project
													.getId()) {
						%>
						<td>
							<button disabled="disabled"><%=pro.getCoursePingJiaProject()
										.getName()%></button>已评教</td>
						<%
							temp = 1;
												p = project.getId();
											}
										}
									}
									if (temp == 0||(temp==1&&p!=project.getId())) {
						%>
						<td>
							<button data-toggle="modal" data-target="#myModal"
								id="<%=project.getId()%>">
								<%=project.getName()%>
							</button></td>
						<%
							}
								}
						%>
					</tr>
					<%
						}
					%>
				</table>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<!-- 表单开始 -->
			<form
				action="${pageContext.request.contextPath}/student/studentEvalute_studentEvaluting.action"
				method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">对学生进行评级和写评语</h4>
					</div>
					<div class="modal-body">
						<div>
							<div id="evaDetails" style="display: none1;">
								<div class="panel panel-default seconde_tab_panel">
									<!-- Default panel contents -->
									<div class="panel-heading">查询结果</div>
									<input name="projectId" value="" style="display:none;" /> <input
										name="courseId" value="" style="display:none;" /> <input
										name="stuId" value="" style="display:none;" />
									<!-- Table -->
									<table id="table_detail2"
										class="table table-bordered table-hover table-condensed">
										<tbody>
											<th>评价标准</th>
											<th>分值</th>
											<th>分数</th>
										</tbody>
									</table>
								</div>
								<span class="evaluate_span">评语：</span>
								<textarea required name="evaluteResult"
									class="form-control teacher_evaluate" rows="6"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="submit" type="submit" class="btn btn-info">提交</button>
						<button type="reset" class="btn btn-info">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

</body>
</html>