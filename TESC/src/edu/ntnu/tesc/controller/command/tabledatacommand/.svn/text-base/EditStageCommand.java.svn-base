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
import edu.ntnu.tesc.module.beans.Stage;
import edu.ntnu.tesc.module.dao.StageDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditStageCommand implements ICommand {
	private HttpServletRequest request;
	private StageDAO stageDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("find id : "+selectid);		
		Stage model = new Stage();
		String title = request.getParameter("title");
	
		int autoindex = -1;
		model.setTitle(title);
		
		try{
			autoindex = Integer.parseInt(selectid);
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).info("未取得autoindex，新增資料");
		}		
		
		if(autoindex<0){
			/**
			 * 增加新的角色
			 */
			LogManager.getLogger(this.getClass()).info(String.format("insert new Title:'%s'",model.getTitle()));
			int i = stageDAO.insertStage(model);
			LogManager.getLogger(this.getClass()).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
			LogManager.getLogger(this.getClass()).info(String.format("update Title : %s '",model.getTitle()));
			/**
			 * 修改舊有的角色
			 */
			
			model.setAutoindex(autoindex);
			//LogManager.getLogger(this.getClass()).info("update id : "+role.getAutoindex());
			int i = stageDAO.updateStage(model);
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
		this.stageDAO = this.variable.getStageDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}
}
