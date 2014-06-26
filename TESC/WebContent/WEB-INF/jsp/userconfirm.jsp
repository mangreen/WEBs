<%@ page contentType="text/html; charset=BIG5" import="java.util.*"
    pageEncoding="UTF-8"%>

<%

String user=request.getParameter("username");
String[] username = {"kevin", "jimmy", "fendy", "admin"};
//String[] emailaddress = {"kevin@hot.com", "jimmy@hot.com", "fendy@hot.com", "admin@hot.com"};
System.out.print(username);
String uservalidator="true";
for(int i=0 ; i < username.length ; i++){
	if(user.equals(username[i])){
		uservalidator="false";
		break;
	}else{
		uservalidator="true";
	}
}
System.out.print(username);
out.print(uservalidator);

//SET RETURN DATA
//CONSOLE CONTROLE。
 %>
