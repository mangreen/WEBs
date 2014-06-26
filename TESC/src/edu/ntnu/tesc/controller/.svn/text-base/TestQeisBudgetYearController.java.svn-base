package edu.ntnu.tesc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.Privilege;
import edu.ntnu.tesc.module.beans.QeisBudgetYear;
import edu.ntnu.tesc.module.dao.QeisBudgetYearDAO;

public class TestQeisBudgetYearController implements Controller {

	private QeisBudgetYearDAO qeisBudgetYearDAO;
	private String viewPage;
	
	public QeisBudgetYearDAO getQeisBudgetYearDAO() {
		return qeisBudgetYearDAO;
	}

	public void setQeisBudgetYearDAO(QeisBudgetYearDAO qeisBudgetYearDAO) {
		this.qeisBudgetYearDAO = qeisBudgetYearDAO;
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
		
		QeisBudgetYear obj1 = new QeisBudgetYear();
		obj1.setBudgetYear(96);
		obj1.setCurrentPrice(100);
		obj1.setCapitalPrice(100);
		System.out.println(this.qeisBudgetYearDAO.insertQeisBudgetYear(obj1));
		
		QeisBudgetYear obj2 = new QeisBudgetYear();
		obj2.setBudgetYear(97);
		obj2.setCurrentPrice(200);
		obj2.setCapitalPrice(100);
		System.out.println(this.qeisBudgetYearDAO.insertQeisBudgetYear(obj2));
		
		QeisBudgetYear obj3 = new QeisBudgetYear();
		obj3.setBudgetYear(98);
		obj3.setCurrentPrice(300);
		obj3.setCapitalPrice(100);
		System.out.println(this.qeisBudgetYearDAO.insertQeisBudgetYear(obj3));
		
		QeisBudgetYear temp;
		List<QeisBudgetYear> lists = this.qeisBudgetYearDAO.getQeisBudgetYearList();
		for(int i = 0; i < lists.size(); i++)
		{
			temp = lists.get(i);
			System.out.println("id: " + temp.getAutoindex());
			System.out.println("budgetYear: " + temp.getBudgetYear());
			System.out.println("CurrentPrice: " + temp.getCurrentPrice());
			System.out.println("CapitalPrice: " + temp.getCapitalPrice());
		}
		
		temp = this.qeisBudgetYearDAO.getQeisBudgetYear(2);
		System.out.println("id: " + temp.getAutoindex());
		System.out.println("budgetYear: " + temp.getBudgetYear());
		System.out.println("CurrentPrice: " + temp.getCurrentPrice());
		System.out.println("CapitalPrice: " + temp.getCapitalPrice());
		
		temp.setCapitalPrice(400);
		System.out.println("Update: " + this.qeisBudgetYearDAO.updateQeisBudgetYear(temp));
		
		System.out.println("Delete: " + this.qeisBudgetYearDAO.delQeisBudgetYear(3));
		
		return new ModelAndView(viewPage, "user", "");
	}

}
