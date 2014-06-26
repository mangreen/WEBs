<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%

String qeii_type=request.getParameter("qeii_type");
String qeii_schoolTitle=request.getParameter("qeii_schoolTitle");
String qeii_schoolAddr=request.getParameter("qeii_schoolAddr");

String success="true";
String qeii_autoIndex="5";

System.out.print(success);
System.out.print("{ "+"qeii.type: '"+qeii_type+"' , qeii.schoolTitle: '"+qeii_schoolTitle+"' , qeii.schoolAddr: '"+qeii_schoolAddr+"' , autoIndex: '"+qeii_autoIndex+"' , success: '"+success+"'"+" }");
out.print("{ "+"qeii_type: '"+qeii_type+"' , qeii_schoolTitle: '"+qeii_schoolTitle+"' , qeii_schoolAddr: '"+qeii_schoolAddr+"' , autoIndex: '"+qeii_autoIndex+"' , success: '"+success+"'"+" }");//设置返回值
//这里可以做后台操作。
 %>