var routeMode = "route";
var travelMode = "car";

$(document).ready(function(){
	mode = "routing";
	//alert($("#r_list").height());
	adjustRoutingTile();
	$("#r_list").scrollbar();
	
	addRRListSortable();
	
	$( "#rl_sortable" ).sortable({
		revert: true
	});
	//$( "ul, li" ).disableSelection();
	
	clickRouteModeButton();
	
	$("#r_clear").click(function() {
		if("route" == routeMode){
			
			directionsDisplay.setMap(null);
			
			styleMakerA.setMap(null);
			styleMakerA = null;
			$("#rr_input_A").val("");
			
			styleMakerB.setMap(null);
			styleMakerB = null;
			$("#rr_input_B").val("");
			

		}else if("line" == routeMode){
			deleteOverlays();
			$("#rl_sortable").html("");
			reAdjustRoutingTile();
			//adjustRoutingTile();
		}
	});
	
	setLoginAndRegisterPage();
});



function addRRListSortable(){
	$( "#rr_sortable" ).sortable({
		revert: true,
		stop: function() {		

			$("#rr_sortable").html('<li id="rr_wrap_A" class="r_wrap"><div class="r_num"><span id="rr_span_A">A</span></div>'+
										'<div class="r_text"><input id="rr_input_A" class="r_input" readonly="readonly" name="rr_input_A"></div></li>'+
									'<li id="rr_wrap_B" class="r_wrap"><div class="r_num"><span id="rr_span_B">B</span></div>'+
										'<div class="r_text"><input id="rr_input_B" class="r_input" readonly="readonly" name="rr_input_B"></div></li>');
			
			if(styleMakerA && styleMakerB){
				var temp = styleMakerA;
				styleMakerA = styleMakerB;
				styleMakerB = temp;
				//alert(styleMakerA.title +" & "+ styleMakerB.title);
				
				$("#rr_input_A").val(styleMakerA.title);
				$("#rr_input_B").val(styleMakerB.title);
				calcRoute();
			}else if(styleMakerA){
				styleMakerB = new StyledMarker({
					styleIcon: new StyledIcon(StyledIconTypes.MARKER,{text: "B"}, styleIconClass),
					position: styleMakerA.position,
					map:map,
					title: styleMakerA.title
				});
				styleMakerA.setMap(null);
				styleMakerA = null;
				//alert(styleMakerB.title);
				$("#rr_input_B").val(styleMakerB.title);
			}else if(styleMakerB){
				styleMakerA = new StyledMarker({
					styleIcon: new StyledIcon(StyledIconTypes.MARKER,{text: "A"}, styleIconClass),
					position: styleMakerB.position,
					map:map,
					title: styleMakerB.title
				});
				styleMakerB.setMap(null);
				styleMakerB = null;
				//alert(styleMakerA.title);
				$("#rr_input_A").val(styleMakerA.title);
			}
		}
	});
}

//取得瀏覽器視窗高度
function getBrowserHeight() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.clientHeight : document.body.clientHeight;
	} else {
		return self.innerHeight;
	}
}

//取得瀏覽器視窗寬度
function getBrowserWidth() {
	if ($.browser.msie) {
		return document.compatMode == "CSS1Compat" ? document.documentElement.clientWidth : document.body.clientWidth;
	} else {
		return self.innerWidth;
    }
}

function clickRouteModeButton(){
	$('#rm_route').click(function() {
		if(routeMode == "line"){
			$('#rm_route').css({
				'background': '#336699'
			});
			$('#rm_r_img').css({
				'background': 'url("img/icon.png") -0px -248px'
			});
			$('#rm_line').css({
				'background': '#4f4f4f'
			});
			$('#rm_l_img').css({
				'background': 'url("img/icon.png") -288px -248px'
			});
			
			$('#r_travelmode_table').show();
			$( "#rr_sortable" ).show();
			$('#rl_sortable').hide();

			directionsDisplay.setMap(map);
			
			clearOverlays();
			
			adjustRoutingTile();
			
			if(infowindowArray[0]){
				//alert(marker.title);
				var contentString = setInfoContent(marker.title, 0);
				infowindowArray[0].setContent(contentString);
			}
			
			if(styleMakerA && styleMakerB){
				$("#rr_input_A").val(styleMakerA.title);
				$("#rr_input_B").val(styleMakerB.title);
				calcRoute();
			}else if(styleMakerA){
				$("#rr_input_A").val(styleMakerA.title);
			}else if(styleMakerB){
				$("#rr_input_B").val(styleMakerB.title);
			}
			addRRListSortable();
			
			routeMode = "route";
			//alert(routeMode);
		}
	});
	
	$('#rm_line').click(function() {
		if(routeMode == "route"){
			$('#rm_route').css({
				'background': '#4f4f4f'
			});
			$('#rm_r_img').css({
				'background': 'url("img/icon.png") -96px -248px'
			});
			$('#rm_line').css({
				'background': '#336699'
			});
			$('#rm_l_img').css({
				'background': 'url("img/icon.png") -192px -248px'
			});
			
			$('#r_travelmode_table').hide();
			$( "#rr_sortable" ).hide();
			$('#rl_sortable').show();
			
			directionsDisplay.setMap(null);
			showOverlays();
			adjustRoutingTile();
			
			if(infowindowArray[0]){
				//alert(marker.title);
				var contentString = setInfoContent(marker.title, 0);
				infowindowArray[0].setContent(contentString);
			}

			routeMode = "line";
			//alert(routeMode);
		}
	});
	
	$("body").on("click", "#rtm_car", function() {
		//alert(routeMode);
		if(routeMode == "route"){
			if(travelMode == 'walk'){
				$('#rtm_car').css({
					'background': 'url("img/icon.png") -64px -280px'
				});
				$('#rtm_walk').css({
					'background': 'url("img/icon.png") -128px -280px'
				});
				travelMode = 'car';
				
				if(styleMakerA && styleMakerB){
					calcRoute();
				}
			}
		}
	});
	
	$("body").on("click", "#rtm_walk", function() {
		//alert(routeMode);
		if(routeMode == "route"){
			if(travelMode == 'car'){
				$('#rtm_car').css({
					'background': 'url("img/icon.png") -32px -280px'
				});
				$('#rtm_walk').css({
					'background': 'url("img/icon.png") -160px -280px'
				});
				travelMode = 'walk';
				
				if(styleMakerA && styleMakerB){
					calcRoute();
				}
			}
		}
	});
}

//調整RoutingTile的高度
function adjustRoutingTile(){
	var tile_h = $("#r_list").height() + 143;
	
	if(tile_h<(getBrowserHeight()*0.9)){
		$("#routing_tile").css({'height':tile_h+'px'});
	}else{
		$("#routing_tile").css({'height':'90%'});
	}
}