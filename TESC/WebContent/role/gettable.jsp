<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%
// 輸出json格式參考
// {total: '99', 
//  page: '1', 
//  rows:[{autoIndex: '1', cell: [1, '1', 'do', 'Db_user_do', '']},
//        {autoIndex: '2', cell: [2, '1', 'professor', 'Db_user_professor', '']},
//        {autoIndex: '3', cell: [3, '0', 'student', 'Db_user_student', '']}
//       ]}
String selectid=request.getParameter("selectid");


System.out.println("XXXXXX");

if(selectid.equals("-1")){

	out.print("{total: '99', page: '1', rows:[{id: '1', cell: [1, '1', 'do', 'Db_user_do', '']},{id: '2', cell: [2, '1', 'professor', 'Db_user_professor', '']},{id: '3', cell: [3, '0', 'student', 'Db_user_student', '']}]}");
}else if(selectid.equals("4")){
	out.print("{total: '99', page: '1', rows:[{id: '1', cell: [1, '1', 'do', 'Db_user_do', '']},{id: '2', cell: [2, '1', 'professor', 'Db_user_professor', '']},{id: '3', cell: [3, '0', 'student', 'Db_user_student', '']},{id: '4', cell: [4, '0', 'school', 'Db_user_school', '']}]}");				  

}


				  

%>