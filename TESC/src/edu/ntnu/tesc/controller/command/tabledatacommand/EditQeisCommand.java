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
import edu.ntnu.tesc.module.viewmodule.FormItemCheckModule;

public class EditQeisCommand implements ICommand {

	private HttpServletRequest request;
	private QeisBudgetYearDAO qeisDAO;
	private TableDataViewList viewList;
	private TableDataCommandVariable variable;
	
	@Override
	public IModelView execute() {
		CommonModelView modelView = new CommonModelView();
		FormItemCheckModule module = new FormItemCheckModule();
		
		String selectid=request.getParameter("selectid");
		LogManager.getLogger(this.getClass()).info("find id : "+selectid);		
		QeisBudgetYear model = new QeisBudgetYear();
		String budgetYear = request.getParameter("budgetYear");
		String currentPrice = request.getParameter("currentPrice");
		String capitalPrice = request.getParameter("capitalPrice");
		
		int autoindex = -1;
		try{
			model.setBudgetYear(Integer.parseInt(budgetYear));
			model.setCapitalPrice(Integer.parseInt(capitalPrice));
			model.setCurrentPrice(Integer.parseInt(currentPrice));
		}
		catch(Exception e){
			LogManager.getLogger(this.getClass()).error(e);
			module.setText("{success:'false',errorMessage:'資料輸入錯誤'}");
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
			LogManager.getLogger(this.getClass()).info(String.format("insert new budget year:'%d'",model.getBudgetYear()));
			int i = qeisDAO.insertQeisBudgetYear(model);
			LogManager.getLogger(this.getClass()).info("insert result : "+i);
			
			if(i==0)
				module.setText("{success:'false'}");
			else
				module.setText("{success:'true'}");			
		}
		else{
			LogManager.getLogger(this.getClass()).info(String.format("update budget year %d '",model.getBudgetYear()));
			/**
			 * 修改舊有的角色
			 */
			
			model.setAutoindex(autoindex);
			//LogManager.getLogger(this.getClass()).info("update id : "+role.getAutoindex());
			int i = qeisDAO.updateQeisBudgetYear(model);
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
		this.qeisDAO = this.variable.getQeisDAO();
		this.request = this.variable.getRequest();
	}

	public void setViewList(IViewList viewlist) {
		this.viewList = (TableDataViewList)viewlist;
	}

}
