package edu.ntnu.tesc.controller.command.account;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.dao.UserDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.UserCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.AccountViewList;
import edu.ntnu.tesc.controller.viewlist.IViewList;

public class CheckAccountCommand implements ICommand {

	private HttpServletRequest request;
	private UserDAO userDAO;
	private AccountViewList viewList;
	
	@Override
	public IModelView execute() {
	
		String account = request.getParameter("account");
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		if(account == null || account==null){
			modelView.setView(viewList.getJsonPage());
			module.setText("請輸入帳號");
			modelView.setModule(module);
			return modelView;
		}			
		
		if(account.length()<2){
			module.setText("false");
			modelView.setModule(module);
			modelView.setView(viewList.getJsonPage());
			return modelView;
		}
		
		User user = userDAO.find(account);	
		if(user == null || user.getAccount() == null ){
			module.setText("true");
		}
		else{			
			module.setText("false");
		}
		System.out.println("account ("+account+") check result : "+module.getText());
		modelView.setModule(module);
		modelView.setView(viewList.getJsonPage());
		
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		UserCommandVariable var = (UserCommandVariable)variable;
		this.request = var.getRequest();
		this.userDAO = var.getUserDAO();
		
	}

	@Override
	public void setViewList(IViewList viewlist) {
		this.viewList = (AccountViewList)viewlist;
	}

}
