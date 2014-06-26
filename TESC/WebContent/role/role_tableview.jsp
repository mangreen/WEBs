<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
	<body>

		
		<input id="hiddenvar" type="hidden" name="hidden" value="null" />  
		<table id="gridtable" class="gridtable" ></table>
		<div id="grideditform"></div>
		<div id="gridMessage"></div>
	<script type="text/javascript">  
			function submitRoleForm(selectidvar){
				/*USE AJAX SEND FORM*/ 
				$.ajax({  
					type:"POST",  
					url:"/TESC/rolemanager.do?action=editrole",  
					data:{
						  selectid: selectidvar,
						  autoIndex:$("#autoIndex").val(),  
						  state:$("#state").val(),
						  title:$("#title").val(),
						  detailTable:$("#detailTable").val(),
						  ps:$("#pstext").val()
					},  
					dataType:"json",  
					cache:false,  
					success:function(json){  
						
						if(json.success=='true'){  
							alert("submit success!!!!!");
							$('#gridtable').flexReload();
						}else{  
							alert("submit failed!!!!");
							//$("#reginfo").html("login failed");  
							//$("#reginfo").css("color","red");  
						}  
					}  
				});//form1.submit(); 
			}	
			
			$(document).ready(function() {  
				$("#gridtable").flexigrid ({  
					//遠端伺服的網址
					url: '/TESC/rolemanager.do?action=gettable',  
					//回傳的資料類型
					dataType: 'json',  
					//定義欄位資訊
					colModel: [  
						{display: '主鍵', name : 'autoIndex', width : 60, sortable : true, align: 'center'},  
						{display: '狀態', name : 'state', width : 60, sortable : false, align: 'left'},  
						{display: '角色名', name : 'title', width : 120, sortable : true, align: 'left'},
						{display: '詳細資料表名', name : 'detailTable', width : 120, sortable : true, align: 'left'},
						{display: '備註', name : 'ps', width : 200, sortable : false, align: 'left'}  
					],  
					//定義搜尋欄位資訊
					//searchitems: [  
					//	{display: '主鍵', name : 'autoIndex', isdefault: true},  
					//	{display: '角色名', name : 'title'}  
					//],
					//定義功能欄的按鈕資訊
					buttons : [
						{name: 'Add', bclass: 'add', onpress : updateDb_role},
						{name: 'Edit', bclass: 'edit', onpress : updateDb_role},
						{name: 'Delete', bclass: 'delete', onpress : updateDb_role},
						{separator: true},
							  ],
					//預設排序的欄位 
					sortname: "autoIndex",  
					//預設排序的方式
					sortorder: "asc",  
					selectid: -1,
					action: 'gettable',
					usepager: true,  
					//標題
					title: '角色資料主表',  
					//使用分頁大小選擇器
					useRp: true,  
					//總頁數
					total: 10,  
					rp: 10,  
					//顯示或關閉隱藏欄位的開啟器
					showTableToggleBtn: true,  
					width: 600,  
					height: 300,  
					//資料列雙色交差  
					striped:true,  
					//欄位雙色交差  
					novstripe:true, 
					//分頁器的顯示資訊  
					pagestat:'檢視{from}到{to}，全部共有{total}筆資料',
					//讀取時的訊息
					procmsg: '更新中, 请稍等 ...',
					//連結資料失敗時的訊息  
					errormsg:'連線資料庫失敗',
					//空資料時的訊息  
					nomsg:'找不到符合絛件的資料' 					
				});  
			});
			function RowSelected(row)
			{
				//$('#tablecontent').html('');
			} 
			function updateDb_role(com, grid){
				if (com=='Add'){
					$("#grideditform").load("rolemanager.do?action=loadeditform",{selectid:'-1'},function(){
						$("#grideditform").fadeIn('slow');}
					);
				
				}else if (com=='Edit'){
					if($(".trSelected").length==1){  
                        var selectidvar = $('.trSelected',grid).find("td:first").eq(i).text();
						$("#grideditform").load("rolemanager.do?action=loadeditform",{selectid: selectidvar},function(){
							$("#grideditform").fadeIn('slow');}
						);  
                    }else if($(".trSelected").length>1){  
                        alert("無法同時修改多條紀錄");  
                    }else if($(".trSelected").length==0){  
                        alert("請選擇一條紀錄");  
                    }  
				   
				}else if (com=='Delete'){
					  
                    if($('.trSelected',grid).length==0){  
                        alert("請選擇要刪除的數據");  
                    }else{  
                        if(confirm('確定删除關於主鍵 ' + $('.trSelected',grid).find("td:first").eq(i).text() + ' 的紀錄嗎?')){  
							var selectidvar = $('.trSelected',grid).find("td:first").eq(i).text();
							deleteRoleForm(selectidvar);  
                            alert('Delete Item Action');
						}  
                    }  
				}   
			}
			function deleteRoleForm(selectidvar){
				/*USE AJAX SEND FORM*/ 
				$.ajax({  
					type:"POST",  
					url:"/TESC/rolemanager.do?action=deleterole",  
					data:{selectid: selectidvar 
					},  
					dataType:"json",  
					cache:false,  
					success:function(json){  
						
						if(json.success=='true'){  
							alert("delete success!!!!!");
							$('#gridtable').flexReload(); 
						}else{  
							alert("delete failed!!!!");
  
						}  
					}  
				});//form1.submit(); 
			}
		</script>		
	</body>  
 