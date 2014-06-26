package edu.ntnu.tesc.controller.command.factory;

import java.util.HashMap;

import org.apache.log4j.LogManager;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteConfigCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteDepartCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteFlowCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteGrowlCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeletePrivCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteQeisCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteRoleCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.DeleteStageCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditConfigCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditDepartCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditFlowCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditGrowlCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditPrivCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditQeisCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditRoleCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.EditStageCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetConfigTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetDepartTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetFlowTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetGrowlTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetPrivTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetQeisTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetRoleTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.GetStageTableCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadConfigEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadDepartEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadFlowEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadGrowlEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadPrivEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadQeisEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadRoleEditFormCommand;
import edu.ntnu.tesc.controller.command.tabledatacommand.LoadStageEditFormCommand;


public class TableDataCommandFactory implements ICommandFactory {

	public final static String GETROLETABLE = "gettable",EDITROLE="editrole",DELETEROLE="deleterole",LOADROLEEDITFORM="loadeditform"; 
	public final static String GETPRIVTABLE = "getprivtable",EDITPRIV="editpriv",DELETEPRIV="deletepriv",LOADPRIVEDITFORM="loadprivform";
	public final static String GETFLOWTABLE = "getflowtable",EDITFLOW="editflow",DELETEFLOW="deleteflow",LOADFLOWEDITFORM="loadflowform";
	public final static String GETQEISTABLE = "getqeistable",EDITQEIS="editqeis",DELETEQEIS="deleteqeis",LOADQEISEDITFORM="loadqeisform";
	public final static String GETSTAGETABLE = "getstagetable",EDITSTAGE="editstage",DELETESTAGE="deletestage",LOADSTAGEEDITFORM="loadstageform";
	public final static String GETGROWLTABLE = "getgrowltable",EDITGROWL="editgrowl",DELETEGROWL="deletegrowl",LOADGROWLEDITFORM="loadgrowlform";
	public final static String GETCONFIGTABLE = "getconfigtable",EDITCONFIG="editconfig",DELETECONFIG="deleteconfig",LOADCONFIGEDITFORM="loadconfigform";
	public final static String GETDEPARTTABLE = "getdeparttable",EDITDEPART="editdepart",DELETEDEPART="deletedepart",LOADDEPARTEDITFORM="loaddepartform";

	private HashMap<String,String> commandMap;
	
	public TableDataCommandFactory(){
		commandMap = new HashMap<String,String>();
		// Role
		commandMap.put(GETROLETABLE, GetRoleTableCommand.class.getName());
		commandMap.put(EDITROLE, EditRoleCommand.class.getName());
		commandMap.put(DELETEROLE, DeleteRoleCommand.class.getName());
		commandMap.put(LOADROLEEDITFORM, LoadRoleEditFormCommand.class.getName());
		// Priviledge
		commandMap.put(GETPRIVTABLE, GetPrivTableCommand.class.getName());
		commandMap.put(EDITPRIV, EditPrivCommand.class.getName());
		commandMap.put(DELETEPRIV, DeletePrivCommand.class.getName());
		commandMap.put(LOADPRIVEDITFORM, LoadPrivEditFormCommand.class.getName());
		//flow
		commandMap.put(GETFLOWTABLE, GetFlowTableCommand.class.getName());
		commandMap.put(EDITFLOW, EditFlowCommand.class.getName());
		commandMap.put(DELETEFLOW, DeleteFlowCommand.class.getName());
		commandMap.put(LOADFLOWEDITFORM, LoadFlowEditFormCommand.class.getName());
		//qeis
		commandMap.put(GETQEISTABLE, GetQeisTableCommand.class.getName());
		commandMap.put(EDITQEIS, EditQeisCommand.class.getName());
		commandMap.put(DELETEQEIS, DeleteQeisCommand.class.getName());
		commandMap.put(LOADQEISEDITFORM, LoadQeisEditFormCommand.class.getName());
		
		//stage
		commandMap.put(GETSTAGETABLE, GetStageTableCommand.class.getName());
		commandMap.put(EDITSTAGE, EditStageCommand.class.getName());
		commandMap.put(DELETESTAGE, DeleteStageCommand.class.getName());
		commandMap.put(LOADSTAGEEDITFORM, LoadStageEditFormCommand.class.getName());
		
		//config
		commandMap.put(GETCONFIGTABLE, GetConfigTableCommand.class.getName());
		commandMap.put(EDITCONFIG, EditConfigCommand.class.getName());
		commandMap.put(DELETECONFIG, DeleteConfigCommand.class.getName());
		commandMap.put(LOADCONFIGEDITFORM, LoadConfigEditFormCommand.class.getName());
		
		//department
		commandMap.put(GETDEPARTTABLE, GetDepartTableCommand.class.getName());
		commandMap.put(EDITDEPART, EditDepartCommand.class.getName());
		commandMap.put(DELETEDEPART, DeleteDepartCommand.class.getName());
		commandMap.put(LOADDEPARTEDITFORM, LoadDepartEditFormCommand.class.getName());
			
		//Growl
		commandMap.put(GETGROWLTABLE, GetGrowlTableCommand.class.getName());
		commandMap.put(EDITGROWL, EditGrowlCommand.class.getName());
		commandMap.put(DELETEGROWL, DeleteGrowlCommand.class.getName());
		commandMap.put(LOADGROWLEDITFORM, LoadGrowlEditFormCommand.class.getName());	
				
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
