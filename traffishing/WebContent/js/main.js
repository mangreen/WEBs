var mode = "fish";
$(document).ready(function(){
	$('#searchbtn').click(function() {
		codeAddress();
	});
	
	$("#searchinput").keypress(function(event){
		if(13 == event.which){
			codeAddress();
		}
	});
	
	setRouteMenu();
	clickRoute();
	clickUser();
	
	clickTrafTile();
	clickPoiTile();
	
	setLoginAndRegisterPage();
	
	zmnImgCenter($(".act_img"));//JQ的dom
	
	$("body").on("click", ".act_web", function() {
		$(".act_web").attr("target","_blank");      // 把超連結中的 target 屬性都套用 另開新頁的方式
	});
});

function setLoginAndRegisterPage(){
	$("#login").click(function() {
		$("#lg_dim").show();
		setLoginFormValidate();
	});
	
	$("#lg_x").click(function() {
		$("#lg_dim").hide();
	});
	
	$("#register").click(function() {
		$("#rg_dim").show();
		setRegisterFormValidate();
	});
	
	$("#rg_x").click(function() {
		$("#rg_dim").hide();
	});
	
	$("#lg_registernew").click(function() {
		$("#lg_dim").hide();
		$("#rg_dim").show();
		setRegisterFormValidate();
	});
	
	$("#rg_haveid").click(function() {
		$("#lg_dim").show();
		$("#rg_dim").hide();
		setLoginFormValidate();
	});
}

//設定jquery.validate.js表單驗證規則
function setLoginFormValidate(){
	//須與form表單ID名稱相同
	$("#login_form").validate({
		event: "keyup",
		rules:{
			lg_mail_input: { 
				required: true,
				email: true
			},
			lg_pw_input: {
				required: true,
				minlength: 8
			}
		},
		messages:{
			lg_mail_input: {
				required: "必填欄位",
				email: "請輸入正確的電子信箱帳號"
			},
			lg_pw_input: {
				required: "必填欄位",
				minlength: "長度最少8位元"
			}
		},
		errorPlacement: function (error, element) {					
	        //alert(element.attr('name'));
			var eid = element.attr('name');
			
			if(element.is('#startdate')){
	        	$('#startdate').parent().append(error);
	        }else if(element.is('#starttime')){
	        	$('#starttime').parent().append(error);
	        }else if(element.is('#enddate')){
	        	$('#enddate').parent().append(error);
	        }else if(element.is('#endtime')){
	        	$('#endtime').parent().append(error);
	        }else if(element.is(':radio')){
				$('input[name=' + eid + ']').parent().append(error);
			}else if (element.is(':checkbox')) {
	            $('input[name=' + eid + ']:last').next().after(error);
	        }else if(element.is('#intervalinput')){
	        	$('#intervalinput').parent().append(error);
	        }else if(element.is('#intervalselect')){
	        	$('#intervalselect').parent().append(error);
	        }else if(element.is('#dateinput')){
	        	$('#dateinput').parent().append(error);
	        }else {
	            error.insertAfter(element);
	        }
	    }
	});
}

function setRegisterFormValidate(){
	//須與form表單ID名稱相同
	$("#register_form").validate({
		event: "keyup",
		rules:{
			rg_mail_input: { 
				required: true,
				email:true
			},
			rg_pw_input: {
				required: true,
				min: 8
			},
			rg_pwcf_input: {
				required: true,
				min: 8,
				equalTo: "#rg_pw_input"
			},
			readservice: {
				required: true
			}
		},
		messages:{
			rg_mail_input: "x",
			rg_pw_input: "x",
			rg_pwcf_input: "x",
			readservice: "x"
		},
		errorPlacement: function (error, element) {					
	        //alert(element.attr('name'));
			var eid = element.attr('name');
			
			if(element.is('#startdate')){
	        	$('#startdate').parent().append(error);
	        }else if(element.is('#starttime')){
	        	$('#starttime').parent().append(error);
	        }else if(element.is('#enddate')){
	        	$('#enddate').parent().append(error);
	        }else if(element.is('#endtime')){
	        	$('#endtime').parent().append(error);
	        }else if(element.is(':radio')){
				$('input[name=' + eid + ']').parent().append(error);
			}else if (element.is(':checkbox')) {
	            $('input[name=' + eid + ']:last').next().after(error);
	        }else if(element.is('#intervalinput')){
	        	$('#intervalinput').parent().append(error);
	        }else if(element.is('#intervalselect')){
	        	$('#intervalselect').parent().append(error);
	        }else if(element.is('#dateinput')){
	        	$('#dateinput').parent().append(error);
	        }else {
	            error.insertAfter(element);
	        }
	    }
	});
}

