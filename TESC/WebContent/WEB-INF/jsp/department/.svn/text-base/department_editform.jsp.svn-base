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
				<input id="department_autoIndex" name="department_autoIndex" type="hidden" value="${department.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="hidden" value="<%=selectid%>" maxlength="50" class="input" />
			<tr>
				<td class="inlist"><label id="lbtitle" for="title">系所名</label></td>
				<td class="inlist3"><input id="department_title" name="department_title" type="text" maxlength="50" value="${department.title}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbcode" for="code">校務系統代碼</label></td>
				<td class="inlist3"><input id="department_code" name="department_code" type="text" maxlength="50" value="${department.code}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbaddress" for="address">地址</label></td>
				<td class="inlist3"><input id="department_address" name="department_address" type="text" maxlength="50" value="${department.address}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbphone" for="phone">聯絡電話</label></td>
				<td class="inlist3"><input id="department_phone" name="department_phone" type="text" maxlength="50" value="${department.phone}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbmobile" for="mobile">行動電話</label></td>
				<td class="inlist3"><input id="department_mobile" name="department_mobile" type="text" maxlength="50" value="${department.mobile}" class="input" /></td>
				<td class="status"></td>
			</tr>
					 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbdepartmentformsubmit" for="departmentformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="departmentformsubmit" name="departmentform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#departmentformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitForm(selectidvar);
	});
</script>
	