<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" import="java.util.*" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<% 
	//http://127.0.0.1:8080/LBSServer/IndoorLBS.jsp?build=iii&ql=escalator
	//禁止緩存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	
	request.setCharacterEncoding("UTF-8");
	
	//Object obj = session.getAttribute("account");
	//String account = request.getParameter("account");
	String floor = request.getParameter("floor");
	String build = request.getParameter("build");
	String ql = request.getParameter("ql");
	out.println("ddddddddddd"+ql);
	String[] qlToken;
	String qlCata = "";
	String qlName = "";
	if(ql != null){
		if(ql.length() > 1 ){
			qlToken = ql.split(",");
			if(qlToken.length >= 1){
				qlCata = qlToken[0];
			}if(qlToken.length >= 2){
				qlName = qlToken[1];
			}
		}
	}
		
	String user = "root";
	String pw = "";
	String database = "test";
	String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";
	String query="";
	String table = "poi_"+build;
	
	if(null == ql){
		
		query = "SELECT * FROM " + table;
	}else{
		query = "SELECT * FROM "+ table +" WHERE (catalog LIKE '%" + qlCata+ "%') AND (name LIKE '%" + qlName + "%')";
	}
	
	//建立一個聯結物件
	Connection conn;
	ResultSet rs;
	//建立Statement物件，建立陳述式物件
	Statement stmt;
	
	try{
	
		//定義驅動程式與資料來源之間的連結
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		//建立一個聯結物件
		conn = DriverManager.getConnection(url,user,pw);
		//建立Statement物件
	
	    //stmt = conn.createStatement();
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    //out.println("<hr>Connect SUCCESS 12345!!<hr>");
		//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		out.println(query);
		rs = stmt.executeQuery(query);
			
		String qlMessage ="";
		int count = 0;
		//System.out.println(rs3.getRow());
		while(rs.next()){
			count ++;
			qlMessage +=rs.getString(2) +","+ rs.getString(3) +","+ rs.getString(4) +","+ 
						rs.getString(5) +","+ rs.getString(6) +","+ rs.getString(7)+","+ rs.getString(8)+";";	

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
	    if(stmt != null)
	    	stmt.close();
		if(conn != null)
			conn.close();	
	
	}catch(SQLException sqle){
		
	    out.println("SQL Exception : " + sqle);
	
	}
%>
