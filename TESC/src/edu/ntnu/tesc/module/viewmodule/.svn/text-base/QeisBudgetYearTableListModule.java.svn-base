package edu.ntnu.tesc.module.viewmodule;

import java.util.List;

import edu.ntnu.tesc.module.beans.QeisBudgetYear;

public class QeisBudgetYearTableListModule implements IModule, ITableModule {

	private List<QeisBudgetYear> rows;
	private int total;
	private int page;
	
	public List<QeisBudgetYear> getRows() {
		return rows;
	}

	public void setRows(List<QeisBudgetYear> rows) {
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

	public String toJSONString() {
		return String.format("{total: '%d', page: '%d', rows:[%s]}", total,page,getRowsJSONString());
	}

	public String getRowsJSONString() {
		// TODO Auto-generated method stub
		if(rows.size()==0)
			return "{}";
		
		String templete = "{id:'%d',cell:[%d, '%d', '%d', '%d']},";
		String jsonString = "";
		int i =1;
		for(QeisBudgetYear model:rows){
			jsonString+=String.format(templete,i,model.getAutoindex(),model.getBudgetYear(),model.getCurrentPrice(),model.getCapitalPrice());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}


}
