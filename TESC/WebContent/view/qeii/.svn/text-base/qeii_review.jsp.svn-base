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
		<table style="width: 64%" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="2">

			

				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%; height: 18px;" ><span class="blue" style="width: 80%; height: 18px;">步驟說明：欲申請實習教師認證須先填寫基本資料及自評表，您已完成申請步驟，按下鎖定後提交即送出資料並不可再修改以。(資料將由評審作業審查，您可再申請進度查詢目前審查狀況)</span></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%; height: 18px;" ></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%; height: 18px;" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_3border">
					<tr>
						<td class="inlist">認證認別</td>
						<td class="inlist3" style="width: 249px">教育實習</td>
						<td class="inlist3" align="right" style="width: 192px">教師證字號</td>
						<td class="inlist3">123456789</td>
					</tr>
					<tr>
						<td class="inlist">實習輔導教師姓名</td>
						<td class="inlist3" style="width: 249px">祝英台</td>
						<td class="inlist3" align="right" style="width: 192px">服務學校全銜</td>
						<td class="inlist3">臺北市立復興高中</td>
					</tr>
					<tr>
						<td class="inlist">學校地址</td>
						<td class="inlist3" style="width: 249px">台北市北投區復興四路70號</td>
						<td class="inlist3" align="right" style="width: 192px">學校類別</td>
						<td class="inlist3">高中</td>
					</tr>
					<tr>
						<td class="inlist">任教年資</td>
						<td class="inlist3" style="width: 249px">5年</td>
						<td class="inlist3" align="right" style="width: 192px">目前任教科別</td>
						<td class="inlist3">英文</td>
					</tr>
					<tr>
						<td class="inlist">教師證登記類科別&nbsp;</td>
						<td class="inlist3" style="width: 249px">英文</td>
						<td class="inlist" align="right" style="width: 192px">AutoIndex</td>
						<td class="inlist"></td>
					</tr>
					<tr>
						<td class="inlist">電話</td>
						<td class="inlist3" style="width: 249px">0919888666</td>
						<td class="inlist3" align="right" style="width: 192px">&nbsp;email </td>
						<td class="inlist3">chinese_girl@gmail.com</td>
					</tr>
					<tr>
						<td class="inlist">擔任實習輔導教師資歷 </td>
						<td colspan="3" class="inlist3">3年</td>
					</tr>
					<tr>
						<td class="inlist">個人教學網頁、部落格 </td>
						<td colspan="3" class="inlist3">www.wretch.cc/blog/chinesegirl</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="width: 60%; height: 18px;" ></td>
				<td style="width:250px; height: 18px;"></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 80%; height: 18px;" ><table style="width: 100%" cellspacing="0" cellpadding="0" class="table_border">
					<tr>
						<td class="table_title" style="width: 79px">已上傳檔案</td>
						<td class="table_title">自評表1</td>
						<td class="table_title">自評表2</td>
						<td class="table_title">自評表3</td>
						<td class="table_title">自評表4</td>
						<td class="table_title">自評表5</td>
					</tr>
					<tr>
						<td style="width: 79px" class="table_td">附件</td>
						<td class="table_td">xxx.doc<br />
							<a href="#">uuu.pdf</a><br />
							<a href="#">ooo.doc</a></td>
						<td class="table_td"><a href="#">xxx.doc</a><br />
							uuu.pdf<br />
							ooo.doc</td>
						<td class="table_td"><a href="#">xxx.doc</a><br />
							uuu.pdf<br />
							ooo.doc</td>
						<td class="table_td">xxx.doc<br />
							uuu.pdf<br />
							ooo.doc</td>
						<td class="table_td">xxx.doc<br />
							uuu.pdf<br />
							ooo.doc<br />
							ggg.doc</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="width: 60%; height: 18px;" ></td>
				<td style="width:250px; height: 18px;"></td>
			</tr>
			<tr>
				<td style="width: 60%; height: 18px;" >&nbsp;</td>
				<td style="width:250px; height: 18px;">  
					<input id="qeiisubmit" name="Button1" type="button" value="鎖定後提交" class="button" onclick="qeii_review_action(1)"/>
					<input id="qeiiedit" name="Button1" type="button" value="編修" class="button" onclick="qeii_review_action(2)"/>
					<input id="qeiireset" name="Button1" type="button" value="取消" class="button" onclick="qeii_review_action(3)"/>
				</td>
			</tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	
	if(nextaction=="qeii_ini_censor"){
		$("#qeiisubmit").hide();
		$("#qeiiedit").show();
		$("#qeiireset").show();
	}else if(nextaction=="qeii_modify"){
		$("#qeiisubmit").show();
		$("#qeiiedit").hide();
		$("#qeiireset").hide();
	}
	
	function qeii_review_action(num){
		switch(num){
			case 1:

			break;
			
			case 2:
				//跳至qeii編修頁面
				nextaction = "qeii_ini_censor";
				getpage("view/qeii/qeii_tabs.jsp","#centercontent");
			break;
			
			case 3:

			break;
		}
	}
</script>
	