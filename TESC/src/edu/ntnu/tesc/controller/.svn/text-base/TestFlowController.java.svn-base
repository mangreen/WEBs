package edu.ntnu.tesc.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.Flow;
import edu.ntnu.tesc.module.beans.Privilege;
import edu.ntnu.tesc.module.dao.FlowDAO;

public class TestFlowController implements Controller {

	private FlowDAO flowDAO;
	private String viewPage;
    
	public FlowDAO getFlowDAO() {
		return flowDAO;
	}

	public void setFlowDAO(FlowDAO flowDAO) {
		this.flowDAO = flowDAO;
	}

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
		
		SimpleDateFormat formatter =  new SimpleDateFormat("yyyy/MM/dd 'at' hh:mm:ss a zzz");
        java.util.Date today = new Date();
        System.out.println(String.valueOf(formatter.format(today)));
        System.out.println(today.getTime());
        
		Flow obj1 = new Flow();
		obj1.setRoleID(1);
		obj1.setUserID(1);
		obj1.setFlowModuleID(1);
		obj1.setFlowStageID(10);
		obj1.setType("t");
		obj1.setState("v");
		obj1.setcDate(new Timestamp(today.getTime()));
		obj1.setpDate(new Timestamp(today.getTime()));
		System.out.println(this.flowDAO.insertFlow(obj1));
		
		Flow obj2 = new Flow();
		obj2.setRoleID(1);
		obj2.setUserID(1);
		obj2.setFlowModuleID(1);
		obj2.setFlowStageID(10);
		obj2.setType("a");
		obj2.setState("b");
		obj2.setcDate(new Timestamp(today.getTime()));
		obj2.setpDate(new Timestamp(today.getTime()));
		System.out.println(this.flowDAO.insertFlow(obj2));
		
		Flow temp;
		List<Flow> lists = this.flowDAO.getFlowList();
		for(int i = 0; i < lists.size(); i++)
		{
			temp = lists.get(i);
			System.out.println("id: " + temp.getAutoindex());
			System.out.println("roleID: " + temp.getRoleID());
			System.out.println("userID: " + temp.getUserID());
			System.out.println("FlowModuleID: " + temp.getFlowModuleID());
			System.out.println("FlowStageID: " + temp.getFlowStageID());
			System.out.println("Tyep: " + temp.getType());
			System.out.println("State: " + temp.getState());
			System.out.println("cDate: " + temp.getcDate());
			System.out.println("pDate: " + temp.getpDate());
		}
		
		temp = this.flowDAO.getFlow(1);
		System.out.println("id: " + temp.getAutoindex());
		System.out.println("roleID: " + temp.getRoleID());
		System.out.println("userID: " + temp.getUserID());
		System.out.println("FlowModuleID: " + temp.getFlowModuleID());
		System.out.println("FlowStageID: " + temp.getFlowStageID());
		System.out.println("Tyep: " + temp.getType());
		System.out.println("State: " + temp.getState());
		System.out.println("cDate: " + temp.getcDate());
		System.out.println("pDate: " + temp.getpDate());
		
		return new ModelAndView(viewPage, "user", "");
	}

}
