package edu.ntnu.tesc.controller.command.tabledatacommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import org.apache.log4j.LogManager;
import edu.ntnu.tesc.module.beans.Growl;
import edu.ntnu.tesc.module.dao.GrowlDAO;
import edu.ntnu.tesc.module.viewmodule.GrowlTableListModule;

public class GetGrowlTableCommand implements ICommand {

	private HttpServletRequest request;
	private GrowlDAO growlDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;	

	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive selectid : "+selectid);
		if(selectid==null|| selectid.equals("null") || selectid.equals("-1")){
			/**
			 * 處理分頁
			 */
			String order_column = request.getParameter("sortname");
			String order_type = request.getParameter("sortorder");
			String per_page = request.getParameter("rp");
			String page = request.getParameter("page");
			
			LogManager.getLogger(this.getClass()).info(String.format("order column:%s order type:%s per_page:%s page:%s \n",order_column,order_type,per_page,page));
			List<Growl> list = growlDAO.getGrowlList();
			
			GrowlTableListModule tablelistmodule = new GrowlTableListModule();
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
		this.growlDAO = this.variable.getGrowlDAO();
	}


	public void setViewList(IViewList viewlist) {
		this.viewList = (TableDataViewList)viewlist;
	}

}
