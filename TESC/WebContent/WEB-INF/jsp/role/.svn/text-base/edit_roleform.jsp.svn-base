<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="editrolewrap">
	<form id="editroleform" autocomplete="off" method="get" action="">
	
		<table style="width: 750px" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

				Please check data correctly.

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
				<input id="role_autoIndex" name="role_autoIndex" type="" value="${role.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="" value="<%=selectid%>" maxlength="50" class="input" />					
			
			<tr>
				<td class="inlist"><label id="lbstate" for="state">State</label></td>
				<td class="inlist3">
					<select id="role_state" name="role_state" class="input">
						<option></option>
						<option value="1">enable</option>
						<option value="0">unable</option>
					</select>				</td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbtitle" for="title">Title</label></td>
				<td class="inlist3"><input id="role_title" name="role_title" type="text" maxlength="50" value="${role.title}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbdetailTable" for="detailTable">DetailTable</label></td>
				<td class="inlist3"><input id="role_detailTable" name="role_detailTable" type="text" maxlength="50" value="${role.detailTable}" class="input" /></td>
				<td class="status"></td>
			</tr>			
			<tr>
				<td class="inlist"><label id="lbps" for="ps">PS.</label></td>
				<td colspan="3" class="inlist3">
					<textarea id="role_ps" name="role_ps" style="width: 95%; height: 179px" class="input">${role.ps}</textarea>
				</td>
			</tr>							 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbroleformsubmit" for="roleformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="roleformsubmit" name="roleform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#roleformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitRoleForm(selectidvar);
	});
</script>
	