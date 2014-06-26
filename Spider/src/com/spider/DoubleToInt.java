package com.spider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DoubleToInt {
	public static void main(String args[]) throws Exception {
		//for(int poi_id=1; poi_id < 97951; poi_id++){
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
		            String query = "SELECT * FROM poi WHERE poi_int_lon = 0"; //+ poi_id;
		            
		            rs = stmt.executeQuery(query);
		            String message = "";
		            while(rs.next()){
		
						String[] token =rs.getString(10).split("[.]");
						if(token[1].length() < 6){
							for(int i=0; i<= (6- token[1].length()); i++){				
								token[1] = token[1] + "0";
							}
							message = token[0]+token[1];
							//System.out.println(rs.getString(2));
							//System.out.println(Integer.parseInt(message));
						}else if(token[1].length() > 6){
							message = token[0]+token[1].substring(0, 6);
							//System.out.println(Integer.parseInt(message));
						}else{
							message = token[0]+token[1];
							//System.out.println(Integer.parseInt(message));
						}
						//message = token[0]+token[1];
						//writeDBPoi(Integer.parseInt(message), rs.getString(2));
						System.out.println(rs.getString(2));
					}
		            //System.out.println(message);
		            conn.close();
				
	
	        }catch(Exception sqle){
	
	            System.out.println("SQL Exception : " + sqle);
	
	        }
		//}
        
    }
	//寫入資料庫
    public static void writeDBPoi(int poi_int_lon, String poi_org_id)throws Exception{
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
            String query1 = "UPDATE poi SET poi_int_lon=" + poi_int_lon + " WHERE poi_org_id like '"+poi_org_id+"'";
            

            stmt1.executeUpdate(query1);       
            
            conn1.close();	

        }catch(Exception sqle){

            System.out.println("SQL Exception : " + sqle);

        }
    }
}
