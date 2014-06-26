<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
  <%@page import="java.lang.reflect.Method" %>
<%

Object obj = request.getAttribute("model");
Method[] m = obj.getClass().getDeclaredMethods();
out.print("{");
String attr = "";
for (int i=0;i<m.length;i++){
	
	if(m[i].getName().equals("getText")){		
		out.print(m[i].invoke(obj));
	}	
}
//out.print(attr.substring(0,attr.length()-1));
//out.print("}");
//System.out.println(attr);
%>