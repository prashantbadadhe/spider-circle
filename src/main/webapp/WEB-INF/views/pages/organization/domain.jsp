<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="../../tiles/template/defaultHeader.jsp" />
<jsp:include page="../../tiles/template/defaultMenu.jsp" />
<link href=${select2 } rel="stylesheet" type="text/css" />
<link href="static/metronic/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
<link href="static/css/ngDialog/ngDialog.css" rel="stylesheet" type="text/css" />
<link href="static/css/ngDialog/ngDialog-theme-default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="static/css/ngDialog/ngDialog-custom-width.css">

<div class="page-content-wrapper ng-cloak" ng-app="myApp">
	<div class="page-content" ng-controller="DomainController as ctrl">
		
 <div id="countryPageInDomain" ng-controller="CountryController as ctrl1"> 
		<div class="btn btn-default"><button ng-click="ctrl.clickToOpen()">My Modal</button></div>
	<!-- 	<div class="cleatfix"></div>
</div> -->

		<div class="row">
			<div class="col-md-12">
				<div class="portlet box blue">
					<div class="portlet-title">
					
						<div class="caption">
							<i class="fa fa-edit"></i>
							<spring:message code="domain.domainList" />
						</div>
						<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="#portlet-config" data-toggle="modal" class="config">
								</a>
								<a ng-click="ctrl.fetchAllDomains(ctrl.pageno)" href="#" class="reload">
								</a>
								
							</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar">
							<div class="btn-group">

								<label id="filterLabel" for="filterLabel" class="col-md-2 control-label"><spring:message code="common.search" /> </label>
								<div class="col-md-4">
									<input id="filter" name="filter" type="text" class="form-control" ng-model="searchKeyword" ng-change="ctrl.filterTable(searchKeyword)" />
								</div>

							</div>
						</div>
						<table class="table table-hover table-bordered table-condensed" id="sample_editable_1">
							<thead>
								<tr>
									<th ng-click="ctrl.sort('id')"><spring:message code="domain.id" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='id'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th ng-click="ctrl.sort('name')"><spring:message code="domain.name" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='name'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!reverse}"></span></th>
									<th ng-click="ctrl.sort('descr')"><spring:message code="domain.descr" /> <span class="glyphicon sort-icon" ng-show="ctrl.sortKey=='descr'"
										ng-class="{'glyphicon-chevron-up' : ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span></th>
									<th><spring:message code="domain.operation" /></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-show="ctrl.domains.length <= 0">
									<td colspan="8" style="text-align: center;"><spring:message code="common.loading" /></td>
								</tr>
								<tr dir-paginate="u in ctrl.domains | itemsPerPage:ctrl.itemsPerPage|filter: searchKeyword|orderBy:sortKey:ctrl.reverse" total-items="ctrl.total_count">
									<td><span ng-bind="u.id"></span>
									<td><span ng-bind="u.name" ng-show="!myForm.$visible" editable-text="u.name" e-name="name" e-form="myForm" onbeforesave="checkName(u.id)"></span></td>
									<td><span ng-bind="u.descr" ng-show="!myForm.$visible" editable-text=u.descr e-name="descr" e-form="myForm" onbeforesave="checkName( u.id)"></span></td>
									<td style="white-space: nowrap">
										<!-- form -->

										<form editable-form name="myForm" onbeforesave="ctrl.submit($data,u.id)" ng-show="myForm.$visible" class="form-buttons form-inline" shown="ctrl.inserted == u">
											<button type="submit" ng-disabled="myForm.$waiting" class="btn btn-primary" id="btnUpdate" value="update">save</button>
											<button type="button" ng-disabled="myForm.$waiting" ng-click="myForm.$cancel()" class="btn btn-default">cancel</button>
										</form>
										<div class="buttons" ng-show="!myForm.$visible">
											<button class="btn btn-primary" ng-click="myForm.$show()">edit</button>
											<button class="btn btn-danger" ng-click="ctrl.remove($index)">delete</button>
										</div>
									</td>
								</tr>
							</tbody>
							<dir-pagination-controls max-size=ctrl.itemsPerPage direction-links="true" boundry-links="true" on-page-change="ctrl.getData(newPageNumber)"></dir-pagination-controls>
						</table>
						<button class="btn btn-default" ng-click="ctrl.addUser();">Add row</button>
						<div>
							<dir-pagination-controls max-size=ctrl.itemsPerPage direction-links="true" boundry-links="true" on-page-change="ctrl.getData(newPageNumber)"></dir-pagination-controls>
						</div>
					</div>

				</div>

			</div>
		</div>
		<div class="cleatfix"></div>
	</div>
</div>



<jsp:include page="../../tiles/template/defaultFooter.jsp" />

<script src="static/metronic/global/scripts/metronic.js" type="text/javascript"></script>
<script src="static/metronic/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="static/js/app.js"></script>
<script src="static/js/common.js"></script>
<script src="static/js/service/organization/domainservice.js"></script>
<script src="static/js/service/countryService.js"></script>

<script src="static/js/controller/organization/domaincontroller.js"></script>
<script src="static/js/controller/countryController.js"></script>

<script src="static/js/utility/dirPagination.js"></script>

<script>
	jQuery(document).ready(function() {
		Metronic.init(); // init metronic core componets
		Layout.init(); // init layout
		TableEditable.init();
		setSidebarMenuSelection("domain");
		removeSideMenu();
	});
</script>
<!-- END Angular JS - PAGE specific controller and service -->
<!-- END JAVASCRIPTS -->
</body>
</html>