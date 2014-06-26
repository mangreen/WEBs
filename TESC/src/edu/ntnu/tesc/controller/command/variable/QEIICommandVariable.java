package edu.ntnu.tesc.controller.command.variable;

import javax.servlet.http.HttpServletRequest;

public class QEIICommandVariable implements ICommandVariable {
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
