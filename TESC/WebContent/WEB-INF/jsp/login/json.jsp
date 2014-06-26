<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
  <%@page import="java.lang.reflect.Method" %>
<%

Object obj = request.getAttribute("model");
Method[] m = obj.getClass().getDeclaredMethods();
boolean printJson = true;

String attr = "{";
for (int i=0;i<m.length;i++){
	
	if(m[i].getName().startsWith("get")){		
		attr+= m[i].getName().substring(3).toLowerCase()+":'"+m[i].invoke(obj)+"',";
	}
		
}
attr = attr.substring(0,attr.length()-1)+"}";
out.print(attr);
System.out.println(attr);

%>