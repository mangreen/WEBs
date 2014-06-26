<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.iii.esti.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">   
<title>Login</title>
</head>
	
<body>
<form name="userForm" action="LoginServlet" method="POST">
	Account:<input type="text" name="Account"><br>
	Password:<input type="text" name="Password"><br>
	<input type="submit" name="Submit" value="Send"><input type="submit" name="Submit" value="Register"><br>
</form>
	
<%
//禁止緩存
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
	/*
	if(request.getParameter("Account")!=null&&request.getParameter("Password")!=null){
		String Account = request.getParameter("Account");
		String Password = request.getParameter("Password");
		
		if(Account.equals("mike")&&Password.equals("1234")){
			session.setAttribute("Login","OK");
			response.sendRedirect("Member.jsp");
		}else{
			out.println("Login ERROR, Please check Account&&Password");
		}
	}
	*/
%>
</body>
</html>