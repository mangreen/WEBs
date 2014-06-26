package edu.ntnu.tesc.module.viewmodule;

public class LoginModule implements IModule {
	private String account;
	private boolean success;
	private String message="";
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String toJSONString() {
		if(success)
			return  String.format("{account:'%s',success:'%s',message:'%s'}", account,"true",message);
		else
			return  String.format("{account:'%s',success:'%s',message:'%s'}", account,"false",message);
	}
	
	
	
}
