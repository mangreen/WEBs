<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>
	<body>

		<div id="gridtable">
			<input id="hiddenvar" type="hidden" name="hidden" value="null" />  
			<table id="flexDb_priv" class="flexDb_priv" ></table>
		</div>
		<div id="grideditform"></div>
		<div id="gridMessage"></div>
	<script type="text/javascript">  
			//將更新後的表格送給後端伺服器
			function submitForm(selectidvar){
				
				/*USE AJAX SEND FORM*/ 
				$.ajax({  
					type:"POST",  
					url:"/TESC/rolemanager.do?action=editflow",  
					data:{selectid: selectidvar,
						  autoIndex: $("#flow_autoIndex").val(),  
						  title: $("#flow_title").val(),
						  Flow_xml: $("#Flow_xml").val()
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
				
				//設定flexgrid
				$("#flexDb_priv").flexigrid ({  
					//遠端伺服的網址
					url: '/TESC/rolemanager.do?action=getflowtable',  
					//回傳的資料類型
					dataType: 'json',  
					//定義欄位資訊
					colModel: [  
						{display: '主鍵', name : 'autoIndex', width : 60, sortable : true, align: 'center'},  
						{display: '流程名', name : 'title', width : 120, sortable : false, align: 'left'},  
						{display: '規畫文件', name : 'currentPrice', width : 185, sortable : true, align: 'left'},
					],  
					
					//定義功能欄的按鈕資訊
					buttons : [
						{name: 'Add', bclass: 'add', onpress : updateGrid},
						{name: 'Edit', bclass: 'edit', onpress : updateGrid},
						{name: 'Delete', bclass: 'delete', onpress : updateGrid},
						{separator: true},
							  ],
					//預設排序的欄位 
					sortname: "autoIndex",  
					//預設排序的方式
					sortorder: "asc",  
					selectid: -1,
					usepager: true,  
					//標題
					title: '流程主資料表',  
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
					nomsg:'找不到符合絛件的資料', 					
				});  
			});
			function RowSelected(row)
			{
				//$('#tablecontent').html('');
			} 
			//判斷按下甚麼按鈕 
			function updateGrid(com, grid){
				if (com=='Add'){
					$("#grideditform").load("/TESC/rolemanager.do?action=loadflowform",{selectid:'-1'},function(){
						$("#grideditform").fadeIn('slow');}
					);
				
				}else if (com=='Edit'){
					if($(".trSelected").length==1){  
                        var selectidvar = $('.trSelected',grid).find("td:first").eq(i).text();
						$("#grideditform").load("/TESC/rolemanager.do?action=loadflowform",{selectid: selectidvar},function(){
							$("#grideditform").fadeIn('slow');}
						);  
                    }else if($(".trSelected").length>1){  
                        alert("無法同時修改多條紀錄");  
                    }else if($(".trSelected").length==0){  
                        alert("請選擇一條紀錄")  
                    }  
				   
				}else if (com=='Delete'){
					  
                    if($('.trSelected',grid).length==0){  
                        alert("請選擇要刪除的數據");  
                    }else{  
                        if(confirm('確定删除關於主鍵 ' + $('.trSelected',grid).find("td:first").eq(i).text() + ' 的紀錄嗎?')){  
							var selectidvar = $('.trSelected',grid).find("td:first").eq(i).text();
							deleteGrid(selectidvar);  
                            alert('Delete Item Action');
						}  
                    }  
				}   
			}
			//將要刪除的資料送給後端
			function deleteGrid(selectidvar){
				/*USE AJAX SEND FORM*/ 
				$.ajax({  
					type:"POST",  
					url:"/TESC/rolemanager.do?action=deleteflow",  
					data:{selectid: selectidvar, 
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
 