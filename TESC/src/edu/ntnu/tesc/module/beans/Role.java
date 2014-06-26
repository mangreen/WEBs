package edu.ntnu.tesc.module.beans;

public class Role {
	private int autoindex;
	private int state;
	private String title;
	private String detailTable;
	private String ps;
	
	public String getDetailTable() {
		return detailTable;
	}
	
	public void setDetailTable(String detailTable) {
		this.detailTable = detailTable;
	}
	
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
	
	public String getPs() {
		return ps;
	}
	
	public void setPs(String ps) {
		this.ps = ps;
	}
	
	
}
