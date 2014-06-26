var geocoder;
var map;
var infowindow= new google.maps.InfoWindow();
var marker;
var markerArray = [];
var infowindowArray = [];
var styleIconClass = new StyledIcon(StyledIconTypes.CLASS,{color:"#66CD00"});//StyledMarker的樣式
var styleMakerA;
var styleMakerB;
var styledMarkerArray = [];
//var markersContentArray = [];
var poly;
var pinCount = 0;

var directionDisplay;
var directionsService = new google.maps.DirectionsService();

var ptsCount = 0;

function initialize() {
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(25.0448, 121.5367);
	var myOptions = {
  		zoom: 14,
  		center: latlng,
  		mapTypeControl: false,
  		streetViewControl: false,
  		navigationControlOptions: {
  	        position: google.maps.ControlPosition.RIGHT_CENTER
  	    },
  		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	var polyOptions = {
	    strokeColor: '#000000',
	    strokeOpacity: 1.0,
	    strokeWeight: 3
	};
	poly = new google.maps.Polyline(polyOptions);
	//poly.setMap(map);

	// Add a listener for the click event
	google.maps.event.addListener(map, 'click', addWaypoint);
	
	directionsDisplay = new google.maps.DirectionsRenderer();
	//directionsDisplay.setMap(map);

	//maker設為起點
	$("body").on("click", ".popsetstart", function(){
		
		var idstr = $(this).attr("id");
		var i = idstr.substring(9,idstr.length);
		//var i = j-1;
		//alert(i);

		$("#rr_input_A").val(marker.title);
		
		if(styleMakerA){
			styleMakerA.setMap(null);
		}
		
		styleMakerA = new StyledMarker({
			styleIcon: new StyledIcon(StyledIconTypes.MARKER,{text: "A"}, styleIconClass),
			position: marker.position,
			map:map,
			title: marker.title
		});
		marker.setMap(null);
		
		if(styleMakerB){
			styleMakerA.setMap(null);
			styleMakerB.setMap(null);
			calcRoute();
		}
		
	});
	//maker設為終點
	$("body").on("click", ".popsetend", function(){
		
		var idstr = $(this).attr("id");
		var i = idstr.substring(7,idstr.length);
		//var i = j-1;
		//alert(i);

		$("#rr_input_B").val(marker.title);
		
		if(styleMakerB){
			styleMakerB.setMap(null);
		}
		
		styleMakerB = new StyledMarker({
			styleIcon: new StyledIcon(StyledIconTypes.MARKER,{text: "B"}, styleIconClass),
			position: marker.position,
			map:map,
			title: marker.title
		});
		marker.setMap(null);
		
		if(styleMakerA){
			styleMakerA.setMap(null);
			styleMakerB.setMap(null);
			calcRoute();
		}
	});
	//加入maker至自訂點擊路線
	$("body").on("click", ".popaddpoint", function(){
		
		var idstr = $(this).attr("id");
		var i = idstr.substring(9,idstr.length);
		//var i = j-1;
		//alert(i);
		
		addStyleMaker(marker.position, marker.title, poly.getPath());
		marker.setMap(null);
		
	});
	//刪除maker
	$("body").on("click", ".popremovepin", function(){
		
		var idstr = $(this).attr("id");
		var i = idstr.substring(10,idstr.length);
		//var i = j-1;
		//alert(i);
		markerArray[i].setMap(null);
	});
}

function addWaypoint(event){
	if(routeMode == "route"){
		
		codeLatLng(event.latLng);
		
	}else if(routeMode == "line"){
		poly.setMap(map);
		var path = poly.getPath();
		// Because path is an MVCArray, we can simply append a new coordinate
		// and it will automatically appear
		
		if (geocoder) {
			geocoder.geocode({'latLng': event.latLng}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					if (results[1]) {
						/*
						map.setZoom(11);
						marker = new google.maps.Marker({
							position: event.latLng, 
							map: map
						}); 
						infowindow.setContent(results[1].formatted_address);
						infowindow.open(map, marker);
						*/
						
						addStyleMaker(event.latLng, results[0].formatted_address, path);
						
					}
				} else {
					alert("Geocoder failed due to: " + status);
					
					addStyleMaker(event.latLng, "ERROR: " + status, path);
					
				}
			});
		}
		
		
		
		//var latlngStr  = "(AB,CD)".split(",",2);
		//var lat = parseFloat(latlngStr[0]);
	    //var lng = parseFloat(latlngStr[1]);
		//alert(latlngStr[1]);
		
		// Add a new marker at the new plotted point on the polyline.
		/*
		var marker = new google.maps.Marker({
			position: event.latLng,
		    title: '#' + path.getLength(),
		    map: map
		});*/
		
		

		/*
		var start;
		var end;
		var waypts = [];
		
		if(2<=path.length){
			
			start = path.getAt(0);
			end = path.getAt(path.length-1);
	
			for (var i = 1; i < path.length-1; i++) {
				waypts.push({
					location:path.getAt(i),
					stopover:true
				});
			}
			
			var request = {
					origin: start, 
					destination: end,
					waypoints: waypts,
					optimizeWaypoints: true,
					travelMode: google.maps.DirectionsTravelMode.WALKING
			};
		  
			directionsService.route(request, function(response, status) {
				
				if (status == google.maps.DirectionsStatus.OK) {
					
					directionsDisplay.setDirections(response);
					var route = response.routes[0];
					
				}
			});
		}
		*/
	}
}

