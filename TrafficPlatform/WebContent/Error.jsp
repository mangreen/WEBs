<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Login ERROR, Please check Account&&Password</p>
<% 

		out.println("<br>After 3 seconds, you will be back to Login.jsp");
		
		response.setHeader("Refresh", "3;URL=Login.jsp");
%>
</body>
</html>