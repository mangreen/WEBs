package edu.ntnu.tesc.module.viewmodule;

import java.util.List;

import edu.ntnu.tesc.module.beans.Privilege;

public class PrivilegeTableListModule implements ITableModule, IModule {

	private int total;
	private int page;
	private List<Privilege> rows;
	
	public void setRows(List<Privilege> list){
		this.rows = list;		
	}
	public List<Privilege> getRows() {
		return rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String toJSONString() {
		// TODO Auto-generated method stub
		return String.format("{total: '%d', page: '%d', rows:[%s]}", total,page,getRowsJSONString());
	}
	public String getRowsJSONString() {
		if(rows.size()==0)
			return "{}";
		
		String templete = "{id:'%d',cell:[%d, '%d', '%s', '%d', '%s']},";
		String jsonString = "";
		int i =1;
		for(Privilege priv:rows){
			jsonString+=String.format(templete,i,priv.getAutoindex(),priv.getState(),priv.getTitle(),priv.getPrivLevel(),priv.getPs());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}
}
