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

import edu.ntnu.tesc.module.beans.Department;
import edu.ntnu.tesc.module.dao.DepartmentDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditDepartCommand implements ICommand {

	private HttpServletRequest request;
	private DepartmentDAO departmentDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(EditDepartCommand.class).info("find id : "+selectid);		
		Department model = new Department();
		
		String title = request.getParameter("title");
		String code = request.getParameter("code");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");
		int autoindex = -1;

		
		try{
			autoindex = Integer.parseInt(selectid);
		}
		catch(Exception e){
			LogManager.getLogger(EditDepartCommand.class).info("未取得autoindex，新增資料");
		}
		
		model.setTitle(title);
		model.setCode(code);
		model.setAddress(address);
		model.setPhone(phone);
		model.setMobile(mobile);
		
		
		if(autoindex<0){
			/**
			 * 增加新的角色
			 */
			LogManager.getLogger(EditDepartCommand.class).info(String.format("insert new Department Title:'%s'",model.getTitle()));
			int i = departmentDAO.insertDepartment(model);
			LogManager.getLogger(EditDepartCommand.class).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
		    LogManager.getLogger(EditDepartCommand.class).info(String.format("update Department Title: %s '",model.getTitle()));
			/**
			 * 修改舊有的角色
			 */
			
			model.setAutoindex(autoindex);
			//LogManager.getLogger().info("update id : "+role.getAutoindex());
			int i = departmentDAO.updateDepartment(model);
			LogManager.getLogger(EditDepartCommand.class).info("update result : "+i);
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
		this.departmentDAO = this.variable.getDepartmentDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
