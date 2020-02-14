'use strict';

App.factory('DomainService', ['$http', '$q', function($http, $q){
 var baseUri = 'http://localhost:8080/circle/domain/';
	return {
			fetchAllDomains: function(itemsPerPage,pagenumber,searchKeyword) {
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get(baseUri+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                   ////console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching domains');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createDomain: function(domain){
					return $http.post(baseUri, domain)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating domain');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateDomain: function(data, id){
		    //	angular.extend(data, {id: id});
					return $http.put('http://localhost:8080/circle/domain/'+id, data)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating domain');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteDomain: function(id){
					return $http.delete(baseUri+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting domain');
										return $q.reject(errResponse);
									}
							);
			},
			
			getFilteredDomainList:function(searchKeyword,itemsPerPage){
  				return $http.get(baseUri+'search/'+searchKeyword+'/'+itemsPerPage)
				.then(
						function(response){
                               //  //console.table(response.data);
							return response;
						}, 
						function(errResponse){
							 console.log(errResponse);
								console.error('Error while fetching domains');
							return $q.reject(errResponse);
						}
				);
			}

		
	};

}]);
