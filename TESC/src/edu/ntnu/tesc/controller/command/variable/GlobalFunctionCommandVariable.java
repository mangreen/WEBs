package edu.ntnu.tesc.controller.command.variable;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.dao.FlowDAO;
import edu.ntnu.tesc.module.dao.GrowlDAO;
import edu.ntnu.tesc.module.dao.RoleDAO;
import edu.ntnu.tesc.module.dao.UserDAO;

public class GlobalFunctionCommandVariable implements ICommandVariable {
	private HttpServletRequest request;
	private UserDAO userDAO;
	private RoleDAO roleDAO;
    private FlowDAO flowDAO;
    private GrowlDAO growlDAO;
    private ConfigDAO configDAO;
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public FlowDAO getFlowDAO() {
		return flowDAO;
	}
	public void setFlowDAO(FlowDAO flowDAO) {
		this.flowDAO = flowDAO;
	}
	public GrowlDAO getGrowlDAO() {
		return growlDAO;
	}
	public void setGrowlDAO(GrowlDAO growlDAO) {
		this.growlDAO = growlDAO;
	}
	public ConfigDAO getConfigDAO() {
		return configDAO;
	}
	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}
    
    
}
