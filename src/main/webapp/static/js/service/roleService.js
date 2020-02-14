'use strict';

App.factory('RoleService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllRoles: function() {
					return $http.get('http://localhost:8080/circle/role/')
							.then(
									function(response){
										 console.log(response.data);
		                                    //console.table(response.data);
										return response.data;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching roles');
										return $q.reject(errResponse);
									}
							);
			},
	};

}]);
