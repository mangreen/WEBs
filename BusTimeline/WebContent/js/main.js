var screentype="";

var JsonData;
var DateStaticUpdated = 0;
var DateDynUpdated = 0;


var MOVEABLE = 500;
var STAND_PLUS_MIDWAY = 96;
var HEAD_AND_TAIL = 128;
var ORIGINAL_LEFT = 220;

var counter = 0;

var groupNum = 0;
var nowGroup = 0;

var HAND_BTN_W = 286;

$(document).ready(function(){
	//alert($.client.os+" & "+$.client.browser);
	if("screen" == screentype){
		$.backstretch("img/loading.jpg", {speed: 'slow'} );
	}
	showLoading();
	/*
	$("#load_span").click(function() {
		$("#backstretch").remove();
		$.backstretch("img/Warning.jpg", {speed: 'slow'} );
	});*/
	
	init();
	adjustCss();
	getJson();
	/*
	$.getJSON("http://140.92.88.103:8080/VMservletDb/GetData.jsp?sqlQueryTime=1331890983610&jsoncallback=?", function(data){
		alert(data);
	});*/
	
	timer();
	$(window).resize(adjustBodySize);
});

function getJson(){

	$.ajax({
        type:'get',
        dataType:'json',
        //url:'http://140.92.88.103:8080/VMservletDb/GetData.jsp',
        //jsonp: 'jsoncallback',
        /*data:{
        	"sqlQueryTime": 1331890983610
    	},*/
        cache: false,
        url:'json.jsp',
        success: function(data){
	            //setContent(data);
    			if(null != data){
    				$("#backstretch").remove();
    				$("#loading").hide();
    				$("#warning").hide();
    				$("#main").show();
    				
    				//JsonData = $.parseJSON(data);
    				JsonData = data;
    				//JsonData = $.parseJSON('{"DateStaticUpdated":1331890983609,"DateDynUpdated":1331891816765,"BusStaticInfo":[{"Rid":"11145", "RouteName":"560副","StopNameList":["副一站","副二站","副三站","副四站","副五站","副六站","副七站"],"CurrentStopIdx":5},{"Rid":"11326","RouteName":"262","StopNameList":["第一站","第二站","第三站","第四站","第五站","第六站","第七站","第八站","第九站"],"CurrentStopIdx":0}],"BusDynInfo":[{"Rid":"11145","StatusCode":3,"TravelTimeList":[-1,-1,-1,-1,-1,-1,-1],"CurrentStopATime":-1,"BusLocationList":[-1,-1,-1,-1,-1,-1,-1]},{"Rid":"11326","StatusCode":1,"TravelTimeList":[-1,-1,-1,-1,-1,-1,25,38,110],"CurrentStopATime":13,"BusLocationList":[0,0,1,0,2,0,1,0,-1]}]}');
    				//alert("bb "+data);
    				
    				if(0 == DateStaticUpdated || JsonData.DateStaticUpdated > DateStaticUpdated){
    					DateStaticUpdated = JsonData.DateStaticUpdated;
    					createNamebtn(JsonData.BusStaticInfo);
        				createRoutediv(JsonData.BusStaticInfo);
        				if("screen" == screentype){
        					dragNameList();
        					moveNameList();
        				}
        				dragStandline();
        				moveStandList();
        				
        				hideRouteDiv();
        				backToList();
        				
        				backToOriginal(JsonData.BusStaticInfo);
        				adjustBodySize();
    				}
    				
    				if(0 == DateDynUpdated || JsonData.DateDynUpdated > DateDynUpdated){
    					DateDynUpdated = JsonData.DateDynUpdated;
    					setDynInfo(JsonData.BusDynInfo, JsonData.BusStaticInfo);
    				}

    			}else{
    				//alert(data);
    				//alert("Delete Fail ?!?!?!");
    				showWarning();
    			}
        	},
        error:function(xhr, ajaxOptions, thrownError){
        		//alert(xhr.status+" "+thrownError);
	            showWarning();
        	}
    });
}

