package edu.ntnu.tesc.controller.command.qeii;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import edu.ntnu.tesc.controller.command.CommonRequestParamCapturer;
import edu.ntnu.tesc.controller.command.ICommand;
import edu.ntnu.tesc.controller.command.variable.ICommandVariable;
import edu.ntnu.tesc.controller.command.variable.QEIICommandVariable;
import edu.ntnu.tesc.controller.modelview.IModelView;
import edu.ntnu.tesc.controller.viewlist.IViewList;
import edu.ntnu.tesc.controller.viewlist.QEIIViewList;

public class EditQEIIBasicDataCommand implements ICommand {
	
	private HttpServletRequest request;	
	private int qeii_type;
	private String qeii_schoolTitle;
	private QEIICommandVariable variable;
	private QEIIViewList viewList;
	public int getQeii_type() {
		return qeii_type;
	}

	public void setQeii_type(int qeii_type) {
		this.qeii_type = qeii_type;
	}

	public String getQeii_schoolTitle() {
		return qeii_schoolTitle;
	}
	
	public void setQeii_schoolTitle(String qeii_schoolTitle) {
		this.qeii_schoolTitle = qeii_schoolTitle;
	}
	
	
	public void loadRequestParameter() throws SecurityException, IllegalArgumentException, NoSuchMethodException, Exception,IllegalAccessException, InvocationTargetException{
		String[] fields = {"qeii_type","qeii_schoolTitle"};
		CommonRequestParamCapturer.captureParam(this, fields, request);
	}
	
	@Override
	public IModelView execute() {
		try {
			loadRequestParameter();
			System.out.println(qeii_type+"\t"+qeii_schoolTitle);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setVariable(ICommandVariable variable) {
		// TODO Auto-generated method stub
		this.variable = (QEIICommandVariable)variable;
		this.request = this.variable.getRequest();
	}

	@Override
	public void setViewList(IViewList viewlist) {
		// TODO Auto-generated method stub
		this.viewList = (QEIIViewList)viewList;
	}

}
