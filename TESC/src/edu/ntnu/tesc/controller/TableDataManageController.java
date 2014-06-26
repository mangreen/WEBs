package edu.ntnu.tesc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.TableDataCommandFactory;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import org.apache.log4j.LogManager;
import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.dao.DepartmentDAO;
import edu.ntnu.tesc.module.dao.FlowDAO;
import edu.ntnu.tesc.module.dao.FlowModuleDAO;
import edu.ntnu.tesc.module.dao.GrowlDAO;
import edu.ntnu.tesc.module.dao.PrivilegeDAO;
import edu.ntnu.tesc.module.dao.QeisBudgetYearDAO;
import edu.ntnu.tesc.module.dao.RoleDAO;
import edu.ntnu.tesc.module.dao.StageDAO;

public class TableDataManageController implements Controller {

    private String jsonPage;
    private String roleeditformPage;
    private String privilegeeditformPage;
    private String floweditformPage;
    private String stageeditformPage;
    private String growleditformPage;
    private String configeditformPage;
    private String qeiseditformPage;
    private String departeditformPage;
    
	private RoleDAO roleDAO;
    private PrivilegeDAO privilegeDAO;    
    private FlowDAO flowDAO;
    private FlowModuleDAO flowModuleDAO;    
    private StageDAO stageDAO;
    private GrowlDAO growlDAO;
    private ConfigDAO configDAO;
    private QeisBudgetYearDAO qeisDAO;
    private DepartmentDAO departmentDAO;
    
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	private TableDataViewList viewList = new TableDataViewList();	
		

	public void setJsonPage(String jsonPage) {
		this.jsonPage = jsonPage;
		this.viewList.setJsonPage(this.jsonPage);
	}

	public void setRoleeditformPage(String roleeditformPage) {
		this.roleeditformPage = roleeditformPage;
		this.viewList.setRoleeditformPage(this.roleeditformPage);
	}

	public void setPrivilegeeditformPage(String privilegeeditformPage) {
		this.privilegeeditformPage = privilegeeditformPage;
		this.viewList.setPrivilegeeditformPage(this.privilegeeditformPage);
	}

	public void setFloweditformPage(String floweditformPage) {
		this.floweditformPage = floweditformPage;
		this.viewList.setFloweditformPage(this.floweditformPage);
	}
	
	public void setStageeditformPage(String stageeditformPage) {
		this.stageeditformPage = stageeditformPage;
		this.viewList.setStageeditformPage(this.stageeditformPage);
	}
	
	public void setGrowleditformPage(String growleditformPage) {
		this.growleditformPage = growleditformPage;
		this.viewList.setGrowleditformPage(this.growleditformPage);
	}

	public void setConfigeditformPage(String configeditformPage) {
		this.configeditformPage = configeditformPage;
		this.viewList.setConfigeditformPage(this.configeditformPage);
	}

	public void setQeiseditformPage(String qeiseditformPage) {
		this.qeiseditformPage = qeiseditformPage;
		this.viewList.setQeiseditformPage(this.qeiseditformPage);
	}
	
	public void setDeparteditformPage(String departeditformPage) {
		this.departeditformPage = departeditformPage;
		this.viewList.setDeparteditformPage(this.departeditformPage);
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
		this.privilegeDAO = privilegeDAO;
	}

	public void setFlowDAO(FlowDAO flowDAO) {
		this.flowDAO = flowDAO;
	}
	
	public void setFlowModuleDAO(FlowModuleDAO flowModuleDAO) {
		this.flowModuleDAO = flowModuleDAO;
	}

	public void setStageDAO(StageDAO stageDAO) {
		this.stageDAO = stageDAO;
	}

	public void setGrowlDAO(GrowlDAO growlDAO) {
		this.growlDAO = growlDAO;
	}

	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}

	public void setQeisDAO(QeisBudgetYearDAO qeisDAO) {
		this.qeisDAO = qeisDAO;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String action = request.getParameter("action");
		LogManager.getLogger(this.getClass()).info("TESC V0.1 receive command : "+action);
		
		if(action != null){			
			TableDataCommandFactory roleFactory = (TableDataCommandFactory)FactoryManager.getInstance().getCommandFacotry(FactoryManager.ROLEFACTORY);			
			ICommand command = roleFactory.getCommandObject(action);
			if(command != null){
				
				TableDataCommandVariable variable = new TableDataCommandVariable();
				variable.setRequest(request);
				variable.setRoleDAO(roleDAO);
				variable.setPrivilegeDAO(privilegeDAO);
				variable.setFlowDAO(flowDAO);
				variable.setConfigDAO(configDAO);
				variable.setFlowModuleDAO(flowModuleDAO);
				variable.setGrowlDAO(growlDAO);
				variable.setQeisDAO(qeisDAO);
				variable.setStageDAO(stageDAO);
				variable.setDepartmentDAO(departmentDAO);
				command.setVariable(variable);
				command.setViewList(viewList);
				
				IModelView mv = command.execute();
				LogManager.getLogger(this.getClass()).info("goto View : "+mv.getView());
				return new ModelAndView(mv.getView(), mv.getModelName(), mv.getModule());
			}
			else{
				LogManager.getLogger(this.getClass()).debug("No Such Command!!");
			}
		}
		
			
		return new ModelAndView(this.jsonPage, "model", null);				

	}

}
