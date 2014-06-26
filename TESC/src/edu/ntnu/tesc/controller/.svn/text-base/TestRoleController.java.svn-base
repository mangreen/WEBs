package edu.ntnu.tesc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.Role;
import edu.ntnu.tesc.module.dao.RoleDAO;

public class TestRoleController implements Controller {

	private RoleDAO roleDAO;
	private String viewPage;
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
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
		
//		Role role = new Role();
//		role.setState(1);
//		role.setTitle("role 2");
//		role.setDetailTable("role2 detail");
//		role.setPs("Role 2 PS");
//		
//		Role role1 = new Role();
//		role1.setState(1);
//		role1.setTitle("role 3");
//		role1.setDetailTable("role3 detail");
//		role1.setPs("Role 3 PS");
//		
//		this.roleDAO.insertRole(role);
//		this.roleDAO.insertRole(role1);
		
		Role role;
		
		List<Role> results = this.roleDAO.getRoleList();
		for(int i = 0; i < results.size(); i++)
		{
			role = results.get(i);
			System.out.println("id: " + role.getAutoindex());
			System.out.println("state: " + role.getState());
			System.out.println("title: " + role.getTitle());
			System.out.println("detailTable: " + role.getDetailTable());
			System.out.println("PS: " + role.getPs());
		}
		
		Role oneRole = this.roleDAO.getRole(3);
		System.out.println("id: " + oneRole.getAutoindex());
		System.out.println("state: " + oneRole.getState());
		System.out.println("title: " + oneRole.getTitle());
		System.out.println("detailTable: " + oneRole.getDetailTable());
		System.out.println("PS: " + oneRole.getPs());
		
		oneRole.setPs("Change infor");
		System.out.println("Update: " + this.roleDAO.updateRole(oneRole));
		
//		this.roleDAO.delRole(4);
		
		return new ModelAndView(viewPage, "user", "");
	}

}
