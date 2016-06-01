<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		//响应由课程到班级
		var courseId;
		$("#course").change(function(){
			$("#stuClass option:not(:first)").remove();
			$("#aspect option:not(:first)").remove();
			$("#table_detail1 tr:not(:first)").remove();
			//$("#main").hide();
			courseId=$(this).val();
			if(courseId != ""){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listClasses.action";
				//alert(url);
				var args={"courseId":courseId,"time":new Date()};
				//alert("begin");
				$.ajax({  
			            url: url,  
			            type:"post",  
			            data:args,  
			            dataType:"json",  
			            success:function (data){
			            	//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
			            	var data = eval("("+data+")");
			            	//alert(2);
			            	//alert(data.length);
			            	if(data.length==0){
			            		alert("当前课程没有班级可选...");
			            	}else{
				            	for(var i=0;i<4;i++){
									var classId=data[i].id;
									var className=data[i].className;
									$("#stuClass").append("<option value='"+classId+"'>"+className+"</option>");
								}
			            	}
			            },
			            error:function(){
			            	alert("服务器故障，- -！");
			            }
				 
			    });
			}
		});
		$("table button").click(function(){
			//将学生id、课程id、和班级id嵌入到模态框中
			$("input[name='courseId']").val($("#course option:selected").val());
			$("input[name='classId']").val($("#stuClass option:selected").val());
			var userId=$(this).parent().siblings("#userColumn").children("input[name='userId']").val();
			var projectId=$(this).attr("id");
			$("input[name='stuId']").val(userId);
			$("input[name='projectId']").val(projectId);
			if(projectId!=null){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listAspects.action";
				//alert(url);
				var args={"linkId":projectId,"time":new Date()};
				var as_count;
				$.ajax({  
		            url: url,  
		            type:"post",  
		            data:args,  
		            dataType:"json",  
		            success:function (data){
		            	$("#table_detail2 tr:not(:first)").remove();
		            	//$("#evaDetails").show();
		            	//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
		            	var data = eval("("+data+")");
		            	if(data.length==0){
		            		alert("当前班级没有环节可选...");
		            	}else{
		            		as_count=data.length;
		            		for(var i=0;i<data.length;i++){
								var aspectId=data[i].id;
								var aspectName=data[i].name;
								var fenzhi=data[i].fenzhi;
								/*$("#aspect").append("<option value='"+aspectId+"'>"+aspectName+"</option>");*/
								var table_content="<tr><td>"+aspectName+"<input style='display:none;' name='aspectId"+i+"' value='"+aspectId+"'/></td><td id='fenzhi'>"+fenzhi+"</td><td><input required class='tf_score' type='textfield' name='score"+i+"' />分</td></tr>";
								$("#table_detail2").append(table_content);
							}
		            		//$("#table_detail2").append("<input name='aspectCount' style='display:none;' value='"+data.length+"'/>");
		            		$("input[name='aspectCount']").val(data.length);
		            	}
		            	//向服务器请求，将已经评价过的信息显示出来
		            	var url="${pageContext.request.contextPath}/teacher/teacherAjax_showEvaluteInfo.action";
						//alert(url);
						var args={"projectId":projectId,"studentId":userId,"courseId":$("#course option:selected").val(),"time":new Date()};
		            	$.ajax({
		            		url:url,
		            		type:"post",
		            		data:args,
		            		dataType:"json",
		            		success:function(data){
		            			var data = eval("("+data+")");
								if(data.length!=0){
			            			$("textarea").val(data[0].pingYu);
			            			$("textarea").attr("disabled",true);
			            			$("#submit").attr("disabled",true);
			            			$("#reset").attr("disabled",true);
								}else{
									$("textarea").val("");
									$("textarea").attr("disabled",false);
			            			$("#submit").attr("disabled",false);
			            			$("#reset").attr("disabled",false);
			            			for(var i=0;i<as_count;i++){
			            				$("input[name='score"+i+"']").val("");
			            			}
								}
								//继续发送ajax请求获得score的情况，并显示出来
								//向服务器请求，将已经评价过的信息显示出来
				            	var url="${pageContext.request.contextPath}/teacher/teacherAjax_showEvaluteScore.action";
								//alert(url);
								var args={"projectId":projectId,"studentId":userId,"courseId":$("#course option:selected").val(),"time":new Date()};
								$.ajax({
				            		url:url,
				            		type:"post",
				            		data:args,
				            		dataType:"json",
				            		success:function(data){
				            			var data = eval("("+data+")");
										for(var i=0;i<data.length;i++){
											$("input[name='score"+i+"']").val(data[i].score);
											$("input[name='score"+i+"']").attr("disabled","true");
										}
				            		},
				            		error:function(){
				            			
				            		}
				            	});
		            		},
		            		error:function(){
		            			alert("服务器异常...");
		            		}
		            	});
		            },
		            error:function(){
		            	alert("出现错误，- -！");
		            }
			 
		    	});
			}
		});
		$("#submit").click(function(){
			var count=$("input[name='aspectCount']").val();
			var score;
			var edit_score;
			var pattern=/^-?\d+$/;
			for(var i=0;i<count;i++){
				edit_score=$("input[name='score"+i+"']");
				score=parseInt(edit_score.parent().siblings("#fenzhi").text());
				//alert(edit_score.val()+":"+score);
				if(isNaN(edit_score.val())){
					alert("输入的值："+edit_score.val()+" 分值格式不正确");
					return false;
				}
				if(score<edit_score.val()){
					alert("输入的值："+edit_score.val()+" 分数过大");
					return false;
				}
				if(edit_score.val()<0){
					alert("输入的值："+edit_score.val()+" 分数过小");
					return false;
				}
			}
			return true;
		});
	}
