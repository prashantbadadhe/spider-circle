<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../tiles/template/defaultHeader.jsp" />
<jsp:include page="../tiles/template/defaultMenu.jsp" />
<link href="static/metronic/global/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<!-- BEGIN CONTENT -->
<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div class="page-content" ng-controller="StateController as ctrl">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					<spring:message code="state.title" />
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="dashboard.html"> <spring:message code="home.title" /></a> <i class="fa fa-angle-right"></i></li>
					<li><a href="#"> <spring:message code="state.title" /></a></li>
					<li class="pull-right">
						<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
							<i class="icon-calendar"></i> <span> </span> <i class="fa fa-angle-down"></i>
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
				<input type="hidden" ng-model="ctrl.state.id" />
				<div class="form-body">
					<div class="col-md-left">

						<div class="form-group">
							<label for="countryNameList" class="col-md-2 control-label"> <spring:message code="country.name" /> <span class="required">*</span>
							</label>
							<div class="col-md-4">
								<select class="form-control select2me" id="countryNameList" ng-model="ctrl.state.country" ng-init="ctrl.state.country"
									ng-options="country.name for country in ctrl.countries" name="countryName" data-placeholder="Country Name" required>
									<option value="" style="display: none;"></option>
								</select>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.countryName.$error.required">This is a required field</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="stateName" class="col-md-2 control-label"><spring:message code="state.name" /> <span class="required">*</span> </label>
							<div class="col-md-4">
								<select class="form-control select2me" id="stateName" ng-model="ctrl.state.name" ng-options="stateName for stateName in ctrl.stateList" name="stateName"
									data-placeholder="State Name">
									<option value=""></option>
								</select>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.stateName.$error.required">This is a required field</span>
								</div>
								<!-- 	<input id="stateName" name="StateName" type="text"
									class="form-control" placeholder="Enter State here"
									ng-model="ctrl.state.name" />-->
							</div>
						</div>
						<div class="form-group">
							<label for="stateCode" class="col-md-2 control-label"><spring:message code="state.code" /><span class="required">*</span> </label>
							<div class="col-md-4">
								<input id="stateCode" name="code" ng-model="ctrl.state.code" type="text" class="form-control" placeholder="Enter state code  here" required="required" ng-minlength="2" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.code.$error.required">This is a required field</span> <span ng-show="myForm.code.$error.minlength">Minimum length required is 2</span> <span
										ng-show="myForm.code.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="capital" class="col-md-2 control-label"><spring:message code="state.capital" /> </label>
							<div class="col-md-4">
								<input id="capital" name="capital" type="text" class="form-control" placeholder="Enter capital here" ng-model="ctrl.state.capital" required="required" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.capital.$error.required">This is a required field</span> <span ng-show="myForm.capital.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>


						<div class="form-group">
							<label for="description" class="col-md-2 control-label"><spring:message code="state.descr" /></label>
							<div class="col-md-4">
								<input id="description" name="descr" type="text" class="form-control" placeholder="Enter description here" ng-model="ctrl.state.descr" />

							</div>
						</div>

						<div class="form-group">
							<label for="latitude" class="col-md-2 control-label"> <spring:message code="state.latitude" />

							</label>
							<div class="col-md-4">
								<input id="lattitude" name="lattitude" type="text" class="form-control" placeholder="Enter Latitude here" ng-model="ctrl.state.latitude">
							</div>
						</div>

						<div class="form-group">
							<label for="longitude" class="col-md-2 control-label"><spring:message code="state.longitude" /></label>
							<div class="col-md-4">
								<input id="lattitude" name="longitude" type="text" class="form-control" placeholder="Enter Longitude here" ng-model="ctrl.state.longitude" />
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div ng-if="!ctrl.state.id">
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
							<button id="btnBack" type="button" name="back" class="btn default btn-medium " ng-click="ctrl.reset()">
								<spring:message code="state.reset" />
							</button>
							<button id="btnAdd" class="btn green btn-medium " type="submit" value="Add" ng-disabled="myForm.$invalid">
								<i class="fa fa-check"></i>
								<spring:message code="state.save" />
							</button>
						</div>
					</div>
				</div>

				<div ng-if="ctrl.state.id">
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
							<button id="btnBack" type="button" name="back" class="btn default btn-medium ">
								<i class="fa fa-angle-left"></i>
								<spring:message code="state.cancel" />
							</button>
							<button id="btnUpdate" class="btn green btn-medium " type="submit" value="Update" ng-disabled="myForm.$invalid">
								<i class="fa fa-check"></i>
								<spring:message code="state.update" />
							</button>
						</div>
					</div>
				</div>

			</form>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12">
				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-edit"></i>
							<spring:message code="state.stateList" />
						</div>
						<div class="tools">
							<a href="#portlet-config" data-toggle="modal" class="config"> </a> <a href="javascript:;" class="reload"></a>
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
									<th ng-click="ctrl.sort('id')"><spring:message code="state.id" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='id'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('country')"><spring:message code="state.country" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='country'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('name')"><spring:message code="state.name" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='name'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('capital')"><spring:message code="state.capital" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='capital'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('code')"><spring:message code="state.code" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='code'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('latitude')"><spring:message code="state.latitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='latitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('longitude')"><spring:message code="state.longitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='longitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('descr')"><spring:message code="state.descr" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='descr'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th><spring:message code="state.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="ctrl.states.length <= 0">
									<td colspan="8" style="text-align: center;"><spring:message code="common.loading" /></td>
								</tr>

								<tr dir-paginate="u in ctrl.states | itemsPerPage:ctrl.itemsPerPage|filter: searchKeyword|orderBy:sortKey:ctrl.reverse" total-items="ctrl.total_count">
									<td contenteditable="true"><span ng-bind="u.id"></span></td>
									<td><span ng-bind="u.country.name"></span></td>
									<td><span ng-bind="u.name"></span></td>
									<td><span ng-bind="u.capital"></span></td>
									<td><span ng-bind="u.code"></span></td>
									<td><span ng-bind="u.latitude"></span></td>
									<td><span ng-bind="u.longitude"></span></td>
									<td><span ng-bind="u.descr"></span></td>
									<td>
										<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width edit " href="javascript:;">
											<spring:message code="state.edit" />
										</button>
										<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width  delete" href="javascript:;">
											<spring:message code="state.remove" />
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
<script src="static/js/common.js"></script>
<script src="static/js/service/stateservice.js"></script>
<script src="static/js/controller/statecontroller.js"></script>
<script src="static/js/utility/dirPagination.js"></script>
<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		TableEditable.init();
		setSidebarMenuSelection("state");
	});
</script>
</body>
</html>