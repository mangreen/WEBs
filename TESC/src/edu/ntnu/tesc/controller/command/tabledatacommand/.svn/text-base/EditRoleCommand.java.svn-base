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
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditRoleCommand implements ICommand {

	private HttpServletRequest request;
	private RoleDAO roleDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("find id : "+selectid);		
		Role role = new Role();
		String title = request.getParameter("title");
		String detail_table = request.getParameter("detailTable");
		String psText = request.getParameter("ps");
		String state = request.getParameter("state");
		role.setTitle(title);
		role.setDetailTable(detail_table);
		int autoindex = -1;
		try{		
			role.setState(Integer.parseInt(state));
			autoindex = Integer.parseInt(selectid);
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).error(e);
			module.setText("{success:'false',errorMessage:'ID格式錯誤或state未選取'}");
			modelView.setView(viewList.getJsonPage());
			modelView.setModule(module);
			return modelView;
		}
		role.setPs(psText);
		
		if(autoindex<0){
			/**
			 * 增加新的角色
			 */
			LogManager.getLogger(this.getClass()).info(String.format("insert new role title:'%s',state:'%s',detailtable:'%s',psText:'%s' \n",title,state,detail_table,psText));
			int i = roleDAO.insertRole(role);	
			LogManager.getLogger(this.getClass()).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
			LogManager.getLogger(this.getClass()).info(String.format("update role %d title:'%s',state:'%s',detailtable:'%s',psText:'%s' \n",autoindex,title,state,detail_table,psText));
			/**
			 * 修改舊有的角色
			 */
			
			role.setAutoindex(autoindex);
			//LogManager.getLogger(this.getClass()).info("update id : "+role.getAutoindex());
			int i = roleDAO.updateRole(role);
			LogManager.getLogger(this.getClass()).info("update result : "+i);
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");
		}
		modelView.setView(viewList.getJsonPage());
		modelView.setModule(module);
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
