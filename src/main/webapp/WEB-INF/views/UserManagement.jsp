<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Circle</title>  

     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Circle User Registration </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.user.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">User Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.username" name=username class="username form-control input-sm" placeholder="Enter Username" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.username.$error.required">This is a required field</span>
                                      <span ng-show="myForm.username.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.username.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                        
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">First Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.firstName" name="firstName"class=" firstName form-control input-sm" placeholder="Enter your First Name" required ng-minlength="3""/>
                             
                              <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.firstName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.firstName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.firstName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
 							<div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Last Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.lastName" name="lastName" class="lastName form-control input-sm" placeholder="Enter your Last Name" required ng-minlength="3""/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.lastName.$error.required">This is a required field</span>
                                      <span ng-show="myForm.lastName.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.lastName.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Email</label>
                              <div class="col-md-7">
                                  <input type="email" ng-model="ctrl.user.email" name="email" class="email form-control input-sm" placeholder="Enter your Email" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.email.$error.required">This is a required field</span>
                                      <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
   <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Phone</label>
                              <div class="col-md-7">
                                  <input maxlength="10" type="text" ng-model="ctrl.user.phone" name="phone" class="phone form-control input-sm" placeholder="Enter your Phone number" required ng-minlength="10" ng-maxlength="10"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.phone.$error.required">This is a required field</span>
                                      <span ng-show="myForm.phone.$invalid">This field is invalid </span>
                                       <span ng-show="myForm.lastName.$error.minlength">Minimum length required is 10</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Address</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.address" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Username</th>
                              <th>Name</th>
                              <th>Email</th>
                              <th>Phone</th>
                                <th>Address</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.users">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.username"></span></td>
                              <td><span ng-bind="u.firstName" ></span>&nbsp<span ng-bind=" u.lastName" ></span></td>
                              <td><span ng-bind="u.email"></span></td>
                              <td><span ng-bind="u.phone"></span></td>
                              <td><span ng-bind="u.address"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/user_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
  </body>
</html>