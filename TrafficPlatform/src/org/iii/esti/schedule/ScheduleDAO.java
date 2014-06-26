package org.iii.esti.schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleDAO {
	
	public static int DATABASE_SCHEDULE_SELECT_ALL = 1;
	public static int DATABASE_SCHEDULE_UPDATE_TIME = 2;
	public static int DATABASE_SCHEDULE_SELECT_LIST = 3;
	public static int DATABASE_SCHEDULE_UPDATE_LIST = 4;
	public static int DATABASE_SCHEDULE_DELETE_LIST = 5;
	public static int DATABASE_SCHEDULE_ADD_LIST = 5;
	
	//資料庫帳密位置
	public static String Database_IP = "127.0.0.1";
	public static String Database_PORT = "3306";
	public static String Database_ACCOUNT = "root";
	public static String Database_PASSWORD = "";
	public static String Database_SCHEMA = "trafficplatform";
	public static String TABLE_SCHEDULE = "schedule";
	
	//找尋全部的Schedule
	public ArrayList<Schedule> selectAll(){
		String query = "SELECT * FROM "+TABLE_SCHEDULE+";";
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		//ResultSet rs = null;
		if(!databaseConnecter(list, query, DATABASE_SCHEDULE_SELECT_ALL)){
				System.out.println("Error: SELECT_ALL - Database Connected Fail");
		}
		return list;
	}
	
	//根據name找尋Schedule
	public ArrayList<Schedule> queryList(String name){
		String query = "SELECT * FROM "+TABLE_SCHEDULE+" WHERE name LIKE '%"+name+"%' ;";
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		//ResultSet rs = null;
		if(!databaseConnecter(list, query, DATABASE_SCHEDULE_SELECT_LIST)){
				System.out.println("Error: SELECT_LIST - Database Connected Fail");
		}
		return list;
	}
	
	//根據id找尋Schedule
	public ArrayList<Schedule> queryList(int id){
		String query = "SELECT * FROM "+TABLE_SCHEDULE+" WHERE id="+id+" ;";
		ArrayList<Schedule> list = new ArrayList<Schedule>();
		//ResultSet rs = null;
		if(!databaseConnecter(list, query, DATABASE_SCHEDULE_SELECT_LIST)){
				System.out.println("Error: SELECT_LIST - Database Connected Fail");
		}
		return list;
	}
	
	//更新時間
	public boolean updateTime(int id, boolean newStatus, String newLast, String newNext){
		String query = "UPDATE "+TABLE_SCHEDULE+" SET status="+newStatus+", last='"+newLast+"', next='"+newNext+"' WHERE id="+id+" ;";
		/*
		if(false == newStatus){
			query = "UPDATE "+TABLE_SCHEDULE+" SET status="+newStatus+", last='"+newLast+"', next='"+newNext+"' WHERE id = "+id+" ;";
		}else{
			query = "UPDATE "+TABLE_SCHEDULE+" SET last='"+newLast+"', next='"+newNext+"' WHERE id = "+id+" ;";
		}*/
		
		if(!databaseConnecter(null, query, DATABASE_SCHEDULE_UPDATE_TIME)){
			System.out.println("Error: UPDATE_TIME - Database Connected Fail");
			return false;
		}
		return true;
	}
	
	//更新Schedule資訊
	public boolean updateList(int id, String newName, String newProgram, boolean newStatus, String newStart, String newEnd, String newInterval, int newWeekday, int newDate){
		String query = "UPDATE "+TABLE_SCHEDULE+" SET name='"+newName+"', program='"+newProgram+"', status="+newStatus+", start='"+newStart+"', end='"+newEnd+"', next='"+newStart+"', inter_val='"+newInterval+"', weekday="+newWeekday+", date="+newDate+" WHERE id = "+id+" ;";
		//System.out.println(query);
		if(!databaseConnecter(null, query, DATABASE_SCHEDULE_UPDATE_LIST)){
			System.out.println("Error: UPDATE_LIST - Database Connected Fail");
			return false;
		}
		return true;
	}
	
	//刪除Schedule
	public boolean deleteList(int id){
		String query = "DELETE FROM "+TABLE_SCHEDULE+" WHERE id = "+id+" ;";
		System.out.println(query);
		if(!databaseConnecter(null, query, DATABASE_SCHEDULE_DELETE_LIST)){
			System.out.println("Error: DELETE_LIST - Database Connected Fail");
			return false;
		}
		return true;
	}
	
	//加入Schedule
	public boolean addList(String newName, String newProgram, boolean newStatus, String newStart, String newEnd, String newInterval, int newWeekday, int newDate){
		//INSERT INTO schedule (name, program, status, start, end, last, next, inter_val, weekday, date) VALUES ('test', 'ping 127.0.0.1', 0, '2011-06-19 14:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '2011-06-19 14:00:00', 'm1', 0, 0);
		String query = "INSERT INTO schedule (name, program, status, start, end, last, next, inter_val, weekday, date) VALUES ('"+newName+"', '"+newProgram+"', "+newStatus+", '"+newStart+"', '"+newEnd+"', '0000-00-00 00:00:00', '"+newStart+"', '"+newInterval+"', "+newWeekday+", "+newDate+");";
		System.out.println(query);
		if(!databaseConnecter(null, query, DATABASE_SCHEDULE_ADD_LIST)){
			System.out.println("Error: ADD_LIST - Database Connected Fail");
			return false;
		}
		return true;
	}
	
	public static boolean databaseConnecter(ArrayList<Schedule> list, String query, int dbAction){
		
		/*zeroDateTimeBehavior=round是為了指定MySql中的DateTime字段默認值查詢時的處理方式；默認是拋出異常，
			對於值為0000-00-00   00:00:00（默認值）的紀錄，如下兩種配置，會返回不同的結果：
			zeroDateTimeBehavior=round   0001-01-01   00:00:00.0
			zeroDateTimeBehavior=convertToNull   null 
		 */
        String url = "jdbc:mysql://"+Database_IP+":"+Database_PORT+"/" + Database_SCHEMA + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
        
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
			//conn = DriverManager.getConnection("proxool.Schedule");
			
	        //建立陳述式物件
            //stmt = conn.createStatement();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //System.out.println("Connect SUCCESS!!");
			//stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			if(DATABASE_SCHEDULE_SELECT_ALL == dbAction || DATABASE_SCHEDULE_SELECT_LIST == dbAction ){
	            //執行sql
	            rs = stmt.executeQuery(query);
	            while(rs.next()){
		            //System.out.println("xxxxxxxxxxxxxx"+rs.getString(7));
		            Schedule s = new Schedule(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4),
		            							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
		            							rs.getString(9), rs.getInt(10), rs.getInt(11));
		            list.add(s);
	            }
			}else if(DATABASE_SCHEDULE_UPDATE_TIME == dbAction || DATABASE_SCHEDULE_UPDATE_LIST == dbAction || DATABASE_SCHEDULE_DELETE_LIST == dbAction){
				stmt.executeUpdate(query);
			}
            
            if(stmt != null)
		    	stmt.close();
			if(conn != null)
				conn.close();	

        }catch(Exception sqle){
            System.out.println("SQL Exception : " + sqle);
            return false;
        }
        
		return true;
	}
}
