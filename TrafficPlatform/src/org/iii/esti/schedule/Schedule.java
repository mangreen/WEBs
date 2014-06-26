package org.iii.esti.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class Schedule {
	public static final String initialTime = "0000-00-00 00:00:00";
	private int id;
	private String name;
	private String program;
	private boolean status;
	private String start;
	private String end;
	private String last;
	private String next;
	private String interval;
	private Date startDate;
	private Date endDate;
	private Date lastDate;
	private Date nextDate;
	private int weekday;
	private int date;
	//private int execute;

	public Schedule(int id, String name, String program, boolean status, String start, String end, String last, String next, String interval, int weekday, int date){
	//public Schedule(int id, String name, String program, boolean status){	
		this.id = id; 
		this.name = name;
		this.program = program;
		this.status = status;
		
		this.start = start;
		this.end = end;
		this.last = last;
		this.next = next;
		
		//System.out.println("schedule:"+start+", "+end+", "+last+", "+next);
		
		if(null != start){
			if(!start.equals(initialTime)){
				this.startDate = stringToDate(start);
			}
		}
		
		if(null != end){
			if(!end.equals(initialTime)){
				this.endDate = stringToDate(end);
			}
		}
		
		if(null != last){
			if(!last.equals(initialTime)){
				this.lastDate = stringToDate(last);
			}
		}
		
		if(null != next){
			if(!next.equals(initialTime)){
				this.nextDate = stringToDate(next);
			}
		}
		
		this.interval = interval; 
		this.weekday = weekday; 
		this.date = date;
		//this.execute = execute;
	}
    
	//get & set id
	public int getID() {
        return id;    
    }
	
	//get & set name
    public String getName() {
        return name;    
    }
    public void setName(String name) {
        this.name = name;
    }
    
    //get & set program
    public String getProgram() {
        return program;    
    }
    public void setProgram(String program) {
        this.program = program;
    }
    
    //get & set status
    public boolean getStatus() {
        return status;    
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    //get & set first
    public String getStartString() {
        return start;    
    }
    public Date getStartDate() {
        return startDate;    
    }
    public void setStart(String start) {
        this.start = start;
        this.startDate = stringToDate(start);
    }
    public void setStart(Date startDate) {
        this.startDate = startDate;
        this.start = dateToString(startDate);
    }
    
    //get & set end
    public String getEndString() {
        return end;    
    }
    public Date getEndDate() {
        return endDate;    
    }
    public void setEnd(String end) {
        this.end = end;
        this.endDate = stringToDate(end);
    }
    public void setEnd(Date endDate) {
        this.endDate = endDate;
        this.end = dateToString(endDate);
    }
    
    //get & set last
    public String getLastString() {
        return last;    
    }
    public Date getLastDate() {
        return lastDate;    
    }
    public void setLast(String last) {
        this.last = last;
        this.lastDate = stringToDate(last);
    }
    public void setLast(Date lastDate) {
        this.lastDate = lastDate;
        this.last = dateToString(lastDate);
    }
    
    //get & set next
    public String getNextString() {
        return next;    
    }
    public Date getNextDate() {
        return nextDate;    
    }
    public void setNext(String next) {
        this.next = next;
    }
    public void setNext(Date nextDate) {
        this.nextDate = nextDate;
        this.next = dateToString(nextDate);
    }

    //get & set weekday
    public int getWeekday() {
        return weekday;    
    }
    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
    
    //get & set date
    public int getDate() {
        return date;    
    }
    public void setDate(int date) {
        this.date = date;
    }
    
    //get & set interval
    public String getInterval() {
    	return interval;
    }
    public void setInterval(String interval) {
    	this.interval = interval;
    }
    /*
    //get & set execute
    public int getExecute() {
        return execute;    
    }
    public void setExecute(int execute) {
        this.execute = execute;
    }
    */
    
    //String轉Date
    public static Date stringToDate(String dateString){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//進行轉換
    	Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return date;
    }
    
    //Date轉String
    public static String dateToString(Date date){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return sdf.format(date);
    }
}
