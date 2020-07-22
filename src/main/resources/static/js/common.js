$(function(){
	// left sldemenu
  $.sidebarMenu($('.sidebar-menu'));
	
  // left menu toggle	
	$(".menu_click").on("click", function(){
		if ( $(this).hasClass("folded") === false ) { 
			$(".cate_wrap").animate({left:"-220"}, 500, "easeInOutQuint");
			$(".ContentWrap").animate({marginLeft:0}, 500, "easeInOutQuint");
			$(this). find('img').attr("src", getContextPath() + "/images/icon_menu.png");
			$(this).addClass("folded");
		} else { 
			$(".cate_wrap").animate({left:"0"}, 500, "easeInOutQuint");
			$(".ContentWrap").animate({marginLeft:220}, 500, "easeInOutQuint");
			$(this). find('img').attr("src", getContextPath() + "/images/icon_menu2.png");
			$(this).removeClass("folded");
		}
	});

	// Main tabmenu
		$(".tab_content").hide();
		$(".tab_content:first").show();

		$("ul.tabs li").click(function () {
			$("ul.tabs li").removeClass("active").css("", "");
			$(this).addClass("active").css("", "");
			$(".tab_content").hide()
			var activeTab = $(this).attr("rel");
			$("#" + activeTab).fadeIn()
		});
	
	


	// custom scrollbar
	$(".ContentWrap, .ManageWrap >  div:first-child ").niceScroll({cursorcolor:"rgba(216,226,233,1)", cursorwidth:6, cursorborder: "1px solid rgba(255,255,255,0)"});
		
});


