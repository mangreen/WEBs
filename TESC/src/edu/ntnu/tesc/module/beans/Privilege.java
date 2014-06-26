package edu.ntnu.tesc.module.beans;

public class Privilege {
	private int autoindex;
	private int state;
	private String title;
	private int privLevel;
	private String ps;
	
	public int getAutoindex() {
		return autoindex;
	}
	
	public void setAutoindex(int autoindex) {
		this.autoindex = autoindex;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrivLevel() {
		return privLevel;
	}
	
	public void setPrivLevel(int privLevel) {
		this.privLevel = privLevel;
	}
	
	public String getPs() {
		return ps;
	}
	
	public void setPs(String ps) {
		this.ps = ps;
	}
	
	
}
