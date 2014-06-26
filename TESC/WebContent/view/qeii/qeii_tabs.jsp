<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>

<div id="tabs">
	<ul>
		<li id="tabs_1"><a href="#tabs-1">帳號資料</a></li>
		<li><a href="#tabs-2">基本資料</a></li>
		<li><a href="#tabs-3">自評表1</a></li>
		<li><a href="#tabs-4">自評表2</a></li>
		<li><a href="#tabs-5">自評表3</a></li>
		<li><a href="#tabs-6">自評表4</a></li>
		<li><a href="#tabs-7">自評表5</a></li>
		<li><a href="#tabs-8">預覽</a></li>
	</ul>
	<div id="tabs-1">
		
	</div>
	
	<div id="tabs-2">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
	
	<div id="tabs-3">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
	
	<div id="tabs-4">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
	
	<div id="tabs-5">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
	
	<div id="tabs-6">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
	
	<div id="tabs-7">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
	
	<div id="tabs-8">
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="red">

					步驟說明：請先完成前面的表格，才能繼續。

				</td>
			</tr>
		</table>
	</div>
</div>

<script type="text/javascript">

	var firsttabs = $('#tabs').tabs();

	//根據nextaction決定載入頁面的
	if(nextaction == "qeii_modify"){
		getpage("view/user/modify.jsp","#tabs-1");
		firsttabs.tabs('select', 0);
	}else if(nextaction == "qeii_ini_censor"){
		//隱藏tabs-1
		$('#tabs_1').hide();
		$('#tabs-1').hide();
		
		//將所有頁面載入
		getpage("view/qeii/qeii_form.jsp","#tabs-2");
		getpage("view/qeii/qeii_item_1_form.jsp","#tabs-3");
		getpage("view/qeii/qeii_item_2_form.jsp","#tabs-4");
		getpage("view/qeii/qeii_item_3_form.jsp","#tabs-5");
		getpage("view/qeii/qeii_item_4_form.jsp","#tabs-6");
		getpage("view/qeii/qeii_item_5_form.jsp","#tabs-7");
		nextaction = "qeii_modify";
		getpage("view/qeii/qeii_review.jsp","#tabs-8");
		//頁面停留在#tabs-2
		firsttabs.tabs('select', 1);
	}
	

</script>


  