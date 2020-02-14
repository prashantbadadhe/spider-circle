'use strict';

App.factory('TalukaService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllTalukas: function(itemsPerPage,pagenumber,searchKeyword) {
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get('http://localhost:8080/circle/taluka/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    //console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching talukas');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createTaluka: function(taluka){
					return $http.post('http://localhost:8080/circle/taluka/', taluka)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating taluka');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateTaluka: function(taluka, id){
					return $http.put('http://localhost:8080/circle/taluka/'+id, taluka)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating taluka');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteTaluka: function(id){
					return $http.delete('http://localhost:8080/circle/taluka/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting taluka');
										return $q.reject(errResponse);
									}
							);
			},
				fetchAllCountry: function() {
			return $http.get('http://localhost:8080/circle/country/')
					.then(
							function(response){
								 console.log(response.data);
                                    //console.table(response.data);
								return response.data;
							}, 
							function(errResponse){
								 console.log(errResponse);
									console.error('Error while fetching states');
								return $q.reject(errResponse);
							}
					);
	},
	fetchAllStateList: function() {
		return $http.get('http://localhost:8080/circle/state/')
				.then(
						function(response){
							 console.log(response.data);
                                //console.table(response.data);
							return response.data;
						}, 
						function(errResponse){
							 console.log(errResponse);
								console.error('Error while fetching talukas');
							return $q.reject(errResponse);
						}
				);
},

fetchAllDistrictList: function() {
	return $http.get('http://localhost:8080/circle/district/')
			.then(
					function(response){
						 console.log(response.data);
                            //console.table(response.data);
						return response.data;
					}, 
					function(errResponse){
						 console.log(errResponse);
							console.error('Error while fetching talukas');
						return $q.reject(errResponse);
					}
			);
},
getFilteredTalukaList:function(searchKeyword,itemsPerPage){
	return $http.get('http://localhost:8080/circle/taluka/search/'+searchKeyword+'/'+itemsPerPage)
.then(
		function(response){
                 //console.table(response.data);
			return response;
		}, 
		function(errResponse){
					console.error('Error while fetching countrys');
			return $q.reject(errResponse);
		}
);
}

			 
	};

}]);
