<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0"> 
	
	<title>Insert title here</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>	
	<script type="text/javascript">
	$(document).ready(function(){
		$("<p>Hi!!!!</p>").insertAfter("#followMe");
		getJson();
	});

	function getJson(){
	    var s='';
	    $.ajax({
	        type:'post',//请求方式
	        url:'ListServlet', // AJAX HTTP请求接口
	        data:'',//提交到服务器接口的参数 比如'{cid:0405}'，结果为out.jsp?cid=0405格式
	        dataType:'json',//请求类型为json, 更多见jquery doc文档 
	        timeout:7000,//请求超时后停止请求
	        success: function(data){
	            $.each(data,function(i){
	                s+=('<p>学号:'+data[i].id +'&nbsp;&nbsp;|&nbsp;&nbsp;姓名:'+data[i].name+'</p><hr/>');
	            });
	            $('#out').html(s);
	        },
        	error:function(xhr, ajaxOptions, thrownError){
	        	alert(xhr.status);
                alert(thrownError);
	        }
        });
	}

	/*
	function getOut(){
	    var s='';
	    $.ajax({
	        type:'get',//请求方式
	        url:'out.jsp', // AJAX HTTP请求接口
	        data:'',//提交到服务器接口的参数 比如'{cid:0405}'，结果为out.jsp?cid=0405格式
	        dataType:'json',//请求类型为json, 更多见jquery doc文档 
	        timeout:7000,//请求超时后停止请求
	        success: function(data){
	            $.each(data,function(i){
	                s+=('<p>学号:'+data[i].id +'&nbsp;&nbsp;|&nbsp;&nbsp;姓名:'+data[i].name+'</p><hr/>');
	            });
	            $('#out').html(s);}});
	}
	setTimeout('getOut()',1000);
	*/
	</script>
</head>

<body>
	
	<p id="followMe">Follow me!</p>
	<div id="out"> 
		正在载入学生数据...
	</div>
	
</body>
</html>