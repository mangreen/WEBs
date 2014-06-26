package edu.ntnu.tesc.controller.command.factory;

import java.util.HashMap;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.account.CheckAccountCommand;
import edu.ntnu.tesc.controller.command.account.CheckEmailCommand;
import edu.ntnu.tesc.controller.command.account.LoadModifyAccountFormCommand;
import edu.ntnu.tesc.controller.command.account.LoginCommand;
import edu.ntnu.tesc.controller.command.account.ModifyAccountCommand;
import edu.ntnu.tesc.controller.command.account.RegisterCommand;

public class LoginCommandFactory implements ICommandFactory {
	
	public final static String REGISTER = "register",CHECKMAIL = "checkemail",CHECKACCOUNT="checkaccount"
		,LOGIN="login",LOADMODIFYACCOUNTFORM="loadmodifyaccountform",MODIFYACCOUNT="modifyaccount"; 
	private HashMap<String,String> commandMap;
	
	public LoginCommandFactory(){
		commandMap = new HashMap<String,String>();
		
		commandMap.put(REGISTER, RegisterCommand.class.getName());
		commandMap.put(CHECKMAIL, CheckEmailCommand.class.getName());
		commandMap.put(LOGIN, LoginCommand.class.getName());
		commandMap.put(CHECKACCOUNT, CheckAccountCommand.class.getName());
		commandMap.put(LOADMODIFYACCOUNTFORM, LoadModifyAccountFormCommand.class.getName());
		commandMap.put(MODIFYACCOUNT, ModifyAccountCommand.class.getName());
	}
	
	public ICommand getCommandObject(String command){
		String class_name = commandMap.get(command);
		if(class_name != null){
			try {
				ICommand ic = (ICommand)Class.forName(class_name).newInstance();
				LogManager.getLogger(TableDataCommandFactory.class).info("Create command : "+ic.getClass().getName());
				return ic;
			} catch (InstantiationException e) {
				LogManager.getLogger(TableDataCommandFactory.class).error(e);
			} catch (IllegalAccessException e) {
				LogManager.getLogger(TableDataCommandFactory.class).error(e);
			} catch (ClassNotFoundException e) {
				LogManager.getLogger(TableDataCommandFactory.class).error(e);
			}
		}
		LogManager.getLogger(TableDataCommandFactory.class).warn("Error Command code : "+command);
		return null;
	}	
	
}
