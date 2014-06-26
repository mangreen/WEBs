//決定modify,qeii_tabs,qeii_review...各page下一步動作的參數
var nextaction = "";
//qeii file upload function
function ajaxFileUpload(){
		//show file upload loading img
		$("#FileUPloading")
		.ajaxStart(function(){
			$(this).show();
		})
		.ajaxComplete(function(){
			$(this).hide();
		});
		//file upload
		$.ajaxFileUpload({
				
				url:'view/qeii/upload.jsp',
				secureuri:false,
				fileElementId:'fileToUpload',
				dataType: 'json',
				success: function (data, status){
					
					
					//if(typeof(data.error) != 'undefined')
					//{
					//	if(data.error != ''){
					//		alert(data.error);
					//	}else{		
					//		alert(data.msg);
					//	}
					//}
				},
				error: function (data, status, e){	
					alert(e);
				}
		})
		return false;
	}

//left regular load page controler
function loadpage(num){
		
	switch(num){
		case 1:
			getuserpage("view/user/login.jsp");
			

		break;
	
		case 2:
			getuserpage("view/user/register.jsp");

		break;
		
		case 3:
			nextaction = "modify";
			getuserpage("view/user/modify.jsp");

		break;
		
		case 4:
			getpage("view/role/role_tableview.jsp","#tabs");

		break;
		
		case 5:
			getpage("view/priv/priv_tableview.jsp","#tabs");

		break;
		
		case 6:
			getpage("view/qeis/qeis_budget_year_tableview.jsp","#tabs");

		break;
		
		case 7:
			getpage("view/flow/flow_module_tableview.jsp","#tabs");

		break;
		
		case 8:
			getpage("view/stage/stage_tableview.jsp","#tabs");

		break;
		
		case 9:
			getpage("view/department/department_tableview.jsp","#tabs");

		break;
		
		case 10:
			getpage("view/growl/growl_tableview.jsp","#tabs");

		break;
		
		case 11:
			getpage("view/config/config_tableview.jsp","#tabs");

		break;
		
		case 12:
			nextaction = "qeii_modify";
			getpage("view/qeii/qeii_tabs.jsp","#centercontent");

		break;
		
		case 13:
			//決定下一步動作的參數
			nextaction = "qeii_ini_censor";
			getnumpage("view/qeii/qeii_ini_censor_list.jsp","3","1");
			
		break;
	}
}

function getnumpage(page,num,code){
	var pagename = page;
	var pgcount = num;
	var pgcode = code;
	$("#tabs").load(pagename,{pagecount:pgcount,pagecode:pgcode},function(){
		$("#tabs").fadeIn('slow');}
	);
	//tabs jump to #tabs-1, when user put button
	firsttabs.tabs('select', 0);
}

function getpage(page,contentlocation){
	var pagename = page;
	var ctlocation = contentlocation;
	//假設帳號為jimmy
	$(ctlocation).load(pagename,{account:'jimmy'},function(){
		$(ctlocation).fadeIn('slow');}
	);
	firsttabs.tabs('select', 0);
}

function getuserpage(page){
	var pagename = page;
	//假設帳號為jimmy
	$("#tabs").load(pagename,{account:'jimmy'},function(){
		$("#tabs").fadeIn('slow');}
	);
	//$.post(pagename,function(data){  
	//	data=data.slice(data.indexOf('<body>')+6,data.indexOf('</body>'));  
	//	$("#tabs").html(data);  
	//});
	firsttabs.tabs('select', 0);
}	

//something will do when index page is ready 	
$(function(){
	$(".containerPlus").buildContainers({
		containment:"parent",
		elementsPath:"elements/"
	});
	$(".containeryellow").buildContainers({
		containment:"parent",
		elementsPath:"elements/"
	});

	$(".containerPluspurple").buildContainers({
		containment:"parent",
		elementsPath:"elements/"
	});
		
	$(".containergreen").buildContainers({
		containment:"parent",
		elementsPath:"elements/"
	});
		

		
		//callUserModel
		//$("#calllogin").click(function(){
		//	$("#tabs-1").load("login.html",function(){
		//		$("#tabs-1").fadeIn('slow');}
		//	);
		//});
	
	//index load topsection page
	$("#topsection").load("topsection.jsp",function(){
		$("#topsection").fadeIn('slow');}
	);
	//index load centercontent	page
	$("#centercontent").load("centercontent.jsp",function(){
		$("#centercontent").fadeIn('slow');}
	);
	//index load leftuser page
	$("#leftuser").load("leftuser.jsp",function(){
		$("#leftuser").fadeIn('slow');}
	);
	//index load lefwait page	
	$("#leftwait").load("leftwait.jsp",function(){
		$("#leftwait").fadeIn('slow');}
	);
	//index load leftregular page
	$("#leftregular").load("leftregular.jsp",function(){
		$("#leftregular").fadeIn('slow');}
	);
	
});