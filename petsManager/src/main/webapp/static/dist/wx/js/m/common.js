$(function(){

	/* --------------------- 公共组件 -------------------------- */
	// 点击关闭
	$(document).on('click', '.j-toggle .j-close', function(){
		$(this).parents(".j-toggle").remove();
	});
	//返回顶部
	$(".back_top").click(function(){
		$("html,body").animate({scrollTop : 0});
	});
	//普通切换
	//弹出提示框：点击切换显示隐藏，点击其他地方关闭
	function clickToggle(){
		var opt = {
			wp : '.j-modtoggle',
			hd : '.j-toggle-hd',
			bd : '.j-toggle-bd'
		}
		var wp = $(opt.wp);
		wp.each(function(i, el){
			var _this = $(el);
			var hd = _this.find(opt.hd);
			var bd = _this.find(opt.bd);
			hd.click(function(){
				bd.toggle();
			});
		});
		//点击其他地方关闭
		$(document).click(function(e){
			var target = e.target;
			var toggleLength = $(target).parents(".j-modtoggle").length
			var isIn = toggleLength ? true : false;
			if(!isIn && $(".j-toggle-bd").filter(":visible").length > 0 ){
				e.stopPropagation();
				e.preventDefault();
				$(".j-toggle-bd").hide();
			}
		});
	}
	clickToggle();
	// 视频弹出
	// 复选框联动：全选，全不选效果
	function linkage(){
		var opt = {
			wp : '.j-modlinkage',
			item : '.j-linkage-item',
			all : '.j-linkage-all'
		}
		var wp = $(opt.wp);
		wp.each(function(i, el){
			var _this = $(el);
			var item = _this.find(opt.item);
			var all = _this.find(opt.all);
			item.change(function(){
				var isAllChecked = !item.not(":checked").length;
				if(isAllChecked){
					all.prop("checked", true);
				}else{
					if(all.prop("checked")){
						all.prop("checked", false);
					}
				}
			});
			all.change(function(){
				var isChecked = $(this).is(":checked");
				item.each(function(){
					$(this).prop("checked", isChecked);
				});
			});
		})
	}
	linkage();
	//页面侧滑
	function slidePage(){
		var opt = {
			wp : '.j-slidepage-wp',
			aside : '.j-slidepage-aside',
			asideItem : '.j-slidepage-aside-item',
			asideClose : '.j-slidepage-aside-close',
			main : '.j-slidepage-main',
			handler : '.j-slidepage-handler',
			value : '.j-slidepage-value'
		}
		var wp = $(opt.wp);
		wp.each(function(i, el){
			var _this = $(el);
			var aside = _this.find(opt.aside);
			var asideItem = aside.find(opt.asideItem);
			var asideClose = aside.find(opt.asideClose);
			var main = _this.find(opt.main);
			var handler = main.find(opt.handler);

			// $("html,body").css({"overflowX":"hidden"});//隐藏横向滚动条

			handler.click(function(){
				var target = $(this).data("target");
				var targetItem = asideItem.filter("[data-target="+target+"]");
				if(targetItem.length == 1){
					_this.addClass("active");
					targetItem.show().siblings().hide();
					$("html,body").scrollTop(0);
					$("body").addClass("ov-h");
				}
			});

			asideClose.click(function(){
				var _close = $(this);
				var parentItem = _close.parents(".j-slidepage-aside-item");
				_this.removeClass("active");
				$("body").removeClass("ov-h");

				// 关闭时赋值给展示文字
				// 无关键字的为普通字符串，普通字符串应避免出现关键字
				// # -> 获得name为#后字母的节点的值或html内容
				var value = $(this).data("value");
				if(value){
					// data-value以#开头,则改变value值
					var isDomValue = value.indexOf("#");
					if(isDomValue == 0){
						// 多个name以逗号分隔
						var names = value.split(",");
						var foo = '';
						$.each(names, function(i,v){
							var dname = v.substring(1);
							var dom = parentItem.find("[name="+dname+"]");
							var domValue = '';
							if(dom.attr("type") == 'radio'){
								if(dom.filter(":checked").length>0){
									domValue = dom.filter(":checked").data("value");
								}
							}else{
								domValue = dom.val() || dom.html();
							}
							foo += "&nbsp;&nbsp;";
							foo += domValue;
						});
						value = foo;
					}
					// 改变展示的字符串
					var target = parentItem.data("target");
					var valueText = handler.filter("[data-target="+target+"]").find(opt.value);
					valueText.html(value);
				}
			});
		});
	}
	slidePage();
	// 数量选择
	// 滚动固定顶部
	function sticky(){
		var $stickywp = $(".j-mod-sticky");
		var $sticky = $stickywp.children().eq(0);
		if($sticky.length == 0) return;
		$(window).scroll(function(){
			var stop = $("body").scrollTop();
			var otop = $stickywp.offset().top;
			console.log(stop,otop)
			if(stop >= otop){
				$sticky.addClass("fixed");
			}else{
				if($sticky.hasClass("fixed")){
					$sticky.removeClass("fixed");
				}
			}
		});
	}
	sticky();

	
	/* --------------------- 首页 -------------------------- */
	// 头部菜单
	$(".index-head .menuHandler").click(function(){
		$(".index-head-menu").toggleClass("active");
		$(this).toggleClass("active");
		$("body").toggleClass("ov-h");
	});
	$(".index-head-menu .menu-item-dropdown .menu-link").click(function(){
		var li = $(this).parent(".menu-item");
		li.toggleClass("active");
	})


});