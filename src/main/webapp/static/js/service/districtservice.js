'use strict';

App.factory('DistrictService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllDistricts: function(itemsPerPage,pagenumber,searchKeyword) {
	if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
		searchKeyword = 'noValue';
	}
	
					return $http.get('http://localhost:8080/circle/district/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    ////console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching districts');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createDistrict: function(district){
					return $http.post('http://localhost:8080/circle/district/', district)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating district');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateDistrict: function(district, id){
					return $http.put('http://localhost:8080/circle/district/'+id, district)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating district');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteDistrict: function(id){
					return $http.delete('http://localhost:8080/circle/district/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting district');
										return $q.reject(errResponse);
									}
							);
			},
			fetchAllStateList: function() {
				return $http.get('http://localhost:8080/circle/state/')
						.then(
								function(response){
	                                  //  //console.table(response.data);
									return response.data;
								}, 
								function(errResponse){
									 console.log(errResponse);
										console.error('Error while fetching districts');
									return $q.reject(errResponse);
								}
						);
		},
	    
		fetchAllCountry: function() {
			return $http.get('http://localhost:8080/circle/country/')
					.then(
							function(response){
                                   // //console.table(response.data);
								return response.data;
							}, 
							function(errResponse){
								 console.log(errResponse);
									console.error('Error while fetching states');
								return $q.reject(errResponse);
							}
					);
	},
	
	getFilteredDistrictList:function(searchKeyword,itemsPerPage){
				return $http.get('http://localhost:8080/circle/district/search/'+searchKeyword+'/'+itemsPerPage)
			.then(
					function(response){
                           //  //console.table(response.data);
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
