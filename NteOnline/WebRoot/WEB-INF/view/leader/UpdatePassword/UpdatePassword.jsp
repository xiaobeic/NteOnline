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
  <title>无标题文档</title>
  	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_style.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/date/laydate.js" type="text/javascript" charset="utf-8"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 
 	var new_psw='';
 	var renew_psw='';
 	var bool=0;
 	var bool_2=0;
 	var bool_3=0;
  	function checkpsw(value)
  	{
  			var oldpsw=${sessionScope.user.password};
  	
			if(oldpsw==value)
			{
				document.getElementById("result_oldpsw").style.color="green";
				document.getElementById("result_oldpsw").innerText="输入正确";
				bool=1;
			}else
			{
				document.getElementById("result_oldpsw").style.color="red";
				document.getElementById("result_oldpsw").innerText="输入错误";
				bool=0;
			}
  	}
  	  	function checknewpsw(value)
  	{
  			var oldpsw=${sessionScope.user.password};
  			new_psw=value;
			if(oldpsw==value)
			{
				document.getElementById("result_newpsw").style.color="red";
				document.getElementById("result_newpsw").innerText="和原密码一致";
				bool_2=0;
			}else
			{
			if(value.length<5)
			{
				document.getElementById("result_newpsw").style.color="red";
				document.getElementById("result_newpsw").innerText="长度不能小于5";
				bool_2=0;
			}
			else{
			if(new_psw!=renew_psw)
			{
				document.getElementById("result_newpsw").style.color="red";
				document.getElementById("result_newpsw").innerText="输入不一致";
				ocument.getElementById("result_renewpsw").style.color="red";
				document.getElementById("result_renewpsw").innerText="输入不一致";
				bool_2=0;
			}else if(value.length>=5){
				document.getElementById("result_newpsw").style.color="green";
				document.getElementById("result_newpsw").innerText="输入正确";
				document.getElementById("result_renewpsw").style.color="green";
				document.getElementById("result_renewpsw").innerText="输入正确";
				bool_2=1;
				}
			}
			}
  	}
  		function checkrenewpsw(value)
  		{
  			if(value.length<5)
			{
				document.getElementById("result_renewpsw").style.color="red";
				document.getElementById("result_renewpsw").innerText="长度不能小于5";
				bool_3=0;
			}
			if(new_psw!=value)
			{
				document.getElementById("result_renewpsw").style.color="red";
				document.getElementById("result_renewpsw").innerText="输入不一致";
				document.getElementById("result_newpsw").style.color="red";
				document.getElementById("result_newpsw").innerText="输入不一致";
				renew_psw=value;
				bool_3=0;
			}
			else if(value.length>=5){
				document.getElementById("result_renewpsw").style.color="green";
				document.getElementById("result_renewpsw").innerText="输入正确";
				document.getElementById("result_newpsw").style.color="green";
				document.getElementById("result_newpsw").innerText="输入正确";
				renew_psw=value;
				bool_3=1;
			}
  		}
  		
  		function updpsw()
  		{
  			if(bool==1&&new_psw==renew_psw)
  			{
  				document.getElementById("updatepassword").submit();
  					alert("请重新登录");
  					window.parent.location.href="main/login_loginUI.action"; 
  			}
  			else
  			{
  				alert("输入有误");
  			}
  		}
  		
  </script>
</head>

<body>

    
    <div class="formbody">
    
    <div class="formtitle"><span>修改密码</span></div>
    <s:form id="updatepassword" action="leader/dudaocourse_updatepassword.action">
    <div style="width:500px;margin: auto;margin-top: 70px;">
    <ul class="forminfo">
    <li><label>旧密码：</label><input id="old_psw" onblur="checkpsw(this.value);" type="password"  class="loginpwd" />  <label id="result_oldpsw" style="color: red;float: right; "></label></li>
    <li><label>新密码：</label><input id="new_psw" onblur="checknewpsw(this.value);" name="password" type="password"  class="loginpwd" /> <label id="result_newpsw" style="color: red;float: right; "></label></li>
    <li><label>确认密码：</label><input id="renew_psw" onblur="checkrenewpsw(this.value);" type="password"  class="loginpwd" /><label id="result_renewpsw" style="color: red;float: right; "></label></li>
    <br />
    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="javascript:if(confirm('确实要更改吗?'))updpsw();"/></li>
    </ul>
 
    </div>
    </s:form>
    
    </div>


</body>

</html>
