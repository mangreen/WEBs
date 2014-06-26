package bean;

public class accessUser {

	private String strName;
	private String strDept;
	
	public accessUser(){}
	
	public void setName(String name){
		
		strName = name;
	}
	
	public void setDept(String dept){
		strDept = dept;
	}
	
	public String getName(){
		return strName;
	}
	
	public String getDept(){
		return strDept;
	}
}
