package edu.ntnu.tesc.module.viewmodule;

import java.util.List;
import edu.ntnu.tesc.module.beans.Stage;

public class StageTableListModule implements ITableModule, IModule {


	private List<Stage> rows;
	private int total;
	private int page;
	
	
	public List<Stage> getRows() {
		return rows;
	}

	public void setRows(List<Stage> rows) {
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

	public String getRowsJSONString() {

		if(rows.size()==0)
			return "{}";
		String templete = "{id:'%d',cell:[%d, '%s']},";
		String jsonString = "";
		int i =1;
		for(Stage model:rows){
			jsonString+=String.format(templete,i,model.getAutoindex(),model.getTitle());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}

	public String toJSONString() {		
		return String.format("{total: '%d', page: '%d', rows:[%s]}", total,page,getRowsJSONString());
	}

}
