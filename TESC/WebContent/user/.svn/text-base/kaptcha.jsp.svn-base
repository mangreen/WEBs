<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>

<%
String c = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
String parm = (String) request.getParameter("kaptchafield");
System.out.print(parm);
//out.println("Parameter: " + parm + " ? Session Key: " + c + " : ");
			
if (c != null && parm != null) {
	if (c.equals(parm)) {
		System.out.print("{ "+"success : 'true'"+" }");
		out.print("{ " + "success : 'true'" + " }");
	} else {
		System.out.print("{ "+"success : 'true'"+" }");
		out.print("{ " + "success : 'false'" + " }");
	}
}

%>