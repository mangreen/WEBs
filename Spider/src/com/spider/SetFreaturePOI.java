package com.spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SetFreaturePOI {
	public static void main(String args[]) throws Exception {
		String user = "root";
		String pass = "";
        String database = "test";
        String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8";

        try{

        	//定義驅動程式與資料來源之間的連結
			Class.forName("org.gjt.mm.mysql.Driver");
			//建立一個聯結物件
			Connection conn = DriverManager.getConnection(url,user,pass);
			//建立Statement物件
			
			ResultSet rs;
	        //建立Statement物件，建立陳述式物件
			Statement stmt;

            //stmt = conn.createStatement();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("Connect SUCCESS!!");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
          	//顯示資料
            String query = "SELECT * FROM poi WHERE (poi_lat BETWEEN 25.023123 AND 25.050123) AND (poi_lon BETWEEN 121.511845 AND 121.521845) AND (poi_org_id LIKE '2%')";
            
            rs = stmt.executeQuery(query);
            String message = "";
            while(rs.next()){

				message += rs.getString(2) +","+ rs.getString(3) +","+ rs.getString(4) +","+ rs.getString(13);
				//writeDBPoi(rs.getString(2) /*poi_org_id*/, rs.getString(3) /*poi_name*/, rs.getString(4) /*poi_address*/, rs.getString(13) /*l3cata_id*/);
			}
            System.out.println(message);
            conn.close();	

        }catch(Exception sqle){

            System.out.println("SQL Exception : " + sqle);

        }
        
    }
	//寫入資料庫
    public static void writeDBPoi(String poi_org_id, String poi_name, String poi_address, String l3cata_id)throws Exception{
    	String user1 = "root";
		String pass1 = "";
        String database1 = "test";
        String url1 = "jdbc:mysql://127.0.0.1:3306/" + database1 + "?useUnicode=true&characterEncoding=UTF-8";

        try{

        	//定義驅動程式與資料來源之間的連結
			Class.forName("org.gjt.mm.mysql.Driver");
			//建立一個聯結物件
			Connection conn1 = DriverManager.getConnection(url1,user1,pass1);
			//建立Statement物件
			
			//ResultSet rs;
	        //建立Statement物件，建立陳述式物件
			Statement stmt1;

            //stmt = conn.createStatement();
			stmt1 = conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("Connect SUCCESS!!");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
          	//顯示資料
            String query1 = "INSERT INTO poi_fea (poi_org_id, poi_price, poi_envir, poi_taste, poi_atmos, poi_name, poi_address, l3cata_id)"+
            				"VALUES ('"+ poi_org_id + "', NULL, NULL, NULL, NULL,'"+ poi_name + "', '" + poi_address +"','"+ l3cata_id +"')";
            

            stmt1.executeUpdate(query1);       
            
            conn1.close();	

        }catch(Exception sqle){

            System.out.println("SQL Exception : " + sqle);

        }
    }
}
