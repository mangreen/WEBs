package edu.ntnu.tesc.controller.modelview;

import edu.ntnu.tesc.module.viewmodule.IModule;

public class CommonModelView implements IModelView {

	private Object module;
	private String view;
	private String modelName = "model";
	public Object getModule() {
		return module;
	}
	public void setModule(Object module) {
		this.module = module;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	@Override
	public String getModelName() {		
		return modelName;
	}
	@Override
	public void setModelName(String modelName) {
		// TODO Auto-generated method stub
		this.modelName = modelName;
	}
	


}
