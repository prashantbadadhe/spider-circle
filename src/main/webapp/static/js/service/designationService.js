'use strict';

App.factory('DesignationService', ['$http', '$q', function($http, $q){

	return {
			fetchAllDesignations: function(itemsPerPage,pagenumber,searchKeyword) {
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get('http://localhost:8080/circle/designation/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    ////console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching designations');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createDesignation: function(designation){
					return $http.post('http://localhost:8080/circle/designation/', designation)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating designation');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateDesignation: function(designation, id){
					return $http.put('http://localhost:8080/circle/designation/'+id, designation)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating designation');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteDesignation: function(id){
					return $http.delete('http://localhost:8080/circle/designation/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting designation');
										return $q.reject(errResponse);
									}
							);
			},
			
			getFilteredDesignationList:function(searchKeyword,itemsPerPage){
  				return $http.get('http://localhost:8080/circle/designation/search/'+searchKeyword+'/'+itemsPerPage)
				.then(
						function(response){
                                // //console.table(response.data);
							return response;
						}, 
						function(errResponse){
							 console.log(errResponse);
								console.error('Error while fetching designations');
							return $q.reject(errResponse);
						}
				);
			}

		
	};

}]);
