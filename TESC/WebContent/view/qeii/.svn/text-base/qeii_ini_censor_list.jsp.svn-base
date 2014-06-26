<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table style="width: 45%" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<select id="qeii_type" name="Select6" style="width: 159px" class="input">
						<option selected="selected">依年度篩選</option>
						<option>98</option>
						<option>99</option>
						<option>100</option>
					</select> 
					<input id="sortbyname" name="sortbyname" type="text" class="input" value="依姓名查詢"/>
					<input name="sortbynameBt" type="button" value="查詢" class="button" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table style="width: 100%" cellspacing="0" cellpadding="0" class="table_border">
						<tr>
							<td class="table_title" style="width: 82px">新增日期</td>
							<td class="table_title" style="width: 61px">姓名</td>
							<td class="table_title" style="width: 71px">認證科別</td>
							<td class="table_title" style="width: 105px">目前任教科別</td>
							<td class="table_title" style="width: 81px">審查結果</td>
							<td class="table_title" style="width: 110px">初審</td>
							<td class="table_title" style="width: 110px">複審</td>
							<td class="table_title" style="width: 110px">鎖定</td>
						</tr>
						<tr>
							<td class="table_td" style="width: 82px">98/09/18</td>
							<td class="table_td" style="width: 61px">王一刪</td>
							<td class="table_td" style="width: 71px">導師實習</td>
							<td class="table_td" style="width: 105px">國文</td>
							<td class="table_td" style="width: 81px">待審查</td>
							<td class="table_td" style="width: 110px">
								<input name="Button1" type="button" value="進入" class="button" onclick="get_ini_tabs('1');"/></td>
							<td class="table_td" style="width: 110px">
								<input name="Button1" type="button" value="進入" class="button" /></td>
							<td class="table_td" style="width: 110px">
								<input name="Button1" type="button" value="鎖定" class="button" /></td>
						</tr>
						<tr>
							<td class="table_td" style="width: 82px">98/09/18</td>
							<td class="table_td" style="width: 61px">黃鈺婷</td>
							<td class="table_td" style="width: 71px">導師實習</td>
							<td class="table_td" style="width: 105px">英文</td>
							<td class="table_td" style="width: 81px"><span class="red">不通過</span></td>
							<td class="table_td" style="width: 110px">
									<input name="Button1" type="button" value="進入" class="button" /></td>
							<td class="table_td" style="width: 110px">
								<input name="Button1" type="button" value="進入" class="button" /></td>
							<td class="table_td" style="width: 110px">
									<input name="Button1" type="button" value="鎖定" class="button" /></td>
						</tr>
						
						<c:forEach var="user" items="${account}">
							<tr>
								<td class="table_td" style="width: 82px"><c:out value="${user.aa}"/></td>
								<td class="table_td" style="width: 61px"><c:out value="${user.bb}"/></td>
								<td class="table_td" style="width: 71px"><c:out value="${user.cc}"/></td>
								<td class="table_td" style="width: 105px"><c:out value="${user.dd}"/></td>
								<td class="table_td" style="width: 81px"><c:out value="${user.aa}"/></td>
								<td class="table_td" style="width: 110px">
									<input name="Button1" type="button" value="進入" class="button" onclick="get_ini_tabs(<c:out value="${user.selectid}"/>);"/></td>
								<td class="table_td" style="width: 110px">
									<input name="Button1" type="button" value="進入" class="button" /></td>
								<td class="table_td" style="width: 110px">
									<input name="Button1" type="button" value="鎖定" class="button" /></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td>審查結果：審查中1人；未通過2人；已通過2人；已鎖定2人</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
				<tr>
					<td>
						<select id="datacount" name="Select6" style="width: 159px" class="input">
							<option selected="selected">顯示資料數</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</select> 
						
						<c:forEach var="num" begin="1" end="${param.pagecount}" step="1"  varStatus ="status">
							<input name="pagenum" type="button" value="<c:out value="${num}" />" class="button" onclick="getnumpage('view/qeii/qeii_ini_censor_list.jsp',${param.pagecount},<c:out value="${num}" />);"/>
						</c:forEach>	
					</td>
				</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		

<script type="text/javascript">
	
	function get_ini_tabs(selectid){
		var selectidvar = selectid;
		$("#centercontent").load("view/qeii/qeii_ini_tabs.jsp",{selectid: selectidvar},function(){
			$("#centercontent").fadeIn('slow');}
		);
	}
	
</script>