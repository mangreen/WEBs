package edu.ntnu.tesc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.ntnu.tesc.module.beans.User;
import edu.ntnu.tesc.module.dao.UserDAO;

public class HelloController implements Controller {
    private String viewPage;
    private UserDAO userDAO;

	public UserDAO getUserDAO()
	{
		return userDAO;
	}
	
	public  void setUserDAO(UserDAO userDao) {
		this.userDAO = userDao;
	}

	public ModelAndView handleRequest(HttpServletRequest req, 
                                            HttpServletResponse res) 
                                                     throws Exception {
//        String user = req.getParameter("user");
        
//        Test test = new Test();
//      test.setId(9);
//      test.setName("§õ¥ý¥Í");
//        testDAO.insert(test);
        
//        List userdata = userDAO.find(1);
        
        
//        User user = new User();
//        user.setAccount("demo_user");
//        user.setEmail("demo@msn.com");
//        user.setPassword("demo");
//        user.setRoleId(1);
//        userDAO.insert(user);
        
        User user = userDAO.find("Jerome");
        System.out.println(user.getLastLoginDateTime());
        
//        userDAO.delete(user.getId());
//        User user = userDAO.find(1);
//        System.out.println(user.getAccount());
//        user.setState(1);
//        System.out.println(user.getState());
//        userDAO.update(user);
//        user.setState(0);
//        Iterator it = testList.iterator();
//        String name = "";
//        while (it.hasNext()) {
//        	Map testMap = (Map) it.next();
//            System.out.println("id=" + testMap.get("id"));
//            System.out.println("name=" + testMap.get("name"));
//            name = (String)testMap.get("name");
//        }
        
        return new ModelAndView(viewPage, "user", user);
    }
    
    public void setViewPage(String viewPage) {
        this.viewPage = viewPage;
    }
}
