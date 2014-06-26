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
import edu.ntnu.tesc.module.beans.Privilege;
import edu.ntnu.tesc.module.dao.PrivilegeDAO;

public class LoadPrivEditFormCommand implements ICommand {

	private HttpServletRequest request;
	private PrivilegeDAO privlegeDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("receive id : "+selectid);
		Privilege priv = new Privilege();
		try{
			int i = Integer.parseInt(selectid);
			if(i>=0){
				priv = privlegeDAO.getPrivilege(i);
				LogManager.getLogger(this.getClass()).info(String.format("Index %d : Title:%s",i, priv.getTitle()));
			}
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).warn(e);
		}
		modelView.setView(viewList.getPrivilegeeditformPage());
		modelView.setModule(priv);
		modelView.setModelName("priv");
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.privlegeDAO = this.variable.getPrivilegeDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
