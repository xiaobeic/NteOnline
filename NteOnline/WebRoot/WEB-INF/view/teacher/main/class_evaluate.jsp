<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			$("#main").hide();
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
		var classId;
		//响应由班级到具体信息的显示
		$("#stuClass").change(function(){
			$("#aspect option:not(:first)").remove();
			$("#main").hide();
			classId=$(this).val();
			if(classId != ""){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listStudentAspects.action";
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
									var aspectId=data[i].id;
									var aspectName=data[i].name;
									$("#aspect").append("<option value='"+aspectId+"'>"+aspectName+"</option>");
								}
			            	}
			            },
			            error:function(){
			            	alert("服务器故障，- -！");
			            }
				 
			    });
			}
		});
		$("#aspect").change(function(){
			$("#main").hide();
			var aspectId=$(this).val();
			if(aspectId != ""){
				var url="${pageContext.request.contextPath}/teacher/teacherAjax_listEcharts.action";
				//alert(url);
				var args={"classId":classId,"aspectId":aspectId,"time":new Date()};
				//alert("begin");
				$.ajax({  
			            url: url,  
			            type:"post",  
			            data:args,  
			            dataType:"json",  
			            success:function (data){
			            	$("#main").show();
			            	//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
			            	var data = eval("("+data+")");
			            	//alert(2);
			            	//alert(data.length);
			            	if(data.length==0){
			            		alert("当前环节还没有评价...");
			            	}else{
			            		my_echart(data);
			            	}
			            },
			            error:function(){
			            	alert("服务器故障，- -！");
			            }
				 
			    });
			}
		});
		function my_echart(data){
			// 路径配置
	        require.config({
	            paths: {
	                echarts: '${pageContext.request.contextPath}/build/dist'
	            }
	        });
	         require(
	            [
	                'echarts',
	                'echarts/chart/pie' ,// 使用柱状图就加载bar模块，按需加载
	                'echarts/chart/funnel'
	            ],
	            function (ec) {
	                // 基于准备好的dom，初始化echarts图表
	                var myChart = ec.init(document.getElementById('main')); 
	                
	         var option = {
	    title : {
	        text: '班级评价信息',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:[data[0].grade,data[1].grade,data[2].grade]
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	                {value:data[0].score, name:data[0].grade},
	                {value:data[1].score, name:data[1].grade},
	                {value:data[2].score, name:data[2].grade}
	            ]
	        }
	    ]
	};
	                                                      
	        
	                // 为echarts对象加载数据 
	                myChart.setOption(option); 
	            }
	        );
		}
			
	};
</script>
</head>
	
<body>
	<form action="" method="post">
		<span class="span_pwd">查看班级评价信息</span>
		<div class="select_group">
			<span>选择所授课程:</span>
			<select id="course" name="course" class="form-control input-sm">
				<option value="">请选择....</option>
				<c:forEach items="${CourseList }" var="Course">
					<option value="${Course.id}">${Course.courseName }</option>
				</c:forEach>
			</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>选择该课程下的班级:</span>
			<select id="stuClass" name="stuClass" class="form-control input-sm">
				<option value="">请选择....</option>	
				<%--<c:forEach items="${classList }" var="classes">
					<option value="${classes.id}">${classes.className }</option>
				</c:forEach>--%>
			</select>
			<span>选择该具体环节:</span>
			<select id="aspect" name="aspect" class="form-control input-sm">
				<option value="">请选择....</option>
			</select>
		</div>
	</form>
	<hr />
	
	<!-- 需要显示的饼状图 -->
	<div style="height: 500px;" class="main" id="main">
			
			
			
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/build/dist/echarts.js" ></script>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

</body>
</html>