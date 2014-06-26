package edu.ntnu.tesc.controller.command.account;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.GlobalCommandFactory;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.UserCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.AccountViewList;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.dao.UserDAO;
import edu.ntnu.tesc.module.viewmodule.CheckLoginModule;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;
import edu.ntnu.tesc.module.viewmodule.LoginModule;

public class LoadModifyAccountFormCommand implements ICommand {

	private HttpServletRequest request;
	private UserDAO userDAO;
	private AccountViewList viewList;
	private UserCommandVariable variable;
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		/**
		 * 檢查有無登入
		 */
		CheckUserLoginCommand command = new CheckUserLoginCommand();
		command.setVariable(variable);
		IModelView mv = command.execute();
		if(mv!=null){
			CheckLoginModule module = (CheckLoginModule)mv.getModule();
			if(module.isSuccess()){
				LogManager.getLogger(this.getClass()).info("user login!!");
				User user = module.getUser();	
				user = userDAO.find(user.getId());
				modelView.setView(viewList.getModifyaccountPage());
				modelView.setModelName("user");
				modelView.setModule(user);				
			}
			else{
				LogManager.getLogger(this.getClass()).info("user not login!!");
				modelView.setView(viewList.getJsonPage());
			}
		}
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		UserCommandVariable var = (UserCommandVariable)variable;
		this.variable = var; 
		this.request = var.getRequest();
		this.userDAO = var.getUserDAO();
		
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (AccountViewList)viewlist;
	}


}
