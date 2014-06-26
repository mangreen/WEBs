package edu.ntnu.tesc.controller.command.variable;

import javax.servlet.http.HttpServletRequest;

public interface ICommandVariable {
	public HttpServletRequest getRequest();
	public void setRequest(HttpServletRequest request);
}
