<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
  <%@page import="java.lang.reflect.Method" %>
<%

Object obj = request.getAttribute("model");
Method[] m = obj.getClass().getDeclaredMethods();

String attr = "";
for (int i=0;i<m.length;i++){
	
	if(m[i].getName().equals("getText")){	
		
		attr = (String) m[i].invoke(obj);
		out.print(attr);
		break;
	}		
}
%>