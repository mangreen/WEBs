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
</head>
<body>

<% 
	//禁止緩存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	
	String Login = (String)session.getAttribute("Login");

	if(Login != null&&Login.equals("OK")){
		out.println("Welcome");
		//session.invalidate();
	}else{
		out.println("Please login first");
		out.println("<br>After 5 seconds, you will be back to Login.jsp");
		
		response.setHeader("Refresh", "5;URL=Login.jsp");
	}
%>

</body>
</html>