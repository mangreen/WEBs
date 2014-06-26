package edu.ntnu.tesc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.factory.FactoryManager;
import edu.ntnu.tesc.controller.command.factory.QEIICommandFactory;
import edu.ntnu.tesc.controller.command.factory.TableDataCommandFactory;
import edu.ntnu.tesc.controller.command.variable.QEIICommandVariable;
import edu.ntnu.tesc.controller.command.variable.TableDataCommandVariable;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.QEIIViewList;

public class QEIIController implements Controller {
	
    private String jsonPage;
    private QEIIViewList viewList = new QEIIViewList();
	public void setJsonPage(String jsonPage) {
		this.jsonPage = jsonPage;
		viewList.setJsonPage(this.jsonPage);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		LogManager.getLogger(this.getClass()).info("QEII V0.01 receive command : "+action);
		if(action != null){			
			QEIICommandFactory qeiiFactory = (QEIICommandFactory)FactoryManager.getInstance().getCommandFacotry(FactoryManager.QEIIFACTORY);			
			ICommand command = qeiiFactory.getCommandObject(action);
			if(command != null){
				
				QEIICommandVariable variable = new QEIICommandVariable();
				variable.setRequest(request);

				command.setVariable(variable);
				command.setViewList(viewList);
				
				IModelView mv = command.execute();
				if(mv!=null){
					LogManager.getLogger(this.getClass()).info("goto View : "+mv.getView());
					return new ModelAndView(mv.getView(), mv.getModelName(), mv.getModule());
				}
			}
			else{
				LogManager.getLogger(this.getClass()).debug("No Such Command!!");
			}
		}
		
			
		return new ModelAndView(this.jsonPage, "model", null);			

	}

}
