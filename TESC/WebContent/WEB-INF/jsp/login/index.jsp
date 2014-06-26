<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
  <%@page import="java.lang.reflect.Method" %>
<%

Object obj = request.getAttribute("model");
response.sendRedirect("index.html");
%>