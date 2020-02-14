$('ul li').removeClass('active');
function setSidebarMenuSelection(selectedMenu) {
	if (selectedMenu == "dashboard") {
		$('.dashboard').addClass('active');
		$('#dashboardSelected').addClass('selected');
	} else if (selectedMenu == "country") {
		$('.country').addClass('active');
		$('#countrySelected').addClass('selected');
	} else if (selectedMenu == "state") {
		$('.state').addClass('active');
		$('#stateSelected').addClass('selected');
	} else if (selectedMenu == "district") {
		$('.district').addClass('active');
		$('#districtSelected').addClass('selected');
	} else if (selectedMenu == "taluka") {
		$('.taluka').addClass('active');
		$('#talukaSelected').addClass('selected');
	} else if (selectedMenu == "help") {
		$('.help').addClass('active');
		$('#helpSelected').addClass('selected');
	} else if (selectedMenu == "accessManagement") {
		$('.accessManagement').addClass('active');
		$('#accessManagementSelected').addClass('selected');
	} else if (selectedMenu == "domain") {
			$('.domain').addClass('active');
			$('#domainSelected').addClass('selected');
	} else if (selectedMenu == "designation") {
		$('.designation').addClass('active');
		$('#designationSelected').addClass('selected');
	} 
	
	removeSideMenu();
}
function removeSideMenu(){
	if($('div').hasClass('some-other-class')==true){
		$('.page-header-fixed.page-quick-sidebar-over-content.ngdialog-open').addClass('page-sidebar-closed');
		$('.page-sidebar-menu').addClass('page-sidebar-menu-closed');
		$('.some-other-class .page-sidebar-wrapper').remove();
	}
	
	//alert('hi');
	
	//page-sidebar-menu page-sidebar-menu-closed
	//page-header-fixed page-quick-sidebar-over-content ngdialog-open page-sidebar-closed
	//page-header-fixed page-quick-sidebar-over-content ngdialog-open
}
/*$(function(){

    $(".dropdown-menu li a").click(function(){

      $(".btn:first-child").text($(this).text());
      $(".btn:first-child").val($(this).text());

   });

});*/