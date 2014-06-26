<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="editflow_modulewrap">
	<form id="editflow_moduleform" autocomplete="off" method="get" action="">
		<table style="width: 750px" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

				送出表格前請詳細確認資料.

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
				<input id="flow_autoIndex" name="flow_autoIndex" type="" value="${flow.autoindex}" maxlength="50" class="input" />
				<input id="selectId" name="selectId" type="" value="<%=selectid%>" maxlength="50" class="input" />					
			
			<tr>
				<td class="inlist"><label id="lbtitle" for="title">流程名</label></td>
				<td class="inlist3"><input id="flow_title" name="flow_title" type="text" maxlength="50" value="${flow.title}" class="input" /></td>
				<td class="status"></td>
			</tr>
			<tr>
				<td class="inlist"><label id="lbFlow_xml" for="Flow_xml">Flow的規畫文件，格式為XML</label></td>
				<td colspan="3" class="inlist3">
					<textarea id="Flow_xml" name="Flow_xml" style="width: 95%; height: 179px" class="input">${flow.flowXML}</textarea>
				</td>
			</tr>			
					 
			
			</table></td>
				</tr>
				<tr>
					<td style="height: 18px" colspan="2"></td>
				</tr>

			<tr>
				<td class="label"><label id="lbflow_moduleformsubmit" for="flow_moduleformsubmit"></label></td>
				<td class="field" colspan="2">
					<input id="flow_moduleformsubmit" name="flow_moduleform" type="button" value="Submit" class="button" />				</td>
		    </tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	$("#flow_moduleformsubmit").click(function(){
		var selectidvar = $("#selectId").val();
		submitForm(selectidvar);
	});
</script>
	