<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录</title>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">

<script type="text/javascript">
	window.onload=function(){
		//响应由班级到学生
		$("#stuClass").change(function(){
			$("#student option:not(:first)").remove();
			$("#link option:not(:first)").remove();
			$("#aspect option:not(:first)").remove();
			$("#evaDetails").hide();
			var classId=$(this).val();
			//alert(classId);
			if(classId != ""){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listStudents.action";
				//alert(url);
				var args={"classId":classId,"time":new Date()};
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
			            		alert("当前班级没有环节可选...");
			            	}else{
				            	for(var i=0;i<4;i++){
									var stuId=data[i].id;
									var stuName=data[i].name;
									$("#student").append("<option value='"+stuId+"'>"+stuName+"</option>");
								}
			            	}
			            },
			            error:function(){
			            	alert("出现错误，- -！");
			            }
				 
			    });
			}
		});
		//响应由学生到环节
		$("#student").change(function(){
			$("#link option:not(:first)").remove();
			$("#aspect option:not(:first)").remove();
			$("#evaDetails").hide();
			var studentId=$(this).val();
			//alert(classId);
			if(studentId != ""){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listLinks.action";
				//alert(url);
				var args={"studentId":studentId,"time":new Date()};
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
			            		alert("当前学生没有环节可选...");
			            	}else{
				            	for(var i=0;i<data.length;i++){
									var linkId=data[i].id;
									var linkName=data[i].name;
									$("#link").append("<option value='"+linkId+"'>"+linkName+"</option>");
								}
			            	}
			            },
			            error:function(){
			            	alert("出现错误，- -！");
			            }
				 
			    });
			}
		});
		//响应由环节到方面
		$("#link").change(function(){
			$("#aspect option:not(:first)").remove();
			$("#table_detail tr:not(:first)").remove();
			$("#evaDetails").hide();
			var linkId=$(this).val();
			//alert(classId);
			if(linkId != ""){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listAspects.action";
				//alert(url);
				var args={"linkId":linkId,"time":new Date()};
				$.ajax({  
		            url: url,  
		            type:"post",  
		            data:args,  
		            dataType:"json",  
		            success:function (data){
		            	$("#evaDetails").show();
		            	//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
		            	var data = eval("("+data+")");
		            	if(data.length==0){
		            		alert("当前班级没有环节可选...");
		            	}else{
		            		for(var i=0;i<data.length;i++){
								var aspectId=data[i].id;
								var aspectName=data[i].name;
								var fenzhi=data[i].fenzhi;
								/*$("#aspect").append("<option value='"+aspectId+"'>"+aspectName+"</option>");*/
								var table_content="<tr><td>"+aspectName+"</td><td>"+fenzhi+"</td><td><input class='tf_score' type='textfileld' name='result' />分</td></tr>";
								$("#table_detail").append(table_content);
							}
		            	}
		            },
		            error:function(){
		            	alert("出现错误，- -！");
		            }
			 
		    	});
			}
		});
	}
</script>
</head>
	
<body>
<div>
	<span class="span_pwd">对学生进行评级和写评语</span>
	<div class="select_group">
		<div class="select_group1">
			<span>选择要评价的课程:</span>
			<select id="courseClass" class="form-control input-sm">
				<option value="">请选择....</option>
				<c:forEach items="${CourseList }" var="Course">
					<option value="${Course.id}">${Course.courseName }</option>
				</c:forEach>
			</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>选择要评价的班级:</span>
			<select id="stuClass" class="form-control input-sm">
				<option value="">请选择....</option>
				<%--<c:forEach items="${classList }" var="classes">
					<option value="${classes.id}">${classes.className }</option>
				</c:forEach>
			--%></select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div class="select_group2">
			<span>选择要评价的学生:</span>
		    <select id="student" class="form-control input-sm">
		        <option value="">请选择....</option>
		    </select>
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>选择要评价的环节:</span>
		    <select id="link" class="form-control input-sm">
		        <option value="">请选择....</option>
		    </select>
	    </div>
	</div>
	<form action="" method="post">
	
	<div id="evaDetails">	
	<div class="panel panel-default tab_panel">
		<!-- Default panel contents -->
		<div class="panel-heading">查询结果</div>
		<!-- Table -->
		<table id="table_detail" class="table table-bordered table-hover table-condensed ">
			<tbody>
				<th>评价标准</th>
				<th>分值</th>
				<th>分数</th>
			</tbody>
		</table>
	</div>
	<span class="evaluate_span">评语：</span>
	<textarea class="form-control teacher_evaluate" rows="6"></textarea>
	<button type="submit" class="btn btn-info">提交</button>
	<button type="reset" class="btn btn-info">重置</button>
	</div>
	</form>
</div>
	

</body>
</html>