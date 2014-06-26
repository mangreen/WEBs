<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.RoutingManager" %>

<%
	String mResponse;
	request.setCharacterEncoding("UTF-8");
	RoutingManager.setDebugMsgEnabled(true);
	
	if(null == request.getQueryString())
		mResponse = RoutingManager.getDirection(null, null, null, null, false);
	else
		mResponse = RoutingManager.getDirection(
	            request.getParameter("origin"),
	            request.getParameter("destination"),
	            request.getParameter("mode"),
	            request.getParameter("transit_avoid"),
	            Boolean.parseBoolean(request.getParameter("alternative")));
	
	response.setCharacterEncoding("UTF-8");
	response.getWriter().print(mResponse);
%>
