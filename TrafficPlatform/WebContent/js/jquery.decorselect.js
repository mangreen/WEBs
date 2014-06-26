(function($) {
	var box_class = "select_box";
	
	var info_class = "tag_select";
	var info_class_open = "tag_select_open";
	var info_class_hover = "tag_select_hover";
	var info_class_disabled = "tag_select_disabled";
	
	var ul_class = "tag_options";
	
	var li_class = "liopt";
	var li_class_selected = "liopt_selected";
	var li_class_hover = "liopt_hover";
	
	$.fn.decorselect = function(params){
		
		params = $.extend( {
			style: 'orange',			//樣式
			timeout: 100,				//查看disabled狀態時間
			mode: "none",				//狀態
			value:""
		}, params);
		
		return this.each(function(){
			
			var $$ = $(this);
			var $id =$$.attr("id");
			var width = $(this).css("width");
			var height = $(this).css("height");
			var attribute = $(this).attr("disabled");
			var value = $(this).val();
			
			//width = width.substring(0,width.length-2);
			height = height.substring(0,height.length-2);
			height = parseInt(height)+2;
			
			$$.css("display", "none");//TODO
			
			/*檢查是否存在, firefox會有問題*/
			//if($("#select_"+$id).length < 1){//TODO
				
				/*建立新的select*/
				$$.after('<div id="select_'+$id+'" class="'+params.style+' select_box" ></>');
				
				var select_box = $('#select_'+$id);
				select_box.append('<div id="select_info_'+$id+'" class="'+params.style+' tag_select"></div>');
				select_box.css({
					"width":width,
					"height":height
				});
				
				var select_info = $('#select_info_'+$id);
				select_info.css({
					"cursor":"pointer",
					"width": width,
					"height":height
				});
				select_info.after('<ul id="options_'+$id+'" class="'+params.style+' tag_options"></ul>');
				
				var select_ul = $('#options_'+$id);
				select_ul.css({
					"position":"absolute",
					"display":"none",
					"zIndex":"999"
				});
				
				var select_li;
				reOptions();
				
				changeAttribute();
				
				onListener_SelectInfo();
				onListener_SelectLi();
				
				/*原select的attribute變更事件判斷*/
				$$.change(function(){//TODO
					/*動態新增option事件處理
					select_info.text("");
					select_ul.html("");
					reOptions();
					onListener_SelectLi();*/
					
					/*動態變更disabled事件處理*/
					changeAttribute();
					
					/*動態指定value事件處理*/
					if($$.children('option[value="'+$$.val()+'"]').text() != select_info.text()){
						select_info.text($$.children('option[value="'+$$.val()+'"]').text());
						select_li.removeClass(li_class_selected);
						select_ul.children('li:contains("'+select_info.text()+'")').addClass(li_class_selected);
					}
				});
				
				/*設置循環, 不斷詢問原select狀態*/
				/*
				var temp = {
						attr: "",
						val: ""
				};
				//if(typeof (this.onpropertychange) == "object"){//TODO
				//	$$.bind("propertychange", changeAttribute);
				//}else if($.browser.mozilla){
				//	$$.bind("DOMAttrModified", changeAttribute);
				//}else {
					setInterval(onAttrChange, params.timeout);
				//}
				function onAttrChange() {//TODO
					var attr = $$.attr("disabled");
					var val = $$.val();
					var changed = false;
					
					if("" != temp.attr || "" != temp.val){
						if(attr != temp.attr){
							temp.attr = attr;
							changed = true;
						}
						
						if(val != temp.val){
							temp.val = val;
							changed = true;
						}
					}else{
						temp.attr = attribute;
						temp.val = value;
					}
					
					if(changed) {
						changeAttribute();
						select_info.text($$.children('option[value="'+$$.val()+'"]').text());
					}
				}*/
				
				function reOptions(){
					/*將原來的select中的option資料讀進新的select, 建立新option*/
					var options = $$.children("option");
					
					options.each(function(){
						var option_text = $(this).text();
						
						if($(this).attr("selected")){
							select_info.text(option_text);
							select_ul.append('<li class="'+params.style+' '+li_class+' '+li_class_selected+'">'+option_text+'</li>');
						}else{
							select_ul.append('<li class="'+params.style+' '+li_class+'">'+option_text+'</li>');
						}
					});
					
					select_li = select_ul.find("li");
					select_li.css({
						"cursor":"pointer",
						"width":width,
						"height":height
					});
				}
				
				//掛上Select_Info的event listener
				function onListener_SelectInfo() {
					
					/*新select info的事件判斷*/
					select_info.mouseover(function(){
						if("none" == params.mode){
							select_info.addClass(info_class_hover);
						}
					});
					
					select_info.mouseout(function(){
						if("none" == params.mode){
							select_info.removeClass(info_class_hover);
						}
						
						$("body").click(function(){//當非disabled狀態時, 點擊其他區域關閉select option顯示
							if("disabled" != params.mode){
								params.mode = "none";
								select_ul.css("display","none");
								select_info.removeClass(info_class_hover);
								select_info.removeClass(info_class_open);
							}
						});
					});
					
					select_info.click(function(){
						if("none" == params.mode){
							params.mode = "open";
							$("body").unbind();
							select_ul.css("display","");
							//select_info.removeClass(info_class_hover);
							select_info.addClass(info_class_open);
						}else if("open" == params.mode){
							params.mode = "none";
							$("body").unbind();
							select_ul.css("display","none");
							select_info.removeClass(info_class_open);
						}
					});
					
					
				}
				
				//掛上Select_Li的event listener
				function onListener_SelectLi(){
					select_li = select_ul.find("li");
					
					/*新option的事件判斷*/
					select_li.mouseover(function(){
					    $(this).addClass(li_class_hover);
					});
	
					select_li.mouseout(function(){				
						$(this).removeClass(li_class_hover);
					});
					
					select_li.click(function(){
						clickOptions($(this).text());
						select_li.attr("class",params.style+' '+li_class);	
						$(this).attr("class",params.style+' '+li_class+' '+li_class_selected);
					});
				}
				
				//新select, 按下option的處理
				function clickOptions(element){
					select_info.text(element);//變更select info的顯示值
					
					/*變更原select之值, 與選中狀態*/
					//$('#'+$id+' option').attr('selected',false);
					//$('#'+$id+' option:contains("'+element+'")').attr('selected','selected');
					$$.val($$.children('option:contains("'+element+'")').val());
					
					select_ul.css("display","none");//關閉選單
				}
				
				//根據元select狀態, 決定新select的顯示模式
				function changeAttribute(){
					
					if("disabled" == $$.attr("disabled")){
						select_info.css("cursor","default");
						select_info.addClass(info_class_disabled);
						params.mode = "disabled";
					}else{
						select_info.css("cursor","pointer");
						select_info.removeClass(info_class_disabled);
						params.mode = "none";
					}
				}
			/*	
			}else{//TODO
				
				if("disabled" == params.mode){
					$$.attr("disabled", "disabled");
				}else{
					$$.removeAttr("disabled");
				}
				changeAttribute();
				
				//options.each(function(){
				//	if(param.value == $(this).val()){
				//		select_info.text(param.value);
				//	}
				//});
			}
			*/
		});
	};
})(jQuery);