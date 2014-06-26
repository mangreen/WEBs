<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<% 
	//禁止緩存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	/*
	經濟部技術處  
	地址：台北市福州街15號
	經緯度：25.027851,121.516823
	*/
	
	Object obj = session.getAttribute("account");
	
	String sessionID;
	int sessionSec;	
	
	String account = request.getParameter("account");
	String lon = request.getParameter("lon");	
	String lat = request.getParameter("lat");
	
	//將帳號經緯度存入session/
	session.setAttribute("account",account);
	session.setAttribute("lon",lon);
	session.setAttribute("lat",lat);
	
	sessionID = session.getId();//取得session ID
	out.println("<hr>Session ID: " + sessionID);
	
	session.setMaxInactiveInterval(5);//設置session 有效時間, 60*5= 5分鐘
	sessionSec = session.getMaxInactiveInterval();//取得session有效時間
	out.println("<br>Session Time: " + sessionSec);
	
	out.println("<br>Account: " + account + "/ Session ID: " + session.getAttribute("account"));
	out.println("<br>Longitude: " + lon + "/ SessLon: " + session.getAttribute("lon"));
	out.println("<br>Latitude: " + lat + "/ SessLat: " + session.getAttribute("lat"));
	
	boolean bolNew =session.isNew();//取得session是否為新建的
	out.println("<hr>New Session: " + bolNew);

	long lngSec = session.getLastAccessedTime();//取得上次存取session的時間
	java.util.Date sesDate = new java.util.Date(lngSec);//還原為date物件, 只有Date date = new Date()會與java.sql.Date混淆
	out.println("<br>Last Get Session Time: " + sesDate);
	
	String user = "root";
	String pass = "";
	String database = "test";
	String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";
	
	//建立一個聯結物件
	Connection conn;
	ResultSet rs;
	//建立Statement物件，建立陳述式物件
	Statement stmt;
	
	try{
	
		//定義驅動程式與資料來源之間的連結
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		//建立一個聯結物件
		conn = DriverManager.getConnection(url,user,pass);
		//建立Statement物件
	
	    //stmt = conn.createStatement();
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    //out.println("<hr>Connect SUCCESS 12345!!<hr>");
		//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
	  	double poilong = 121.516823;
		double poilat = 25.027851;
	  	//SELECT poi.* FROM poi, poi_fea WHERE (poi.poi_lon BETWEEN 121.506823 AND 121.536823) AND (poi.poi_lat BETWEEN 25.026851 AND 25.029851 ) AND (poi_fea.poi_price =2) AND (poi.poi_org_id = poi_fea.poi_org_id)
		String query = "SELECT * FROM poi WHERE (poi_lon BETWEEN " + (poilong-0.01) + " AND "+ (poilong+0.02)+
						") AND (poi_lat BETWEEN " + (poilat-0.0001) + " AND " + (poilat+0.0001) + " )";
		out.println(query);
		rs = stmt.executeQuery(query);
		
		String message = "";
		String newMessage = "";
		int count = 0;
		
		/*
		while(rs.next()){
			
			out.println("<hr><tr>");
			//out.println(rs.getString(0));
			out.println("<td>" + rs.getString(1) + "</td>");
			out.println("<td>" + rs.getString(2) + "</td>");
			//out.println("<td>" + rs.getString(3) + "</td>");
			//out.println("<td>" + rs.getString(4) + "</td>");
			//out.println("<td>" + rs.getString(5) + "</td>");
			//out.println("<td>" + rs.getString(6) + "</td>");
			//out.println("<td>" + rs.getString(7) + "</td>");
			//out.println("<td>" + rs.getString(8) + "</td>");
			//out.println("<td>" + rs.getString(9) + "</td>");
			//out.println("<td>" + rs.getString(10) + "</td>");
			out.println("</tr>");
			
		}*/
		
		while(rs.next()){
			count ++;
			message += rs.getString(2) +","+ rs.getString(3) +","+ rs.getString(4) +","+ rs.getString(5) +","+	
					   	rs.getString(6) +","+ rs.getString(7) +","+ rs.getString(8) +","+ rs.getString(9) +","+
					   	rs.getString(10) +";";
		
			if(session.getAttrubute("message").indexOf(rs.getString(2)) == -1){
				newMessage += rs.getString(2) +","+ rs.getString(3) +","+ rs.getString(4) +","+ rs.getString(5) +","+	
							  	rs.getString(6) +","+ rs.getString(7) +","+ rs.getString(8) +","+ rs.getString(9) +","+
							  	rs.getString(10) +";";
			}
		}
		
		session.setAttribute("message",message);
		
		out.println("<hr>" + count + "@" + message);
		
		/*
		//顯示資料   
	    String query8="ALTER table device drop device_report_email";
	    int d8=stmt.executeUpdate(query8);
	    
	    String query9="ALTER table device_group add device_report TEXT";
	    int d9=stmt.executeUpdate(query9);
		*/
		session.invalidate();
	    if(stmt != null)
	    	stmt.close();
		if(conn != null)
			conn.close();	
	
	}catch(SQLException sqle){
	
	    out.println("SQL Exception : " + sqle);
	
	}

%>