<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%
	String selectid =  request.getParameter("selectid");
	//System.out.println(selectid);
	//out.print(selectid);
%>

<div id="qeiiitem3wrap">
	<form id="qeiiitem3form" autocomplete="off" method="get" action="">
		<table style="width: 80%" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left" colspan="2">
					<span class="blue" style="width: 80%; height: 18px;">步驟說明：欲申請實習教師認證須先填寫基本資料及自評表，本張為自評表3，尚有自評表4~5未填寫。按下一步以填寫自評表4或按取消回到認證系統首頁。</span>
				</td>
			</tr>
			<tr>
				<td align="left" colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<table style="width: 100%" cellspacing="0" cellpadding="0" class="table_border">
						<tr>
							<td align="left" class="table_title" colspan="2">
							&nbsp; 項目三：導師實習</td>
						</tr>
						<tr>
							<td colspan="2" class="table_td">
							<span class="blue">項目內涵：<br />
							&nbsp;   &nbsp;  本項目係指實習輔導教師應能營造良好互動的班級氣氛、締造安全且有助於學習的情境，並能與家長互動密切，使學生在校能積極參與學習；同時，能夠妥善處理學生的不當行為，足為實習生學習楷模。</span></td>
						</tr>
						<tr>
							<td class="table_td">&nbsp;
							</td>
							<td>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="table_td" colspan="2">
							<table style="width: 100%" cellspacing="0" cellpadding="0" border="1" bordercolor="#CCCCCC">
								<tr>
									<td style="width: 726px" rowspan="2">&nbsp; 認證指標：</td>
									<td colspan="4">自評</td>
								</tr>
								<tr>
									<td class="gray" style="width: 26px">優</td>
									<td class="gray" style="width: 26px">良</td>
									<td class="gray" style="width: 26px">可</td>
									<td class="gray" style="width: 26px">不足</td>
								</tr>
								<tr>
									
									<td style="width: 726px">※ 3-1 能輔導實習學生建立班級規則和常規</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-1" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-1" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-1" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-1" type="radio" /></td>
									
								</tr>
								<tr>
									<td style="width: 726px">※ 3-2 能輔導實習學生處理學生不當行為與獎懲策略</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-2" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-2" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-2" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-2" type="radio" /></td>
								</tr>
								<tr>
									<td style="width: 726px">※ 3-3 能輔導實習學生營造班級的物理環境(包括教室布置、設備)</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-3" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-3" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-3" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-3" type="radio" /></td>
								</tr>
								<tr>
									<td style="width: 726px">※ 3-4 能輔導實習學生建立師生關係(包括聯絡本和週記批改)</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-4" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-4" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-4" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-4" type="radio" /></td>
								</tr>
								<tr>
									<td style="width: 726px">※ 3-5 能輔導實習學生和家長的溝通與互動(如學校日、書面和面對面溝通等)</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-5" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-5" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-5" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-5" type="radio" /></td>
								</tr>
								<tr>
									<td style="width: 726px">※ 3-6 能輔導實習學生處理班及危機事件(如學生意外傷害)</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-6" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-6" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-6" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-6" type="radio" /></td>
								</tr>
								<tr>
									<td style="width: 726px">※ 3-7 其他(包括較一般性的說明)</td>
									<td style="width: 26px"> 
										<input name="qeiiradio3-7" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-7" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-7" type="radio" /></td>
									<td style="width: 26px">
										<input name="qeiiradio3-7" type="radio" /></td>
								</tr>
								
								<tr>
									<td style="width: 726px">提供相關檢核資料：<br />
									※1. 班級經營績效之證明或紀錄。<br />
									※2. 班級營造之相關運作資料(例如：班級常規語教室布置...等)。<br />
									※3. 親師互動之相關運作資料(例如：班親會、班級聯絡簿、班級網頁的製作...等)<br />
									&nbsp;&nbsp; 4. 輔導學生及班級危機處理之紀錄。(本項資料若涉及學生隱私權的機密文件，不必在申請書中呈現)。<br />
									※5. 曾經擔任五年以上教師之證明。<br />
									&nbsp;&nbsp; 6. 其他相關佐證之資料。
									</td>
									<td colspan="4"> 
										<div id="FileUPlist"></div>
										
										<p>
											<input id="FileUP" name="FileUP" type="file"/>
											<input name="FileUPButton" type="button" value="上傳" onclick="return ajaxFileUpload();"/>
											<img id="FileUPloading" src="images/load.gif" style="display:none;" class="input">
										</p>
										
										
										<p>
										(單個檔案大小請勿超過1mb)<br />
										</p>
									</td>
								</tr>
							</table>
							<span class="blue">有※者係為必要指標；無※者係參考指標<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 18px" colspan="2"></td>
			</tr>
			<tr>
				<td align="right" style="width: 81%"></td>
				<td align="right">
					<input id="nextsubmit" name="Buttonnext" type="button" value="下一步" class="button" onclick="qeii_item_3_action(1);"/> 
					<input id="resetsubmit" name="Buttonreset" type="reset" value="重置" class="button" /> 
					<input id="cancelsubmit" name="Buttoncancel" type="button" value="取消" class="button" onclick="qeii_item_3_action(3);"/>
				</td>
			</tr>
			<tr>
				<td align="right" colspan="2">&nbsp;</td>
			</tr>
		</table>
  </form>
</div>

<script type="text/javascript">
	function qeii_item_3_action(num){
		switch(num){
			case 1:
				getpage("view/qeii/qeii_item_4_form.jsp","#tabs-6");
				firsttabs.tabs('select', 5); 
			break;
			

			
			case 3:
				getuserpage("view/user/login.jsp");
				//tabs jump to #tabs-1, when user put button
				firsttabs.tabs('select', 4); 
			break;
		}
	}
</script>
	