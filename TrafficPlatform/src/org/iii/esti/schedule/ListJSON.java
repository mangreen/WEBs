package org.iii.esti.schedule;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class ListJSON implements JSONAware{
	
	int id;
	String name;
	String program;
	boolean status;
	String start;
	String end;
	String last;
	String next;
	String interval;
	int weekday;
	int date;
	
	ListJSON(int id, String name, String program, boolean status, String start, String end, String last, String next, String interval, int weekday, int date){
		this.id = id;
		this.name = name;
		this.program = program;
		this.status = status;
		this.start = start;
		this.end = end;
		this.last = last;
		this.next = next;
		this.interval = interval;
		this.weekday = weekday;
		this.date = date;
	}
	
	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
        
        sb.append("\""+ JSONObject.escape("id") +"\"");
        sb.append(":");
        sb.append("\""+id+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("name") +"\"");
        sb.append(":");
        sb.append("\""+name+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("program") +"\"");
        sb.append(":");
        sb.append("\""+program+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("status") +"\"");
        sb.append(":");
        sb.append("\""+status+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("start") +"\"");
        sb.append(":");
        sb.append("\""+start+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("end") +"\"");
        sb.append(":");
        sb.append("\""+end+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("last") +"\"");
        sb.append(":");
        sb.append("\""+last+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("next") +"\"");
        sb.append(":");
        sb.append("\""+next+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("interval") +"\"");
        sb.append(":");
        sb.append("\""+interval+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("weekday") +"\"");
        sb.append(":");
        sb.append("\""+weekday+"\"");
        
        sb.append(",");
        
        sb.append("\""+ JSONObject.escape("date") +"\"");
        sb.append(":");
        sb.append("\""+date+"\"");
        
        sb.append("}");
        return sb.toString();
	}
	
}
