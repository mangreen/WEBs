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
	//out.print(origin+"; "+destination+"; "+mode+"; "+transit_avoid+"; "+alternative);
	
	//maps.google.com/maps/api/directions/json?origin=25.053471, 121.55048&destination=25.058496, 121.55433&mode=walking&language=zh-TW&sensor=false
	//http://localhost:8080/WalkMap/WalkMap.jsp?origin=25.053471,121.554880,1&destination=25.058721,121.554735,13,III&mode=walk&transit_avoid=bus&alternative=true
			
	//out.print(RouteManager.assignJob("25.053471,121.554880,1", "25.058721,121.554735,13,III", "walk", "none", "true"));
	
	if(null == origin || null == destination || null == mode){
		out.print("wrong parameters");
	}else if(null != transit_avoid && null != alternative){
		out.print("1->");
		out.print(RouteManager.assignJob(origin, destination, mode, transit_avoid, alternative));
	}else if(null == transit_avoid && null == alternative){
		out.print("2->");
		out.print(RouteManager.assignJob(origin, destination, mode, "none", "false"));
	}else if(null != transit_avoid){
		out.print("3->");
		out.print(RouteManager.assignJob(origin, destination, mode, transit_avoid, "false"));
	}else if(null != alternative){
		out.print("4->");
		out.print(RouteManager.assignJob(origin, destination, mode, "none", alternative));
	}
	//out.print(RouteManager.startGDARoute("25.058657,121.554880", "25.058721,121.554735"));
	//out.print(RouteManager.startIdrRoute(25058657, 121554880, 1, 25058721, 121554735, 13, "III"));
	//RouteManager.setMessage1("cubic");
	//RouteManager.getPoiExit(25058657, 121554880, 1, "mrt_yc");
%>
</body>
</html>