'use strict';

App.factory('LoginService', ['$http', '$q', function($http, $q){

	return {
		
		userLogin: function(user){
			return $http.post('http://localhost:8080/circle/login/', user)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('User login failed.');
								return $q.reject(errResponse);
							}
					);
    },
		
	};

}]);
