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
		<table style="width: 72%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2" class="blue">

					步驟說明：欲申請實習教師認證須先填寫基本資料及自評表，本張為基本資料頁面，尚有自評表1~5未填寫。點選新增建立個人基本資料後會導入自評表填寫頁面，取消則離開。

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
					<tr>
						<td class="inlist">認證認別</td>
						<td class="inlist3">
						<select id="qeii_type" name="Select6" style="width: 159px">
							<option selected="selected">行政實習與研習活動</option>
							<option>教師實習</option>
							<option>導師實習</option>
						</select> 
						</td>
						<td class="inlist3" align="right">教師證字號</td>
						<td class="inlist3">
						<input id="qeii_" name="Text34" type="text" class="input" /></td>
					</tr>
					<tr>
						<td class="inlist">實習輔導教師姓名</td>
						<td class="inlist3">
							<p>
								<input name="Text30" type="text" class="input" />
							</p>
							<p class="red">錯誤訊息*輸入不正確</p></td>
						<td class="inlist3" align="right">服務學校全銜</td>
						<td class="inlist3">
						<input id="qeii_schoolTitle" name="Text34" type="text" class="input" /></td>
					</tr>
					<tr>
						<td class="inlist">學校地址</td>
						<td class="inlist3">
						<input id="qeii_schoolAddr" name="Text29" type="text" class="input" /></td>
						<td class="inlist3" align="right">學校類別</td>
						<td class="inlist3">
										<select name="Select7" style="width: 159px">
											<option></option>
											<option>完全中學</option>
											<option>國中</option>
											<option>高中</option>
											<option>高職</option>
										</select></td>
					</tr>
					<tr>
						<td class="inlist">任教年資</td>
						<td class="inlist3">
						<input name="Text31" type="text" class="input" /></td>
						<td class="inlist3" align="right">目前任教科別</td>
						<td class="inlist3">
						<input name="Text35" type="text" class="input" /></td>
					</tr>
					<tr>
						<td class="inlist">教師證登記類科別&nbsp;</td>
						<td class="inlist3">
						<input name="Text32" type="text" class="input" /></td>
						<td class="inlist" align="right">AutoIndex</td>
						<td class="inlist">
						<input id="qeii_autoIndex" name="qeii_autoIndex" type="text" style="width: 226px" class="input" value="${qeii.autoIndex}"/></td>
					</tr>
					<tr>
						<td class="inlist">電話</td>
						<td class="inlist3">
						<input name="Text33" type="text" class="input" /></td>
						<td class="inlist3" align="right">&nbsp;email </td>
						<td class="inlist3">
						<input name="Text37" type="text" style="width: 226px" class="input" /></td>
					</tr>
					<tr>
						<td class="inlist">擔任實習輔導教師資歷 </td>
						<td colspan="3" class="inlist3">
						<input name="Text38" type="text" style="width: 485px; height: 179px" class="input" value=""/></td>
					</tr>
					<tr>
						<td class="inlist">個人教學網頁、部落格 </td>
						<td colspan="3" class="inlist3">
						<input name="Text38" type="text" style="width: 485px; height: 179px" class="input" value=""/></td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="width: 80%" >&nbsp;</td>
				<td style="width:250px">&nbsp;</td>
			</tr>
			<tr>
				<td style="width: 80%">&nbsp;</td>
				<td style="width:250px">
					<input name="Button1" type="button" value="新增" class="button" onclick="qeii_form_action(1);"/>  
					<input name="Button2" type="reset" value="重置" class="button" />  
					<input name="Button3" type="button" value="取消" class="button" onclick="qeii_form_action(3);"/></td>
			</tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	function qeii_form_action(num){
		switch(num){
			case 1:
				nexttabs();
			break;
			
			
			case 3:
				getuserpage("view/user/login.jsp");
				//tabs jump to #tabs-1, when user put button
				firsttabs.tabs('select', 1); 
			break;
		}
	}
	
	function nexttabs(){
		$.ajax({ 				
		    type:"POST",  
		    url:"view/qeii/sendform.jsp",  
		    data:{qeii_autoIndex:$("#qeii_autoIndex").val(),
				  qeii_type:$("#qeii_type").val(),  
		          qeii_schoolTitle:$("#qeii_schoolTitle").val(),
				  qeii_schoolAddr:$("#qeii_schoolAddr").val()    
		         },  
		    dataType:"json",  
		    cache:false,  
		    success:function(json){  
		                     
				if(json.success=='true'){  
					alert("seccessed!");
					//表格成功送出後,設定autoIndex
					$("#qeii_autoIndex").val(json.autoIndex);
					//載入下一頁
					getpage("view/qeii/qeii_item_1_form.jsp","#tabs-3");
					//跳頁
					firsttabs.tabs('select', 2); 
 
		        }else{  
		            alert("failed!");
 
		        }  
		    }  
		});//form1.submit(); 
	}
	
</script>
	