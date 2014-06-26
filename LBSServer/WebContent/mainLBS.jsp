<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" import="java.util.*" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<% 
	//http://127.0.0.1:8080/LBSServer/mainLBS.jsp?account=kevin&lon=121516823&lat=25028113
	//禁止緩存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	
	request.setCharacterEncoding("UTF-8");
	
	Object obj = session.getAttribute("account");
	String account = request.getParameter("account");
	String lon = request.getParameter("lon");	
	String lat = request.getParameter("lat");
	
	String ql = request.getParameter("ql");
	
  	int poilong = 0;
	int poilat = 0;
	
	if(lon != null){
		poilong = Integer.parseInt(lon);
	}
	if(lat != null){
		poilat = Integer.parseInt(lat);
	}
	
	if(ql != null){
		
		String[] qlToken;
		String qlCata = "";
		String qlName = "";
		
		if(ql.length() > 1 ){
			qlToken = ql.split(",");
			if(qlToken.length >= 1){
				qlCata = qlToken[0];
			}if(qlToken.length >= 2){
				qlName = qlToken[1];
			}
		}
		
		String user3 = "root";
		String pass3 = "";
		String database3 = "test";
		String url3 = "jdbc:mysql://127.0.0.1:3306/" + database3 + "?useUnicode=true&characterEncoding=UTF-8";
		String query3="";
		
		if(qlName.length() < 1){
		
			query3 = "SELECT * FROM poi WHERE (poi_int_lon BETWEEN " + (poilong-5000) + " AND "+ (poilong+5000)+
							") AND (poi_int_lat BETWEEN " + (poilat-5000) + " AND " + (poilat+5000) + " ) AND (l3cata_id LIKE '" + qlCata+ 
							"%') AND (poi_name LIKE '%" + qlName + "%')";
		}else{
			query3 = "SELECT * FROM poi WHERE (l3cata_id LIKE '" + qlCata+ "%') AND (poi_name LIKE '%" + qlName + "%')";
		}
		
		//建立一個聯結物件
		Connection conn3;
		ResultSet rs3;
		//建立Statement物件，建立陳述式物件
		Statement stmt3;
		
		try{
		
			//定義驅動程式與資料來源之間的連結
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			//建立一個聯結物件
			conn3 = DriverManager.getConnection(url3,user3,pass3);
			//建立Statement物件
		
		    //stmt = conn.createStatement();
			stmt3 = conn3.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    //out.println("<hr>Connect SUCCESS 12345!!<hr>");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			out.println(query3);
			rs3 = stmt3.executeQuery(query3);
				
			String qlMessage ="";
			int count = 0;
			//System.out.println(rs3.getRow());
			while(rs3.next()){
				count ++;
				qlMessage += rs3.getString(2) +","+ rs3.getString(14) +","+	rs3.getString(15) +","+ 
											rs3.getString(3) +","+ rs3.getString(4) +","+ rs3.getString(5) +","+ rs3.getString(16)+";";	
						   				//rs3.getString(6) +","+ rs3.getString(7) +","+ rs3.getString(8)+";";
				if(count>=2000){
					break;
				}
			}
			
			out.print("<hr>#" + count + "@" + qlMessage);
			
			/*
			//顯示資料   
		    String query8="ALTER table device drop device_report_email";
		    int d8=stmt.executeUpdate(query8);
		    
		    String query9="ALTER table device_group add device_report TEXT";
		    int d9=stmt.executeUpdate(query9);
			*/
			//session.invalidate();
		    if(stmt3 != null)
		    	stmt3.close();
			if(conn3 != null)
				conn3.close();	
		
		}catch(SQLException sqle){
		
		    out.println("SQL Exception : " + sqle);
		
		}

	}else{
		
		String message = "";
		String newMessage = "";
		int count = 0;
		
		String query = "SELECT * FROM poi WHERE (poi_int_lon BETWEEN " + (poilong-5000) + " AND "+ (poilong+5000)+
						") AND (poi_int_lat BETWEEN " + (poilat-5000) + " AND " + (poilat+5000) + " ) AND (l3cata_id LIKE '%')";
		
		if(obj == null){	
			//將帳號經緯度存入session
			session.setAttribute("account",account);
			session.setAttribute("lon",lon);
			session.setAttribute("lat",lat);
			session.setMaxInactiveInterval(60);//設置session 有效時間, 60*5= 5分鐘
		
			String user1 = "root";
			String pass1 = "";
			String database1 = "test";
			String url1 = "jdbc:mysql://127.0.0.1:3306/" + database1 + "?useUnicode=true&characterEncoding=UTF-8";
			
			//建立一個聯結物件
			Connection conn1;
			ResultSet rs1;
			//建立Statement物件，建立陳述式物件
			Statement stmt1;
			
			try{
			
				//定義驅動程式與資料來源之間的連結
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
				//建立一個聯結物件
				conn1 = DriverManager.getConnection(url1,user1,pass1);
				//建立Statement物件
			
			    //stmt = conn.createStatement();
				stmt1 = conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			    //out.println("<hr>Connect SUCCESS 12345!!<hr>");
				//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				
				out.println(query);
				rs1 = stmt1.executeQuery(query);
				
				while(rs1.next()){
					count ++;
					message += rs1.getString(2) +","+ rs1.getString(14) +","+	rs1.getString(15) +","+ 
											rs1.getString(3) +","+ rs1.getString(4) +","+ rs1.getString(5) +"," + rs1.getString(16)+";";	
						   				//rs1.getString(6) +","+ rs1.getString(7) +","+ rs1.getString(8)+";";
				}
				
				session.setAttribute("message",message);
				out.print("<hr>#" + count + "@" + message);
				
				//session.invalidate();
			    if(stmt1 != null)
			    	stmt1.close();
				if(conn1 != null)
					conn1.close();	
			
			}catch(SQLException sqle){
			
			    out.println("SQL Exception : " + sqle);
			
			}
			
		}else{
			if(lon != session.getAttribute("lon") || lat != session.getAttribute("lat")){
				String user2 = "root";
				String pass2 = "";
				String database2 = "test";
				String url2 = "jdbc:mysql://127.0.0.1:3306/" + database2 + "?useUnicode=true&characterEncoding=UTF-8";
				
				//建立一個聯結物件
				Connection conn2;
				ResultSet rs2;
				//建立Statement物件，建立陳述式物件
				Statement stmt2;
				
				try{
				
					//定義驅動程式與資料來源之間的連結
					Class.forName("org.gjt.mm.mysql.Driver").newInstance();
					//建立一個聯結物件
					conn2 = DriverManager.getConnection(url2,user2,pass2);
					//建立Statement物件
				
				    //stmt = conn.createStatement();
					stmt2 = conn2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				    //out.println("<hr>Connect SUCCESS 12345!!<hr>");
					//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					
				  	
					
					out.println(query);
					rs2 = stmt2.executeQuery(query);
						
					message = "";
					String sessMessage = session.getAttribute("message").toString();
					while(rs2.next()){
						count ++;
						message += rs2.getString(2) +","+ rs2.getString(14) +","+	rs2.getString(15) +","+ 
														rs2.getString(3) +","+ rs2.getString(4) +","+ rs2.getString(5) +"," + rs2.getString(16)+";";	
						   							//rs2.getString(6) +","+ rs2.getString(7) +","+ rs2.getString(8)+";";
					
						if(sessMessage.indexOf(rs2.getString(2)) == -1){
							newMessage += rs2.getString(2) +","+ rs2.getString(14) +","+	rs2.getString(15) +","+ 
														rs2.getString(3) +","+ rs2.getString(4) +","+ rs2.getString(5) +"," + rs2.getString(16)+";";	
						   							//rs2.getString(6) +","+ rs2.getString(7) +","+ rs2.getString(8)+";";
						}
					}
					
					session.setAttribute("message",message);
					
					out.print("<hr>#" + count + "@" + newMessage);
					
					/*
					//顯示資料   
				    String query8="ALTER table device drop device_report_email";
				    int d8=stmt.executeUpdate(query8);
				    
				    String query9="ALTER table device_group add device_report TEXT";
				    int d9=stmt.executeUpdate(query9);
					*/
					//session.invalidate();
				    if(stmt2 != null)
				    	stmt2.close();
					if(conn2 != null)
						conn2.close();	
				
				}catch(SQLException sqle){
				
				    out.println("SQL Exception : " + sqle);
				
				}
			}
		}
		
	}

%>