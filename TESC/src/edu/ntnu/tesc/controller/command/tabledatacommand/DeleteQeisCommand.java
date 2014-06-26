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
import edu.ntnu.tesc.module.dao.QeisBudgetYearDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class DeleteQeisCommand implements ICommand {
	
	private HttpServletRequest request;
	private QeisBudgetYearDAO qeisDAO;
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
				int i = qeisDAO.delQeisBudgetYear(seletedid);
				LogManager.getLogger(this.getClass()).info("delete qeis budgetyear id : "+i);
				
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

	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.qeisDAO = this.variable.getQeisDAO();
		this.request = this.variable.getRequest();
	}

	public void setViewList(IViewList viewlist) {
		this.viewList = (TableDataViewList)viewlist;
	}
}
