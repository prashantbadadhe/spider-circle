'use strict';

App.factory('WardService', ['$http', '$q', function($http, $q){

	return {
		
			fetchAllWards: function(itemsPerPage,pagenumber,searchKeyword) {
				if(searchKeyword==undefined || searchKeyword=="" ||  searchKeyword==null){
					searchKeyword = 'noValue';
				}
					return $http.get('http://localhost:8080/circle/ward/'+itemsPerPage+'/'+pagenumber+'/'+searchKeyword)
							.then(
									function(response){
		                                    //console.table(response.data);
										return response;
									}, 
									function(errResponse){
										 console.log(errResponse);
 										console.error('Error while fetching wards');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createWard: function(ward){
					return $http.post('http://localhost:8080/circle/ward/', ward)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating ward');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateWard: function(ward, id){
					return $http.put('http://localhost:8080/circle/ward/'+id, ward)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating ward');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteWard: function(id){
					return $http.delete('http://localhost:8080/circle/ward/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting ward');
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
								console.error('Error while fetching wards');
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
							console.error('Error while fetching wards');
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
							console.error('Error while fetching wards');
						return $q.reject(errResponse);
					}
			);
},
getFilteredWardList:function(searchKeyword,itemsPerPage){
	return $http.get('http://localhost:8080/circle/ward/search/'+searchKeyword+'/'+itemsPerPage)
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
