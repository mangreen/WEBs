<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%@ page import="edu.ntnu.tesc.module.viewmodule.*" %>
<%@ page import="java.util.*" %>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
	<%
	//HashMap module = (HashMap)request.getAttribute("model");
	ArrayList<MenuItem> list = (ArrayList<MenuItem>)request.getAttribute("model");
	System.out.println("size : "+list.size());
	
	List li=new ArrayList();
	li.add("123");
	li.add("234");
	li.add("456");
	request.setAttribute("li",li);
	
	%>
	<h3> 常用功能表</h3>
	<p>
	    <c:forEach items="${model}" var="menu" varStatus="status">
	   	model : ${model} <br />
	   	menu : ${menu} <br />
	   	val : <c:out value ="{menu.val}" /> <br />
	   	menu : <c:out value ="${menu}" /> <br />
		</c:forEach>    
		<br />	
		<c:forEach var="lli" items="${li}">
		    ${li}<br><br>
		</c:forEach>
		<br />
		<spring:bind path="model">
			value : <c:out value="${status.val}" /> <br />
		</spring:bind>
	</p>
	<p></p>
	