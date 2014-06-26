package edu.ntnu.tesc.controller.command.globalfunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.account.CheckUserLoginCommand;
import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.GlobalCommandFactory;
import edu.ntnu.tesc.controller.command.variable.GlobalFunctionCommandVariable;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.modelview.CommonModelView;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.GlobalFunctionViewList;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.dao.ConfigDAO;
import edu.ntnu.tesc.module.viewmodule.CheckLoginModule;
import edu.ntnu.tesc.module.viewmodule.GlobalFucntionModule;
import edu.ntnu.tesc.module.viewmodule.LoginModule;
import edu.ntnu.tesc.module.viewmodule.MenuItem;

public class LoadCommonFeaturesCommand implements ICommand {
	
	private HttpServletRequest request;
    private ConfigDAO configDAO;	
	private GlobalFunctionViewList viewlist;
	private GlobalFunctionCommandVariable variable;
	private String type;
	
	
	@Override
	public IModelView execute() {
		
		CommonModelView modelView = new CommonModelView();
		GlobalFucntionModule g_module = new GlobalFucntionModule();		
		CheckUserLoginCommand command = new CheckUserLoginCommand();
		command.setVariable(variable);
		IModelView mv = command.execute();
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		if(mv!=null){
			CheckLoginModule module = (CheckLoginModule)mv.getModule();
			if(module.isSuccess()){
				LogManager.getLogger(this.getClass()).info("user login!!");
				User user = module.getUser();
				modelView.setModule(user);
				
				type = request.getParameter("type");
				String role_name = "";
				if(type.equals("sys")){
					role_name = "leftbtn_sys";
				}
				else{
					role_name = String.format("leftbtn_%d_1", user.getRoleId());
				}
				MenuItem mi = new MenuItem();
				mi.setVal("登入師培網");
				mi.setVal2("loadpage(1);");
				items.add(mi);
			}
			else{
				LogManager.getLogger(this.getClass()).info("user not login!!");
				for(int i=0;i<4;i++){
					MenuItem mi = new MenuItem();
					mi.setVal("12345");
					mi.setVal2("loadpage(1);");
					items.add(mi);
				}
			}
		}
		g_module.setItems(items);
		modelView.setView(viewlist.getCommonFeaturesPage());
		modelView.setModule(items);
		
		return modelView;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		GlobalFunctionCommandVariable var = (GlobalFunctionCommandVariable)variable;
		this.variable = var;
		this.configDAO = var.getConfigDAO();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewlist = (GlobalFunctionViewList) viewlist; 
	}

}
