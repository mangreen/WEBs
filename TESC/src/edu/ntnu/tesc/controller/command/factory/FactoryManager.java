package edu.ntnu.tesc.controller.command.factory;

import java.util.HashMap;

public class FactoryManager {
	
	private static FactoryManager instance;
	private HashMap<String,ICommandFactory> factoryMap;
	public static String LOGINFACTORY="login",ROLEFACTORY="role",QEIIFACTORY="qeii",GLOBALFACTORY="global";
	
	
	private FactoryManager(){		
		factoryMap = new HashMap<String,ICommandFactory>();
		LoginCommandFactory lcf = new LoginCommandFactory();
		factoryMap.put(LOGINFACTORY, lcf);
		TableDataCommandFactory rcf = new TableDataCommandFactory();
		factoryMap.put(ROLEFACTORY,rcf);
		QEIICommandFactory qcf = new QEIICommandFactory();
		factoryMap.put(QEIIFACTORY, qcf);
		GlobalCommandFactory gcf = new GlobalCommandFactory();
		factoryMap.put(GLOBALFACTORY, gcf);
	}
	
	public static FactoryManager getInstance(){
		if(instance == null)
			instance = new FactoryManager();
		return instance;
	}
	
	public ICommandFactory getCommandFacotry(String factory_name){
		return factoryMap.get(factory_name);
	}
}
