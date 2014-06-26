package edu.ntnu.tesc.controller.command.factory;

import java.util.HashMap;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.account.CheckUserLoginCommand;
import edu.ntnu.tesc.controller.command.globalfunction.LoadCommonFeaturesCommand;

public class GlobalCommandFactory implements ICommandFactory {
	
	private HashMap<String,String> commandMap;
	public static final String LOADCOMMONFEATURES="loadcommonfeatures",CHECKUSERLOGIN="checkuserlogin";
	public GlobalCommandFactory(){
		commandMap = new HashMap<String,String>();
		commandMap.put(LOADCOMMONFEATURES, LoadCommonFeaturesCommand.class.getName());
		commandMap.put(CHECKUSERLOGIN,CheckUserLoginCommand.class.getName());
	}
	
	@Override
	public ICommand getCommandObject(String command) {
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
