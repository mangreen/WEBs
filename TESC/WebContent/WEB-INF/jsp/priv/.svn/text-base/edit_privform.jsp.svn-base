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
				<input id="priv_autoIndex" name="priv_autoIndex" type="hidden" value="${priv.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="hidden" value="<%=selectid%>" maxlength="50" class="input" />					
		
			<tr>
				<td class="inlist"><label id="lbstate" for="state">狀態</label></td>
				<td class="inlist3">
					<select id="priv_state" name="priv_state" class="input">
						<option></option>
						<option value="1">enable</option>
						<option value="0">unable</option>
					</select>				</td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbtitle" for="title">權限名</label></td>
				<td class="inlist3"><input id="priv_tittle" name="priv_tittle" type="text" maxlength="50" value="${priv.title}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbprivLevel" for="privLevel">權限層級</label></td>
				<td class="inlist3"><input id="priv_privLevel" name="priv_privLevel" type="text" maxlength="50" value="${priv.privLevel}" class="input" /></td>
				<td class="status"></td>
			</tr>			
			<tr>
				<td class="inlist"><label id="lbps" for="ps">備註</label></td>
				<td colspan="3" class="inlist3">
					<textarea id="priv_ps" name="priv_ps" style="width: 95%; height: 179px" class="input">${priv.ps}</textarea>
				</td>
			</tr>							 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbroleformsubmit" for="privformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="privformsubmit" name="roleform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#privformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitPrivForm(selectidvar);
	});
</script>
	