function showLoading(){
	
	var topnum = $(window).height()/4;
	var leftnum = ($(window).width()-120)/2;
	
	var opts = {
		lines: 15, // The number of lines to draw
		length: 30, // The length of each line
		width: 10, // The line thickness
		radius: 25, // The radius of the inner circle
		rotate: 0, // The rotation offset
		color: '#fff', // #rgb or #rrggbb
		speed: 1, // Rounds per second
		trail: 60, // Afterglow percentage
		shadow: false, // Whether to render a shadow
		hwaccel: false, // Whether to use hardware acceleration
		className: 'spinner', // The CSS class to assign to the spinner
		zIndex: 2e9, // The z-index (defaults to 2000000000)
		top: topnum, // Top position relative to parent in px
		left: leftnum // Left position relative to parent in px
	};
	
	$.fn.spin = function(opts) {
		this.each(function() {
			var $this = $(this),
			spinner = $this.data('spinner');
	
			if (spinner) spinner.stop();
			if (opts !== false) {
				opts = $.extend({color: $this.css('color')}, opts);
				spinner = new Spinner(opts).spin(this);
				$this.data('spinner', spinner);
			}
		});
		return this;
	};
	
	$("#loading").show().spin(opts);
	
}

function showWarning(){
	$("#backstretch").remove();
	$.backstretch("img/Warning.jpg", {speed: 'slow'} );
	$("#loading").hide();
	$("#main").hide();
	$("#warning").show();
}

function createRoutediv(BusStaticInfo){
	$("#routelist").html("");
	
	$.each(BusStaticInfo, function(i){
		var length=BusStaticInfo[i].StopNameList.length;
		var stopidx = BusStaticInfo[i].CurrentStopIdx;
		
		var groupid = 0;
		
		if("screen" == screentype){
			var remainder = i%3;
			groupid = i/3;
			
			if(0 == remainder){
				groupid = i/3;
			}else{
				groupid = Math.ceil(i/3)-1;
			}
			
			$("#routelist").append('<div id="routegroup_'+groupid+'" class="routegroup"></div>');
			
			$("#routegroup_"+groupid).append('<div id="route_'+i+'" class="routediv">'+
					'<div id="content_'+i+'" class="route_content">'+
						'<div class="route_bar">'+
							'<div id="rl_'+i+'" class="r_light"></div>'+
							'<span class="route_span">'+BusStaticInfo[i].RouteName+'</span>'+
						'</div>'+
						
						'<div class="est_time_bar">預估到站時間：'+
							'<span id="e_'+i+'" class="e_time"></span></div>'+
						
						'<div id="line_'+i+'" class="stand_line">'+
						'</div>'+
					'</div>'+
					'<div id="routemove_'+i+'" class="routemove">'+
						'<div id="routeleft_'+i+'" class="arrowleft btn"></div>'+
						'<div id="routeright_'+i+'" class="arrowright btn"></div>'+
					'</div>'+
				'</div>');
			
		}else if("hand" == screentype){
			groupid = i;
			$("#routelist").append('<div id="routegroup_'+groupid+'" class="routegroup"></div>');
			
			$("#routegroup_"+groupid).append('<div id="route_'+i+'" class="routediv">'+
					'<div id="content_'+i+'" class="route_content">'+
						'<div class="route_bar">'+
							'<div id="rl_'+i+'" class="n_light"></div>'+
							'<span class="route_span">'+BusStaticInfo[i].RouteName+'</span>'+
						'</div>'+
						
						'<div class="est_time_bar">預估到站時間：'+
							'<span id="e_'+i+'" class="e_time"></span></div>'+
						
						'<div id="routemove_'+i+'" class="routemove">'+
							'<div id="routeleft_'+i+'" class="arrowleft btn"></div>'+
							'<div id="routeright_'+i+'" class="arrowright btn"></div>'+
						'</div>'+
							
						'<div id="line_'+i+'" class="stand_line">'+
						'</div>'+
					'</div>'+
				'</div>');
		}
		
		groupNum = groupid;	

		//根據站數設定stand_line的width
		var linewidth = HEAD_AND_TAIL + STAND_PLUS_MIDWAY * BusStaticInfo[i].StopNameList.length;
		$("#line_"+i).css("width", linewidth+"px");
		
		$("#line_"+i).append('<div class="midway mpast"></div>');
		
		$.each(BusStaticInfo[i].StopNameList, function(j){
			if((length-1)!=j && stopidx > j){
				$("#line_"+i).append('<div id="s_'+i+'_'+j+'" class="stand">'+
						'<div id="c_'+i+'_'+j+'" class="circle cpast"></div>'+
						'<span class="stand_span">'+BusStaticInfo[i].StopNameList[j]+'</span>'+
					'</div>'+
					//'<div class="midway mpast"></div>'+
					'<div id="m_'+i+'_'+j+'" class="midway mpast"></div>');
			}else if((length-1)!=j && stopidx == j){
				$("#line_"+i).append('<div id="s_'+i+'_'+j+'" class="stand">'+
						'<div id="c_'+i+'_'+j+'" class="circle cnow"></div>'+
						'<span class="stand_span">'+BusStaticInfo[i].StopNameList[j]+'</span>'+
					'</div>'+
					//'<div class="midway mnext"></div>'+
					'<div id="m_'+i+'_'+j+'" class="midway mnext"></div>');
			}else if((length-1)!=j && stopidx < j){
				$("#line_"+i).append('<div id="s_'+i+'_'+j+'" class="stand">'+
						'<div id="c_'+i+'_'+j+'" class="circle cnext"></div>'+
						'<span class="stand_span">'+BusStaticInfo[i].StopNameList[j]+'</span>'+
					'</div>'+
					//'<div class="midway mnext"></div>'+
					'<div id="m_'+i+'_'+j+'" class="midway mnext"></div>');
			}else if((length-1) ==j && stopidx == j){
				$("#line_"+i).append('<div id="s_'+i+'_'+j+'" class="stand">'+
						'<div id="c_'+i+'_'+j+'" class="circle cnow"></div>'+
						'<span class="stand_span">'+BusStaticInfo[i].StopNameList[j]+'</span>'+
					'</div>');
			}else{
				$("#line_"+i).append('<div id="s_'+i+'_'+j+'" class="stand">'+
						'<div id="c_'+i+'_'+j+'" class="circle cnext"></div>'+
						'<span class="stand_span">'+BusStaticInfo[i].StopNameList[j]+'</span>'+
					'</div>');
			}
			
		});
		
		$("#line_"+i).append('<div class="midway mnext"></div>');
	});
	
	if("screen" == screentype){
		$("#routegroup_0").show();
	}else if("hand" == screentype){
		$("#routelist").hide();
	}	
}

