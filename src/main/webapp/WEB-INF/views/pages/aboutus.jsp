<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../tiles/template/defaultHeader.jsp" />
<jsp:include page="../tiles/template/defaultMenu.jsp" />
<link href="static/metronic/global/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div class="page-content" ng-controller="VillageController as ctrl">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
				About Us
				<%-- 	<spring:message code="village.title" /> --%>
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="dashboard.html">Dashboard<%-- <spring:message code="home.title" /> --%></a> <i class="fa fa-angle-right"></i></li>
					<li><a href="#">About us<%-- <spring:message code="village.title" /> --%></a></li>
					<li class="pull-right">
						<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
							<i class="icon-calendar"></i> <span></span> <i class="fa fa-angle-down"></i>
						</div>
					</li>
				</ul>
			</div>
		</div>
 <h1>Under Contruction.</h1><br><h3> Comming soon....</h3>
		<div class="cleatfix"></div>
 
	</div>
</div>

<jsp:include page="../tiles/template/defaultFooter.jsp" />
<script src="static/metronic/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="static/metronic/admin/pages/scripts/table-editable.js"></script>
<script src="static/metronic/global/scripts/metronic.js" type="text/javascript"></script>
<script src="static/metronic/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		TableEditable.init();
	});
</script>
<!-- BEGIN Angular JS - PAGE specific controller and service -->
<script src="static/js/utility/angular.js"></script>
<script src="static/js/app.js"></script>
<script src="static/js/service/villageservice.js"></script>
<script src="static/js/controller/villagecontroller.js"></script>
<script src="static/js/utility/dirPagination.js"></script>

<!-- END Angular JS - PAGE specific controller and service -->
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>