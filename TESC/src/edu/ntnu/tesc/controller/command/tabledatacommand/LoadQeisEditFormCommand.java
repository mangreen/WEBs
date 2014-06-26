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
import edu.ntnu.tesc.module.beans.QeisBudgetYear;
import edu.ntnu.tesc.module.dao.QeisBudgetYearDAO;

public class LoadQeisEditFormCommand implements ICommand {

	private HttpServletRequest request;
	private QeisBudgetYearDAO qeisDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		 QeisBudgetYear model = new  QeisBudgetYear();
		try{
			int i = Integer.parseInt(selectid);
			if(i>=0){
				model = qeisDAO.getQeisBudgetYear(i);
				LogManager.getLogger(this.getClass()).info(String.format("Index %d : Budget Year:%s",i, model.getBudgetYear()));
			}
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).warn(e);
		}
		modelView.setView(viewList.getQeiseditformPage());
		modelView.setModule(model);
		modelView.setModelName("qeis");
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.qeisDAO = this.variable.getQeisDAO();
		this.request = this.variable.getRequest();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		this.viewList = (TableDataViewList)viewlist;
	}

}
