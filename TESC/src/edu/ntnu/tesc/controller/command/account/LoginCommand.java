package edu.ntnu.tesc.controller.command.account;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.dao.UserDAO;
import edu.ntnu.tesc.module.viewmodule.LoginModule;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.UserCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.AccountViewList;
import edu.ntnu.tesc.controller.viewlist.IViewList;

public class LoginCommand implements ICommand {

	private HttpServletRequest request;
	private UserDAO userDAO;
	private AccountViewList viewList;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		if(account == null || password==null || account.equals("") || password.equals("") ){
			modelView.setView(viewList.getJsonPage());
			return modelView;
		}
		
		LoginModule module = new LoginModule();
		User user = userDAO.findUser(account,password);
		module.setAccount(account);
		if(user != null && user.getAccount()!=null && user.getPassword()!=null){
			module.setSuccess(true);
			request.getSession().setAttribute("login_user_bean",user );
		}
		else{
			module.setSuccess(false);
		}
		modelView.setModule(module);
		modelView.setView(viewList.getJsonPage());
		
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
