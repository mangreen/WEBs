<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ page import="edu.ntnu.tesc.module.viewmodule.*" %>
<%
Object obj = request.getAttribute("model");
if(obj !=null){
	if(obj instanceof IModule){
		String jsonString = ((IModule)obj).toJSONString(); 
		
		out.print(jsonString.replaceAll("\n","<br />"));
		System.out.println(jsonString);
	}	
	else{
		System.out.println("model type invalid!!");
	}
}
else{
	System.out.println("model is null!!");
}
%>