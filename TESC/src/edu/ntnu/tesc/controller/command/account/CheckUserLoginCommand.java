package edu.ntnu.tesc.controller.command.account;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.viewmodule.CheckLoginModule;

public class CheckUserLoginCommand implements ICommand {
	
	private HttpServletRequest request;

	public IModelView execute() {
		User user = (User) request.getSession().getAttribute("login_user_bean");		
		CommonModelView modelView = new CommonModelView();
		CheckLoginModule module = new CheckLoginModule();
		if(user == null ){
			module.setSuccess(false);	
			module.setUser(null);
		}else{
			module.setSuccess(true);
			module.setUser(user);
		}
		modelView.setModule(module);		
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.request = variable.getRequest();

	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		
	}

}