function createNamebtn(BusStaticInfo){
	$("#namedrag").html("");
	$.each(BusStaticInfo, function(i){
		
		if("screen" == screentype){
			var remainder = i%3;
			var groupid = i/3;
			
			if(0 == remainder){
				groupid = i/3;
				$("#namedrag").append('<div id="namegroup_'+groupid+'" class="namegroup"></div>');
			}else{
				groupid = Math.ceil(i/3)-1;
			}
			
			if(i<3){
				$("#namegroup_"+groupid).append('<div id="namebtn_'+i+'" class="namebtn btn b_press">'+
						'<div id="nl_'+i+'" class="n_light"></div>'+
						'<span class="n_span">'+BusStaticInfo[i].RouteName+'</span></div>');
			}else{
				$("#namegroup_"+groupid).append('<div id="namebtn_'+i+'" class="namebtn btn">'+
						'<div id="nl_'+i+'" class="n_light"></div>'+
						'<span class="n_span">'+BusStaticInfo[i].RouteName+'</span></div>');
			}
		}else if("hand" == screentype){
			/*
			if(0 == i){
				$("#namedrag").append('<div id="namegroup_'+i+'" class="namegroup"></div>');
				$("#namegroup_"+i).append('<div id="namebtn_'+i+'" class="namebtn btn b_press">'+
						'<div id="nl_'+i+'" class="n_light"></div>'+
						'<span class="n_span">'+BusStaticInfo[i].RouteName+'</span></div>');
			}else if(0 < i){
				$("#namedrag").append('<div id="namegroup_'+i+'" class="namegroup"></div>');
				$("#namegroup_"+i).append('<div id="namebtn_'+i+'" class="namebtn btn">'+
						'<div id="nl_'+i+'" class="n_light"></div>'+
						'<span class="n_span">'+BusStaticInfo[i].RouteName+'</span></div>');
			}*/
			$("#namedrag").append('<div id="namegroup_'+i+'" class="namegroup"></div>');
			$("#namegroup_"+i).append('<div id="namebtn_'+i+'" class="namebtn btn">'+
					'<div id="nl_'+i+'" class="n_light"></div>'+
					'<span class="n_span">'+BusStaticInfo[i].RouteName+'</span></div>');
		}
		
	});

}