function clickTrafTile(){
	$("#traf_pin").click(function() {		
		if($("#traf_check_float").css("display")=="none"){
			$("#poi_check_float").hide();
			$("#poi_pin").css({
				'background': 'url("img/icon.png") -64px -0px'
			});
			
			$("#traf_check_float").show();
			$(this).css({
				'background': 'url("img/icon.png") -80px -0px'
			});
		}else{
			$("#traf_check_float").hide();
			
			$(this).css({
				'background': 'url("img/icon.png") -48px -0px'
			});
		}
	});
	
	$("#traf_pin").mouseenter(function() {
		if($("#traf_check_float").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -48px -0px'
			});
		}
	});
	
	$("#traf_pin").mouseleave(function() {
		if($("#traf_check_float").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -64px -0px'
			});
		}
	});
	
	$("#traf_event").click(function() {
		if($("#list_tile").css("display")=="none"){
			$("#list_tile").show();
			//$('#traf_list').empty();
			//$('#traf_list').css('width', '350px');
	    	//$('#traf_list').css('height', '');
			$("#traf_list").scrollbar();
			$(this).css({
				'background': 'url("img/icon.png") -128px -0px'
			});
		}else{
			$("#list_tile").hide();
			
			$(this).css({
				'background': 'url("img/icon.png") -96px -0px'
			});
		}
	});
	
	$("#traf_event").mouseenter(function() {
		if($("#list_tile").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -96px -0px'
			});
		}
	});
	
	$("#traf_event").mouseleave(function() {
		if($("#list_tile").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -112px -0px'
			});
		}
	});
}

function clickPoiTile(){
	$("#poi_pin").click(function() {
		if($("#poi_check_float").css("display")=="none"){
			$("#traf_check_float").hide();
			$("#traf_pin").css({
				'background': 'url("img/icon.png") -64px -0px'
			});
			
			$("#poi_check_float").show();
			
			$(this).css({
				'background': 'url("img/icon.png") -80px -0px'
			});
		}else{
			$("#poi_check_float").hide();
			
			$(this).css({
				'background': 'url("img/icon.png") -48px -0px'
			});
		}
	});
	
	$("#poi_pin").mouseenter(function() {
		if($("#poi_check_float").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -48px -0px'
			});
		}
	});
	
	$("#poi_pin").mouseleave(function() {
		if($("#poi_check_float").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -64px -0px'
			});
		}
	});
	
	$("#poi_event").click(function() {

	});
	
	$("#poi_event").mouseenter(function() {
		if($("#poi_list").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -48px -0px'
			});
		}
	});
	
	$("#poi_event").mouseleave(function() {
		if($("#poi_list").css("display")=="none"){	
			$(this).css({
				'background': 'url("img/icon.png") -64px -0px'
			});
		}
	});
}

function setRouteMenu(){
	var routeList = ["English", "简体中文", "日本語", "Deutsch", "Espa?ol", "Fran?ais", "Italiano", "Polski", "Português", "Svenska", "Руccкий"];
	var routeListNum = routeList.length;
	/*
	for (var i = 0; i < routeListNum; i++) {
		alert(routeList[i]);
	}*/
	var liLine = $('.li_line');
	for(var i = 0; i < routeListNum; i++){
		liLine.before('<li id="select_info_'+i+'" class="route_opt">'+
						'<a class="route_btn" href="#">'+
							'<span class="route_name">'+routeList[i]+'</span>'+
							'<span class="countwrap">'+
								'<span class="route_count traf_count">1</span>'+
								'<span class="route_count poi_count">2</span>'+
							'</span>'+
						'</a>'+
						'<a class="button route_tool" href="#"></a>'+
						'<ul class="rttl_menu">'+
							'<li class="liopt rttl_opt">編輯</li>'+
							'<li class="liopt rttl_opt">刪除</li>'+
						'</ul></li>');
	}
	
	//$('#route_menu').scrollbar();
	
	$('.route_btn').mouseenter(function() {
		if($(this).nextAll('.rttl_menu').css("display")=="none"){
			$(this).next('.route_tool').css({
				"background":"url('img/icon.png') -16px 0px"
			});
			$(this).children(".route_name").css("color","white");
		}else{
			/*
			$(this).next('.route_tool').css({
				"background":"url('img/icon.png') -32px 0px"
			});*/
		}
	});
	
	$('.route_btn').mouseleave(function() {
		if($(this).nextAll('.rttl_menu').css("display")=="none"){
			$(this).next('.route_tool').css({
				"background":""
			});
			$(this).children(".route_name").css("color","#33ccff");
		}else{
			/*
			$(this).next('.route_tool').css({
				"background":"url('img/icon.png') -32px 0px"
			});*/
		}
		
	});
	
	$('.route_tool').click(function() {
		$(this).parent().siblings().children(".rttl_menu").hide();
		$(this).parent().siblings().children(".route_tool").css({"background":""});
		$(this).parent().siblings().children(".route_btn").children(".route_name").css("color","#33ccff");
		if($(this).next().css("display")=="none"){
			$(this).css({
				"background":"url('img/icon.png') -32px 0px"
			});
			$(this).next().show();
			$(this).prev().children(".route_name").css("color","white");
		}else{
			$(this).css({
				"background":"url('img/icon.png') 0px 0px"
			});
			$(this).next().hide();
			$(this).prev().children(".route_name").css("color","#33ccff");
		}
	});
	
	$('.route_tool').mouseenter(function() {
		if($(this).next().css("display")=="none"){
			$(this).css({
				"background":"url('img/icon.png') 0px 0px"
			});
		}else{
			
		}
		
	});
	
	$('.route_tool').mouseleave(function() {
		if($(this).next().css("display")=="none"){
			$(this).css({
				"background":""
			});
		}else{
			
		}
		
	});
}