function addStyleMaker(latLng, address, path){
	pinCount++;
	path.push(latLng);
	
	var styleMaker = new StyledMarker({
		styleIcon:new StyledIcon(StyledIconTypes.MARKER,{text: ""+pinCount}, styleIconClass),
		position: latLng,
		title: address,
		map: map
	});
	styledMarkerArray.push(styleMaker);
	
	$('#rl_sortable').append('<li id="rl_wrap_'+path.getLength()+'" class="r_wrap"><div class="r_num"><span id="rl_span_'+path.getLength()+'">'+pinCount+
								'</span></div><div class="r_text"><input id="rl_input_'+path.getLength()+
								'" class="r_input" name="rl_input_'+path.getLength()+
								'" readonly="readonly"/></div><a title="刪除" id="rl_x_'+path.getLength()+'" class="r_x" href="#"></a></li>');
	
	//$('#r_input_'+path.getLength()).val(results[0].formatted_address);
	//markersContentArray.push(address);
	
	reAdjustRoutingTile();
	
	$.each(styledMarkerArray,function(i){
		
		//var j = i+1;
		//$('#r_input_'+j).val(styledMarkerArray[i].title);
		$('input[name*="rl_input_"]:eq('+i+')').val(styledMarkerArray[i].title);
		
    });
	
	addRLSortable(path);
	
	//TODO
	//刪除styleMaker
	$(".r_wrap").on("click", ".r_x", function() {
		var idstr = $(this).attr("id");
		if(-1 != idstr.indexOf('rl_x')){
			var j = idstr.substring(5,idstr.length);
			var i = j-1;
			//alert(idstr.substring(4,idstr.length));
			
			path.removeAt(i);
			styledMarkerArray[i].setMap(null);
			styledMarkerArray.splice(i, 1);//刪除Maker
			$('#rl_wrap_'+j).remove();
			
			for(var k = j; k <= styledMarkerArray.length+1; k++){
				
				//alert($('#r_wrap_'+k).attr('id'));
				
				var l = k-1;
				$('#rl_wrap_'+k).attr('id', 'rl_wrap_'+l);
				//$('#r_span_'+k).html(l);
				$('#rl_span_'+k).attr('id', 'rl_span_'+l);
				$('#rl_input_'+k).attr('name', 'rl_input_'+l);
				$('#rl_input_'+k).attr('id', 'rl_input_'+l);
				$('#rl_x_'+k).attr('id', 'rl_x_'+l);	
			}
			/*
			reAdjustRoutingTile();
			
			$.each(styledMarkerArray,function(i){
				
				//var j = i+1;
				//$('#r_input_'+j).val(styledMarkerArray[i].title);
				$('input[name*="rl_input_"]:eq('+i+')').val(styledMarkerArray[i].title);
				
		    });
			
			addRLSortable(path);
			*/
			if(0 == styledMarkerArray.length){
				pinCount = 0;
			}
		}
	});
}