//TODO
function setDynInfo(BusDynInfo, BusStaticInfo){
	$.each(BusDynInfo, function(i){
		
		$.each(BusDynInfo[i].TravelTimeList, function(j){
			if(-1 != BusDynInfo[i].TravelTimeList[j]){
				$('#c_'+i+'_'+j).html(BusDynInfo[i].TravelTimeList[j]);
			}
		});
		
		$.each(BusDynInfo[i].BusLocationList, function(j){
			if(0 == BusDynInfo[i].BusLocationList[j] && j < BusStaticInfo[i].CurrentStopIdx){
				$('#m_'+i+'_'+j).attr("class", "midway mpast");
			}else if(1 == BusDynInfo[i].BusLocationList[j] && j < BusStaticInfo[i].CurrentStopIdx){
				$('#m_'+i+'_'+j).attr("class", "midway mpast_bus");
			}else if(0 == BusDynInfo[i].BusLocationList[j] && j >= BusStaticInfo[i].CurrentStopIdx){
				$('#m_'+i+'_'+j).attr("class", "midway mnext");
			}else if(1 == BusDynInfo[i].BusLocationList[j] && j >= BusStaticInfo[i].CurrentStopIdx){
				$('#m_'+i+'_'+j).attr("class", "midway mnext_bus");
			}
		});
		
		if(3 == BusDynInfo[i].StatusCode){
			$('#rl_'+i).attr("class", "r_light l_leave");
			$('#nl_'+i).attr("class", "n_light l_leave");
			
			$('#e_'+i).html("末班車已駛離");
		}else if(2 == BusDynInfo[i].StatusCode){
			$('#rl_'+i).attr("class", "r_light l_off");
			$('#nl_'+i).attr("class", "n_light l_off");
			
			$('#e_'+i).html("尚未發車");
		}else if(1 == BusDynInfo[i].StatusCode || 0 == BusDynInfo[i].StatusCode){
			$('#rl_'+i).attr("class", "r_light l_on");
			$('#nl_'+i).attr("class", "n_light l_on");
			
			if(0 < BusDynInfo[i].CurrentStopATime){
				$('#e_'+i).html(BusDynInfo[i].CurrentStopATime+"分鐘");
			}else if(0 == BusDynInfo[i].CurrentStopATime){
				$('#e_'+i).html("即將進站");
			}else{
				$('#e_'+i).html("尚未發車");
			}
		}
	});
}

function dragStandline(){
	
	var standlineW = 0;
	var routecontentW = 0;
	var linemoveable = 0;
	//var standlineL = 0;
	
	$(".stand_line").draggable({ 
		axis: "x",
		delay: 0,
		
		drag: function(event, ui){
			counter = 0;
			standlineW = $(this).width();
			
			routecontentW =  $(this).parent().width();
			
			linemoveable = routecontentW - standlineW;
			//standlineL = $(this).css('left');
			//standlineL = standlineL.substring(0,standlineL.length-2);
			
			//alert(standlineW+" "+routecontentW+" "+ standlineL+" "+linemoveable);
			/*
			if(ui.position.top>0 || ui.position.top < namemoveable){
				this.draggable({ disabled: true });
			}*/
		},
		stop: function(event, ui){
			//alert("dragH:"+namedragH+" dropH:"+namedropH+" dragT:"+namedragT+" uiT:"+ui.position.top);
			if(ui.position.left > 0 || linemoveable >= 0){
				$(this).css('left', 0);
			}else if(ui.position.left < linemoveable){
				$(this).css('left', linemoveable);
			}
		}
	});
}

