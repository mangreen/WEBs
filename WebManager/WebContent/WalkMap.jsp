<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.RouteManager" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//禁止緩存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	
	request.setCharacterEncoding("UTF-8");
	
	String user = request.getParameter("user");
	String password = request.getParameter("password");
	
	String origin = request.getParameter("origin");
	String destination = request.getParameter("destination");
	String mode = request.getParameter("mode");	
	String transit_avoid = request.getParameter("transit_avoid");
	String alternative = request.getParameter("alternative");
	
	//maps.google.com/maps/api/directions/json?origin=25.053471, 121.55048&destination=25.058496, 121.55433&mode=walking&language=zh-TW&sensor=false
	//out.print(RouteManager.AssignJob(origin, destination, mode, transit_avoid, alternative));
	out.print(RouteManager.assignJob("25.053471,121.554880,1", "25.058721,121.554735,13,III", "walk", "bus", "true"));
	//out.print(RouteManager.startGDARoute("25.058657,121.554880", "25.058721,121.554735"));
	//out.print(RouteManager.startIdrRoute(25058657, 121554880, 1, 25058721, 121554735, 13, "III"));
	//RouteManager.setMessage1("cubic");
	//RouteManager.getPoiExit(25058657, 121554880, 1, "mrt_yc");
%>
</body>
</html>