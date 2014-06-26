package edu.ntnu.tesc.module.viewmodule;

import java.util.List;

import edu.ntnu.tesc.module.beans.Config;

public class ConfigTableListModule implements IModule, ITableModule {

	private List<Config> rows;
	private int total;
	private int page;
	
	public List<Config> getRows() {
		return rows;
	}

	public void setRows(List<Config> rows) {
		this.rows = rows;
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

	@Override
	public String toJSONString() {
		return String.format("{total: '%d', page: '%d', rows:[%s]}", total,page,getRowsJSONString());
	}

	@Override
	public String getRowsJSONString() {
		if(rows.size()==0)
			return "{}";
		String templete = "{id:'%d',cell:[%d, '%s' ,'%s','%s']},";
		String jsonString = "";
		int i =1;
		for(Config model:rows){
			jsonString+=String.format(templete,i,model.getAutoindex(),model.getTitle(),model.getVal(),model.getVal2());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}



}