function dragNameList(){
	
	var namedragH = 0;
	var namedropH = 0;
	var namemoveable = 0;
	//var namedragT = 0;
	
	$( "#namedrag" ).draggable({ 
		axis: "y",
		delay: 0,
		
		drag: function(event, ui){
			counter = 0;
			namedragH = $("#namedrag").height();
			
			namedropH = $("#namedrop").height();
			
			namemoveable = namedropH - namedragH;
			//namedragT = $("#namedrag").css('top');
			//namedragT = namedragT.substring(0,namedragT.length-2);
			/*
			if(ui.position.top>0 || ui.position.top < namemoveable){
				this.draggable({ disabled: true });
			}*/
		},
		stop: function(event, ui){
			//alert("dragH:"+namedragH+" dropH:"+namedropH+" dragT:"+namedragT+" uiT:"+ui.position.top);
			if(ui.position.top > 0 || namemoveable>=0){
				$("#namedrag").css('top', 0);
			}else if(ui.position.top < namemoveable){
				$("#namedrag").css('top', namemoveable);
			}
		}
	});
}

//處理按下namebtn時, routediv顯示
function hideRouteDiv(){
	
	$('.namegroup').click(function() {

		if("hand" == screentype){
			$("#routelist").show(100);
			$("#namelist").hide(100);
		}
		
		counter = 0;
		var groupidx = $(this).attr("id");
		groupidx = groupidx.substring(10,groupidx.length);
		
		for(var i = 0; i<=groupNum; i++){
			if(i == groupidx){
				$("#routegroup_"+i).show();
			}else{
				$("#routegroup_"+i).hide();
			}
		}
		
		if("screen" == screentype){
			$(".namebtn").removeClass("b_press");
			$(this).children(".namebtn").addClass("b_press");
		}	
		
		backToOriginal(JsonData.BusStaticInfo);
		nowGroup = groupidx;
	});
}

//TODO
function backToList(){	
	
	$(".route_bar").click(function(event){
		if("hand" == screentype){
			$("#routelist").hide(100);
			$("#namelist").show(100);
		}
	});
	/*
	$("body").on("click", ".route_bar", function(event){
		if("hand" == screentype){
			$("#routelist").hide(100);
			$("#namelist").show(100);
		}
	});
	*/
}

function moveNameList(){
	
	var namedragH = 0;
	var namedropH = 0;
	var namemoveable = 0;
	var namedragT = 0;
	
	$('#nameup').click(function() {
		counter = 0;
		namedragH = $("#namedrag").height();
		namedropH = $("#namedrop").height();
		namemoveable = namedropH - namedragH;
		
		if($("#namedrop").offset().top == $("#namedrag").position().top){
			namedragT = 0;
		}else{
			namedragT = $("#namedrag").position().top;
		}

		if(namemoveable < 0 && namedragT >= namemoveable){
			var top = namedragT + MOVEABLE;
			//alert("bb "+namedragT+" "+namemoveable+" "+top);
			if(top > 0){
				//$("#namedrag").css("top", '0px');
				$("#namedrag").animate({"top": "0px"}, "fast");
			}else{
				//$("#namedrag").css("top", top+'px');
				$("#namedrag").animate({"top": top+"px"}, "fast");
			}
		}
	});
	
	$('#namedown').click(function() {
		counter = 0;
		namedragH = $("#namedrag").height();
		namedropH = $("#namedrop").height();
		namemoveable = namedropH - namedragH;
		
		if($("#namedrop").offset().top == $("#namedrag").position().top){
			namedragT = 0;
		}else{
			namedragT = $("#namedrag").position().top;
		}

		if(namemoveable < 0 && namedragT >= namemoveable){
			var top = namedragT - MOVEABLE;
			//alert("bb "+namedragT+" "+namemoveable+" "+top);
			if(top < namemoveable){
				//$("#namedrag").css("top", namemoveable+'px');
				$("#namedrag").animate({"top": namemoveable+"px"}, "fast");
			}else{
				//$("#namedrag").css("top", top+'px');
				$("#namedrag").animate({"top": top+"px"}, "fast");
			}
		}
	});
}

