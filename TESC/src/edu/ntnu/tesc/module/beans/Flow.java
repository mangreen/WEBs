package edu.ntnu.tesc.module.beans;

import java.sql.Timestamp;

public class Flow {

	private int autoindex;
	private int roleID;
	private int userID;
	private int flowModuleID;
	private int flowStageID;
	private String type;
	private String state;
	private Timestamp cDate;
	private Timestamp pDate;
	
	public int getAutoindex() {
		return autoindex;
	}
	
	public void setAutoindex(int autoindex) {
		this.autoindex = autoindex;
	}
	
	public int getRoleID() {
		return roleID;
	}
	
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getFlowModuleID() {
		return flowModuleID;
	}
	
	public void setFlowModuleID(int flowModuleID) {
		this.flowModuleID = flowModuleID;
	}
	
	public int getFlowStageID() {
		return flowStageID;
	}
	
	public void setFlowStageID(int flowStageID) {
		this.flowStageID = flowStageID;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Timestamp getcDate() {
		return cDate;
	}
	
	public void setcDate(Timestamp cDate) {
		this.cDate = cDate;
	}
	
	public Timestamp getpDate() {
		return pDate;
	}
	
	public void setpDate(Timestamp pDate) {
		this.pDate = pDate;
	}
	
}
