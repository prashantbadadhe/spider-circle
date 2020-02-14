'use strict';

App.factory('VillageService', ['$http', '$q', function($http, $q){

	return {
		
		fetchAllVillages: function(itemsPerPage,pagenumber,searchKeyword) {
				
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get('http://localhost:8080/circle/village/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    //console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching villages');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createVillage: function(village){
					return $http.post('http://localhost:8080/circle/village/', village)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating village');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateVillage: function(village, id){
					return $http.put('http://localhost:8080/circle/village/'+id, village)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating village');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteVillage: function(id){
					return $http.delete('http://localhost:8080/circle/village/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting village');
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
								console.error('Error while fetching villages');
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
							console.error('Error while fetching villages');
						return $q.reject(errResponse);
					}
			);
},

fetchAllTalukaList: function() {
	return $http.get('http://localhost:8080/circle/taluka/')
			.then(
					function(response){
						 console.log(response.data);
                            //console.table(response.data);
						return response.data;
					}, 
					function(errResponse){
						 console.log(errResponse);
							console.error('Error while fetching villages');
						return $q.reject(errResponse);
					}
			);
},

fetchAllWardList: function() {
	return $http.get('http://localhost:8080/circle/ward/')
			.then(
					function(response){
						 console.log(response.data);
                            //console.table(response.data);
						return response.data;
					}, 
					function(errResponse){
						 console.log(errResponse);
							console.error('Error while fetching villages');
						return $q.reject(errResponse);
					}
			);
},
getFilteredVillageList:function(searchKeyword,itemsPerPage){
	return $http.get('http://localhost:8080/circle/village/search/'+searchKeyword+'/'+itemsPerPage)
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
