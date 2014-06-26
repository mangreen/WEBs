package edu.ntnu.tesc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.Stage;
import edu.ntnu.tesc.module.dao.StageDAO;

public class TestStageController implements Controller{
    private StageDAO stageDAO;
    private String viewPage;
    
	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	public StageDAO getStageDAO() {
		return stageDAO;
	}

	public void setStageDAO(StageDAO stageDAO) {
		this.stageDAO = stageDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Stage stage = new Stage();
		stage.setTitle("testStage0");
		
		System.out.println("Start to insert 2 records...");
		int insertReturnCode = stageDAO.insertStage(stage);
		System.out.println("insertReturnCode = " + insertReturnCode);
		
		stage.setTitle("testStage1");
		
		insertReturnCode = stageDAO.insertStage(stage);
		System.out.println("insertReturnCode = " + insertReturnCode);
		
		System.out.println("\nGet List...");
		List<Stage> stageList = stageDAO.getStageList();
		for (int i = 0 ; i < stageList.size() ; i++) {
			Stage stageTemp = stageList.get(i);
			System.out.println("The " + i + "'s title = " + stageTemp.getTitle() + "; autoindex = " + stageTemp.getAutoindex());
			
			System.out.println("Update it...");
			stageTemp.setTitle("testStageUpdate" + i);
			int updateReturnCode = stageDAO.updateStage(stageTemp);
			System.out.println("The " + i + "'s updateReturnCode = " + updateReturnCode);
		}
		
		System.out.println("\nGet List after updating them...");
		stageList = stageDAO.getStageList();
		for (int i = 0 ; i < stageList.size() ; i++) {
			Stage stageTemp = stageList.get(i);
			System.out.println("The " + i + "'s title = " + stageTemp.getTitle() + "; autoindex = " + stageTemp.getAutoindex());
			
			Stage stageTemp2 = stageDAO.getStage(stageTemp.getAutoindex());
			System.out.println("The getByID " + i + "'s title = " + stageTemp2.getTitle() + "; autoindex = " + stageTemp2.getAutoindex());
			
			System.out.println("Delete it...");
			int delReturnCode = stageDAO.delStage(stageTemp.getAutoindex());
			System.out.println("The " + i + "'s delReturnCode = " + delReturnCode);
		}
		
		return new ModelAndView(viewPage, "user", "");
	}

}