function moveStandList(){
	
	var standline_W = 0;
	var routecontent_W = 0;
	var linemoveable = 0;
	var standline_L = 0;
	
	$('.arrowleft').click(function() {
		counter = 0;
		var standline = $(this).parent().siblings().children(".stand_line");
		var routecontent = $(this).parent().siblings(".route_content");
		
		if("hand" == screentype){
			standline = $(this).parent().siblings(".stand_line");
			routecontent = $(this).parent().parent(".route_content");
		}
		
		standline_W = standline.width();
		routecontent_W = routecontent.width();
		linemoveable = routecontent_W - standline_W;
		
		standline_L = standline.position().left;

		if(linemoveable < 0 && standline_W >= routecontent_W){
			
			var left = standline_L + MOVEABLE;
			//alert("bb "+namedragT+" "+namemoveable+" "+top);
			if(left > 0){
				//standline.css("left", '0px');
				standline.animate({"left": "0px"}, "fast");
			}else{
				//standline.css("left", left+'px');
				standline.animate({"left": left+"px"}, "fast");
			}
		}
	});
	
	$('.arrowright').click(function() {
		counter = 0;
		
		var standline = $(this).parent().siblings().children(".stand_line");
		var routecontent = $(this).parent().siblings(".route_content");	
			
		if("hand" == screentype){
			standline = $(this).parent().siblings(".stand_line");
			routecontent = $(this).parent().parent(".route_content");
		}
		
		standline_W = standline.width();
		routecontent_W = routecontent.width();
		linemoveable = routecontent_W - standline_W;
		
		standline_L = standline.position().left;

		if(linemoveable < 0 && standline_W >= routecontent_W){
			
			var left = standline_L - MOVEABLE;
			//alert("bb "+namedragT+" "+namemoveable+" "+top);
			if(left < linemoveable){
				//standline.css("left", linemoveable+'px');
				standline.animate({"left": linemoveable+"px"}, "fast");
			}else{
				//standline.css("left", left+'px');
				standline.animate({"left": left+"px"}, "fast");
			}
		}
	});
}

function backToOriginal(BusStaticInfo){
	$.each(BusStaticInfo, function(i){
		var content_W = 0;
		if("screen" == screentype){
			content_W = $('#content_'+i).width();
		}else if("hand" == screentype){
			content_W = $(window).width();
		}
		
		var dist = content_W - $('#line_'+i).width();
		if($('#line_'+i).width() > content_W){
			var stopOffset = $('#s_'+i+'_'+BusStaticInfo[i].CurrentStopIdx).position().left;
			//alert(stopOffset+" "+ content_W +" "+ $('#line_'+i).width());
			
			if(stopOffset > ORIGINAL_LEFT){
				var diff = ORIGINAL_LEFT - stopOffset;
				
				if(dist > diff){
					//$('#line_'+i).css("left",dist+"px");
					$('#line_'+i).animate({"left": dist+"px"}, "slow");
				}else{
					//$('#line_'+i).css("left",diff+"px");
					$('#line_'+i).animate({"left": diff+"px"}, "slow");
					//alert(dist+"  "+diff);
				}
				
			}else{
				//$('#line_'+i).css("left","0px");
				$('#line_'+i).animate({"left": "0px"}, "slow");
			}
		}
		
		
	});
}
//TODO
function rotationRoutelist(){
	
	var id = nowGroup+1;
	$(".routegroup").fadeOut(300);
	
	if(id <= groupNum){
		//$(".routegroup").hide();
		//$("#routegroup_"+id).show();	
		$("#routegroup_"+id).fadeIn(300);
		nowGroup = id;
	}else{
		//$(".routegroup").hide();
		//$("#routegroup_"+0).show();
		$("#routegroup_"+0).fadeIn(300);
		nowGroup = 0;
	}
	
	if("screen" == screentype){
		$(".namebtn").removeClass("b_press");
		$("#namegroup_"+nowGroup).children(".namebtn").addClass("b_press");
		
		var drop_H = $("#namedrop").height();
		var drag_H = $("#namedrag").height();
		var group_T = $("#namegroup_"+nowGroup).position().top;
		if(drag_H > drop_H){
			if((drag_H-group_T) > drop_H){
				var top = - group_T;
				$("#namedrag").animate({"top": "-"+top+"px"}, "slow");
			}else{
				var top = drop_H-drag_H;
				$("#namedrag").animate({"top": top+"px"}, "slow");
			}
		}
	}
}

