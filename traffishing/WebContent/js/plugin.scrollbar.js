/************ Plugin JQuery ************* */
/* R�alis� par Creamama - Matthieu L�orat */
/* ************ juillet 2010 ************ */ 
/* ******** http://www.creamama.fr ****** */

/********** NECESSITE ************/
/* ******************************** 
jquery-1.4.2.min.js
ui.core-1.7.2.js
ui.draggable-1.7.2.js
jquery.mousewheel.min.js
******************************** */

(function($) {
	$.fn.scrollbar = function(params) {
		// Fusionner les param�tres par d�faut et ceux de l'utilisateur
		params = $.extend( {
			taille_englobe: 'auto',			//Taille de l'espace visible - /!\ Doit �tre un nombre ou 'auto'
			taille_scrollbar: "auto",		//scrollBar的高度
			taille_bouton: 40,				//button的高度
			pas:75,							//Pas du scroll molette - /!\ Doit �tre un nombre ou 'auto'
			molette: true,					//D�tection du scroll molette - /!\ true ou false
			drag: true,						//Bouton de la scrollbar d�placable � la souris - /!\ true ou false
			debug: true,					//Afficher la console de debug - /!\ true ou false
			style: 'basic',					//Choix des styles - /!\ Par d�fault, il n'y a que le style 'basic'
			position:'droite',				//Position de la scrollBar - /!\ 'gauche' ou 'droite'
			alignement_scrollbar:'haut',	//Alignement de la scrollBar. Utilis� uniquement si elle � une taille inf�rieur � celle de taille_englobe
			orientation: 'vertical',		//Orientation du contenu, 'vertical' ou 'horizontal'
			marge_scroll_contenu: 5,		//scrollBar與內容區塊的距離
			largeur_scrollbar:5				//scrollBar的寬度
		}, params);
		
		return this.each(function() {
			var $$ = $(this);
			var taille_englobe_init = params.taille_englobe;
			var taille_scrollbar_init = params.taille_scrollbar;
			
			//Fonction de calcul de position top maximum du contenu
			function calcul_contenu_top_max(){
				return  params.taille_englobe - taille_contenu ;
			}
			
			//Fonction de calcul de position top maximum du bouton
			function calcul_bouton_top_max(){
				return params.taille_scrollbar - params.taille_bouton;
			}
			
			//處理bouton的位移
			function deplacement_bouton(info_top_contenu){
				//On calcul la nouvelle position du bouton
				var depl_bouton = (info_top_contenu/calcul_contenu_top_max())*(calcul_bouton_top_max());
				//On v�rifie que ca d�borde pas en haut
				if(depl_bouton < 0){depl_bouton = 0;}
				//On v�rifie que ca d�borde pas en bas
				if(depl_bouton > calcul_bouton_top_max()){depl_bouton = calcul_bouton_top_max();}
				$$.find('.bouton').css({'top':depl_bouton+"px"});
			}
			
			//內容移動
			function deplacement_contenu(info_top_bouton){
				//On calcul la nouvelle position du contenu
				var depl_contenu = (info_top_bouton/calcul_bouton_top_max())*(calcul_contenu_top_max());
				//On v�rifie que ca d�borde pas en haut
				if (depl_contenu > 0){depl_contenu = 0;}
				//On v�rifie que ca d�borde pas en bas
				if (depl_contenu < calcul_contenu_top_max()){depl_contenu = calcul_contenu_top_max();}
				$$.find('.englobe').css({'top':depl_contenu+"px"});
			}
			
			function styler_scrollbar(position,orientation){
				var type_marge_position;
				var marge_position;
				var marge_orientation;
				switch (position){
					case 'droite':
						$$.find('.englobe').after('<div class="scrollbar"><div class="bouton">&nbsp;</div></div>');
						$$.find('.scrollbar').css({'margin-left':params.marge_scroll_contenu+'px'});
					break;
					case 'gauche':
						$$.find('.englobe').before('<div class="scrollbar"><div class="bouton">&nbsp;</div></div>');
						$$.find('.scrollbar').css({'margin-right':params.marge_scroll_contenu+'px'});
					break;
				}
				switch (orientation){
					case 'haut':
						$$.find('.scrollbar').css({'margin-top':'0px'});
					break;
					case 'centre':
						var marge = (params.taille_englobe -params.taille_scrollbar)/2;
						$$.find('.scrollbar').css({'margin-top':marge+'px'});
					break;
					case 'bas':
						var marge = params.taille_englobe -params.taille_scrollbar;
						$$.find('.scrollbar').css({'margin-top':marge+'px'});
					break;
				}
			}
			var padTop=0;
			var padBot=0;
			//計算內容的高度
            var taille_contenu = $$.height()+32;
			/*
			var taille_contenu = 32;
			$('.iA').each(function(i){
				taille_contenu += $(this).outerHeight()+4;
		    });*/
			
            //計算高度
			function calcul_hauteur_auto(){
				if(params.taille_englobe == "auto"){
					padTop = $$.css('padding-top');
					padTop = padTop.substring(0,padTop.length-2);
					
					padBot = $$.css('padding-bottom');
					padBot = padBot.substring(0,padBot.length-2);
					
					//params.taille_englobe = $$.height()-padBot-padTop;//TODO
					params.taille_englobe = getBrowserHeight()*0.75;
				}else{return false;}
			}
			calcul_hauteur_auto();
			
			//alert(taille_contenu+" & "+params.taille_englobe);
			//La hauteur de l'espace visible est la hauteur de la fenetre du navigateur si taille_englobe="auto"
			
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
			
			//數值控制
			function controle_donnee(){
				calcul_hauteur_auto();
				//La taille du contenu doit �tre sup�rieur � celle de l'espace visible (taille_englobe)
				if(taille_contenu > params.taille_englobe){
					//La hauteur de la scroll bar est �gale � la hauteur de "englobe" si hauteur_srollbar="auto"
					if(params.taille_scrollbar == "auto"){
						params.taille_scrollbar = params.taille_englobe;
					}
					
					//La taille de la scrollbar doit �tre inf�rieur ou �gale � la taille de taille_englobe
					if(params.taille_scrollbar > params.taille_englobe){						
						params.taille_scrollbar = params.taille_englobe;	
					}
					return true;
				}else{return false;}		
			}
			
			//Si la hauteur du contenu est sup�rieur � la hauteur de l'espace visible
			if(controle_donnee()){
				//Au redimensionnement de la fenetre
				//N'est concern� par cette fonction que les �l�ments en 'auto'
				/*
				window.onresize = function() {
					if(taille_englobe_init == "auto"){
						params.taille_englobe = $(window).height()-40-padBot-padTop;
						$$.css({'height':params.taille_englobe+'px'});
						if(taille_scrollbar_init == "auto"){
							params.taille_scrollbar = params.taille_englobe;
							$('#scrollbar').css({'height':params.taille_scrollbar+'px'});
							deplacement_bouton($("#englobe").css('top').substring(0,$("#englobe").css('top').length-2));
							if(params.debug){affiche_position();}
						}
					}	
				};*/

				//calcul des largeurs
				var tempW = $$.width();
				var tempH=$$.height();
				$$.css({'width':params.marge_scroll_contenu+params.largeur_scrollbar+tempW+'px'});
				//On construit une div autour du contenu, mais � l'int�rieur de la div
				$$.wrapInner('<div class="englobe"></div>');
				$$.css({'height':params.taille_englobe+'px','overflow':'hidden'});
				$$.find('.englobe').css({'top':0+'px','float':'left','position':'relative','width':tempW+'px'});	
				$$.css({'padding':5+'px'});
				
				//On construit la scrollBar
				styler_scrollbar(params.position,params.alignement_scrollbar);
				
				$$.append('<div class="clear"></div>');
				$(".clear").css({'clear':'both'});
				
				
				switch (params.style) {
					case 'basic':
						//Style de la scrollBar
						$$.find('.scrollbar').css({'width':params.largeur_scrollbar+'px',
											'float':'left',
											'height':params.taille_scrollbar+'px',
											'background':'#dddddd'		
						});
				
						//Style du bouton de la scrollBar
						$$.find('.bouton').css({'width':params.largeur_scrollbar+'px',
										'height':params.taille_bouton+'px',
										//'background':'#cda042',
										'background':'#3399cc',
										'top':0+'px',
										'cursor':'pointer'
										});
					break;
				}

				
				
				//Si le drag du bouton est activ�(true)
				if(params.drag){
					$$.find('.bouton').draggable({ 
						containment: 'parent',
						axis: 'y',
						start:function(){},
						drag: function(event, ui) {
							//ui.position.top est la valeur renvoy� par le plugin JQuery UI
							deplacement_contenu(ui.position.top);
							//if(params.debug){affiche_position();}
						},
						stop: function(){}
					});
				}
				
				if(params.molette){
					
					$$.mousewheel(function(event, delta) {
						
						//On r�cup�re la position du contenu
						var top_contenu = $$.find('.englobe').css('top');
						
						//On enl�ve le 'px' et on le convertit en entier pour pouvoir le manipuler
						top_contenu = parseInt( top_contenu.substring(0,(top_contenu.length-2)) );
						
						//On r�cup�re la position du bouton
						var top_bouton = $$.find('.bouton').css('top');
						//On enl�ve le 'px' et on le convertit en entier pour pouvoir le manipuler
						top_bouton = parseInt( top_bouton.substring(0,(top_bouton.length-2)) );

						//Si le delta est positif, c'est � dire que l'on "pousse" la molette
						if (delta > 0) {							
							top_contenu = top_contenu + params.pas;
							//On v�rifie que l'on n'a pas atteint le haut du contenu
							if(top_contenu > 0){top_contenu = 0;}
							$$.find('.englobe').css({'top':top_contenu+"px"});
						//Si le delta est n�gatif, c'est � dire que l'on "ram�ne" la molette
						}else if (delta < 0){							
							top_contenu = top_contenu - params.pas;
							//On v�rifie que l'on n'a pas atteint le bas du contenu
							if(top_contenu < calcul_contenu_top_max()){top_contenu = calcul_contenu_top_max();}
							$$.find('.englobe').css({'top':top_contenu+"px"});							
						}
						
						//calcul de d�placement du bouton					
						deplacement_bouton(top_contenu);
						
						//if(params.debug){affiche_position();}
						
					});
				}
				
				//Affiche la console de debug
				if(params.debug){
					/*
					$$.after('<div id="debug"></div>');
					$('#debug').css({'position':'fixed','top':'200px','right':'50px', 'border':'2px solid #EF9700','background':'#FFDC9F','padding':'0px'})				
					.append('<p id="hauteurcontenu">Hauteur du contenu:'+taille_contenu+'</p>')
					.append('<p id="hauteurenglobe">Hauteur de l\'espace visible:'+params.taille_englobe+'</p>')
					.append('<p id="hauteurscrollbar">Hauteur du srcoll:'+params.taille_scrollbar+'</p>')
					.append('<p id="hauteurbouton">Hauteur du bouton:'+params.taille_bouton+'</p>')
					.append('<p id="topcontenu">Top du contenu:'+$$.css('top')+'</p>')
					.append('<p id="topbouton">Top du bouton:'+$('#bouton').css('top')+'</p>')
					.append('<p id="topmaxbouton">Top max du bouton:'+calcul_contenu_top_max()+'</p>')
					.append('<p id="topmaxcontenu">Top max du contenu:'+calcul_bouton_top_max()+'</p>')
					.children().css({'margin':'0','padding':'5px'});
					*/
					//Fonction de mise � jour des infos de la console de debug
					function affiche_position(){
						$("#hauteurcontenu").html('Hauteur du contenu:'+taille_contenu);
						$("#hauteurenglobe").html('Hauteur de l\'espace visible:'+params.taille_englobe);
						$("#hauteurscrollbar").html('Hauteur du srcoll:'+params.taille_scrollbar);
						$("#hauteurbouton").html('Hauteur du bouton:'+params.taille_bouton);
						$("#topcontenu").html('Top du contenu:'+$('#englobe').css('top'));
						$("#topbouton").html('Top du bouton:'+$('#bouton').css('top'));
						$("#topmaxbouton").html('Top max du bouton:'+calcul_contenu_top_max());
						$("#topmaxcontenu").html('Top max du contenu:'+calcul_bouton_top_max());
					}
					
				}
				
			}else{
				//alert('scrollbar fail');
				/*
				//calcul des largeurs
				var tempW = $$.width();
				$$.css({'width':params.marge_scroll_contenu+params.largeur_scrollbar+tempW+'px'});
				//On construit une div autour du contenu, mais � l'int�rieur de la div
				$$.wrapInner('<div id="englobe"></div>');
				$$.css({'height':params.taille_englobe+'px','overflow':'hidden'});
				$("#englobe").css({'top':0+'px','float':'left','position':'relative','width':tempW+'px'});	
				
				//On construit la scrollBar
				styler_scrollbar(params.position,params.alignement_scrollbar);
				
				$$.append('<div class="clear"></div>');
				$(".clear").css({'clear':'both'});
				
				
				switch (params.style) {
					case 'basic':
						//Style de la scrollBar
						$('#scrollbar').css({'width':params.largeur_scrollbar+'px',
											'float':'left',
											'height':650+'px',
											'background':'#ebd9b3'		
						});
					break;
				}
				*/
			}
        });
	};
})(jQuery);