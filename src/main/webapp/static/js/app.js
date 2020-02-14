'use strict';

var App = angular.module('myApp',['angularUtils.directives.dirPagination','xeditable','ngDialog','ui.bootstrap']);


App.config(['ngDialogProvider', function (ngDialogProvider) {
    ngDialogProvider.setDefaults({
        className: 'ngdialog-theme-default',
        plain: false,
        showClose: true,
        closeByDocument: true,
        closeByEscape: true,
        appendTo: false,
        disableAnimation: false,
        preCloseCallback: function (value) {
        	$('.page-header-fixed.page-quick-sidebar-over-content.ngdialog-open').removeClass('page-sidebar-closed');
    		$('.page-sidebar-menu').removeClass('page-sidebar-menu-closed');
            console.log('default pre-close callback');
            /* if (confirm('Are you sure you want to close without saving your changes?')) {
                return true;
            }
            return false;*/
            $('#ngdialog1').removeAttr();
        }
    });
}]);

//angular.module('myApp')
//.factory('XSRFInterceptor', function ($cookies, $log) {
//
//  var XSRFInterceptor = {
//
//    request: function(config) {
//
//      var token = $cookies.get('XSRF-TOKEN');
//
//      if (token) {
//        config.headers['X-XSRF-TOKEN'] = token;
//        $log.info("X-XSRF-TOKEN: " + token);
//      }
//
//      return config;
//    }
//  };
//  return XSRFInterceptor;
//});
//
//angular.module('myApp', ['ngCookies', 'ngMessages', 'ui.bootstrap', 'vcRecaptcha'])
//  .config(['$httpProvider', function ($httpProvider) {
//
//    $httpProvider.defaults.withCredentials = true;
//    $httpProvider.interceptors.push('XSRFInterceptor');
//
//  }]);
//
//

