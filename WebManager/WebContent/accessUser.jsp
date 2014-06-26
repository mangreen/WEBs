<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accessUser Test</title>
</head>
<body>

<jsp:useBean id="beanUser" class="bean.accessUser" scope="page" />
	<jsp:setProperty name="beanUser" property="name" value="Kevin" />

<%	beanUser.setDept("總經理室");
	String strName = beanUser.getName();
	out.println("員工姓名:<b>" + strName + "</b><p>");
%>
<jsp:getProperty name="beanUser" property="dept" />
</body>
</html>