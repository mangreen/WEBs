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
import edu.ntnu.tesc.module.beans.Config;
import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditConfigCommand implements ICommand {

	private HttpServletRequest request;
	private ConfigDAO configDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(EditConfigCommand.class).info("find id : "+selectid);		
		Config model = new Config();
		
		String title = request.getParameter("title");
		String val = request.getParameter("val");
		String val2 = request.getParameter("val2");
		int autoindex = -1;

		
		try{
			autoindex = Integer.parseInt(selectid);
		}
		catch(Exception e){
			LogManager.getLogger(EditConfigCommand.class).info("未取得autoindex，新增資料");
		}
		
		model.setTitle(title);
		model.setVal(val);
		model.setVal2(val2);
		
		
		if(autoindex<0){
			/**
			 * 增加新的角色
			 */
			LogManager.getLogger(EditConfigCommand.class).info(String.format("insert new Config Title:'%s'",model.getTitle()));
			int i = configDAO.insertConfig(model);
			LogManager.getLogger(EditDepartCommand.class).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
			LogManager.getLogger(EditConfigCommand.class).info(String.format("update Config Title: %s '",model.getTitle()));
			/**
			 * 修改舊有的角色
			 */
			
			model.setAutoindex(autoindex);
			//LogManager.getLogger(EditConfigCommand.class).info("update id : "+role.getAutoindex());
			int i = configDAO.updateConfig(model);
			LogManager.getLogger(EditConfigCommand.class).info("update result : "+i);
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");
		}
		modelView.setView(viewList.getJsonPage());
		modelView.setModule(module);
		return modelView;
	}



	public void setVariable(ICommandVariable variable) {
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.configDAO = this.variable.getConfigDAO();
	}


	public void setViewList(IViewList viewlist) {
		this.viewList = (TableDataViewList)viewlist;
	}

}
