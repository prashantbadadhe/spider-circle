<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../tiles/template/defaultHeader.jsp" />
<jsp:include page="../tiles/template/defaultMenu.jsp" />
<link href="static/metronic/global/plugins/select2/select2.css" rel="stylesheet" type="text/css" />
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div class="page-content" ng-controller="WardController as ctrl">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					<spring:message code="ward.title" />
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="dashboard.html"><spring:message code="home.title" /></a> <i class="fa fa-angle-right"></i></li>
					<li><a href="#"><spring:message code="ward.title" /></a></li>
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
				<input type="hidden" ng-model="ctrl.ward.id" />
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
									<select class="form-control select2me" id="stateNameList" ng-model="ctrl.district" ng-init="ctrl.district"
										ng-options="district.name for district in ctrl.filteredDistrictList" name="districtName" ng-change="ctrl.getTalukaFromDistrict()" data-placeholder="District Name">
										<option value=""></option>
									</select>

								</div>
							</div>
							<div class="form-group">
								<label for="TalukaNameList" class="col-md-2 control-label"> <spring:message code="taluka.name" /> <span class="required">*</span>

								</label>
								<div class="col-md-4">
									<select class="form-control select2me" id="talukaNameList" ng-model="ctrl.ward.taluka" ng-init="ctrl.ward.taluka"
										ng-options="taluka.name for taluka in ctrl.filteredTalukaList" name="talukaName" data-placeholder="Taluka Name">
										<option value=""></option>
									</select>

								</div>
							</div>
							<div class="form-group">
								<label for="wardName" class="col-md-2 control-label"> <spring:message code="ward.name" /> <span class="required">*</span></label>
								<div class="col-md-4">
									<input id="wardName" name="WardName" type="text" class="form-control" placeholder="Enter ward name" ng-model="ctrl.ward.name" />
								</div>
							</div>
							<div class="form-group">
								<label for="wardNumber" class="col-md-2 control-label"> <spring:message code="ward.number" /><span class="required">*</span>
								</label>
								<div class="col-md-4">
									<input id="wardNumber" name="number" ng-model="ctrl.ward.number" type="text" class="form-control" placeholder="Enter ward Number" />
								</div>
							</div>

							<div class="form-group">
								<label for="wardAddress" class="col-md-2 control-label"><spring:message code="ward.address" /><span class="required">*</span> </label>
								<div class="col-md-4">
									<input id="wardAddress" name="address" ng-model="ctrl.ward.address" type="text" class="form-control" placeholder="Enter ward Address" />
								</div>
							</div>
							<div class="form-group">
								<label for="wardPin" class="col-md-2 control-label"><spring:message code="ward.pin" /><span class="required">*</span> </label>
								<div class="col-md-4">
									<input id="wardPin" name="pin" ng-model="ctrl.ward.pincode" type="text" class="form-control" placeholder="Enter ward Address" />
								</div>
							</div>
							<div class="form-group">
								<label for="wardPopulation" class="col-md-2 control-label"><spring:message code="ward.population" /></label>
								<div class="col-md-4">
									<input id="wardPopulation" name="population" ng-model="ctrl.ward.population" type="text" class="form-control" placeholder="Enter ward population" />
								</div>
							</div>
							<div class="form-group">
								<label for="wardArea" class="col-md-2 control-label"><spring:message code="ward.area" /></label>
								<div class="col-md-4">
									<input id="wardArea" name="area" ng-model="ctrl.ward.area" type="text" class="form-control" placeholder="Enter ward area" />
								</div>
							</div>
							<div class="form-group">
								<label for="description" class="col-md-2 control-label"><spring:message code="ward.descr" /> </label>
								<div class="col-md-4">
									<input id="description" name="descr" type="text" class="form-control" placeholder="Enter description" ng-model="ctrl.ward.descr" />

								</div>
							</div>

							<div class="form-group">
								<label for="latitude" class="col-md-2 control-label"> <spring:message code="ward.latitude" />
								</label>
								<div class="col-md-4">
									<input id="latitude" name="latitude" type="text" class="form-control" placeholder="Enter latitude" ng-model="ctrl.ward.latitude">

								</div>
							</div>

							<div class="form-group">
								<label for="longitude" class="col-md-2 control-label"> <spring:message code="ward.longitude" />
								</label>
								<div class="col-md-4">
									<input id="lattitude" name="longitude" type="text" class="form-control" placeholder="Enter longitude" ng-model="ctrl.ward.longitude" />
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>

					<div ng-if="!ctrl.ward.id">
						<div class="form-actions fluid">
							<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
								<button id="btnBack" type="button" name="back" class="btn default btn-medium " ng-click="ctrl.reset()">
									<spring:message code="ward.reset" />
								</button>
								<button id="btnAdd" class="btn green btn-medium " type="submit" value="Add" ng-disabled="myForm.$invalid">
									<i class="fa fa-check"></i>
									<spring:message code="ward.save" />
								</button>
							</div>
						</div>
					</div>

					<div ng-if="ctrl.ward.id">
						<div class="form-actions fluid">
							<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
								<button id="btnBack" type="button" name="back" class="btn default btn-medium ">
									<i class="fa fa-angle-left"></i>
									<spring:message code="ward.cancel" />
								</button>
								<button id="btnUpdate" class="btn green btn-medium " type="submit" value="Update" ng-disabled="myForm.$invalid">
									<i class="fa fa-check"></i>
									<spring:message code="ward.update" />
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
							<spring:message code="ward.wardList" />
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
									<th ng-click="ctrl.sort('id')"><spring:message code="ward.id" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='id'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('district')"><spring:message code="ward.taluka" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='district'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('name')"><spring:message code="ward.name" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='name'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('number')"><spring:message code="ward.number" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='number'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('address')"><spring:message code="ward.address" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='address'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('pin')"><spring:message code="ward.pin" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='pin'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('population')"><spring:message code="ward.population" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='population'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('area')"><spring:message code="ward.area" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='area'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('latitude')"><spring:message code="ward.latitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='latitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('longitude')"><spring:message code="ward.longitude" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='longitude'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('descr')"><spring:message code="ward.descr" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='descr'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th><spring:message code="ward.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="ctrl.wards.length <= 0">
									<td colspan="12" style="text-align: center;"><spring:message code="common.loading" /></td>
								</tr>
								<tr dir-paginate="u in ctrl.wards | itemsPerPage:ctrl.itemsPerPage|filter: searchKeyword|orderBy:sortKey:ctrl.reverse" total-items="ctrl.total_count">
									<td><span ng-bind="u.id"></span></td>
									<td><span ng-bind="u.taluka.name"></span></td>
									<td><span ng-bind="u.name"></span></td>
									<td><span ng-bind="u.number"></span></td>
									<td><span ng-bind="u.address"></span></td>
									<td><span ng-bind="u.pincode"></span></td>
									<td><span ng-bind="u.population"></span></td>
									<td><span ng-bind="u.area"></span></td>
									<td><span ng-bind="u.latitude"></span></td>
									<td><span ng-bind="u.longitude"></span></td>
									<td><span ng-bind="u.descr"></span></td>
									<td>
										<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width edit " href="javascript:;">Edit</button>
										<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width  delete" href="javascript:;">Remove</button>
									</td>
								</tr>
								<dir-pagination-controls max-size=ctrl.itemsPerPage direction-links="true" boundry-links="true" on-page-change="ctrl.getData(newPageNumber,searchKeyword)"></dir-pagination-controls>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="cleatfix"></div>

	</div>
</div>

<jsp:include page="../tiles/template/defaultFooter.jsp" />

<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		TableEditable.init();
	});
</script>
<script src="static/js/utility/angular.js"></script>
<script src="static/js/app.js"></script>
<script src="static/js/service/wardservice.js"></script>
<script src="static/js/controller/wardcontroller.js"></script>
<script src="static/js/utility/dirPagination.js"></script>
</body>
</html>