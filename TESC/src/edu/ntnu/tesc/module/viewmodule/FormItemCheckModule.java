package edu.ntnu.tesc.module.viewmodule;

public class FormItemCheckModule implements IModule {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return this.text;
	}


}
