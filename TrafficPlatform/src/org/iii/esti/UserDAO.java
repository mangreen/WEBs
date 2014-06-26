package org.iii.esti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
	
	public static int DATABASE_USER_CHECK = 0;
	public static int DATABASE_USER_INSERT = 1;
	public static int DATABASE_USER_DELETE = 2;
	
	//資料庫帳密位置
	public static String TABLE_USER = "user";
	public static String Database_IP = "140.92.13.128";
	public static String Database_PORT = "3306";
	public static String Database_SCHEMA = "schedule";
	public static String Database_ACCOUNT = "mangreen";
	public static String Database_PASSWORD = "12345678";
	
	
	public boolean check(User user){
		String query = "SELECT * FROM "+TABLE_USER+";";
		if(DatabaseConnecter(user, query, DATABASE_USER_CHECK)){
			return true;
		}
		return false;
	}
	
	public static boolean DatabaseConnecter(User userDao, String query, int dbAction){
		
		
        String url = "jdbc:mysql://"+Database_IP+":"+Database_PORT+"/" + Database_SCHEMA + "?useUnicode=true&characterEncoding=UTF-8";
        
        //建立一個聯結物件
        Connection conn;
		//建立ResultSet物件
		ResultSet rs;
        //建立Statement物件
		Statement stmt;
        
        try{
        	//定義驅動程式與資料來源之間的連結
			Class.forName("org.gjt.mm.mysql.Driver");
			//建立聯結
			conn = DriverManager.getConnection(url,Database_ACCOUNT,Database_PASSWORD);
			
	        //建立陳述式物件
            //stmt = conn.createStatement();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("Connect SUCCESS!!");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			if(DATABASE_USER_CHECK == dbAction){
	            //執行sql
	            rs = stmt.executeQuery(query);       
	            //顯示資料
	            while(rs.next()){
	            	//System.out.print(rs.getString(1)+", "+rs.getString(2));
	            	if(rs.getString(1).equals(userDao.getAccount())||rs.getString(2).equals(userDao.getPassword())){
	            		if(stmt != null)
	        		    	stmt.close();
	        			if(conn != null)
	        				conn.close();
	        			
	            		return true;
	            	}
	            }
			}
            
            if(stmt != null)
		    	stmt.close();
			if(conn != null)
				conn.close();	

        }catch(Exception sqle){
            System.out.println("SQL Exception : " + sqle);
            return false;
        }
        
		return false;
	}
}
