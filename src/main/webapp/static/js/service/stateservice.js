'use strict';

App.factory('StateService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllStates: function(itemsPerPage,pagenumber,searchKeyword) {
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get('http://localhost:8080/circle/state/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    //console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching states');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createState: function(state){
					return $http.post('http://localhost:8080/circle/state/', state)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating state');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateState: function(state, id){
					return $http.put('http://localhost:8080/circle/state/'+id, state)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating state');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteState: function(id){
					return $http.delete('http://localhost:8080/circle/state/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting state');
										return $q.reject(errResponse);
									}
							);
			},
			fetchAllCountry: function() {
				return $http.get('http://localhost:8080/circle/country/')
						.then(
								function(response){
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
	    
			
			getFilteredStateList:function(searchKeyword,itemsPerPage){
  				return $http.get('http://localhost:8080/circle/state/search/'+searchKeyword+'/'+itemsPerPage)
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
