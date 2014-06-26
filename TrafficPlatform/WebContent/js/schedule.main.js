var jData = '';
var mode = 'none';

$(document).ready(function(){
	
	$.ajaxSetup ({
	    cache: false //stop jQuery cache
	});
	
	getList("selectAll","all",jData);
	
	/*
	$('#content').load('config.html', function() {
		$( ".datepicker" ).datepicker();
		$('.timepicker').timepicker({});
		$('#content').scrollbar();
	});
	*/
	
	$('#content').load('tutorial.html', function() {
		//$('#content').scrollbar();
	});
	
	$('#logo').click(function() {
		freshList();
		loadTutorial();
	});
	
	$('#news').click(function() {
		freshList();
		mode = 'none';
		setButtonDisabled(true);
		$('#content').load('news.html', function() {
			$('#content').css('width', '560px');
			$('#content').css('height', '');
			$('#content').scrollbar();
		});
	});
	
	$('#searchbtn').click(function() {
		mode = 'none';
		setButtonDisabled(true);
		getList("select",$("#searchinput").val(),jData);
		loadTutorial();
	});
	
	$("#searchinput").keypress(function(event){
		if(13 == event.which){
			mode = 'none';
			setButtonDisabled(true);
			getList("select",$("#searchinput").val(),jData);
			loadTutorial();
		}
	});
	
	$('#add').click(function() {
		freshList();
		mode = 'none';
		setButtonDisabled(true);
		$('#content').load('config.html', function() {
			setFormValidate();
			$('#intervalselect').decorselect();//TODO
			
			$( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
			$('.timepicker').timepicker({});
			
			$('#enddate').attr('disabled', 'disabled');
			$('#endtime').attr('disabled', 'disabled');
			
			$('#intervalinput').attr('disabled', 'disabled');
			$('#intervalselect').attr('disabled', 'disabled').change();
			
			$('#weekday0').attr('disabled', 'disabled');
			$('#weekday1').attr('disabled', 'disabled');
			$('#weekday2').attr('disabled', 'disabled');
			$('#weekday3').attr('disabled', 'disabled');
			$('#weekday4').attr('disabled', 'disabled');
			$('#weekday5').attr('disabled', 'disabled');
			$('#weekday6').attr('disabled', 'disabled');
			
			$('#dateinput').attr('disabled', 'disabled');
			
			configListener();
			
			$("#confirm").click(function() {
				
                if( $("#scheduleform").valid()){
                	var status = false;
        			var start = "0000-00-00 00:00:00";
        			var end = "0000-00-00 00:00:00";
        			var interval = "0";
        			var weekday = 0;
        			var date = 0;
        			
        			//是否執行
        			if('on' == $('input[name=statuscheck]:checked').val()){
        				status = true;
        			}
        			
        			//開始時間確定
        			//start = pickerstrToDbstr($('#startdate').val()) +" "+ $('#starttime').val() +":00";
        			start = $('#startdate').val() +" "+ $('#starttime').val() +":00";
        			
        			//終止時間確定
        			if('on' == $('input[name=endcheck]:checked').val()){
        				//end = pickerstrToDbstr($('#enddate').val()) +" "+ $('#endtime').val() +":00";
        				end = $('#enddate').val() +" "+ $('#endtime').val() +":00";
        			}			
        			
        			//循環資料確定
        			if('interval' == $('input[name=circulation]:checked').val()){
        				interval = $('#intervalselect').val() + $('#intervalinput').val();
        			}else if('weekday' == $('input[name=circulation]:checked').val()){
        				if('on' == $('input[id=weekday0]:checked').val()){
        					weekday+=1;
        				}
        				if('on' == $('input[id=weekday1]:checked').val()){
        					weekday+=2;
        				}
        				if('on' == $('input[id=weekday2]:checked').val()){
        					weekday+=4;
        				}
        				if('on' == $('input[id=weekday3]:checked').val()){
        					weekday+=8;
        				}
        				if('on' == $('input[id=weekday4]:checked').val()){
        					weekday+=16;
        				}
        				if('on' == $('input[id=weekday5]:checked').val()){
        					weekday+=32;
        				}
        				if('on' == $('input[id=weekday6]:checked').val()){
        					weekday+=64;
        				}		
        			}else if('monthdate' == $('input[name=circulation]:checked').val()){
        				date = $('#dateinput').val();
        			}
        			
        			setConfigDisabled(true);
        			
                	addList($('#nameinput').val(), $('#programinput').val(), status, start, end, interval, weekday, date);
                }else{
                	//var msg = "<p>Please check that you realiy fill every fields correctly!!!</p>";
            		//alertDialog(msg, false);
                }
            });
			
			//按下Cancel鍵
			$('#cancel').click(function(){
				freshList();
				loadTutorial();
			});

		});
	});
	
	//Adjust height of overlay to fill screen when page loads  
	$("#fuzz").css("height", $(document).height());  
	
	/*
	$('#scroll_down').click(function() {
		$('#content').load('config.html', function() {
			$('#content').css('width', '560px');
			$('#content').css('height', '');
			$('#content').scrollbar();
		});
	});*/
	
	//每30分鐘更新一次list
	//setInterval(getList("selectAll","all",jData), 1000*60*30);
});

//設定jquery.validate.js表單驗證規則
function setFormValidate(){
	//須與form表單ID名稱相同
	$("#scheduleform").validate({
		rules:{
			nameinput: { required: true },
			programinput: { required: true },
			startdate: { required: true },
			starttime: { required: true },
			enddate: { required: true },
			endtime: { required: true },
			circulation : {required :true},
			weekday: {required :true},
			intervalinput: {
				required :true,
				min:1
			},
			intervalselect: {required :true},
			dateinput: {
				required: true,
				range: [1, 31]
			}
		},
		messages:{
			nameinput: "x",
			programinput: "x",
			startdate: "x",
			starttime: "x",
			enddate: "x",
			endtime: "x",
			circulation: "x",
			weekday: "x",
			intervalinput: "x",
			intervalselect: "x",
			dateinput: "x"
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

//載入tutorial.html
function loadTutorial(){
	$('#content').load('tutorial.html', function() {
		$('#content').css('width', '560px');
		$('#content').css('height', '');
		//$('#content').scrollbar();
	});
}

//更新sidelist
function freshList(){
	mode = 'none';
	setButtonDisabled(true);
	getList("selectAll","all",jData);
}

//自製alert dialog
function alertDialog(message, confirmmode, closefunc, checkfunc){

	$(".msgbox").draggable();
	if(100 < message.length){
		$(".msgbox").css({"height":message.length*1.4+"px"});
	}
	
	$(".msg").empty();
	$(".msg").html(message);
	    
	if(false == confirmmode){
		$(".close").hide();
	}else{
		$(".close").show();
	}
	
	$("#fuzz").fadeIn();  
    //return false;
    
	//unbind button event
	$(".close").unbind();
	$(".check").unbind();
	
	//When the message box is closed, fade out  
	$(".close").click(function(event){  
		$("#fuzz").fadeOut();  
		closefunc();
	});
	
	//When the message box is checked, fade out  
	$(".check").click(function(event){  
		$("#fuzz").fadeOut();  
		checkfunc();
	});
}

//取得list
function getList(query, name, jData){
    var s='';
    $.ajax({
        type:'post',
        url:'ListServlet',
        data:{
    		query:query,
    		name:name
    	},
        dataType:'json',
        timeout:7000,
        success: function(data){
    			$('#slidelist').empty();
    			$('#slidelist').html("<ol id='selectable'></ol>");
    			jData = "";
    			jData = data;
    			$('#selectable').empty();
	            $.each(data,function(i){
	            	
	                s=('<li id="'+data[i].id+'" class="ui-widget-content">'+data[i].name+'</li>');
	                $('#selectable').append(s);
	            });
	            $('#slidelist').css('width', '25%');
 	    		$('#slidelist').css('height', '');
	            $("#slidelist").scrollbar();
	            setSelectable(jData);
        	},
        error:function(xhr, ajaxOptions, thrownError){
	        	//alert(xhr.status);
	            //alert(thrownError);
        		var msg = "<p>Error: "+xhr.status+"</p><p>"+thrownError+"</p>";
        		alertDialog(msg, false);
        	}
    });
}

//編輯List
function updateListContent(id, name, program, status, start, end, interval, weekday, date){
    $.ajax({
        type:'post',
        url:'ListServlet',
        data:{
    		query:'update',
    		id:id,
    		name:name,
    		program:program,
    		status:status,
    		start:start,
    		end:end,
    		interval:interval,
    		weekday:weekday,
    		date:date
    	},
        dataType:'json',
        timeout:7000,
        success: function(data){
	            //setContent(data);
    			if(true == data){
    				//alert("Update Success");
    				freshList();
    				alertDialog("Update Success", false, null, loadTutorial);
    			}else{
    				//alert("Update Fail ?!");
    				alertDialog("Update Fail ?!?!?!", false);
    			}
        	},
        error:function(xhr, ajaxOptions, thrownError){
        		//alert(xhr.status);
	            //alert(thrownError);
        		var msg = "<p>Error: "+xhr.status+"</p><p>"+thrownError+"</p>";
        		alertDialog(msg, false);
        	}
    });
}

//刪除List
function deleteList(id){
    $.ajax({
        type:'post',
        url:'ListServlet',
        data:{
    		query:'delete',
    		id:id
    	},
        dataType:'json',
        timeout:7000,
        success: function(data){
	            //setContent(data);
    			if(true == data){
    				//alert("Delete Success");
    				freshList();
    				alertDialog("Delete Success", false, null, loadTutorial);
    			}else{
    				//alert("Delete Fail ?!");
    				alertDialog("Delete Fail ?!?!?!", false);
    			}
        	},
        error:function(xhr, ajaxOptions, thrownError){
        		//alert(xhr.status);
	            //alert(thrownError);
        		var msg = "<p>Error: "+xhr.status+"</p><p>"+thrownError+"</p>";
        		alertDialog(msg, false);
        	}
    });
}

//新增List
function addList(name, program, status, start, end, interval, weekday, date){
    $.ajax({
        type:'post',
        url:'ListServlet',
        data:{
    		query:'add',
    		name:name,
    		program:program,
    		status:status,
    		start:start,
    		end:end,
    		interval:interval,
    		weekday:weekday,
    		date:date
    	},
        dataType:'json',
        timeout:7000,
        success: function(data){
	            //setContent(data);
    			if(true == data){
    				//alert("Update Success");
    				freshList();
    				alertDialog("Add Success", false, null, loadTutorial);
    			}else{
    				//alert("Update Fail ?!");
    				alertDialog("Add Fail ?!?!?!", false);
    			}
        },
        error:function(xhr, ajaxOptions, thrownError){
        		//alert(xhr.status);
	            //alert(thrownError);
        		var msg = "<p>Error: "+xhr.status+"</p><p>"+thrownError+"</p>";
        		alertDialog(msg, false);
        }
    });
}

//取得Log
function getLog(id){
    $.ajax({
        type:'post',
        url:'ListServlet',
        data:{
    		query:'getlog',
    		id:id
    	},
        dataType:'html',
        timeout:7000,
        success: function(data){//TODO
	            
    			$('#content').html("");
    			$('#content').html(data);
    			$('#content').css('width', '560px');
    			$('#content').css('height', '');
    			$('#content').scrollbar();
    			$('#edit').removeClass("button");
    			$('#edit').addClass("disable");
    			
        },
        error:function(xhr, ajaxOptions, thrownError){
        		//alert(xhr.status);
	            //alert(thrownError);
        		var msg = "<p>Error: "+xhr.status+"</p><p>"+thrownError+"</p>";
        		alertDialog(msg, false);
        }
    });
}

//設定sidelist頁面選擇動作
function setSelectable(jData){
	$("#selectable").selectable({
		stop: function() {
			setButtonDisabled(false);
			var result = $( "#select-result" ).empty();
			var listName = '';
			$( ".ui-selected", this ).each(function() {
				var index = $( "#selectable li" ).index( this );
				listName = $( this ).text();
				result.append( " #" + ( index + 1 ) + listName );
			});

			$.each(jData, function(i){
				if(listName == jData[i].name){
					//設定config頁面
					mode = 'edit';
					setConfigValue(jData[i]);
				}
			});
			
			/*
			$('#content').load('config.html', function() {
				$( ".datepicker" ).datepicker();
				$('.timepicker').timepicker({});
				
				$('#content').css('width', '560px');
				$('#content').css('height', '');
				//$('#content').scrollbar();
				
			});
			*/
		}
	});
}

//設定config頁面輸入框內容
function setConfigValue(jList){
	$('#content').css('width', '560px');
	$('#content').css('height', '');
	$('#content').load('config.html', function() {
		setFormValidate();
		$('#intervalselect').decorselect();//TODO
		
		$( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
		$('.timepicker').timepicker({});
		
		/*
		$('#content').css('width', '560px');
		$('#content').css('height', '');
		$('#content').scrollbar();
		*/
		setConfigDisabled(true);
		
		//狀態確認
		if("true" == jList.status){
			$('#statuscheck').attr('checked', 'checked');
		}else{
			$('#statuscheck').attr('checked', false);
		}
		
		//名稱輸入
		$('#nameinput').val(jList.name);
		
		//程式路徑輸入
		$('#programinput').val(jList.program);
		//$("input:file").fileEveryWhere(0);
		
		//起始日期輸入
		var startstr = jList.start;
		//startstr = dbstrToPickerstr(startstr);
		startstr = startstr.substring(0,10);
		$('#startdate').val(startstr);
		//起始時間輸入
		$('#starttime').val(jList.start.substring(11, 16));
		
		//終止日期輸入&終止時間輸入
		if("null" != jList.end){
			$('#endcheck').attr('checked', 'checked');
			
			var endstr = jList.end;
			//endstr = dbstrToPickerstr(endstr);
			endstr = endstr.substring(0,10);
			$('#enddate').val(endstr);
			
			$('#endtime').val(jList.end.substring(11, 16));
		}else{
			$('#endcheck').attr('checked', false);
		}
		
		//循環時間設置
		if(0 != jList.interval){
			var interval_str = jList.interval.substring(0, 1);
			var interval_int = jList.interval.substring(1, jList.interval.length);
			
			$('#interval').attr('checked', 'checked');
			$('#intervalinput').val(interval_int);
			$('#intervalselect').val(interval_str).change();//TODO
			/*
			if(interval_str == ""){
				$('#intervalselect option:eq(0)').attr('selected','selected');
			}else if(interval_str == "m"){
				$('#intervalselect option:eq(1)').attr('selected','selected');
			}else if(interval_str == "h"){
				$('#intervalselect option:eq(2)').attr('selected','selected');
			}else if(interval_str == "d"){
				$('#intervalselect option:eq(3)').attr('selected','selected');
			}else if(interval_str == "w"){
				$('#intervalselect option:eq(4)').attr('selected','selected');
			}else if(interval_str == "M"){
				$('#intervalselect option:eq(5)').attr('selected','selected');
			}*/
		}else if(0 != jList.weekday){
			var weekday_int = parseInt(jList.weekday);
			$('#weekday').attr('checked', 'checked');
			
			if(1 == (weekday_int & 1)){
				$('#weekday0').attr('checked', 'checked');
			}
			if(2 == (weekday_int & 2)){
				$('#weekday1').attr('checked', 'checked');
			}
			if(4 == (weekday_int & 4)){
				$('#weekday2').attr('checked', 'checked');
			}
			if(8 == (weekday_int & 8)){
				$('#weekday3').attr('checked', 'checked');
			}
			if(16 == (weekday_int & 16)){
				$('#weekday4').attr('checked', 'checked');
			}
			if(32 == (weekday_int & 32)){
				$('#weekday5').attr('checked', 'checked');
			}
			if(64 == (weekday_int & 64)){
				$('#weekday6').attr('checked', 'checked');
			}
			
		}else if(0 != jList.date){
			$('#monthdate').attr('checked', 'checked');
			$('#dateinput').val(jList.date);
		}
		
		//按下Log紐
		$('#log').unbind();
		$('#log').click(function(){//TODO
			getLog(jList.id);
		});
		
		//按下編輯紐
		$('#edit').unbind();
		$('#edit').click(function(){
			if('edit' == mode){
				//if("true" == jList.status){
					setConfigDisabled(false);
				//}else{
					//setConfigDisabled(true);
				//}
			}
		});
		
		//按下Delete
		$('#delete').unbind();
		$('#delete').click(function(){
			if('edit' == mode){
				var closefunc = function(){};
				var checkfunc = function(){
						deleteList(jList.id);
					};
				alertDialog("DELETE this schedule ?!", true, closefunc, checkfunc);
			}
		});
		
		/*
		//當#statuscheck被click時, 決定輸入框是否開啟
		$('#statuscheck').click(function(){
			if('on' == $('input[name=statuscheck]:checked').val()){
				setConfigDisabled(false);
			}else{
				setConfigDisabled(true);
			}
		});*/
		
		configListener();
		
		//按下Confirm鍵, 送出資料
		$('#confirm').unbind();
		$('#confirm').click(function(){
			if( $("#scheduleform").valid()){
				var status = false;
				var start = "0000-00-00 00:00:00";
				var end = "0000-00-00 00:00:00";
				var interval = "0";
				var weekday = 0;
				var date = 0;
				
				//是否執行
				if('on' == $('input[name=statuscheck]:checked').val()){
					status = true;
				}
				
				//開始時間確定
				//start = pickerstrToDbstr($('#startdate').val()) +" "+ $('#starttime').val() +":00";
				start = $('#startdate').val() +" "+ $('#starttime').val() +":00";
				
				//終止時間確定
				if('on' == $('input[name=endcheck]:checked').val()){
					//end = pickerstrToDbstr($('#enddate').val()) +" "+ $('#endtime').val() +":00";
					end = $('#enddate').val() +" "+ $('#endtime').val() +":00";
				}			
				
				//循環資料確定
				if('interval' == $('input[name=circulation]:checked').val()){
					interval = $('#intervalselect').val() + $('#intervalinput').val();
				}else if('weekday' == $('input[name=circulation]:checked').val()){
					if('on' == $('input[id=weekday0]:checked').val()){
						weekday+=1;
					}
					if('on' == $('input[id=weekday1]:checked').val()){
						weekday+=2;
					}
					if('on' == $('input[id=weekday2]:checked').val()){
						weekday+=4;
					}
					if('on' == $('input[id=weekday3]:checked').val()){
						weekday+=8;
					}
					if('on' == $('input[id=weekday4]:checked').val()){
						weekday+=16;
					}
					if('on' == $('input[id=weekday5]:checked').val()){
						weekday+=32;
					}
					if('on' == $('input[id=weekday6]:checked').val()){
						weekday+=64;
					}		
				}else if('monthdate' == $('input[name=circulation]:checked').val()){
					date = $('#dateinput').val();
				}
				
				setConfigDisabled(true);
				
				//資料送出
				updateListContent(jList.id, $('#nameinput').val(), $('#programinput').val(), status, start, end, interval, weekday, date);
				/*
				alert(//$('#nameinput').val()+"  "+
						//$('#programinput').val()+"  "+
						//status+"  "+
						//$('#startdate').val()+"  "+
						//$('#endcheck').attr('checked')+"  "+
						//$('input[name=endcheck]:checked').val()+"  "+
						//$('#enddate').val()+"  "+
						$('input[name=circulation]:checked').val()+"  "+
						interval+"  "+
						weekday+"  "+
						date
					);
				*/
			}else{
				
			}
		});
		
		//按下Cancel鍵
		$('#cancel').unbind();
		$('#cancel').click(function(){
			//mode = 'none';
			setConfigDisabled(true);
			//$("label.error").remove();
			//$(".error").removeClass("error");
			setConfigValue(jList);
		});
		
	});
}

//判定config.html輸入框狀態
function configListener(){
	//當#endcheck被click時, 決定結束時間輸入框是否開啟
	$('#endcheck').click(function(){
		if('on' == $('input[name=endcheck]:checked').val()){
			$('#enddate').attr('disabled', false);
			$('#endtime').attr('disabled', false);
		}else{
			$('#enddate').attr('disabled', 'disabled');
			$('#endtime').attr('disabled', 'disabled');
		}
	});
	
	//當input[name=circulation]被click時, 決定 每個循環表單輸入框是否開啟
	$('input[name=circulation]').click(function(){
		if('interval' == $(this).val()){
			$('#intervalinput').attr('disabled', false);
			$('#intervalselect').attr('disabled', false).change();//TODO
			
			$('#weekday0').attr('disabled', 'disabled');
			$('#weekday1').attr('disabled', 'disabled');
			$('#weekday2').attr('disabled', 'disabled');
			$('#weekday3').attr('disabled', 'disabled');
			$('#weekday4').attr('disabled', 'disabled');
			$('#weekday5').attr('disabled', 'disabled');
			$('#weekday6').attr('disabled', 'disabled');
			
			$('#dateinput').attr('disabled', 'disabled');
		}else if('weekday'  == $(this).val()){
			$('#intervalinput').attr('disabled', 'disabled');
			$('#intervalselect').attr('disabled', 'disabled').change();//TODO
			
			$('#weekday0').attr('disabled', false);
			$('#weekday1').attr('disabled', false);
			$('#weekday2').attr('disabled', false);
			$('#weekday3').attr('disabled', false);
			$('#weekday4').attr('disabled', false);
			$('#weekday5').attr('disabled', false);
			$('#weekday6').attr('disabled', false);
			
			$('#dateinput').attr('disabled', 'disabled');
		}else if('monthdate'  == $(this).val()){
			$('#intervalinput').attr('disabled', 'disabled');
			$('#intervalselect').attr('disabled', 'disabled').change();//TODO
			
			$('#weekday0').attr('disabled', 'disabled');
			$('#weekday1').attr('disabled', 'disabled');
			$('#weekday2').attr('disabled', 'disabled');
			$('#weekday3').attr('disabled', 'disabled');
			$('#weekday4').attr('disabled', 'disabled');
			$('#weekday5').attr('disabled', 'disabled');
			$('#weekday6').attr('disabled', 'disabled');
			
			$('#dateinput').attr('disabled', false);
		}
	});
}

//設定config頁面輸入框狀態
function setConfigDisabled(flag){
	
	if(flag == true){
		$('#statuscheck').attr('disabled', 'disabled');
		
		$('#nameinput').attr('readonly', 'readonly');
		$('#programinput').attr('disabled', 'disabled');
		
		$('#startdate').attr('disabled', 'disabled');
		$('#starttime').attr('disabled', 'disabled');
		
		$('#endcheck').attr('disabled', 'disabled');
		$('#enddate').attr('disabled', 'disabled');
		$('#endtime').attr('disabled', 'disabled');
		
		$('#interval').attr('disabled', 'disabled');
		$('#weekday').attr('disabled', 'disabled');
		$('#monthdate').attr('disabled', 'disabled');
		
		$('#intervalinput').attr('disabled', 'disabled');
		$('#intervalselect').attr('disabled', 'disabled').change();//TODO
		
		$('#weekday0').attr('disabled', 'disabled');
		$('#weekday1').attr('disabled', 'disabled');
		$('#weekday2').attr('disabled', 'disabled');
		$('#weekday3').attr('disabled', 'disabled');
		$('#weekday4').attr('disabled', 'disabled');
		$('#weekday5').attr('disabled', 'disabled');
		$('#weekday6').attr('disabled', 'disabled');
		
		$('#dateinput').attr('disabled', 'disabled');
		
		$('#confirm').removeClass("button");
		$('#confirm').addClass("disable");
		
		$('#cancel').removeClass("button");
		$('#cancel').addClass("disable");
	}else{
		$('#statuscheck').attr('disabled', false);
		
		$('#nameinput').attr('readonly', false);
		$('#programinput').attr('disabled', false);
		
		$('#startdate').attr('disabled', false);
		$('#starttime').attr('disabled', false);
		
		$('#endcheck').attr('disabled', false);
		
		if('checked' == $('#endcheck').attr('checked')){
			$('#enddate').attr('disabled', false);
			$('#endtime').attr('disabled', false);
		}
		
		$('#interval').attr('disabled', false);
		$('#weekday').attr('disabled', false);
		$('#monthdate').attr('disabled', false);
		
		if('checked' == $('#interval').attr('checked')){		
			$('#intervalinput').attr('disabled', false);
			$('#intervalselect').attr('disabled', false).change();//TODO
		}else if('checked' == $('#weekday').attr('checked')){		
			$('#weekday0').attr('disabled', false);
			$('#weekday1').attr('disabled', false);
			$('#weekday2').attr('disabled', false);
			$('#weekday3').attr('disabled', false);
			$('#weekday4').attr('disabled', false);
			$('#weekday5').attr('disabled', false);
			$('#weekday6').attr('disabled', false);
		}else if('checked' == $('#monthdate').attr('checked')){
			$('#dateinput').attr('disabled', false);
		}
		
		$('#confirm').addClass("button");
		$('#confirm').removeClass("disable");
		
		$('#cancel').addClass("button");
		$('#cancel').removeClass("disable");
	}
}

//設定按鈕狀態
function setButtonDisabled(flag){
	if(flag == true){
		$('#delete').removeClass("button");
		$('#delete').addClass("disable");
		
		$('#edit').removeClass("button");
		$('#edit').addClass("disable");
		
		//$('#add').removeClass("button");
		//$('#add').addClass("disable");
		
		$('#log').removeClass("button");
		$('#log').addClass("disable");
	}else{
		$('#delete').addClass("button");
		$('#delete').removeClass("disable");
		
		$('#edit').addClass("button");
		$('#edit').removeClass("disable");
		
		//$('#add').addClass("button");
		//$('#add').removeClass("disable");
		
		$('#log').addClass("button");
		$('#log').removeClass("disable");
	}
}

//Database sting轉DatePicker string
function dbstrToPickerstr(str){
	var yearstr = str.substring(0, 4);
	var monthstr = str.substring(5, 7);
	var datestr = str.substring(8, 10);
	
	return str = monthstr+"/"+datestr+"/"+yearstr;
}

//DatePicker string轉Database sting
function pickerstrToDbstr(str){
	var monthstr = str.substring(0, 2);
	var datestr = str.substring(3, 5);
	var yearstr = str.substring(6, 10);
	
	return str = yearstr+"-"+monthstr+"-"+datestr;
}

//setTimeout('getList()',1000);