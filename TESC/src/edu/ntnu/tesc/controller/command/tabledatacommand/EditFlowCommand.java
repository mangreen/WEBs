package edu.ntnu.tesc.controller.command.tabledatacommand;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.TableDataViewList;

import edu.ntnu.tesc.module.beans.FlowModule;
import edu.ntnu.tesc.module.dao.FlowModuleDAO;
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditFlowCommand implements ICommand {

	private HttpServletRequest request;
	private FlowModuleDAO flowModuleDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		Logger.getLogger(this.getClass()).info("find id : "+selectid);		
		FlowModule model = new FlowModule();
		
		String title = request.getParameter("title");
		String flowXML = request.getParameter("Flow_xml");
		int autoindex = -1;

		
		try{
			autoindex = Integer.parseInt(selectid);
		}
		catch(Exception e){
			Logger.getLogger(this.getClass()).info("未取得autoindex，新增資料");
		}		
		
		if(!this.checkXMLFormat(flowXML)){
			module.setText("{success:'false'}");
			modelView.setView(viewList.getJsonPage());
			modelView.setModule(module);			
			return modelView;
		}
		model.setTitle(title);
		model.setFlowXML(flowXML);
		
		
		if(autoindex<0){
			/**
			 * 增加新的角色
			 */
			Logger.getLogger(this.getClass()).info(String.format("insert new FlowModule Title:'%s'",model.getTitle()));
			int i = flowModuleDAO.insertFlowModule(model);
			Logger.getLogger(this.getClass()).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
			Logger.getLogger(this.getClass()).info(String.format("update FlowModule Title %s '",model.getTitle()));
			/**
			 * 修改舊有的角色
			 */
			
			model.setAutoindex(autoindex);
			//Logger.getLogger(this.getClass()).info("update id : "+role.getAutoindex());
			int i = flowModuleDAO.updateFlowModule(model);
			Logger.getLogger(this.getClass()).info("update result : "+i);
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");
		}
		modelView.setView(viewList.getJsonPage());
		modelView.setModule(module);
		return modelView;
	}
	
	private boolean checkXMLFormat(String xmlString){
		return true;
	}
	
	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (TableDataCommandVariable)variable;
		this.request = this.variable.getRequest();		
		this.flowModuleDAO = this.variable.getFlowModuleDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (TableDataViewList)viewlist;
	}

}
