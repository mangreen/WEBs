<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="configwrap">
	<form id="configform" autocomplete="off" method="get" action="">
		<table style="width: 750px" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

				送出表格前請詳細確認資料.

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">				
				<input id="config_autoIndex" name="config_autoIndex" type="hidden" value="${config.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="hidden" value="<%=selectid%>" maxlength="50" class="input" />
			<tr>
				<td class="inlist"><label id="lbtitle" for="title">Title</label></td>
				<td class="inlist3"><input id="config_title" name="config_title" type="text" maxlength="50" value="${config.title}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbval" for="val">val</label></td>
				<td class="inlist3"><input id="config_val" name="config_val" type="text" maxlength="50" value="${config.val}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbval2" for="val2">val2</label></td>
				<td class="inlist3"><input id="config_val2" name="config_val2" type="text" maxlength="50" value="${config.val2}" class="input" /></td>
				<td class="status"></td>
			</tr>			
					 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbconfigformsubmit" for="configformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="configformsubmit" name="configform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#configformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitForm(selectidvar);
	});
</script>
	