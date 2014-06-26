package edu.ntnu.tesc.controller.command.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.UserCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.AccountViewList;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.module.dao.UserDAO;
import edu.ntnu.tesc.module.viewmodule.LoginModule;

public class ModifyAccountCommand implements ICommand {
	
	private HttpServletRequest request;
	private UserDAO userDAO;
	
	private AccountViewList viewList;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		LoginModule module = new LoginModule();
		module.setSuccess(true);
		modelView.setModule(module);
		modelView.setView(viewList.getJsonPage());
		LogManager.getLogger(this.getClass()).info("Test load");
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		UserCommandVariable var = (UserCommandVariable)variable;
		this.request = var.getRequest();
		this.userDAO = var.getUserDAO();
		
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (AccountViewList)viewlist;
	}


}
