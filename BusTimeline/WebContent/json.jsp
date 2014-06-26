<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String jsonData="{\"IsFullData\":true,"+
		"\"DateStaticUpdated\":1331890983609,"+
		"\"DateDynUpdated\":1331891816765,"+
		"\"BusStaticInfo\":[{\"Rid\":\"11326\","+
							"\"RouteName\":\"262\","+
							"\"StopNameList\":[\"第一站四五六七八九十\",\"第二站\",\"第三站\",\"第四站\",\"第五站\",\"第六站\",\"第七站\",\"第八站\",\"第九站\"],"+
							"\"CurrentStopIdx\":5},"+
						"{\"Rid\":\"11145\","+
							"\"RouteName\":\"560副\","+
							"\"StopNameList\":[\"副一站\",\"副二站\",\"副三站\",\"副四站\",\"副五站\",\"副六站\",\"副七站\"],"+
							"\"CurrentStopIdx\":0},"+
						"{\"Rid\":\"11111\","+
							"\"RouteName\":\"281\","+
							"\"StopNameList\":[\"參一站\",\"參二站\",\"參三站\",\"參四站\",\"參五站\",\"參六站\",\"參七站\"],"+
							"\"CurrentStopIdx\":2},"+
						"{\"Rid\":\"12345\","+
							"\"RouteName\":\"懷恩專車s31(公館六張犁)\","+
							"\"StopNameList\":[\"肆一站\",\"肆二站\",\"肆三站\",\"肆四站\",\"肆五站\",\"肆六站\",\"肆七站\"],"+
							"\"CurrentStopIdx\":6},"+
						"{\"Rid\":\"10401\","+
							"\"RouteName\":\"299\","+
							"\"StopNameList\":[\"伍一站\",\"伍二站\",\"伍三站\",\"伍四站\",\"伍五站\",\"伍六站\",\"伍七站\"],"+
							"\"CurrentStopIdx\":1}],"+
		"\"BusDynInfo\":[{\"Rid\":\"11326\","+
			   			"\"StatusCode\":1,"+
			  			"\"TravelTimeList\":[-1,-1,-1,-1,-1,-1,25,38,110],"+		// NOTE: -1統一為Not available的意思
			   			"\"CurrentStopATime\":13,"+
			   			"\"BusLocationList\":[0,0,1,0,2,0,1,0,-1]},"+
					 "{\"Rid\":\"11145\","+
			   			"\"StatusCode\":3,"+
			  			"\"TravelTimeList\":[-1,-1,-1,-1,-1,-1,-1],"+
			   			"\"CurrentStopATime\":-1,"+
			   			"\"BusLocationList\":[-1,-1,-1,-1,-1,-1,-1]},"+
					 "{\"Rid\":\"11111\","+
			   			"\"StatusCode\":2,"+
			  			"\"TravelTimeList\":[-1,-1,-1,-1,-1,-1,-1],"+
			   			"\"CurrentStopATime\":-1,"+
			   			"\"BusLocationList\":[-1,-1,-1,-1,-1,-1,-1]},"+
					 "{\"Rid\":\"12345\","+
			   			"\"StatusCode\":0,"+
			  			"\"TravelTimeList\":[-1,-1,-1,-1,-1,-1,-1],"+
			   			"\"CurrentStopATime\":-1,"+
			   			"\"BusLocationList\":[-1,-1,-1,-1,-1,-1,-1]},"+
					 "{\"Rid\":\"10401\","+
			   			"\"StatusCode\":3,"+
			  			"\"TravelTimeList\":[-1,-1,-1,-1,-1,-1,-1],"+
			   			"\"CurrentStopATime\":-1,"+
			   			"\"BusLocationList\":[-1,-1,-1,-1,-1,-1,-1]}]}";
		
		
out.write(jsonData);
		//out.println("<br>After 3 seconds, you will be back to Login.jsp");

%>