function addRLSortable(path){
	var sortArray = [];
	var itemId = "";
	var preIndex = -1;
	var postIndex = -1;
	$( "#rl_sortable" ).sortable({
		revert: true,
		start: function(event, ui){
			sortArray = $("#rl_sortable").sortable("toArray");//該方法, 可以取出該容器內的元素的順序. 元素取出來了, 至於怎麼保存就是a piece of cake了~
			itemId = ui.item.attr("id");
			preIndex = sortArray.indexOf(itemId);

		},
		stop: function() {		
			sortArray = $("#rl_sortable").sortable("toArray");
			postIndex = sortArray.indexOf(itemId);
			//alert(preIndex+' & '+postIndex);

			var tempLatLng = path.getAt(preIndex);
			path.removeAt(preIndex);
			path.insertAt(postIndex, tempLatLng);
			
			var tempMaker = styledMarkerArray[preIndex];
			//alert(tempMaker.title);
			styledMarkerArray.splice(preIndex, 1);//刪除Maker
			styledMarkerArray.splice(postIndex, 0, tempMaker);//插入Maker
			//alert(styledMarkerArray[0].title+' & '+styledMarkerArray[1].title+' & '+styledMarkerArray[2].title);
			/*
			var tempMakerContent = markersContentArray[preIndex];
			alert(tempMakerContent);
			markersContentArray.splice(preIndex, 1);
			markersContentArray.splice(postIndex, 0, tempMaker);
			//alert(markersContentArray[0]+' & '+markersContentArray[1]+' & '+markersContentArray[2]);
			*/
		}
	});
}

function reAdjustRoutingTile(){
	var contents = "";
	if(null != $('.englobe').html()){
		contents = $('.englobe').html();
	}else{
		contents = $('#r_list').html();
	}
	//alert(contents);
	$('#r_list').empty();
	$('#r_list').css({
		"width": "",
		"height": ""
	});
	$('#r_list').html(contents);
	$("#r_list").scrollbar();
	
	adjustRoutingTile();
}

function addMarker(event){
	var path = poly.getPath();

	// Because path is an MVCArray, we can simply append a new coordinate
	// and it will automatically appear
	path.push(event.latLng);

	// Add a new marker at the new plotted point on the polyline.
	var marker = new google.maps.Marker({
		position: event.latLng,
	    title: '#' + path.getLength(),
	    map: map
	});
}
//TODO
//規劃路線
function calcRoute() {
	var start = styleMakerA.title;
	var end = styleMakerB.title;
	//var waypts = [];

	var travel;
	
	if(travelMode == "car"){
		travel = google.maps.DirectionsTravelMode.DRIVING;
	}else if(travelMode == 'walk'){
		travel = google.maps.DirectionsTravelMode.WALKING;
	}
	
	var request = {
			origin: start, 
			destination: end,
			//waypoints: waypts,
			optimizeWaypoints: true,
			travelMode: travel
	};
  
	directionsService.route(request, function(response, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			directionsDisplay.setMap(map);
			directionsDisplay.setDirections(response);
		}
	});
}

