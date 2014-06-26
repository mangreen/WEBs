package edu.ntnu.tesc.module.viewmodule;

import java.util.List;

import edu.ntnu.tesc.module.beans.Role;

public class RoleTableListModule implements ITableModule,IModule {

	private List<Role> rows;
	private int total;
	private int page;
	
	public List<Role> getRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	
	public void setRows(List<Role> list) {
		// TODO Auto-generated method stub
		rows = list;
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
		return String.format("{total: '%d', page: '%d', rows:[%s]}", total,page,getRowsJSONString());
	}

	public String getRowsJSONString() {
		// TODO Auto-generated method stub
		if(rows.size()==0)
			return "{}";
		
		String templete = "{id:'%d',cell:[%d, '%d', '%s', '%s', '%s']},";
		String jsonString = "";
		int i =1;
		for(Role role:rows){
			jsonString+=String.format(templete,i,role.getAutoindex(),role.getState(),role.getTitle(),role.getDetailTable(),role.getPs());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}
	
}
