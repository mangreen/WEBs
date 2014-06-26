<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="editstageewrap">
	<form id="editstageform" autocomplete="off" method="get" action="">
		<table style="width: 750px" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

				送出表格前請詳細確認資料.

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
				<input id="autoIndex" name="autoIndex" type="hidden" value="${stage.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="hidden" value="<%=selectid%>" maxlength="50" class="input" />					
			
			<tr>
				<td class="inlist"><label id="lbtitle" for="title">流程名</label></td>
				<td class="inlist3"><input id="title" name="title" type="text" maxlength="50" value="${stage.title}" class="input" /></td>
				<td class="status"></td>
			</tr>			 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbstagesubmit" for="stageformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="stageformsubmit" name="stageform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#stageformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitForm(selectidvar);
	});
</script>
	