//地址轉經緯度
function codeAddress() {
	var address = document.getElementById("searchinput").value;
	
	if(marker){
		marker.setMap(null);
		markerArray.length = 0;
	}
	
	if (geocoder) {
		geocoder.geocode( { 'address': address}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
				marker = new google.maps.Marker({
					map: map, 
					position: results[0].geometry.location,
					title: results[0].formatted_address
				});
				
				markerArray[0] = marker;
				
				var contentString = setInfoContent(results[0].formatted_address, 0);

				var infowindow = new google.maps.InfoWindow({
				    content: contentString
				});
				infowindowArray[0] = infowindow;
				infowindow.open(map,marker);
				google.maps.event.addListener(marker, 'click', function() {
					  infowindow.open(map,marker);
				});
				//attachSecretMessage(marker, contentString);

				/*infowindow = new google.maps.InfoWindow({
					content: contentString
				});*/
				/*
				google.maps.event.addListener(marker, 'click', function(){
					infowindow.open(map, marker);
				});*/
			}else{
				alert("Geocode was not successful for the following reason: " + status);
			}
		});
    }
}

//設定infowindow中content的內容
function setInfoContent(address, index){
	var contentString="";
	if("fish" == mode ){
		contentString = '<div id="content">'+
							'<div id="siteNotice"></div>'+
							'<div id="firstHeading" class="firstHeading"></div>'+
							'<div id="bodyContent" class="bodyContent"><span>'+
							address +
							'</span></div>'+
							'<div class="popseparator"></div>'+
							'<table id="poptool" cellspacing="0"><tbody><tr>'+
							
								'<td><a id="setstart_'+index+'" title="設為起點" class="popsetstart popbtn btn popbtnhide" href="#"></a></td>'+
								'<td><a id="setend_'+index+'" title="設為起點" class="popsetend popbtn btn popbtnhide" href="#"></a></td>'+
								'<td><a id="addpoint_'+index+'" title="加入節點" class="popaddpoint popbtn btn popbtnhide" href="#"></a></td>'+
							
								'<td><a id="removepin_'+index+'" title="從地圖上移除" class="popremovepin btn" href="#"></a></td>'+
							'</tr></tbody></table>'+
						'</div>';
	}else if("routing" == mode ){
		if("route" == routeMode){
			contentString = '<div id="content">'+
								'<div id="siteNotice"></div>'+
								'<div id="firstHeading" class="firstHeading"></div>'+
								'<div id="bodyContent" class="bodyContent"><span>'+
								address +
								'</span></div>'+
								'<div class="popseparator"></div>'+
								'<table id="poptool" cellspacing="0"><tbody><tr>'+
								
									'<td><a id="setstart_'+index+'" title="設為起點" class="popsetstart popbtn btn" href="#"></a></td>'+
									'<td><a id="setend_'+index+'" title="設為起點" class="popsetend popbtn btn" href="#"></a></td>'+
									'<td><a id="addpoint_'+index+'" title="加入節點" class="popaddpoint popbtn btn popbtnhide" href="#"></a></td>'+
								
									'<td><a id="removepin_'+index+'" title="從地圖上移除" class="popremovepin btn" href="#"></a></td>'+
								'</tr></tbody></table>'+
							'</div>';
		}else if("line" == routeMode){
			contentString = '<div id="content">'+
								'<div id="siteNotice"></div>'+
								'<div id="firstHeading" class="firstHeading"></div>'+
								'<div id="bodyContent" class="bodyContent"><span>'+
								address +
								'</span></div>'+
								'<div class="popseparator"></div>'+
								'<table id="poptool" cellspacing="0"><tbody><tr>'+
								
									'<td><a id="setstart_'+index+'" title="設為起點" class="popsetstart popbtn btn popbtnhide" href="#"></a></td>'+
									'<td><a id="setend_'+index+'" title="設為起點" class="popsetend popbtn btn popbtnhide" href="#"></a></td>'+
									'<td><a id="addpoint_'+index+'" title="加入節點" class="popaddpoint popbtn btn" href="#"></a></td>'+
								
									'<td><a id="removepin_'+index+'" title="從地圖上移除" class="popremovepin btn" href="#"></a></td>'+
								'</tr></tbody></table>'+
							'</div>';
		}
	}
	return contentString;
}

