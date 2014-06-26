package org.iii.esti;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	public void init(ServletConfig conf)throws ServletException{
		super.init(conf);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		 res.setContentType("text/html;charset=UTF-8");  
	        res.setHeader("Cache-Control","no-cache");   
	        String jsonData="[{id:200901,name:'姓名1'},{id:200902,name:'姓名2'},{id:200903,name:'姓名3'},{id:200904,name:'姓名4'},{id:200905,name:'姓名5'}]";
	        
	        System.out.println(jsonData);  
	        res.getWriter().write(jsonData);
	        res.getWriter().close();
		/*
		HttpSession session = req.getSession(false);
		
		if(session == null){
			res.sendRedirect("Error.jsp");
		}
		
		String action = req.getParameter("Submit"); // 取得目前使用者要執行的動作
		
		if(action.equals("Send")){
			
			User user = getUser(req);
			/
			String account = req.getParameter("Account");
			String password = req.getParameter("Password");
			if(account.equals("mike")&&password.equals("1234")){
				session.setAttribute("Login","OK");
				res.sendRedirect("Member.jsp");
			}else{
				res.sendRedirect("Error.jsp");
			}/
			UserDAO uDAO = new UserDAO();
			if(uDAO.check(user)){
				session.setAttribute("Login","OK");
				res.sendRedirect("Member.jsp");
			}else{
				res.sendRedirect("Error.jsp");
			}
		}else if(action.equals("Register")){
			//res.sendRedirect("Error.jsp");
		}*/
	}
	
	private User getUser(HttpServletRequest req){
		//String account = encoding(req.getParameter("Account"));
		//String password = encoding(req.getParameter("Password"));
		String account = req.getParameter("Account");
		String password = req.getParameter("Password");
		
		User user = new User(account, password);
		//user.setAccount(account);
		//user.setPassword(password);
		
		return user;
	}
	
	private String encoding(String str){
		try{
			str = new String(str.getBytes(""), "");
		}catch(UnsupportedEncodingException uee){
			System.out.println("UnsupportedEncodingException: "+uee.getMessage());
		}
		
		return str;
	}
}
