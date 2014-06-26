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

public class CheckEmailCommand implements ICommand {
	
	private HttpServletRequest request;
	private UserDAO userDAO;
	private AccountViewList viewList;
	
	@Override
	public IModelView execute() {
		String email = request.getParameter("email");
		CommonModelView modelView = new CommonModelView();		
		FormItemCheckModule module = new FormItemCheckModule();
		
		if(email == null || email==null){
			modelView.setView(viewList.getJsonPage());
			module.setText("請輸入email");
			modelView.setModule(module);
			return modelView;
		}				
		User user = userDAO.findEmail(email);	
		
		if(user == null || user.getAccount() == null ){
			module.setText("true");
		}
		else{			
			module.setText("false");
		}
		System.out.println("EMail ("+email+") check result : "+module.getText());
		
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
