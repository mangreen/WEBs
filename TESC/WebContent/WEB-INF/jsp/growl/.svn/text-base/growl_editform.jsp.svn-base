<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%@page import="edu.ntnu.tesc.module.beans.Growl"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	Growl g = (Growl)request.getAttribute("growl");
	System.out.println(g.getGActionIP());
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="growlwrap">
	<form id="growlform" autocomplete="off" method="get" action="">
		<table style="width: 750px" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

				送出表格前請詳細確認資料.

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
				<input id="growl_autoIndex" name="growl_autoIndex" type="hidden" value="${growl.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="hidden" value="<%=selectid%>" maxlength="50" class="input" />					
			
			<tr>
				<td class="inlist"><label id="lbuserID" for="userID">userID</label></td>
				<td class="inlist3"><input id="growl_userID" name="growl_userID" type="text" maxlength="50" value="${growl.userID}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbtargetUserID" for="targetUserID">targetUserID</label></td>
				<td class="inlist3"><input id="growl_targetUserID" name="growl_targetUserID" type="text" maxlength="50" value="${growl.targetUserID}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
			<tr>
				<td class="inlist"><label id="lbtype" for="type">類型</label></td>
				<td class="inlist3">
					<select id="growl_type" name="growl_type" class="input">
						<option></option>
						<option value="0">低</option>
						<option value="1">中</option>
						<option value="2">高</option>
					</select>				</td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbstate" for="state">狀態</label></td>
				<td class="inlist3">
					<select id="growl_state" name="growl_state" class="input">
						<option></option>
						<option value="0">未通知</option>
						<option value="1">已通知</option>
					</select>				</td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbcDate" for="cDate">cDate</label></td>
				<td class="inlist3"><input id="growl_cDate" name="growl_cDate" type="text" maxlength="50" value="<%=g.getCDate()%>" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbactionIP" for="actionIP">actionIP</label></td>
				<td class="inlist3"><input id="growl_actionIP" name="growl_actionIP" type="text" maxlength="50" value="${growl.actionIP}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbsummary" for="summary">摘要</label></td>
				<td class="inlist3"><input id="growl_summary" name="growl_summary" type="text" maxlength="50" value="${growl.summary}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbgDate" for="gDate">gDate</label></td>
				<td class="inlist3"><input id="growl_gDate" name="growl_gDate" type="text" maxlength="50" value="<%=g.getGDate()%>" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbgActionIP" for="gActionIP">gActionIP</label></td>
				<td class="inlist3"><input id="growl_gActionIP" name="growl_gActionIP" type="text" maxlength="50" value="<%=g.getGActionIP()%>" class="input" /></td>
				<td class="status"></td>
			</tr>
					 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbgrowlformsubmit" for="growlformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="growlformsubmit" name="growlform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#growlformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitForm(selectidvar);
	});
</script>
	