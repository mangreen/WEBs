package edu.ntnu.tesc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.GlobalCommandFactory;
import edu.ntnu.tesc.controller.command.variable.GlobalFunctionCommandVariable;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.GlobalFunctionViewList;
import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.dao.FlowDAO;
import edu.ntnu.tesc.module.dao.GrowlDAO;
import edu.ntnu.tesc.module.dao.RoleDAO;
import edu.ntnu.tesc.module.dao.UserDAO;

public class GlobalFunctionController implements Controller {
	
	private UserDAO userDAO;
	private RoleDAO roleDAO;
    private FlowDAO flowDAO;
    private GrowlDAO growlDAO;
    private ConfigDAO configDAO;
    private String jsonPage;
    private String commonFeaturesPage;
    private String loginPage;
    
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
		this.viewList.setLoginPage(this.loginPage);
	}
	private GlobalFunctionViewList viewList = new GlobalFunctionViewList();
    
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String action = request.getParameter("action");
		LogManager.getLogger(this.getClass()).info("Global V0.01 receive command : "+action);
		if(action != null){			
			GlobalCommandFactory globalFactory = (GlobalCommandFactory)FactoryManager.getInstance().getCommandFacotry(FactoryManager.GLOBALFACTORY);			
			ICommand command = globalFactory.getCommandObject(action);
			if(command != null){
				GlobalFunctionCommandVariable variable = new GlobalFunctionCommandVariable();
				variable.setRequest(request);
				variable.setConfigDAO(configDAO);
				variable.setFlowDAO(flowDAO);
				variable.setGrowlDAO(growlDAO);
				variable.setRoleDAO(roleDAO);
				variable.setUserDAO(userDAO);
				command.setVariable(variable);
				command.setViewList(viewList);
				IModelView mv = command.execute();
				if(mv!=null){
					LogManager.getLogger(this.getClass()).info("goto View : "+mv.getView());
					return new ModelAndView(mv.getView(), mv.getModelName(), mv.getModule());
				}
			}
			else{
				LogManager.getLogger(this.getClass()).debug("No Such Command!!");
			}
		}
		
			
		return new ModelAndView(this.jsonPage, "model", null);	
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public void setFlowDAO(FlowDAO flowDAO) {
		this.flowDAO = flowDAO;
	}
	public void setGrowlDAO(GrowlDAO growlDAO) {
		this.growlDAO = growlDAO;
	}
	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}
	public void setJsonPage(String jsonPage) {
		this.jsonPage = jsonPage;
		viewList.setJsonPage(this.jsonPage);
	}
    public void setCommonFeaturesPage(String commonFeaturesPage) {
		this.commonFeaturesPage = commonFeaturesPage;
		viewList.setCommonFeaturesPage(this.commonFeaturesPage);
	}

}
