package edu.ntnu.tesc.controller.command.tabledatacommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import edu.ntnu.tesc.module.beans.Role;
import edu.ntnu.tesc.module.dao.RoleDAO;

public class LoadRoleEditFormCommand implements ICommand {

	private HttpServletRequest request;
	private RoleDAO roleDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;	
	@Override
	public IModelView execute() {
		
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		Role role = new Role();
		if(selectid == null || selectid.equals("null") || selectid.equals("-1")){
			
		}
		else{
			int i = Integer.parseInt(selectid);
			role = roleDAO.getRole(i);
			LogManager.getLogger(this.getClass()).info(String.format("Index %d : Title:%s",i, role.getTitle()));
		}
		modelView.setView(viewList.getRoleeditformPage());
		modelView.setModule(role);
		modelView.setModelName("role");
		return modelView;
	}


	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();
		this.roleDAO = this.variable.getRoleDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
