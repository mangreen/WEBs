package edu.ntnu.tesc.module.viewmodule;

import java.util.List;

import edu.ntnu.tesc.module.beans.Growl;

public class GrowlTableListModule implements IModule, ITableModule {

	private List<Growl> rows;
	private int total;
	private int page;
	


	public List<Growl> getRows() {
		return rows;
	}

	public void setRows(List<Growl> rows) {
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
		if(rows.size()==0)
			return "{}";
		///                      i, 	ai,	userid,,	target	type state cdate aIp sum  gdate aip
		String templete = "{id:'%d',cell:[%d,'%d','%d','%s','%s','%s','%s','%s','%s','%s']},";
		String jsonString = "";
		int i =1;
		for(Growl model:rows){
			jsonString+=String.format(templete,i,model.getAutoindex(),model.getUserID(),model.getTargetUserID(),model.getType(),model.getState(),model.getCDate().toString(),model.getActionIP(),model.getSummary(),model.getGDate().toString(),model.getActionIP());
			i++;
		}
		return jsonString.substring(0,jsonString.length()-1);
	}

}