//藉由瀏覽器與OS判斷要載入的CSS
function adjustCss(){
	//alert($.client.os + " & " + $.client.browser +" & "+ $.client.version );
	
	if("Windows" == $.client.os || "Mac" == $.client.os){
		
		var newCss=document.createElement("link");
		newCss.setAttribute("rel", "stylesheet");
		newCss.setAttribute("type", "text/css");
		newCss.setAttribute("href", "css/screen.css");
		//newCss.setAttribute("href", "css/handheld.css");
		document.getElementsByTagName("head")[0].appendChild(newCss);
		//TODO
		screentype = "screen";
		//screentype = "hand";
		
	}else if("iPhone/iPod" == $.client.os){
		
		var newCss=document.createElement("link");
		newCss.setAttribute("rel", "stylesheet");
		newCss.setAttribute("type", "text/css");
		newCss.setAttribute("href", "css/handheld.css");
		document.getElementsByTagName("head")[0].appendChild(newCss);
		
		screentype = "hand";
	
	}else if("Linux" == $.client.os && "Mozilla" == $.client.browser){
		
		var newCss=document.createElement("link");
		newCss.setAttribute("rel", "stylesheet");
		newCss.setAttribute("type", "text/css");
		newCss.setAttribute("href", "css/handheld.css");
		document.getElementsByTagName("head")[0].appendChild(newCss);
		
		screentype = "hand";
	}else{
		var newCss=document.createElement("link");
		newCss.setAttribute("rel", "stylesheet");
		newCss.setAttribute("type", "text/css");
		newCss.setAttribute("href", "css/handheld.css");
		document.getElementsByTagName("head")[0].appendChild(newCss);
		
		screentype = "hand";
	}
	
	if("hand" == screentype){
		MOVEABLE = 300;
		ORIGINAL_LEFT = 100;
		STAND_PLUS_MIDWAY = 84;
		HEAD_AND_TAIL = 120;
	}
	
}
//TODO
function adjustBodySize(){
	//alert($(window).width()+" "+$(window).height());
	
	$("body").width($(window).width());
	$("body").height($(window).height());
	
	$("#load_span").css("line-height", $(window).height()+"px");
	$("#warn_span").css("line-height", $(window).height()+"px");
	
	if("hand" == screentype){
		var width = $(window).width() - HAND_BTN_W;
		//alert($(window).width()+" x "+$(window).height());
		$("#routelist").width($(window).width());
		$("#namelist").width($(window).width());
	}
	
	if("none" != $("#loading").css("display")){
		showLoading();
	}
}

function timer(){
	$('body').everyTime('15s',function(){
		getJson();
		counter = counter + 15;
		if(counter >= 180){
			rotationRoutelist();
			backToOriginal(JsonData.BusStaticInfo);
			counter = 0;
		}
	});
}

//touch event轉成mouse event
function touchHandler(event){

	var touches = event.changedTouches,
    first = touches[0],
    type = "";

    switch(event.type){
	 	case "touchstart": type = "mousedown"; break;
	    case "touchmove":  type="mousemove"; break;        
	    case "touchend":   type="mouseup"; break;
	    default: return;
	}
	var simulatedEvent = document.createEvent("MouseEvent");
	simulatedEvent.initMouseEvent(type, true, true, window, 1,
	                          first.screenX, first.screenY,
	                          first.clientX, first.clientY, false,
	                          false, false, false, 0/*left*/, null);
	
	first.target.dispatchEvent(simulatedEvent);
	//event.preventDefault();
}

function init(){
   document.addEventListener("touchstart", touchHandler, true);
   document.addEventListener("touchmove", touchHandler, true);
   document.addEventListener("touchend", touchHandler, true);
   document.addEventListener("touchcancel", touchHandler, true);    
}

