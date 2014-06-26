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


public class LoadConfigEditFormCommand implements ICommand {

	private HttpServletRequest request;
	private ConfigDAO configDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		Config model = new Config();
		try{
			int i = Integer.parseInt(selectid);
			if(i>=0){
				model = configDAO.getConfig(i);
				LogManager.getLogger(this.getClass()).info(String.format("Index %d : Title:%s",i, model.getTitle()));
				
			}
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).warn(e);
		}
		modelView.setView(viewList.getConfigeditformPage());
		modelView.setModule(model);
		modelView.setModelName("config");
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
