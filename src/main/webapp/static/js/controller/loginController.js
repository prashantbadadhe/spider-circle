'use strict';

App.controller('LoginController', [
		'$scope',
		'LoginService',
		function($scope, LoginService) {
			
			self.submit = function() {
				
				if (self.user.username == null || self.user.password == null ) {
					console.log('username or passsowrd should not be empty.', self.country);
					return;
				} else {
					self.userLogin(self.user);
				}
				self.reset();
			};

			
			self.userLogin = function(user) {
				LoginiService.userLogin(user).then(function(errResponse) {
					console.error('Error while login user.');
				});
			};
			
			self.reset = function() {
			};

		} ]);
