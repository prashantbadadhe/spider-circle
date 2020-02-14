'use strict';

App.factory('ContactDetailService', ['$http', '$q', function($http, $q){
 var baseUri = 'http://localhost:8080/circle/contactDetail/';
	return {
			fetchAllContactDetails: function(itemsPerPage,pagenumber,searchKeyword) {
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
 										console.error('Error while fetching contactDetails');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createContactDetail: function(contactDetail){
					return $http.post(baseUri, contactDetail)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating contactDetail');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateContactDetail: function(data, id){
		    //	angular.extend(data, {id: id});
					return $http.put('http://localhost:8080/circle/contactDetail/'+id, data)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating contactDetail');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteContactDetail: function(id){
					return $http.delete(baseUri+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting contactDetail');
										return $q.reject(errResponse);
									}
							);
			},
			
			getFilteredContactDetailList:function(searchKeyword,itemsPerPage){
  				return $http.get(baseUri+'search/'+searchKeyword+'/'+itemsPerPage)
				.then(
						function(response){
                               //  //console.table(response.data);
							return response;
						}, 
						function(errResponse){
							 console.log(errResponse);
								console.error('Error while fetching contactDetails');
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
								console.error('Error while fetching country');
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
							console.error('Error while fetching state');
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
						console.error('Error while fetching taluka');
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
						console.error('Error while fetching taluka');
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
						console.error('Error while fetching Ward');
					return $q.reject(errResponse);
				}
		);
},
fetchAllVillages:function(){
return $http.get('http://localhost:8080/circle/village/')
.then(
		function(response){
			 console.log(response.data);
                //console.table(response.data);
			return response.data;
		}, 
		function(errResponse){
			 console.log(errResponse);
				console.error('Error while fetching Ward');
			return $q.reject(errResponse);
		}
);
},
		
	};

}]);
