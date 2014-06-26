<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="editqeis_budget_yearwrap">
	<form id="editqeis_budget_yearform" autocomplete="off" method="get" action="">
		<table style="width: 750px" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

				送出表格前請詳細確認資料.

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
				<input id="qeis_budget_year_autoIndex" name="qeis_budget_year_autoIndex" type="hidden" value="${qeis.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="hidden" value="<%=selectid%>" maxlength="50" class="input" />					
			
			<tr>
				<td class="inlist"><label id="lbbudgetYear" for="state">年度</label></td>
				<td class="inlist3"><input id="qeis_budget_year_budgetYear" name="qeis_budget_year_budgetYear" type="text" maxlength="50" value="${qeis.budgetYear}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbcurrentPrice" for="currentPrice">經常門金額</label></td>
				<td class="inlist3"><input id="qeis_budget_year_currentPrice" name="qeis_budget_year_currentPrice" type="text" maxlength="50" value="${qeis.currentPrice}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbcapitalPrice" for="capitalPrice">資本門金額</label></td>
				<td class="inlist3"><input id="qeis_budget_year_capitalPrice" name="qeis_budget_year_capitalPrice" type="text" maxlength="50" value="${qeis.capitalPrice}" class="input" /></td>
				<td class="status"></td>
			</tr>			
					 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbqeis_budget_yearformsubmit" for="qeis_budget_yearformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="qeis_budget_yearformsubmit" name="qeis_budget_yearform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#qeis_budget_yearformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitForm(selectidvar);
	});
</script>
	