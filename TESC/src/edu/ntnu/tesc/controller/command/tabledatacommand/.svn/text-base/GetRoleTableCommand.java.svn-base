package edu.ntnu.tesc.controller.command.tabledatacommand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import edu.ntnu.tesc.module.beans.Role;
import edu.ntnu.tesc.module.dao.RoleDAO;
import edu.ntnu.tesc.module.viewmodule.RoleTableListModule;

public class GetRoleTableCommand implements ICommand {

	private HttpServletRequest request;
	private RoleDAO roleDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		// TODO Auto-generated method stub
		CommonModelView modelView = new CommonModelView();
		
		String selectid=request.getParameter("selectid");
		if(selectid==null || selectid.equals("-1")){
			String order_column = request.getParameter("sortname");
			String order_type = request.getParameter("sortorder");
			String per_page = request.getParameter("rp");
			String page = request.getParameter("page");
			
			System.out.printf("order column:%s order type:%s per_page:%s page:%s \n",order_column,order_type,per_page,page);
			List<Role> list = roleDAO.getRoleList();
			
			RoleTableListModule tablelistmodule = new RoleTableListModule();
			tablelistmodule.setRows(list);
			tablelistmodule.setPage(1);
			tablelistmodule.setTotal(list.size());
			modelView.setModule(tablelistmodule);
			modelView.setView(viewList.getJsonPage());
			//System.out.println(modelView.getView());
		}
		else{
			
		}
		
		
		
		
		
		
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