//經緯度轉地址
function codeLatLng(latlng) {
	/*
	var input = document.getElementById("latlng").value;
	var latlngStr = input.split(",",2);
	var lat = parseFloat(latlngStr[0]);
	var lng = parseFloat(latlngStr[1]);
	var latlng = new google.maps.LatLng(lat, lng);
	*/
	if(marker){
		marker.setMap(null);
		markerArray.length = 0;
	}
	
	if (geocoder) {
		geocoder.geocode({'latLng': latlng}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {					
					//map.setZoom(11);
					marker = new google.maps.Marker({
						position: latlng, 
						map: map,
						title: results[0].formatted_address
					}); 
					
					markerArray[0] = marker;
					
					var contentString = setInfoContent(results[0].formatted_address, 0);

					var infowindow = new google.maps.InfoWindow({
					    content: contentString
					});
					infowindowArray[0] = infowindow;
					infowindow.open(map,marker);
					google.maps.event.addListener(marker, 'click', function() {
						  infowindow.open(map,marker);
					});
					//infowindow.setContent(results[0].formatted_address);
					//infowindow.open(map, marker);
				}
			} else {
				alert("Geocoder failed due to: " + status);
			}
		});
	}
}

//The five markers show a secret message when clicked
//but that message is not within the marker's instance data
function attachSecretMessage(marker, message) {
	var infoBubble = new InfoBubble({
		//ShadowStyle: 0,
		padding: 10,
	    borderRadius: 4,
	    //borderWidth: 1,
	    //borderColor: '#ccc',
	    //backgroundColor: '#fff',
	    minWidth: 350,
	    maxWidth: 900,
	    minHeight: 100,
	    //maxHeight: 1000,
	    arrowSize: 10,
	    arrowPosition: 50,
	    arrowStyle: 0
	});
	
	infoBubble.setContent(message);
    infoBubble.open(map, marker);

	/*
	var infowindow = new google.maps.InfoWindow({ 
		content: message,
	    size: new google.maps.Size(100,100)
	});*/
	google.maps.event.addListener(marker, 'click', function(){
		//infowindow.open(map,marker);
		//marker.setMap(null);
		//marker = null;
		infoBubble.open(map, marker);
	});
	
}

//Removes the overlays from the map, but keeps them in the array
function clearOverlays() {
	
	if (styledMarkerArray) {
		
		$.each(styledMarkerArray, function(i){
			styledMarkerArray[i].setMap(null);
		});
		/*
		for (i in styledMarkerArray) {
			styledMarkerArray[i].setMap(null);
			
		}*/
		poly.setMap(null);
	}
}

// Shows any overlays currently in the array
function showOverlays() {
	if (styledMarkerArray) {
		
		$.each(styledMarkerArray, function(i){
			styledMarkerArray[i].setMap(map);
		});
		/*
		for (i in styledMarkerArray) {
			styledMarkerArray[i].setMap(map);
		}*/
		poly.setMap(map);
	}
}

// Deletes all markers in the array by removing references to them
function deleteOverlays() {
	if (styledMarkerArray) {
		
		$.each(styledMarkerArray, function(i){
			styledMarkerArray[i].setMap(null);
		});
		/*
		for (i in styledMarkerArray) {
			styledMarkerArray[i].setMap(null);
		}
		*/
		styledMarkerArray.length = 0;//從地圖上移除疊加層並將陣列的length設為 0 即可刪除疊加層，這樣做會移除疊加層的所有參照。

		//從地圖上移除折線，及移除折線的所有參照。
		poly.setMap(null);
		for(i in poly.getPath()){
			poly.getPath().removeAt(i);
		}
		pinCount = 0;
	}
}