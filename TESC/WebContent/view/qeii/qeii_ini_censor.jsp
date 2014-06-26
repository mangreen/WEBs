<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@pagepageEncoding="UTF-8"%>
<%response.setContentType("text/html;charset=UTF-8");request.setCharacterEncoding("UTF-8");%>

<style type="text/css">
			#gallery { float: left; width: 65%; min-height: 12em; } * html #gallery { height: 12em; } /* IE6 */
			.gallery.custom-state-active { background: #eee; }
			.gallery li { float: left; width: 96px; padding: 0.4em; margin: 0 0.4em 0.4em 0; text-align: center; }
			.gallery li h5 { margin: 0 0 0.4em; cursor: move; }
			.gallery li a { float: right; }
			.gallery li a.ui-icon-zoomin { float: left; }
			.gallery li img { width: 100%; cursor: move; }

			#trash { float: right; width: 32%; min-height: 18em; padding: 1%;} * html #trash { height: 18em; } /* IE6 */
			#trash h4 { line-height: 16px; margin: 0 0 0.4em; }
			#trash h4 .ui-icon { float: left; }
			#trash .gallery h5 { display: none; }
</style>


<table style="width: 562px" cellspacing="0" cellpadding="0">
		<tr>
			<td style="width: 142px" class="inlist">初審結果</td>
			<td class="inlist3" colspan="3">
				<input name="Radio4" type="radio" checked="checked" value="V1" />通過&nbsp; 
				<input name="Radio5" type="radio" checked="checked" value="V1" />未通過<span class="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;勾選通過將選擇評審</span></td>
		</tr>
		<tr>
			<td style="width: 142px; height: 130px;" class="inlist2" align="right">評審選擇</td>
			
			<td style="padding:6px 6px 6px 6px;">
				<div id="trash" class="ui-widget-content ui-state-default" style="width:100%;">
					<h4 class="ui-widget-header" style="height: 20px;padding:6px 4px 0px 4px;"><span class="ui-icon ui-icon-trash">Trash</span> Trash</h4>
				</div>
			</td>
			
			<td style="padding:6px 6px 6px 6px;">

			<ul id="gallery" class="gallery ui-helper-reset ui-helper-clearfix">
				<li class="ui-widget-content ui-corner-tr" jid="1">
					<h5 class="ui-widget-header">High Tatras</h5>

				</li>
				<li class="ui-widget-content ui-corner-tr" jid="2">
					<h5 class="ui-widget-header">High Tatras 2</h5>
				</li>
				<li class="ui-widget-content ui-corner-tr" jid="3">
					<h5 class="ui-widget-header">High Tatras 3</h5>
				</li>
				<li class="ui-widget-content ui-corner-tr">
					<h5 class="ui-widget-header">High Tatras 4</h5>
					<img src="/demos/droppable/images/high_tatras4_min.jpg"  alt="On top of Kozi kopka" width="96" height="72" //>
					<a href="images/high_tatras4.jpg" title="View larger image" class="ui-icon ui-icon-zoomin">View larger</a>
					<a href="link/to/trash/script/when/we/have/js/off" title="Delete this image" class="ui-icon ui-icon-trash">Delete image</a>
				</li>
				<li class="ui-widget-content ui-corner-tr">
					<h5 class="ui-widget-header">High Tatras 3</h5>
				</li>
				<li class="ui-widget-content ui-corner-tr">
					<h5 class="ui-widget-header">High Tatras 3</h5>
				</li>
				<li class="ui-widget-content ui-corner-tr">
					<h5 class="ui-widget-header">High Tatras 3</h5>
				</li>
			</ul>
			
			</td>
	
		</tr>
		<tr>
			<td style="width: 142px; height: 130px;" class="inlist2" align="right">評語以及建議</td>
			<td style="height: 130px;" colspan="3">
				<textarea id="ini_censor_command" name="ini_censor_command" style="width: 95%; height: 179px" class="input"></textarea>
			</td>
			<input id="judgelist" name="judgelist" type="text" style="width: 325px; height: 67px" />
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td align="center" style="width: 142px"></td>
			<td align="center" style="width: 207px"></td>
			<td align="center" colspan="2">
				<input name="qeii_ini_censor_bt" type="button" value="送出" class="button" onclick="judgesubmit();"/>  
			  &nbsp;<input name="Button1" type="button" value="取消" class="button" />
			</td>
		</tr>
</table>

<script type="text/javascript">
			$(function() {
				// there's the gallery and the trash
				var $gallery = $('#gallery'), $trash = $('#trash');

				// let the gallery items be draggable
				$('li',$gallery).draggable({
					cancel: 'a.ui-icon',// clicking an icon won't initiate dragging
					revert: 'invalid', // when not dropped, the item will revert back to its initial position
					containment: $('#demo-frame').length ? '#demo-frame' : 'document', // stick to demo-frame if present
					helper: 'clone',
					cursor: 'move'
				});

				// let the trash be droppable, accepting the gallery items
				$trash.droppable({
					accept: '#gallery > li',
					activeClass: 'ui-state-highlight',
					drop: function(ev, ui) {
						deleteImage(ui.draggable);
					}
				});

				// let the gallery be droppable as well, accepting items from the trash
				$gallery.droppable({
					accept: '#trash li',
					activeClass: 'custom-state-active',
					drop: function(ev, ui) {
						recycleImage(ui.draggable);
					}
				});

				// image deletion function
				var recycle_icon = '<a href="link/to/recycle/script/when/we/have/js/off" title="Recycle this image" class="ui-icon ui-icon-refresh">Recycle image</a>';
				function deleteImage($item) {
					$item.fadeOut(function() {
						var $list = $('ul',$trash).length ? $('ul',$trash) : $('<ul class="gallery ui-helper-reset"/>').appendTo($trash);

						$item.find('a.ui-icon-trash').remove();
						$item.append(recycle_icon).appendTo($list).fadeIn(function() {
							$item.animate({ width: '48px' }).find('img').animate({ height: '36px' });
						});
					});
				}

				// image recycle function
				var trash_icon = '<a href="link/to/trash/script/when/we/have/js/off" title="Delete this image" class="ui-icon ui-icon-trash">Delete image</a>';
				function recycleImage($item) {
					$item.fadeOut(function() {
						$item.find('a.ui-icon-refresh').remove();
						$item.css('width','96px').find('img').css('height','72px').end().appendTo($gallery).fadeIn();
					});
				}

				// image preview function, demonstrating the ui.dialog used as a modal window
				function viewLargerImage($link) {
					var src = $link.attr('href');
					var title = $link.siblings('img').attr('alt');
					var $modal = $('img[src$="'+src+'"]');

					if ($modal.length) {
						$modal.dialog('open')
					} else {
						var img = $('<img alt="'+title+'" width="384" height="288" style="display:none;padding: 8px;" />')
							.attr('src',src).appendTo('body');
						setTimeout(function() {
							img.dialog({
									title: title,
									width: 400,
									modal: true
								});
						}, 1);
					}
				}

				// resolve the icons behavior with event delegation
				//$('ul.gallery > li').click(function(ev) {
				//	var $item = $(this);
				//	var $target = $(ev.target);
				//	if ($target.is('a.ui-icon-trash')) {
				//		deleteImage($item);
				//	} else if ($target.is('a.ui-icon-zoomin')) {
				//		viewLargerImage($target);
				//	} else if ($target.is('a.ui-icon-refresh')) {
				//		recycleImage($item);
				//	}
				//	return false;
				//});		
			});
			
	function judgesubmit(){
		$("#trash ul li").each(
			alert($(this).attr("jid"))
		);
	}
</script>
