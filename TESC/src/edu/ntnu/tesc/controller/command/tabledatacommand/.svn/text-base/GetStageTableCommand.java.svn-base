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
import edu.ntnu.tesc.module.beans.Stage;
import edu.ntnu.tesc.module.dao.StageDAO;
import edu.ntnu.tesc.module.viewmodule.StageTableListModule;

public class GetStageTableCommand implements ICommand {
	private HttpServletRequest request;
	private StageDAO stageDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	@Override
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
			List<Stage> list = stageDAO.getStageList();
			
			StageTableListModule tablelistmodule = new StageTableListModule();
			tablelistmodule.setRows(list);
			tablelistmodule.setPage(1);
			tablelistmodule.setTotal(list.size());
			modelView.setModule(tablelistmodule);
			modelView.setView(viewList.getJsonPage());
			//System.out.println(modelView.getView());
		}
		else{
			
		}		
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.stageDAO = this.variable.getStageDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
