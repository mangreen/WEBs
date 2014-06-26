package edu.ntnu.tesc.controller.command.account;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.dao.UserDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;
import edu.ntnu.tesc.module.viewmodule.LoginModule;
import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.LoginCommandFactory;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.UserCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.AccountViewList;
import edu.ntnu.tesc.controller.viewlist.IViewList;
public class RegisterCommand implements ICommand {
	
	private HttpServletRequest request;
	private UserDAO userDAO;
	private UserCommandVariable variable;
	private AccountViewList viewList;
	
	@Override
	public IModelView execute() {
		// TODO Auto-generated method stub
		CommonModelView modelView = new CommonModelView();
		LoginModule module = new LoginModule();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String email = request.getParameter("email");	
		
		if(account == null || password==null || email ==null || account.equals("") || password.equals("") || email.equals("") ){
			module.setAccount("");
			module.setSuccess(false);
			modelView.setModule(module);
			modelView.setView(viewList.getJsonPage());
			return modelView;
		}
		
		
		module.setAccount(account);
		
		/**
		 * 
		 */
		LoginCommandFactory loginFactory = (LoginCommandFactory)FactoryManager.getInstance().getCommandFacotry(FactoryManager.LOGINFACTORY); 
		ICommand checkCommand = loginFactory.getCommandObject(LoginCommandFactory.CHECKMAIL);
		checkCommand.setVariable(variable);
		checkCommand.setViewList(viewList);
		
		FormItemCheckModule checkmodule = (FormItemCheckModule)checkCommand.execute().getModule();
		
		if( checkmodule.getText().equals("false")){
			module.setSuccess(false);
			modelView.setView(viewList.getJsonPage());
			modelView.setModule(module);
			return modelView;
		}
		
		/**
		 * 
		 */
		checkCommand = loginFactory.getCommandObject(LoginCommandFactory.CHECKACCOUNT);
		checkCommand.setVariable(variable);
		checkCommand.setViewList(viewList);
		
		checkmodule = (FormItemCheckModule)checkCommand.execute().getModule();
		
		if( checkmodule.getText().equals("false")){
			module.setSuccess(false);
			modelView.setView(viewList.getJsonPage());
			modelView.setModule(checkmodule);
			return modelView;
		}		
		
		
		/**
		 * ���s�Wuser
		 */
	
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setEmail(email);
		user.setRoleId(1);
		//System.out.println(user.getRoleId());
		userDAO.insert(user);
			
		
		
		module.setAccount(account);
		module.setSuccess(true);
		modelView.setModule(module);
		modelView.setView(viewList.getJsonPage());
		
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
