package edu.ntnu.tesc.controller.command.tabledatacommand;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;
import edu.ntnu.tesc.module.beans.Growl;
import edu.ntnu.tesc.module.dao.GrowlDAO;

public class LoadGrowlEditFormCommand implements ICommand {

	private HttpServletRequest request;
	private GrowlDAO growlDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;	

	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		Growl model = new  Growl();
		model.setGActionIP("");
		Date d = new Date();
		model.setGDate(new Timestamp(d.getTime()));
		model.setCDate(new Timestamp(d.getTime()));
		try{
			int i = Integer.parseInt(selectid);
			if(i>=0){
				model = growlDAO.getGrowl(i);
				LogManager.getLogger(this.getClass()).info(String.format("Index %d : Title:%s",i, model.getUserID()));
				
			}
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).warn(e);
		}
		modelView.setView(viewList.getGrowleditformPage());
		modelView.setModule(model);
		modelView.setModelName("growl");
		return modelView;
	}


	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.growlDAO = this.variable.getGrowlDAO();
	}


	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