</script>
</head>
	
<body>
<div>
	<form action="${pageContext.request.contextPath}/teacher/teacherEvalute_teacherSeeStudentInfo.action" method="post">
	<span class="first_span_pwd">请选择课程和班级对学生进行评级和写评语</span>
	<div class="select_group">
		<span>选择所授课程:</span>
		<s:select id="course" name="course"  theme="simple" cssClass="form-control"
		    list="#request.CourseList"  
		    listKey="id" 
			listValue="courseName"  
		    value="#request.courseKey"   
		    headerKey="0"
		    headerValue="请选择....">  
		</s:select>
		<%--  
		<select id="course" name="course" class="form-control input-sm">
			<option value="">请选择....</option>
			<c:forEach items="${CourseList }" var="Course">
			<option value="${Course.id}">${Course.courseName }</option>
			</c:forEach>
		</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		--%>
		<span>选择该课程下的班级:</span>
		<s:select id="stuClass" name="stuClass"   theme="simple" cssClass="form-control"
		    list="#request.ClassList"  
		    listKey="id" 
			listValue="className"  
		    value="#request.classKey"   
		    headerKey="0"
		    headerValue="请选择....">  
		</s:select>
		<%--
		<select id="stuClass" name="stuClass" class="form-control input-sm">
			<option value="">请选择....</option>	
			<c:forEach items="${classList }" var="classes">
				<option value="${classes.id}">${classes.className }</option>
			</c:forEach>
		</select>
		--%>
		<button type="submit">查询</button>
	</div>
	</form>
<div id="evaDetails" style="display: none1;">	
	<div class="panel panel-default first_tab_panel">
		<!-- Default panel contents -->
		<div class="panel-heading">查询结果</div>
		<!-- Table -->
		<table id="table_detail1" class="table table-bordered table-hover table-condensed student_table_result">
			<tbody>
				<th>学生姓名</th>
				<th colspan="4">环节评价状态</th>
			</tbody>
			<%
				List<List<Integer>>validate=(List<List<Integer>>)request.getAttribute("evalute_validate");
			%>
			<s:iterator id="student" value="#request.studentsList" >
				<tr>
					<td id="userColumn">
						<s:property value="#student.name"/><input name="userId" value='<s:property value="#student.id"/>' style="display: none;" />
					</td>
					<s:iterator id="project" value="#request.listCourseProject" >
						<s:if test="#request.evalute_validate.get(0).contains(#project.id)">
							<td>
								<button data-toggle="modal" data-target="#myModal" id='<s:property value='#project.id' />'><s:property value="#project.name"/></button>					
								<span style="color:red">已经评论</span>
							</td>
						</s:if>
						<s:else>
							<td>
								<button data-toggle="modal" data-target="#myModal" id='<s:property value='#project.id' />'><s:property value="#project.name"/></button>
								<span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>					
							</td>
						</s:else>
					</s:iterator>
				</tr>
				<%
					if(validate!=null)
						validate.remove(0);
				%>
			</s:iterator> 
		</table>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
  	<!-- 表单开始 -->
	<form action="${pageContext.request.contextPath}/teacher/teacherEvalute_teacherEvaluting.action" method="post">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">对学生进行评级和写评语</h4>
      </div>
      <div class="modal-body">
        <div>
			<div id="evaDetails" style="display: none1;">	
			<div class="panel panel-default seconde_tab_panel">
				<!-- Default panel contents -->
				<div class="panel-heading">查询结果</div>
				<input name="projectId" value="" style="display:none;"/>
				<input name="courseId" value="" style="display:none;"/>
				<input name="classId" value="" style="display:none;"/>
				<input name="stuId" value="" style="display:none;"/>
				<input name="aspectCount" value="" style="display:none;"/>
				<!-- Table -->
				<table id="table_detail2" class="table table-bordered table-hover table-condensed">
					<tbody>
						<th>评价标准</th>
						<th>分值</th>
						<th>分数</th>
					</tbody>
				</table>
			</div>
			<span class="evaluate_span">评语：</span>
			<textarea required name="evaluteResult" class="form-control teacher_evaluate" rows="6"></textarea>
			</div>
		</div>
      </div>
      <div class="modal-footer">
		<button id="submit" type="submit" class="btn btn-info">提交</button>
		<button id="reset" type="reset" class="btn btn-info">重置</button>
      </div>
    </div>
	</form>
  </div>
</div>	

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

</body>
</html>