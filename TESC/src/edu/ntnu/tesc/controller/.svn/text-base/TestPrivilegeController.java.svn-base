package edu.ntnu.tesc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.Privilege;
import edu.ntnu.tesc.module.dao.PrivilegeDAO;

public class TestPrivilegeController implements Controller {

	private PrivilegeDAO privilegeDAO;
    private String viewPage;
    
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	public PrivilegeDAO getPrivilegeDAO() {
		return privilegeDAO;
	}

	public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
		this.privilegeDAO = privilegeDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
		Privilege obj1 = new Privilege();
		obj1.setPrivLevel(1);
		obj1.setPs("Top level");
		obj1.setState(1);
		obj1.setTitle("Admin");
		System.out.println(this.privilegeDAO.insertPrivilege(obj1));
		
		Privilege obj2 = new Privilege();
		obj2.setPrivLevel(2);
		obj2.setPs("2nd level");
		obj2.setState(1);
		obj2.setTitle("Editor");
		System.out.println(this.privilegeDAO.insertPrivilege(obj2));
		
		Privilege obj3 = new Privilege();
		obj3.setPrivLevel(3);
		obj3.setPs("3nd level");
		obj3.setState(1);
		obj3.setTitle("User");
		System.out.println(this.privilegeDAO.insertPrivilege(obj3));
		
		Privilege priv;
		List<Privilege> lists = this.privilegeDAO.getPrivilegeList();
		for(int i = 0; i < lists.size(); i++)
		{
			priv = lists.get(i);
			System.out.println("id: " + priv.getAutoindex());
			System.out.println("state: " + priv.getState());
			System.out.println("title: " + priv.getTitle());
			System.out.println("PrivLevel: " + priv.getPrivLevel());
			System.out.println("PS: " + priv.getPs());
		}
		
		priv = this.privilegeDAO.getPrivilege(2);
		System.out.println("id: " + priv.getAutoindex());
		System.out.println("state: " + priv.getState());
		System.out.println("title: " + priv.getTitle());
		System.out.println("PrivLevel: " + priv.getPrivLevel());
		System.out.println("PS: " + priv.getPs());
		
		priv.setPs("2nd Level and changing");
		System.out.println("Update: " + this.privilegeDAO.updatePrivilege(priv));
		
		System.out.println("Delete: " + this.privilegeDAO.delPrivilege(3));
		
		return new ModelAndView(viewPage, "user", "");
	}

}
