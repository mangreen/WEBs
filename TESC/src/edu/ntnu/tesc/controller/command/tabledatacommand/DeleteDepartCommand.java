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
import edu.ntnu.tesc.module.dao.DepartmentDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class DeleteDepartCommand implements ICommand {

	private HttpServletRequest request;
	private DepartmentDAO departmentDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		if(selectid != null && !selectid.equals("null") && !selectid.equals("-1") ){
			try{
				int seletedid = Integer.parseInt(selectid);
				int i = departmentDAO.delDepartment(seletedid);
				LogManager.getLogger(this.getClass()).info("delete department id : "+i);
				
				if(i==0)
					module.setText("{success:'false'}");
				else
					module.setText("{success:'true'}");						
			}
			catch(Exception e){
				LogManager.getLogger(this.getClass()).error(e);
			}
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
		this.departmentDAO = this.variable.getDepartmentDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
