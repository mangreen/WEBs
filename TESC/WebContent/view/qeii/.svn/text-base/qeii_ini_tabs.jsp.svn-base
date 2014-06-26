<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">基本資料</a></li>
		<li><a href="#tabs-2">初步審查結果</a></li>
	</ul>
	<div id="tabs-1">
		
	</div>
	
	<div id="tabs-2">

	</div>

</div>

<script type="text/javascript">
	var firsttabs = $('#tabs').tabs();
	firsttabs.tabs('select', 0);
	
	$("#tabs-1").load("view/qeii/qeii_review.jsp",{account:'jimmy'},function(){
		$("#tabs-1").fadeIn('slow');}
	);
	
	$("#tabs-2").load("view/qeii/qeii_ini_censor.jsp",{account:'jimmy'},function(){
		$("#tabs-2").fadeIn('slow');}
	);

</script>