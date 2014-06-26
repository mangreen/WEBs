package edu.ntnu.tesc.controller.command.variable;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.module.beans.Role;
import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.dao.DepartmentDAO;
import edu.ntnu.tesc.module.dao.FlowDAO;
import edu.ntnu.tesc.module.dao.FlowModuleDAO;
import edu.ntnu.tesc.module.dao.GrowlDAO;
import edu.ntnu.tesc.module.dao.PrivilegeDAO;
import edu.ntnu.tesc.module.dao.QeisBudgetYearDAO;
import edu.ntnu.tesc.module.dao.RoleDAO;
import edu.ntnu.tesc.module.dao.StageDAO;


public class TableDataCommandVariable implements ICommandVariable {
	private HttpServletRequest request;
	private RoleDAO roleDAO;
    private PrivilegeDAO privilegeDAO;    
    private FlowDAO flowDAO;
    private FlowModuleDAO flowModuleDAO;    
    private StageDAO stageDAO;
    private GrowlDAO growlDAO;
    private ConfigDAO configDAO;
    private QeisBudgetYearDAO qeisDAO;
    private DepartmentDAO departmentDAO;
    
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	public FlowModuleDAO getFlowModuleDAO() {
		return flowModuleDAO;
	}
	public void setFlowModuleDAO(FlowModuleDAO flowModuleDAO) {
		this.flowModuleDAO = flowModuleDAO;
	}
	public StageDAO getStageDAO() {
		return stageDAO;
	}
	public void setStageDAO(StageDAO stageDAO) {
		this.stageDAO = stageDAO;
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
	public QeisBudgetYearDAO getQeisDAO() {
		return qeisDAO;
	}
	public void setQeisDAO(QeisBudgetYearDAO qeisDAO) {
		this.qeisDAO = qeisDAO;
	}
	public FlowDAO getFlowDAO() {
		return flowDAO;
	}
	public void setFlowDAO(FlowDAO flowDAO) {
		this.flowDAO = flowDAO;
	}
	public PrivilegeDAO getPrivilegeDAO() {
		return privilegeDAO;
	}
	public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
		this.privilegeDAO = privilegeDAO;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}


}
