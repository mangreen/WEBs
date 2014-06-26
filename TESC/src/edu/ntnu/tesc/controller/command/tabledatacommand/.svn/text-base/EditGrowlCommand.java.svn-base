package edu.ntnu.tesc.controller.command.tabledatacommand;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditGrowlCommand implements ICommand {

	private HttpServletRequest request;
	private GrowlDAO growlDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;	

	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("find id : "+selectid);		
		Growl model = new Growl();
		
		String userID = request.getParameter("userID");
		String targetUserID = request.getParameter("targetUserID");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String cDate = request.getParameter("cDate");
		String actionIP = request.getParameter("actionIP");
		String summary = request.getParameter("summary");
		String gDate = request.getParameter("gDate");
		String gActionIP = request.getParameter("gActionIP");
		
		int autoindex = -1;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

		try{		
			
			model.setUserID(Integer.parseInt(userID));
			model.setActionIP(actionIP);
			model.setTargetUserID(Integer.parseInt(targetUserID));
			model.setType(type);
			model.setState(state);
			model.setCDate(new Timestamp(format1.parse(cDate).getTime()));
			
			model.setSummary(summary);
			model.setGActionIP(gActionIP);
			model.setGDate(new Timestamp(format1.parse(gDate).getTime()));
			LogManager.getLogger(this.getClass()).info(String.format("%d , %d", model.getCDate().getTime(),model.getGDate().getTime()));
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).error(e);
			module.setText("{success:'false',errorMessage:'輸入值有錯誤'}");
			modelView.setView(viewList.getJsonPage());
			modelView.setModule(module);
			return modelView;
		}		
		
		
		
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
			LogManager.getLogger(this.getClass()).info(String.format("insert new Growl UserID:'%d'",model.getUserID()));
			int i = growlDAO.insertGrowl(model);
			LogManager.getLogger(this.getClass()).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
			LogManager.getLogger(this.getClass()).info(String.format("update Growl UserID: %d '",model.getUserID()));
			/**
			 * 修改舊有的角色
			 */
			
			model.setAutoindex(autoindex);
			//LogManager.getLogger(this.getClass()).info("update id : "+role.getAutoindex());
			int i = growlDAO.updateGrowl(model);
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
