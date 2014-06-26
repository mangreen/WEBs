package edu.ntnu.tesc.module.viewmodule;

import java.util.List;

import edu.ntnu.tesc.module.beans.Department;

public class DepartmentTableListModule implements IModule, ITableModule {

	private int total;
	private int page;
	private List<Department> rows;
	
	
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


	public List<Department> getRows() {
		return rows;
	}


	public void setRows(List<Department> rows) {
		this.rows = rows;
	}


	@Override
	public String toJSONString() {
		return String.format("{total: '%d', page: '%d', rows:[%s]}", total,page,getRowsJSONString());
	}


	@Override
	public String getRowsJSONString() {
		if(rows.size()==0)
			return "{}";
		
		String templete = "{id:'%d',cell:[%d, '%s', '%s','%s','%s','%s']},";
		String jsonString = "";
		int i =1;
		for(Department model:rows){
			jsonString+=String.format(templete,i,model.getAutoindex(),model.getTitle(),model.getCode(),model.getAddress(),model.getPhone(),model.getMobile());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}


}
