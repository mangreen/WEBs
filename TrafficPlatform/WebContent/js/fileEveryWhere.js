/*
* file everywhere - 瀏覽器通用文件上傳
* copyright->flowerszhong
* flowerszhong@gmail.com
* http://www.cnblogs.com/flowerszhong/
*/
(function($) {
    $.fn.fileEveryWhere = function(options) {
        var defaults = {
            WrapWidth: 300,
            WrapHeight: 30,
            ButtonWidth: 60,
            ButtonHeight: 28,
            ButtonText: "Browse...",
            TextHeight: 28,
            TextWidth: 240
        };
        var options = $.extend(defaults, options);
        var browser_ver = $.browser.version.substr(0, 1);
        var displayMode = ($.browser.msie && browser_ver <= "7") ? "inline" : "inline-block";
        return this.each(function() {
            //創建包含，設置為相對定位
            var wrapper = $("<div class='fileWraper'>")
                            .css({
                                "width": options.WrapWidth + "px",
                                "height": options.WrapHeight + "px",
                                "display": displayMode,
                                "zoom": "1",
                                "position": "relative",
                                "overflow": "hidden",
                                "z-index":"1"
                            });
            //創建文本輸入框，用於存放上傳文件名稱
            var text = $('<input class="filename" type="text" />')
                             .css({
                            	 "position": "absolute",
                                 "bottom": "0",
                                 "width": options.TextWidth + "px",
                                 "heigth": options.TextHeight + "px"
                             });
            //創建瀏覽按鈕
            //var button = $('<input class="btnfile" type="button" />').val(options.ButtonText);
            var button = $('').val(options.ButtonText);
            
            $(this).wrap(wrapper).parent().append(text, button);
            $(this).css({
                "position": "absolute",
                "top": "0",
                "left": "0",
                "z-index": "2",
                "height": options.WrapHeight + "px",
                "width": options.WrapWidth + "px",
                "cursor": "pointer",
                "opacity": "0.0",
                "outline":"0",
                "filter": "alpha(opacity:0)"
            });
            if ($.browser.mozilla) { $(this).attr("size", 1 + (options.WrapWidth - 85) / 6.5); }
            $(this).bind("change", function() {
            	alert($(this).val());
                text.val($(this).val());
            });
        });
    };
})(jQuery);