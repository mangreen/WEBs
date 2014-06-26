<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%

String username=request.getParameter("account");
String password=request.getParameter("password");
String success="";
System.out.print(username);
System.out.print(password);
if(username.equals("kevin") && password.equals("12345")){
	success="true";
}else{
	success="false";
}
System.out.print(success);
System.out.print("{ "+"username: '"+username+"' , password: '"+password+"' , success: '"+success+"'"+" }");
out.print("{ "+"username: '"+username+"' , password: '"+password+"' , success: '"+success+"'"+" }");//设置返回值
//这里可以做后台操作。
 %>