function clickRoute(){
	/*
	$('#route_now').click(function() {
		var bold_route = $('#route_now').css("font-weight");
		//alert(bold_user);
		if(bold_route == 700 || bold_route == "bold"){

			$('#route_now').css("font-weight","normal");
			//$('#route_menu').hide();
		}else{
			$('#route_now').css("color","white");
			$('#route_now').css("font-weight","bold");
			//$('#route_menu').show();
		}
	});
	
	$('#route_now').mouseenter(function() {
		$('#route_now').css("color","white");
		//$('#user_menu').show();

	});
	
	$('#route_now').mouseleave(function() {
		var bold_route = $('#route_now').css("font-weight");
		if(bold_route == 700 || bold_route == "bold"){
			$('#route_now').css("color","white");
		}else if(bold_route == 400 || bold_route == "normal"){
			$('#route_now').css("color","#33ccff");
		}	
		
		//$('#user_menu').css("display","none");
	});
	*/
	$('#route_arrow').click(function() {
		if($('#route_menu').css("display") == "none"){
			$('#route_menu').show();
			$(this).css("border-top-color","white");
		}else{
			$('#route_menu').hide();
			$(this).css("border-top-color","#33ccff");
		}
	});
	
	$('#route_arrow').mouseenter(function() {
		//$('#user_menu').show();
		if($('#route_menu').css("display") == "none"){
			$(this).css("border-top-color","#33ccff");
		}else{
			$(this).css("border-top-color","white");
		}
	});
	
	$('#route_arrow').mouseleave(function() {
		if($('#route_menu').css("display") == "none"){
			$(this).css("border-top-color","gray");
		}else{
			$(this).css("border-top-color","white");
		}
	});
}

function clickUser(){
	$('#username').click(function() {
		var bold_user = $('#username').css("font-weight");
		//alert(bold_user);
		if(bold_user == 700 || bold_user == "bold"){

			$('#username').css("font-weight","normal");
			$('#user_menu').hide();
		}else{
			$('#username').css("color","white");
			$('#username').css("font-weight","bold");
			$('#user_menu').show();
		}
	});
	
	$('#username').mouseenter(function() {
		$('#username').css("color","white");
		//$('#user_menu').show();

	});
	
	$('#username').mouseleave(function() {
		var bold_user = $('#username').css("font-weight");
		if(bold_user == 700 || bold_user == "bold"){
			$('#username').css("color","white");
		}else if(bold_user == 400 || bold_user == "normal"){
			$('#username').css("color","#33ccff");
		}	
		
		//$('#user_menu').css("display","none");
	});
}

//Array Remove - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
	var rest = this.slice((to || from) + 1 || this.length);
	this.length = from < 0 ? this.length + from : from;
	return this.push.apply(this, rest);
};

//圖片居中
function zmnImgCenter(obj){
	obj.each(function(){
		var $this=$(this);
		var objHeight=$this.height();//圖片高度
		var objWidth=$this.width();//圖片宽度
		var parentHeight=$this.parent().parent().height();//圖片父容器高度
		var parentWidth=$this.parent().parent().width();//圖片父容器宽度
		var ratio=objHeight/objWidth;
		
		if(objHeight>parentHeight && objWidth>parentWidth){//當圖片寬高都大於父容器寬高
			if(objHeight>objWidth) {//賦值寬高
				$this.height(parentHeight);
				$this.width(parentHeight/ratio);
			}else {
				$this.width(parentWidth);
				$this.height(parentWidth*ratio);
			}
			
			objHeight=$this.height();//重新取得寬高
			objWidth=$this.width();
			
			if(objHeight>objWidth) {
				$(this).css("left",(parentWidth-objWidth)/2);
				//定義top属性
			}else{
				//定義left属性
				$(this).css("top",(parentHeight-objHeight)/2);
			}
		}else{//當圖片寬高小於父容器寬高
			if(objWidth>parentWidth){//當圖片寬大於容器寬，小于时利用css text-align属性居中
				$(this).css("left",(parentWidth-objWidth)/2);
			}
		
			$(this).css("top",(parentHeight-objHeight)/2);
		}
	});
}
