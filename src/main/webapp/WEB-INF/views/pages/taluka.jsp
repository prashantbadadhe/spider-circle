<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../tiles/template/defaultHeader.jsp" />
<jsp:include page="../tiles/template/defaultMenu.jsp" />
<link href="static/metronic/global/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<!-- BEGIN CONTENT -->
<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div class="page-content" ng-controller="TalukaController as ctrl">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					<spring:message code="taluka.title" />
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="dashboard.html"><spring:message code="home.title" /></a> <i class="fa fa-angle-right"></i></li>
					<li><a href="#"><spring:message code="taluka.title" /></a></li>
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
				<input type="hidden" ng-model="ctrl.taluka.id" />
				<div class="form-body">
					<div class="col-md-left">
						<div class="form-group">
							<div class="form-group">
								<label for="countryNameList" class="col-md-2 control-label"><spring:message code="country.name" /> <span class="required">*</span> </label>
								<div class="col-md-4">
									<select class="form-control select2me" id="countryNameList" ng-model="ctrl.country" ng-init="ctrl.country" ng-options="country.name for country in ctrl.countries"
										ng-change="ctrl.getStateFromCountry()" name="countryName" data-placeholder="Country Name">
										<option value=""></option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="stateNameList" class="col-md-2 control-label"> <spring:message code="state.name" /> <span class="required">*</span>

								</label>
								<div class="col-md-4">
									<select class="form-control select2me" id="stateNameList" ng-model="ctrl.state" ng-init="ctrl.state" ng-options="state.name for state in ctrl.filteredState"
										ng-change="ctrl.getDistrictFromState()" name="stateName" data-placeholder="State Name">
										<option value=""></option>
									</select>

								</div>
							</div>
							<div class="form-group">
								<label for="DistrictNameList" class="col-md-2 control-label"> <spring:message code="district.name" /> <span class="required">*</span>

								</label>
								<div class="col-md-4">
									<select class="form-control select2me" id="stateNameList" ng-model="ctrl.taluka.district" ng-init="ctrl.taluka.district"
										ng-options="district.name for district in ctrl.filteredDistrictList" name="districtName" data-placeholder="District Name">
										<option value=""></option>
									</select>

								</div>
							</div>
							<div class="form-group">
								<label for="talukaName" class="col-md-2 control-label"> <spring:message code="taluka.name" /> <span class="required">*</span></label>
								<div class="col-md-4">
									<input id="talukaName" name="TalukaName" type="text" class="form-control" placeholder="Enter Taluka here" ng-model="ctrl.taluka.name" />
								</div>
							</div>
							<div class="form-group">
								<label for="talukaCode" class="col-md-2 control-label"><spring:message code="taluka.code" /><span class="required">*</span> </label>
								<div class="col-md-4">
									<input id="talukaCode" name="code" ng-model="ctrl.taluka.code" type="text" class="form-control" placeholder="Enter taluka code  here" />
								</div>
							</div>
							<div class="form-group">
								<label for="iscity" class="col-md-2 control-label"> <spring:message code="taluka.iscity" /> <span class="required">*</span></label>
								<div class="col-md-4">
									<select class="form-control select2me" id="iscity" ng-model="ctrl.taluka.city" ng-init="true" name="iscity" placeholder="is city">
										<option value="false">No</option>
										<option value="true">Yes</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-md-2 control-label"><spring:message code="taluka.descr" /> </label>
								<div class="col-md-4">
									<input id="description" name="descr" type="text" class="form-control" placeholder="Enter description here" ng-model="ctrl.taluka.descr" />
								</div>
							</div>

							<div class="form-group">
								<label for="latitude" class="col-md-2 control-label"> <spring:message code="taluka.latitude" />
								</label>
								<div class="col-md-4">
									<input id="latitude" name="latitude" type="text" class="form-control" placeholder="Enter Latitude here" ng-model="ctrl.taluka.latitude">

								</div>
							</div>

							<div class="form-group">
								<label for="longitude" class="col-md-2 control-label"> <spring:message code="taluka.longitude" />
								</label>
								<div class="col-md-4">
									<input id="lattitude" name="longitude" type="text" class="form-control" placeholder="Enter Longitude here" ng-model="ctrl.taluka.longitude" />
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>

					<div ng-if="!ctrl.taluka.id">
						<div class="form-actions fluid">
							<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
								<button id="btnBack" type="button" name="back" class="btn default btn-medium " ng-click="ctrl.reset()">
									<spring:message code="taluka.reset" />
								</button>
								<button id="btnAdd" class="btn green btn-medium " type="submit" value="Add" ng-disabled="myForm.$invalid">
									<i class="fa fa-check"></i>
									<spring:message code="taluka.save" />
								</button>
							</div>
						</div>
					</div>

					<div ng-if="ctrl.taluka.id">
						<div class="form-actions fluid">
							<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
								<button id="btnBack" type="button" name="back" class="btn default btn-medium " ng-click="ctrl.reset()">
									<i class="fa fa-angle-left"></i>
									<spring:message code="taluka.cancel" />
								</button>
								<button id="btnUpdate" ng-click="ctrl.fetchAllTalukas(1)" class="btn green btn-medium " type="submit" value="Update" ng-disabled="myForm.$invalid">
									<i class="fa fa-check"></i>
									<spring:message code="taluka.update" />
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
							<spring:message code="taluka.talukaList" />
						</div>
						<div class="tools">
							<a href="#portlet-config" data-toggle="modal" class="config"> </a> <a href="javascript:;" class="reload"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar">
							<div class="btn-group">

								<label id="filterLabel" for="filter" class="col-md-2 control-label"><spring:message code="common.search" /> </label>
								<div class="col-md-4">
									<input id="filter" name="filter" type="text" class="form-control" ng-model="searchKeyword" ng-change="ctrl.filterTable(searchKeyword)" />
								</div>

							</div>
						</div>
						<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
								<tr>
									<th ng-click="ctrl.sort('id')"><spring:message code="taluka.id" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='id'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('district')"><spring:message code="taluka.district" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='district'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>

									<th ng-click="ctrl.sort('name')"><spring:message code="taluka.name" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='name'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('code')"><spring:message code="taluka.code" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='code'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('city')"><spring:message code="taluka.iscity" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='city'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('latitude')"><spring:message code="taluka.latitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='latitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>

									<th ng-click="ctrl.sort('longitude')"><spring:message code="taluka.longitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='longitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('descr')"><spring:message code="taluka.descr" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='descr'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>

									<th><spring:message code="ward.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="ctrl.talukas.length <= 0">
									<td colspan="8" style="text-align: center;"><spring:message code="common.loading" /></td>
								</tr>
								<tr dir-paginate="u in ctrl.talukas | itemsPerPage:ctrl.itemsPerPage|filter: searchKeyword|orderBy:sortKey:ctrl.reverse" total-items="ctrl.total_count">
									<td><span ng-bind="u.id"></span></td>
									<td><span ng-bind="u.district.name"></span></td>
									<td><span ng-bind="u.name"></span></td>
									<td><span ng-bind="u.code"></span></td>
									<td><span ng-bind="u.city"></span></td>
									<td><span ng-bind="u.latitude"></span></td>
									<td><span ng-bind="u.longitude"></span></td>
									<td><span ng-bind="u.descr"></span></td>
									<td>
										<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width edit " href="javascript:;">Edit</button>
										<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width  delete" href="javascript:;">Remove</button>
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
<script src="static/js/service/talukaservice.js"></script>
<script src="static/js/controller/talukacontroller.js"></script>
<script src="static/js/utility/dirPagination.js"></script>
<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		TableEditable.init();
		setSidebarMenuSelection("taluka");
	});
</script>
</body>
</html>