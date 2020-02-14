<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../../tiles/template/defaultHeader.jsp" />
<jsp:include page="../../tiles/template/defaultMenu.jsp" />
<link href=${select2 } rel="stylesheet" type="text/css" />
 

<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div class="page-content" ng-controller="ContactDetailController as ctrl">
		<div class="row">
			<div class="col-md-12">
				<h3 class="page-title">
					<spring:message code="contactDetail.title" />
				</h3>
				<ul class="page-breadcrumb breadcrumb">
					<li><i class="fa fa-home"></i> <a href="dashboard.html">Home</a> <i class="fa fa-angle-right"></i></li>
					<li><a href="#"> <spring:message code="contactDetail.title" /></a></li>
					<li class="pull-right">
						<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
							<i class="icon-calendar"></i> <span></span> <i class="fa fa-angle-down"></i>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="portlet-body form">
			<form class="form-horizontal" ng-submit="ctrl.submit()" name="myForm">
				<input type="hidden" ng-model="ctrl.contactDetail.id" />
				<div class="form-body">
					<div class="form-group">
						<div class="panel panel-primary">
							<div class="panel-heading">Basic Information</div>
							<div class="panel-body">
								<div class="  row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<label for="firstname" class="control-label "><spring:message code="contactDetail.firstname" /> <span class="required">*</span></label>
										<div class="input-group">
											<span class="input-group-addon info" id="basic-addon1"><i class="	glyphicon glyphicon-user" aria-hidden="true"></i></span> <input id="firstname" name="firstname"
												type="text" class="form-control" placeholder="First Name" ng-model="ctrl.contactDetail.firstName" required />
										</div>
										<div class="has-error" ng-show="myForm.$dirty">
											<span ng-show="myForm.capital.$error.required">This is a required field</span>
										</div>
									</div>

									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<label for=middlename class="control-label"><spring:message code="contactDetail.middlename" /> </label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><i class="	glyphicon glyphicon-user" aria-hidden="true"></i></span> <input id="middlename" name="middlename"
												type="text" class="form-control" placeholder="Middle Name" ng-model="ctrl.contactDetail.middleName" />
										</div>
									</div>

									<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
										<label for="lastname" class="control-label"><spring:message code="contactDetail.lastname" /><span class="required">*</span></label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><i class="	glyphicon glyphicon-user" aria-hidden="true"></i></span> <input id="lastname" name="lastname" type="text"
												class="form-control" placeholder="Last Name" ng-model="ctrl.contactDetail.lastName" required />
										</div>

										<div class="has-error" ng-show="myForm.$dirty">
											<span ng-show="myForm.capital.$error.required">This is a required field</span>
										</div>
									</div>

								</div>

								<div class=" row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<label for="nickname" class="control-label"><spring:message code="contactDetail.nickname" /></label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><i class="	glyphicon glyphicon-user" aria-hidden="true"></i></span> <input id="nickname" name="nickname" type="text"
												class="form-control" placeholder="Nick Name" ng-model="ctrl.contactDetail.nickName" />
										</div>
									</div>
								</div>
								<div class=" margin-bottom-xs row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<label for="firstPhone" class="control-label"><spring:message code="contactDetail.firstphone" /></label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><i class="fa fa-phone" aria-hidden="true"></i></span>
											 <input type="text" class="form-control bfh-phone"
												data-format="+1 (ddd) ddd-dddd" placeholder="Phone Number" ng-model="ctrl.contactDetail.firstPhone">
										</div>
								</div>
								</div>
							<div class=" margin-bottom-xs row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<label for="secondPhone" class="control-label"><spring:message code="contactDetail.secondphone" /></label>
											<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><i class="fa fa-phone" aria-hidden="true"></i></span> 
											<input id="secondPhone" name="secondPhone" type="text"
												class="form-control" placeholder="Secondry Phone" ng-model="ctrl.contactDetail.secondPhone" />
										</div>

									</div>
								</div>
								<div class=" margin-bottom-xs row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<label for="email" class="control-label"><spring:message code="contactDetail.email" /></label>
										<div class="input-group">
											<span class="input-group-addon" id="basic-addon1"><i class="fa fa-envelope" aria-hidden="true"></i></span> 
											<input id="email" name="email" type="text"
												class="form-control" placeholder="Email id" ng-model="ctrl.contactDetail.email" />
										</div>
											</div>
								</div>
									
								<div class=" margin-bottom-xs row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<label for="address" class="control-label"><spring:message code="contactDetail.address" /></label>
										<textarea class="form-control" id="address" rows="5" ng-model="ctrl.contactDetail.address"></textarea>
									</div>
								</div>

								<div class=" margin-bottom-xs row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
										<div class="col-md-6  col-xs-6">
											<div class="btn-group dropdown dropdown-scroll"  uib-dropdown dropdown-append-to-body>
												<button id="religionDropdown" type="button" class="btn btn-primary buttonType" uib-dropdown-toggle>
														<spring:message code="contactDetail.religion" />
														<span class="caret"></span>
												</button>
											<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.contactDetail.religion">
												<li role="presentation" value="Religion"'> <a ng-click ="ctrl.checkValue('Religion','religionDropdown')" href>Religion</a></li>
												<li role="presentation" ng-repeat='religion in ctrl.religions'><a ng-click ="ctrl.checkValue(religion,'religionDropdown')" href> {{religion}} </a></li>
											</ul>
											</div>
										</div>
										</div>
								<div class=" margin-bottom-xs row col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
										<label for="additionalInfo" class="control-label"><spring:message code="contactDetail.additionalInfo" /></label> 
										<input id="additionalInfo" name="additionalInfo" type="text"
											class="form-control form-control-blue" placeholder="Addition Information" ng-model="ctrl.contactDetail.additionalInfo" />
									</div>
								</div>

							</div>
						</div>

						<div class="panel panel-primary">
							<div class="panel-heading">Designations</div>
							<div class="panel-body">
								<div class="  row  col-md-offset-1 col-xs-offset-1 ">
									<div class="form-group">
										<div class="col-md-6  col-xs-6">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.organization" /> <span class="required">*</span></label>

											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="organizationDropDown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Organization <span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.contactDetail.designation">
													<li ng-repeat="choice in ctrl.designations" value="{{choice}}"><a ng-click="ctrl.checkValue(choice,'organizationDropDown')" href>{{choice}}</a></li>

												</ul>
											</div>
										</div>
										<div class="col-md-6  col-xs-6">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.designation" /> <span class="required">*</span></label>

											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="designationDropDown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Designation<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.contactDetail.designation">
													<li ng-repeat="choice in ctrl.designations" value="{{choice}}"><a ng-click="ctrl.checkValue(choice,'designationDropDown')" href>{{choice}}</a></li>

												</ul>
											</div>
										</div>
									</div>
								</div>
								</div>
								</div>
								<div class="panel panel-primary">
							<div class="panel-heading">Location</div>
							<div class="panel-body">
								<div class="row col-lg-offset-1">
									<div class="form-group">
										<div class="col-md-1  col-xs-1">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.country" /></label>
							 			</div>
										<div class="col-md-4  col-xs-4">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="countryDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Country<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.country"  >
													<li ng-repeat="country in ctrl.countries" value="{{country.name}}"><a ng-click="ctrl.setLocationValues(country.name,'countryDropdown','country');ctrl.getStateFromCountry(country.name)" href>{{country.name}}</a></li>
												</ul>
											</div>
										</div>
										<div class="col-md-1  col-xs-1">
											<label for="contactDetail" class="control-label "><spring:message code="contactDetail.state" /></label>
										</div>
										<div class="col-md-4  col-xs-4">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="stateDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													State<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.state">
													<li ng-repeat="state in ctrl.filteredState" value="{{state.name}}"><a ng-click="ctrl.setLocationValues(state.name,'stateDropdown','state');ctrl.getDistrictFromState(state.name)" href>{{state.name}}</a></li>

												</ul>
											</div>
										</div>
										</div>
										 </div>
											<div class="  row col-lg-offset-1">
											<div class="form-group">
										<div class="col-md-1  col-xs-1">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.district" /></label>
										</div>
									<div class="col-md-4 col-xs-4">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="districtDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													District<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.district">
													<li ng-repeat="district in ctrl.filteredDistrictList" value="{{district.name}}"><a ng-click="ctrl.setLocationValues(district.name,'districtDropdown','district');ctrl.getTalukaFromDistrict(district.name)" href>{{district.name}}</a></li>
												</ul>
											</div>
										</div>
										<div class="col-md-1  col-xs-1">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.taluka" /> </label>
										</div>
									<div class="col-md-4  col-xs-4">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="talukaDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Taluka<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.taluka">
													<li ng-repeat="taluka in ctrl.filteredTalukaList" value="{{taluka.name}}"><a ng-click="ctrl.setLocationValues(taluka.name,'talukaDropdown','taluka');ctrl.getWardFromTaluka(taluka.name)" href>{{taluka.name}}</a></li>

												</ul>
											</div>
										</div>
										</div>
										</div>
											<div class="row col-lg-offset-1">
											<div class="form-group">
											<div class="col-md-1  col-xs-1">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.ward" /> </label>
										</div>
									<div class="col-md-4  col-xs-4">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="wardDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Ward<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.ward">
													<li ng-repeat="ward in ctrl.filteredWardList" value="{{ward.name}}"><a ng-click="ctrl.setLocationValues(ward.name,'wardDropdown','ward');ctrl.getVillageFromWard(ward.name)" href>{{ward.name}}</a></li>

												</ul>
											</div>
										</div>
										<div class="col-md-1  col-xs-1">
											<label for="firstname" class="control-label "><spring:message code="contactDetail.village" /></label>
										</div>
									<div class="col-md-4  col-xs-4">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="villageDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Village<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.village">
													<li ng-repeat="village in ctrl.filteredVillageList" value="{{village.name}}"><a ng-click="ctrl.setLocationValues(village.name,'villageDropdown','village')" href>{{village.name}}</a></li>

												</ul>
											</div>
										</div>
										</div>
									</div>
							<div class="row  col-lg-offset-1">
											<div class="form-group">
										  	<div class="col-md-2  col-xs-2">
											<label for="firstname" class="control-label ">Not finding location?</label>
										</div>  
									<div class="col-md-1  col-xs-1 locationbuttonSize">
											<div class="btn-group" uib-dropdown dropdown-append-to-body>
												<button id="locationDropdown" type="button" class="btn btn-primary" uib-dropdown-toggle>
													Here<span class="caret"></span>
												</button>
												<ul class="dropdown-menu" uib-dropdown-menu role="menu" aria-labelledby="btn-append-to-body" ng-model="ctrl.otherType">
													<li ng-repeat="location in ctrl.locationType" value="{{location}}"><a ng-click="ctrl.setLocationValues(location,'locationDropdown','locationType');ctrl.selectionCheck(location)" href>{{location}}</a></li>
												</ul>
								 </div>
								 </div>
								 <div class="col-md-4  col-xs-4">
									<div class="input-group">
										<span ng-show="ctrl.locationSelected" class="input-group-addon" id="basic-addon1"><i class="fa fa-home" aria-hidden="true"></i></span> 
										<input  ng-show="ctrl.locationSelected" id="otherlocation" name="otherlocation" type="text"
											class="form-control form-control-blue" placeholder="Location" ng-model="ctrl.otherlocation" />
									 </div>
											</div>
										</div>
										</div>
									</div>
								</div>
							</div>
						</div>
				<div ng-if="!ctrl.contactDetail.id">
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-9 " style="margin-left: 0px;">
							<button id="btnBack" type="button" name="back" class="btn default btn-medium " ng-click="ctrl.reset()">
								<i class="fa fa-times"></i>
								<spring:message code="contactDetail.reset" />
							</button>
							<button id="btnAdd" class="btn green btn-medium " type="submit" value="Add"  ng-disabled="myForm.$invalid">
								<i class="fa fa-check"></i>
								<spring:message code="contactDetail.save" />
							</button>
						</div>
					</div>
				</div>

				<div ng-if="ctrl.contactDetail.id">
					<div class="form-actions fluid">
						<div class="col-md-offset-6 col-md-9" style="margin-left: 0px;">
							<button id="btnBack" type="button" name="back" class="btn btn-danger btn-medium " ng-click="ctrl.reset()" >
								<i class="fa fa-times"></i>
								<spring:message code="contactDetail.cancel" />
							</button>
							<button id="btnUpdate" class="btn green btn-medium " type="submit" value="Update" ng-disabled="myForm.$invalid">
								<i class="fa fa-check"></i>
								<spring:message code="contactDetail.update" />
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
							<spring:message code="contactDetail.contactDetailList" />
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
									<th ng-click="ctrl.sort('id')"><spring:message code="contactDetail.id" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='id'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('firstName')"><spring:message code="contactDetail.firstname" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='firstName'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('middleName')"><spring:message code="contactDetail.middlename" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='middleName'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('lastName')"><spring:message code="contactDetail.lastname" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='lastName'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('firstPhone')"><spring:message code="contactDetail.firstphone" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='firstPhone'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('email')"><spring:message code="contactDetail.email" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='email'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
								<th ng-click="ctrl.sort('address')"><spring:message code="contactDetail.address" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='address'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
								
									<th ng-click="ctrl.sort('additionalInfo')"><spring:message code="contactDetail.additionalInfo" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='additionalInfo'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th><spring:message code="contactDetail.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="ctrl.states.length <= 0">
									<td colspan="8" style="text-align: center;"><spring:message code="common.loading" /></td>
								</tr>

								<tr dir-paginate="u in ctrl.contactDetails | itemsPerPage:ctrl.itemsPerPage|filter: searchKeyword|orderBy:sortKey:ctrl.reverse" total-items="ctrl.total_count">
									<td contenteditable="true"><span ng-bind="u.id"></span></td>
									<td><span ng-bind="u.firstName"></span></td>
									<td><span ng-bind="u.middleName"></span></td>
									<td><span ng-bind="u.lastName"></span></td>
									<td><span ng-bind="u.firstPhone"></span></td>
									<td><span ng-bind="u.email"></span></td>
									<td><span ng-bind="u.address"></span></td>
									<td><span ng-bind="u.additionalInfo"></span></td>
									<td class="fixingcolumnwith">
										<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width edit " href="javascript:;">
											<%-- <spring:message code="contactDetail.edit" /> --%>
											<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										</button>
										<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width  delete" href="javascript:;">
											<%-- <spring:message code="contactDetail.remove" /> --%>
											<i class="fa fa-times" aria-hidden="true"></i>
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


	<jsp:include page="../../tiles/template/defaultFooter.jsp" />

	<script src="static/metronic/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="static/metronic/admin/layout/scripts/layout.js" type="text/javascript"></script>
	<script src="static/js/app.js"></script>
	<script src="static/js/common.js"></script>
	<script src="static/js/service/organization/contactdetailservice.js"></script>
	<script src="static/js/controller/organization/contactdetailcontroller.js"></script>
	<script src="static/js/utility/dirPagination.js"></script>
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core componets
			Layout.init(); // init layout
			TableEditable.init();
			setSidebarMenuSelection("contactDetail");
			//	removeSideMenu();
		});
	</script>
	<!-- END Angular JS - PAGE specific controller and service -->
	<!-- END JAVASCRIPTS -->
	</body>
	</html>