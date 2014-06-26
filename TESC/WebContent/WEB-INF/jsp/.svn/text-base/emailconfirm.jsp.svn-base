<%@ page contentType="text/html; charset=BIG5" import="java.util.*"
    pageEncoding="UTF-8"%>

<%

String email=request.getParameter("email");
String[] emailaddress = {"kevin@hot.com", "jimmy@hot.com", "fendy@hot.com", "admin@hot.com"};
String emailvalidator="true";

for(int i=0 ; i < emailaddress.length ; i++){
	if(email.equals(emailaddress[i])){
		emailvalidator="false";
		break;
	}else{
		emailvalidator="true";
	}
}

out.print(emailvalidator);

//SET RETURN DATA
//CONSOLE CONTROLE。
 %>
