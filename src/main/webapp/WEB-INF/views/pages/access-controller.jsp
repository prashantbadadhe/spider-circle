<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../tiles/template/defaultHeader.jsp" />
<jsp:include page="../tiles/template/defaultMenu.jsp" />
<link href="static/metronic/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="static/js/bonsai/css/jquery.bonsai.css"rel="stylesheet" type="text/css"/>
<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper" ng-app="myApp">
			<div class="page-content" ng-controller="RoleController as ctrl">
				<!-- END STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">Manage Roles Access</h3>
						<ul class="page-breadcrumb breadcrumb">
							<li><i class="fa fa-home"></i> <a href="#">Manage Roles
									Access</a></li>
							<li class="pull-right">
								<div id="dashboard-report-range"
									class="dashboard-date-range tooltips" data-placement="top"
									data-original-title="Change dashboard date range">
									<i class="icon-calendar"></i> <span></span> <i
										class="fa fa-angle-down"></i>
								</div>
							</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="response-msg-div">
							<div align="left" id="successMessage" class="success-message">
							</div>
							<div class="clearfix"></div>
							<div align="left" id="errorMessage" class="error-message">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PORTLET-->
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i>Edit Role Access
								</div>
							</div>
							<div class="portlet-body">
								<!-- BEGIN FORM-->
								<form class="form-horizontal">
									<div class="form-group">
							<label for="roleNameList" class="col-md-2 control-label"><spring:message
									code="role.rolesList" /> <span class="required">*</span> </label>
							<div class="col-md-4">
								<select class="form-control select2me" id="roleNameList"
									ng-model="ctrl.role"
									ng-init = "ctrl.role"
									ng-options="role.name for role in ctrl.roles"
									name="roleName" data-placeholder="Role Name">
									<option value=""></option>
								</select>

							</div>
						</div>
									<div class="form-group">
										<label class="col-md-2 control-label"> </label>
										<div class="col-md-9">
											<table id="" class="col-md-12">
												<tr>
													<td><ol id='urlGroup-checkboxes' data-name='url-group'></ol>
													<li>Dashboard</li>
													<li>Country</li>
													<li>State</li>
													<li>District</li>
													<li>User</li>
													<li>Settings</li>
													</td>
												</tr>
												<tr>
													<td><span id="role-error" class='error-message'></span></td>
												</tr>
											</table>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-2"></div>
										<div class="col-md-2">
											<div class="btn green" id="btnSave">
												<i class="fa fa-check"></i>Save
											</div>
										</div>
									</div>
									<!-- END FORM-->
								</form>
							</div>
						</div>
						<!-- END PORTLET-->
					</div>
				</div>

				<div class="clearfix"></div>

			</div>
		</div>
		<!-- END CONTENT -->

<jsp:include page="../tiles/template/defaultFooter.jsp" />
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="static/metronic/global/plugins/respond.min.js"></script>
<script src="static/metronic/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="static/metronic/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script	src="static/metronic/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script
	src="static/metronic/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
	type="text/javascript"></script>
<script
	src="static/metronic/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>
<script src="static/metronic/global/plugins/jquery.cokie.min.js"
	type="text/javascript"></script>
<script
	src="static/metronic/global/plugins/uniform/jquery.uniform.min.js"
	type="text/javascript"></script>
<script src="static/metronic/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="static/metronic/admin/pages/scripts/table-editable.js"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="static/metronic/global/scripts/metronic.js"	type="text/javascript"></script>
<script src="static/metronic/admin/layout/scripts/layout.js"	type="text/javascript"></script>
	<script src="static/js/bonsai/js/jquery.bonsai.js" type="text/javascript"></script> 
	<script src="static/js/bonsai/js/jquery.qubit.js" type="text/javascript"></script> 
	<script src="static/js/bonsai/js/jquery.json-list.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN Angular JS - PAGE specific controller and service -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="static/js/app.js"></script>
<script src="static/js/common.js"></script>
<script src="static/js/service/roleService.js"></script>
<script src="static/js/controller/roleController.js"></script>
<!-- END Angular JS - PAGE specific controller and service -->
<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		 TableEditable.init();
		 setSidebarMenuSelection("accessManagement");
	});
</script>

<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>