package edu.ntnu.tesc.controller.command.tabledatacommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import edu.ntnu.tesc.module.beans.FlowModule;
import edu.ntnu.tesc.module.dao.FlowModuleDAO;

public class LoadFlowEditFormCommand implements ICommand {
	
	private HttpServletRequest request;
	private FlowModuleDAO flowModuleDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		 FlowModule model = new  FlowModule();
		try{
			int i = Integer.parseInt(selectid);
			if(i>=0){
				model = flowModuleDAO.getFlowModule(i);
				LogManager.getLogger(this.getClass()).info(String.format("Index %d : Title:%s",i, model.getTitle()));
				
			}
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).warn(e);
		}
		modelView.setView(viewList.getFloweditformPage());
		modelView.setModule(model);
		modelView.setModelName("flow");
		return modelView;
	}
	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.flowModuleDAO = this.variable.getFlowModuleDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
