<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>后台登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/non-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/teacher.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.metadata.js"></script>

<script type="text/javascript">

 jQuery.extend(jQuery.validator.messages, {
    required: "请填写本字段",
    remote: "验证失败",
    email: "请输入正确的电子邮件",
    url: "请输入正确的网址",
    date: "请输入正确的日期",
    dateISO: "请输入正确的日期 (ISO).",
    number: "请输入正确的数字",
    digits: "请输入正确的整数",
    creditcard: "请输入正确的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入指定的后缀名的字符串",
    maxlength: jQuery.validator.format("允许的最大长度为 {0} 个字符"),
    minlength: jQuery.validator.format("允许的最小长度为 {0} 个字符"),
    rangelength: jQuery.validator.format("允许的长度为{0}和{1}之间"),
    range: jQuery.validator.format("请输入介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
    min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});


	$(function(){
		$("#submit").validate();
	});
	$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
})
	function show_type(course,projectid,value)
	{
	$("#project_yu").attr("readOnly","false");
		$("#project_yu").val("");
		//判断是否已经评价过 value=0代表未评价
	if(value==0)
	{
		$.ajax({
		
		type:'post',
		dataType:'JSON',
		url:"${pageContext.request.contextPath}/leaderAjax/coursetype_type.action?id="+projectid,
		success:function cbf(data)
		{
			$("#project_yu").removeAttr("readOnly");
				$("#submit_reset").attr("style","display:block;");
			$("#close").attr("style","display:none");
			$("#typeTable").html("");
			$.each(data, function(i, item) {
            $("#typeTable").append(
            		"<tr>"+
            		""+
            		""+
                    "<td><input style='display:none;' name='courseid' type='text' value='"+course+"'/>" + item[1] + "</td>" + 
                    "<td><input style='display:none;' name='projectid' type='text' value='"+projectid+"'/>" + item[2]    + "</td>" +
                    "<td><input type='text' class='{required:true,min:0,max:"+item[2]+"}' name='score"+item[0]+"'></input></td>" + "</tr>");
                  
        });
		},
		error:function cbf1(data)
		{
			alert('出错啦 ');	
		}
		});
		}
		
		else //已评价，则获取成绩
		{
		
		$.ajax({
		
		type:'post',
		dataType:'JSON',
		url:"${pageContext.request.contextPath}/leaderAjax/coursetype_score.action?projectid="+projectid+"&courseid="+course,
		success:function cbf(data)
		{
			$("#typeTable").html("");
			$.each(data, function(i, item) {
            $("#typeTable").append(
            		"<tr>"+
            		""+
            		""+
                    "<td><input style='display:none;' name='courseid' type='text' value='"+course+"'/>" + item[7] + "</td>" + 
                    "<td><input style='display:none;' name='projectid' type='text' value='"+projectid+"'/>" + item[10]    + "</td>" +
                    "<td><input readOnly='true' type='text' name='score"+item[0]+"'value='"+item[1]+"' ></input></td>" + "</tr>");
                  
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
		url:"${pageContext.request.contextPath}/leaderAjax/coursetype_py.action?projectid="+projectid+"&courseid="+course,
		success:function cbf(data)
		{
			$.each(data, function(i, item) {
			$("#project_yu").attr("readOnly","true");
			$("#project_yu").val(item[1]);
			$("#submit_reset").attr("style","display:none;");
			$("#close").attr("style","display:block;");
        });
     
		},
		error:function cbf1(data)
		{
			alert('出错啦 ');	
		}
		});
		
		}
		
	}
</script>
</head>
	
<body>
<div id="evaDetails" style="display: none1;">	
	<div class="panel panel-default first_tab_panel">
		<!-- Default panel contents -->
		<!-- Table -->
		<table id="table_detail" class="table table-bordered table-hover table-condensed student_table_result">
			<thead>
				<th style="text-align: center;">课程名称</th>
				
				<th style="text-align: center;">环节评价状态</th>
			</thead>
				<tbody>
			<s:iterator id="course" value="courselist" var="course">
				<tr>
					<td>${courseName }<input name="userId" style="display: none;" /></td>
					<s:iterator id="project" value="projects" var="project">
						<td>
						<% int v=0; %>
						<s:iterator value="duDaoYuanToCoursePingJiaScores" var="scores">
						
						<s:if test="#scores.course.id==#course.id">
						<% v=1; %>
						</s:if>
							
						</s:iterator>
							<button data-toggle="modal" data-target="#myModal" href="" onclick="show_type('${course.id}',${id},<%if(v==1){ %>'1'<%}else {%>'0'<%} %>)">${name}</button> 状态:<SPAN><%if(v==1){ %>已评价<%}else{ %>未评价<%} %></SPAN>				
						</td>
					</s:iterator>
				</tr>
			</s:iterator>
			</tbody>
		</table>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
	<form id="submit" action="${pageContext.request.contextPath}/leader/dudaocourse_addscore.action" method="post">
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
			<textarea name="project_yu" id="project_yu" class="{required:true} form-control teacher_evaluate" rows="6"></textarea>
			</div>
		</div>
      </div>
      <div id="submit_reset" class="modal-footer">
		<button id="submit" type="submit" class="btn btn-info">提交</button>
		<button type="reset" id="reset" class="btn btn-info">重置</button>
		
      </div>
        <div id="close" class="modal-footer" style="display: none;">
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

<SCRIPT type="text/javascript">
(function(jQuery){  

if(jQuery.browser) return;  

jQuery.browser = {};  
jQuery.browser.mozilla = false;  
jQuery.browser.webkit = false;  
jQuery.browser.opera = false;  
jQuery.browser.msie = false;  

var nAgt = navigator.userAgent;  
jQuery.browser.name = navigator.appName;  
jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);  
jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);  
var nameOffset,verOffset,ix;  

// In Opera, the true version is after "Opera" or after "Version"  
if ((verOffset=nAgt.indexOf("Opera"))!=-1) {  
jQuery.browser.opera = true;  
jQuery.browser.name = "Opera";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+6);  
if ((verOffset=nAgt.indexOf("Version"))!=-1)  
jQuery.browser.fullVersion = nAgt.substring(verOffset+8);  
}  
// In MSIE, the true version is after "MSIE" in userAgent  
else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {  
jQuery.browser.msie = true;  
jQuery.browser.name = "Microsoft Internet Explorer";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+5);  
}  
// In Chrome, the true version is after "Chrome"  
else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {  
jQuery.browser.webkit = true;  
jQuery.browser.name = "Chrome";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+7);  
}  
// In Safari, the true version is after "Safari" or after "Version"  
else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {  
jQuery.browser.webkit = true;  
jQuery.browser.name = "Safari";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+7);  
if ((verOffset=nAgt.indexOf("Version"))!=-1)  
jQuery.browser.fullVersion = nAgt.substring(verOffset+8);  
}  
// In Firefox, the true version is after "Firefox"  
else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {  
jQuery.browser.mozilla = true;  
jQuery.browser.name = "Firefox";  
jQuery.browser.fullVersion = nAgt.substring(verOffset+8);  
}  
// In most other browsers, "name/version" is at the end of userAgent  
else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <  
(verOffset=nAgt.lastIndexOf('/')) )  
{  
jQuery.browser.name = nAgt.substring(nameOffset,verOffset);  
jQuery.browser.fullVersion = nAgt.substring(verOffset+1);  
if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) {  
jQuery.browser.name = navigator.appName;  
}  
}  
// trim the fullVersion string at semicolon/space if present  
if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1)  
jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);  
if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1)  
jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);  

jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10);  
if (isNaN(jQuery.browser.majorVersion)) {  
jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);  
jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);  
}  
jQuery.browser.version = jQuery.browser.majorVersion;  
})(jQuery); 
</SCRIPT>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.metadata.js"></script>
</body>
</html>