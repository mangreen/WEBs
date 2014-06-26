package edu.ntnu.tesc.controller.viewlist;

public class GlobalFunctionViewList implements IViewList {
	private String jsonPage;
    private String commonFeaturesPage;
    private String loginPage;
    
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public String getCommonFeaturesPage() {
		return commonFeaturesPage;
	}

	public void setCommonFeaturesPage(String commonFeaturesPage) {
		this.commonFeaturesPage = commonFeaturesPage;
	}

	public String getJsonPage() {
		return jsonPage;
	}

	public void setJsonPage(String jsonPage) {
		this.jsonPage = jsonPage;
	}
	
}
