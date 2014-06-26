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

public class LoadStageEditFormCommand implements ICommand {
	private HttpServletRequest request;
	private StageDAO stageDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		Stage model = new  Stage();
		try{
			int i = Integer.parseInt(selectid);
			if(i>=0){
				model = stageDAO.getStage(i);
				LogManager.getLogger(this.getClass()).info(String.format("Index %d : Title:%s",i, model.getTitle()));
				
			}
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).warn(e);
		}
		modelView.setView(viewList.getStageeditformPage());
		modelView.setModule(model);
		modelView.setModelName("stage");
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
