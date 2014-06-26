<%@page contentType="text/plain; charset=UTF-8"
language="java"
import="java.io.*,java.net.*,java.util.*"
buffer="8kb"
session="false"
autoFlush="true"
%>
<%
/*
 * jsonData 可通过数据库查询生成JSON格式，输出到客户端，然后客户端通过AJAX获取后格式化数据并在HTML中展现出来
 * JSON使用方便，数据体积相对较小，结合JQUERY后非常容易上手使用。现在JSON+JQUERY已成非常流行的使用组合。
 * */
String jsonData="[{id:200901,name:'姓名1'},{id:200902,name:'姓名2'},{id:200903,name:'姓名3'},{id:200904,name:'姓名4'},{id:200905,name:'姓名5'}]";
out.write(jsonData);
%>
