package edu.ntnu.tesc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.FlowModule;
import edu.ntnu.tesc.module.beans.Privilege;
import edu.ntnu.tesc.module.dao.FlowModuleDAO;

public class TestFlowModuleController implements Controller {

	private FlowModuleDAO flowModuleDAO;
    private String viewPage;
    
    
	public FlowModuleDAO getFlowModuleDAO() {
		return flowModuleDAO;
	}


	public void setFlowModuleDAO(FlowModuleDAO flowModuleDAO) {
		this.flowModuleDAO = flowModuleDAO;
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
		
		FlowModule obj1 = new FlowModule();
		obj1.setTitle("Flow 1");
		obj1.setFlowXML("<xml><doc>Flow 1</doc>");
		System.out.println(this.flowModuleDAO.insertFlowModule(obj1));
		
		FlowModule obj2 = new FlowModule();
		obj2.setTitle("Flow 2");
		obj2.setFlowXML("<xml><doc>Flow 2</doc>");
		System.out.println(this.flowModuleDAO.insertFlowModule(obj2));
		
		FlowModule obj3 = new FlowModule();
		obj3.setTitle("Flow 3");
		obj3.setFlowXML("<xml><doc>Flow 3</doc>");
		System.out.println(this.flowModuleDAO.insertFlowModule(obj3));
		
		FlowModule temp;
		List<FlowModule> lists = this.flowModuleDAO.getFlowModuleList();
		for(int i = 0; i < lists.size(); i++)
		{
			temp = lists.get(i);
			System.out.println("id: " + temp.getAutoindex());
			System.out.println("state: " + temp.getTitle());
			System.out.println("title: " + temp.getFlowXML());
		}
		
		temp = this.flowModuleDAO.getFlowModule(2);
		System.out.println("id: " + temp.getAutoindex());
		System.out.println("state: " + temp.getTitle());
		System.out.println("title: " + temp.getFlowXML());
		
		temp.setTitle("Flow 2 changed");
		System.out.println("Update: " + this.flowModuleDAO.updateFlowModule(temp));
		
		System.out.println("Delete: " + this.flowModuleDAO.delFlowModule(3));
		
		return new ModelAndView(viewPage, "user", "");
	}

}
