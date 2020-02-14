<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../tiles/template/defaultHeader.jsp" />
<jsp:include page="../tiles/template/defaultMenu.jsp" />
<link href=${select2 } rel="stylesheet" type="text/css" />
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div  class="page-content" ng-controller="CountryController as ctrl">

		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					<spring:message code="country.title" />
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="dashboard.html">Home</a> <i class="fa fa-angle-right"></i></li>
					<li><a href="#"> <spring:message code="country.title" /></a></li>
					<li class="pull-right">
						<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
							<i class="icon-calendar"></i> <span></span> <i class="fa fa-angle-down"></i>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="response-msg-div">
			<div align="left" id="successMessage" class="success-message"></div>
			<div class="clearfix"></div>
			<div align="left" id="errorMessage" class="error-message"></div>
		</div>
		<div class="portlet-body form">
			<form class="form-horizontal" ng-submit="ctrl.submit()" name="myForm">
				<input type="hidden" ng-model="ctrl.country.id" />
				<div class="form-body">
					<div class="col-md-left">

						<div class="form-group">
							<label for="countryNameList" class="col-md-2 control-label"><spring:message code="country.name" /> <span class="required">*</span> </label>
							<div class="col-md-4">
								<select class="countryName form-control select2me" id="countryNameList" ng-model="ctrl.country.name" ng-options="countryName for countryName in ctrl.countryNames"
									name="countryName" data-placeholder="Country Name" required>
									<option value=""></option>
								</select>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.countryName.$error.required">This is a required field</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="capital" class="col-md-2 control-label">Capital </label>
							<div class="col-md-4">
								<input id="capital" name="capital" type="text" class="form-control" placeholder="Enter capital here" ng-model="ctrl.country.capital" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.capital.$error.required">This is a required field</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="countryCode" class="col-md-2 control-label"><spring:message code="country.code" /><span class="required">*</span> </label>
							<div class="col-md-4">
								<input id="countryCode" name="code" ng-model="ctrl.country.code" type="text" class="code form-control" placeholder="Enter Country Code  here" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.code.$error.required">This is a required field</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="isocode" class="col-md-2 control-label"><spring:message code="country.code" /><span class="required">*</span> </label>
							<div class="col-md-4">
								<input id="isocode" name="isocode" ng-model="ctrl.country.isocode" type="text" class="form-control" placeholder="Enter Country ISO Code  here" required ng-minlength="2" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.isocode.$error.required">This is a required field</span> <span ng-show="myForm.isocode.$error.minlength">Minimum length required is 2</span>
								</div>
							</div>
						</div>


						<div class="form-group">
							<label for="description" class="col-md-2 control-label"><spring:message code="country.descr" /> </label>
							<div class="col-md-4">
								<input id="description" name="descr" type="text" class="form-control" placeholder="Enter description here" ng-model="ctrl.country.descr" />

							</div>
						</div>
						<div class="form-group">
							<label for="latitude" class="col-md-2 control-label"><spring:message code="country.latitude" /> </label>
							<div class="col-md-4">
								<input id="latitude" name="latitude" type="text" class="form-control" placeholder="Enter Latitude here" ng-model="ctrl.country.latitude">

							</div>
						</div>

						<div class="form-group">
							<label for="longitude" class="col-md-2 control-label"><spring:message code="country.longitude" /> </label>
							<div class="col-md-4">
								<input id="lattitude" name="longitude" type="text" class="form-control" placeholder="Enter Longitude here" ng-model="ctrl.country.longitude" />
							</div>
						</div>

						<!-- END DESCRIPTION FIELD  -->
						<div class="clearfix"></div>
					</div>



				</div>

				<div ng-if="!ctrl.country.id">
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
							<button id="btnBack" type="button" name="back" class="btn default btn-medium " ng-click="ctrl.reset()">
								<spring:message code="country.reset" />
							</button>
							<button id="btnAdd" class="btn green btn-medium " type="submit" value="Add" ng-disabled="myForm.$invalid">
								<i class="fa fa-check"></i>
								<spring:message code="country.save" />
							</button>
						</div>
					</div>
				</div>

				<div ng-if="ctrl.country.id">
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
							<button id="btnBack" type="button" name="back" class="btn default btn-medium" ng-click="ctrl.reset()">
								<i class="fa fa-angle-left"></i>
								<spring:message code="country.cancel" />
							</button>
							<button id="btnUpdate" class="btn green btn-medium " type="submit" value="Update" ng-disabled="myForm.$invalid">
								<i class="fa fa-check"></i>
								<spring:message code="country.update" />
							</button>
						</div>


					</div>
				</div>

			</form>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-edit"></i>
							<spring:message code="country.countryList" />
						</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar">
							<div class="btn-group">

								<label id="filterLabel" for="longitude" class="col-md-2 control-label"><spring:message code="common.search" /> </label>
								<div class="col-md-4">
									<input id="filter" name="filter" type="text" class="form-control" ng-model="searchKeyword" ng-change="ctrl.filterTable(searchKeyword)" />
								</div>

							</div>
						</div>
						<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
								<tr>
									<th ng-click="ctrl.sort('id')"><spring:message code="country.id" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='id'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('name')"><spring:message code="country.name" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='name'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>

									<th ng-click="ctrl.sort('capital')"><spring:message code="country.capital" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='capital'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('isocode')"><spring:message code="country.isocode" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='isocode'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('code')"><spring:message code="country.code" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='code'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>

									<th ng-click="ctrl.sort('latitude')"><spring:message code="country.latitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='latitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>

									<th ng-click="ctrl.sort('longitude')"><spring:message code="country.longitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='longitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('descr')"><spring:message code="country.descr" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='descr'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th><spring:message code="country.operation" /></th>

								</tr>

							</thead>
							<tbody>

								<tr ng-show="ctrl.countries.length <= 0">
									<td colspan="8" style="text-align: center;"><spring:message code="common.loading" /></td>
								</tr>

								<tr dir-paginate="u in ctrl.countries| itemsPerPage:ctrl.itemsPerPage|filter: searchKeyword|orderBy:sortKey:ctrl.reverse" total-items="ctrl.total_count">
									<td><span ng-bind="u.id"></span></td>
									<td><span ng-bind="u.name"></span></td>
									<td><span ng-bind="u.capital"></span></td>
									<td><span ng-bind="u.isocode"></span></td>
									<td><span ng-bind="u.code"></span></td>
									<td><span ng-bind="u.latitude"></span></td>
									<td><span ng-bind="u.longitude"></span></td>
									<td><span ng-bind="u.descr"></span></td>
									<td>
										<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width edit " href="javascript:;">
											<spring:message code="country.edit" />
										</button>
										<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width  delete" href="javascript:;">
											<spring:message code="country.remove" />
										</button>
									</td>
								</tr>


							</tbody>
							<dir-pagination-controls max-size=ctrl.itemsPerPage direction-links="true" boundry-links="true" on-page-change="ctrl.getData(newPageNumber,searchKeyword)"></dir-pagination-controls>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="cleatfix"></div>

	</div>
</div>

<jsp:include page="../tiles/template/defaultFooter.jsp" />

<script src="static/metronic/global/scripts/metronic.js" type="text/javascript"></script>
<script src="static/metronic/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="static/js/utility/angular.js"></script>
<script src="static/js/app.js"></script>
<script src="static/js/common.js"></script>
<script src="static/js/service/countryService.js"></script>
<script src="static/js/controller/countryController.js"></script>
<script src="static/js/utility/dirPagination.js"></script>

<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		TableEditable.init();
		setSidebarMenuSelection("country");

	});
</script>
<!-- END Angular JS - PAGE specific controller and service -->
<!-- END JAVASCRIPTS -->
</body>
</html>