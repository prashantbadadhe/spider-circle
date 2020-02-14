'use strict';

App.factory('CountryService', ['$http', '$q', function($http, $q){

	return {
			fetchAllCountries: function(itemsPerPage,pagenumber,searchKeyword) {
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get('http://localhost:8080/circle/country/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    ////console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching countrys');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createCountry: function(country){
					return $http.post('http://localhost:8080/circle/country/', country)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating country');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateCountry: function(country, id){
					return $http.put('http://localhost:8080/circle/country/'+id, country)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating country');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteCountry: function(id){
					return $http.delete('http://localhost:8080/circle/country/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting country');
										return $q.reject(errResponse);
									}
							);
			},
			
			getFilteredCountryList:function(searchKeyword,itemsPerPage){
  				return $http.get('http://localhost:8080/circle/country/search/'+searchKeyword+'/'+itemsPerPage)
				.then(
						function(response){
                                // //console.table(response.data);
							return response;
						}, 
						function(errResponse){
							 console.log(errResponse);
								console.error('Error while fetching countrys');
							return $q.reject(errResponse);
						}
				);
			}

		
	};

}]);
