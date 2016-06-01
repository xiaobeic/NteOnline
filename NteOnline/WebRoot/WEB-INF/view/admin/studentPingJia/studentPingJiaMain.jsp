<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'studentPingJiaMain.jsp' starting page</title>
    
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>

<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">
<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></SCRIPT>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){

    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
});
});
	function show_(project,student,course)
	{
	$.ajax({
		
		type:'post',
		dataType:'JSON',
		url:"${pageContext.request.contextPath}/studentAjax/coursetype1_score_.action?projectid="+project+"&courseid="+course+"&studentid="+student,
		success:function cbf(data)
		{
			$("#typeTable").html("");
			$.each(data, function(i, item) {
            $("#typeTable").append(
            		"<tr>"+
            		""+
            		""+
                    "<td><input style='display:none;' name='courseid' type='text' value='"+course+"'/>" + item[7] + "</td>" + 
                    "<td><input style='display:none;' name='projectid' type='text' value='"+project+"'/>" + item[8]    + "</td>" +
                    "<td><input readOnly='true' type='text' name='score"+item.id+"'value='"+item[1]+"' ></input></td>" + "</tr>");
                  
        });
		},
		error:function cbf1(data)
		{
			alert('出错啦 ');	
		}
		});
		
		   //获取评语
        $.ajax({
		
		type:'post',
		dataType:'JSON',
		url:"${pageContext.request.contextPath}/studentAjax/coursetype1_py.action?projectid="+project+"&courseid="+course+"&studentid="+student,
		success:function cbf(data)
		{
			$.each(data, function(i, item) {
			$("#project_yu").attr("readOnly","true");
			$("#project_yu").val(item[1]);
        });
     
		},
		error:function cbf1(data)
		{
			alert('出错啦 ');	
		}
		});
		
		}
</script>
</head>


<body>
    
    <div class="rightinfo">
    
    <div id="tab2" class="tabson">
    
  
    <ul class="seachform">
      <s:form action="/admin/studentPingJiaInfo_studentPingJiaMain.action">
    <li><label>课程名</label>  
    <div class="vocation">
    	<s:select name="courseid" list="courses" listKey="id" listValue="courseName" theme="simple" cssClass="select2">
    		
    	</s:select>
    </div>
    </li>
    
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" id="btn_login" value="查询"/></li>
      </s:form>
    </ul>
  
    <table class="tablelist">
    	<thead>
    	<tr id="header">
     			<th>评价教师</th>
     		<th>被评价学生</th>
     		<s:iterator var="projects" value="projects">
     			<th>${name }总成绩</th>
     		</s:iterator>
     
        </tr>
        <% int a=0; %>
        </thead>
        <tbody>

			<s:if test="#scores==null"></s:if>
			<s:else>
				<s:iterator var="scores" value="#scores" status="status" id="status"> 
					<tr>
                				<td>${teacherName }</td>
                					<td>${studentName }</td>		
                					<s:iterator var="projects2" value="projects_score">
                						<s:iterator var="scores_sum" value="sum_score">
                						
                							<s:if test="#projects2.id==#scores_sum.studentPingJiaProject.id"><td><button data-toggle="modal" data-target="#myModal" onclick="show_(${projects2.id},${scores_sum.student.id},${scores_sum.course.id})">${score }</button><% a=1; %></td></s:if>
                						</s:iterator>
                					<% if(a==0){ %>
                					
                							<td>未评价</td>
                					<%}a=0; %>
                					</s:iterator>
                				
					</tr>
				</s:iterator>
			</s:else>
      
        </tbody>
    </table>
    
   
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
   
  
    
    </div>  
    
    
    
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
	<form action="${pageContext.request.contextPath}/leader/dudaocourse_addscore.action" method="post">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">对课程进行评级和写评语</h4>
      </div>
      <div class="modal-body">
        <div>
			<div id="evaDetails" style="display: none1;">	
			<div class="panel panel-default seconde_tab_panel">
				<!-- Default panel contents -->
				<div class="panel-heading">查询结果</div>
				<span id="stuId"></span>
				<!-- Table -->
				<table id="table_detail" class="table table-bordered table-hover table-condensed">
					<thead>
						<th>评价标准</th>
						<th>分值</th>
						<th>分数</th>
					</thead>
					<tbody id="typeTable">
						
					</tbody>
				</table>
			</div>
			<span class="evaluate_span">评语：</span>
			<textarea name="project_yu" id="project_yu" class="form-control teacher_evaluate" rows="6"></textarea>
			</div>
		</div>
      </div>
      <div id="submit_reset" class="modal-footer">
		
		<button  type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
      </div>
    </div>
	</form>
  </div>
</div>	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/editor/kindeditor.js"></script>
</body>

</html>
    