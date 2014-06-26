package edu.ntnu.tesc.controller.command.tabledatacommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import edu.ntnu.tesc.module.beans.Config;
import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.viewmodule.ConfigTableListModule;

public class GetConfigTableCommand implements ICommand {

	private HttpServletRequest request;
	private ConfigDAO configDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		
		String selectid=request.getParameter("selectid");
		Logger.getLogger(this.getClass()).info("receive selectid : "+selectid);
		if(selectid==null|| selectid.equals("null") || selectid.equals("-1")){
			/**
			 * 處理分頁
			 */
			String order_column = request.getParameter("sortname");
			String order_type = request.getParameter("sortorder");
			String per_page = request.getParameter("rp");
			String page = request.getParameter("page");
			
			Logger.getLogger(this.getClass()).info(String.format("order column:%s order type:%s per_page:%s page:%s \n",order_column,order_type,per_page,page));
			List<Config> list = configDAO.getConfigList();
			
			ConfigTableListModule tablelistmodule = new ConfigTableListModule();
			tablelistmodule.setRows(list);
			tablelistmodule.setPage(1);
			tablelistmodule.setTotal(list.size());
			modelView.setModule(tablelistmodule);
			modelView.setView(viewList.getJsonPage());

		}
		else{
			
		}		
		return modelView;
	}



	public void setVariable(ICommandVariable variable) {
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.configDAO = this.variable.getConfigDAO();
	}


	public void setViewList(IViewList viewlist) {
		this.viewList = (TableDataViewList)viewlist;
	}

}
