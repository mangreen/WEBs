package edu.ntnu.tesc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.dao.UserDAO;

import edu.ntnu.tesc.controller.command.ICommand;

import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.LoginCommandFactory;
import edu.ntnu.tesc.controller.command.variable.UserCommandVariable;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.AccountViewList;

public class LoginController implements Controller {

    private UserDAO userDAO;
    private String jsonPage;
    private String modifyaccountPage;

    private AccountViewList viewList= new AccountViewList();

	public void setModifyaccountPage(String modifyaccountPage) {
		this.modifyaccountPage = modifyaccountPage;
		viewList.setModifyaccountPage(this.modifyaccountPage);
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}
	
	public  void setUserDAO(UserDAO userDao) {
		this.userDAO = userDao;
	}	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		if(action != null){
			LoginCommandFactory loginFactory = (LoginCommandFactory)FactoryManager.getInstance().getCommandFacotry(FactoryManager.LOGINFACTORY);			
			ICommand command = loginFactory.getCommandObject(action);
			if(command != null){
				UserCommandVariable variable = new UserCommandVariable();
				variable.setRequest(request);
				variable.setUserDAO(userDAO);
				
				command.setVariable(variable);
				command.setViewList(viewList);
				IModelView mv = command.execute();
				if(mv!=null)
					return new ModelAndView(mv.getView(), mv.getModelName(), mv.getModule());
			}
			else{
				System.out.println("No Such Command!!");
			}
		}
		
			
		return new ModelAndView(this.jsonPage, "model", null);		
	}
	
    public void setJsonPage(String jsonPage) {
        this.jsonPage = jsonPage;
        viewList.setJsonPage(this.jsonPage);
        
    }	

}
