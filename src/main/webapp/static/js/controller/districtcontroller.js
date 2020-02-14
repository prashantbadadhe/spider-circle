'use strict';

App.controller('DistrictController', ['$scope', 'DistrictService', function($scope, DistrictService) {
	 var self = this;
			self.district = {
				id : null,
				state : '',
				name : '',
				code : '',
				descr : '',
				latitude : '',
				longitude : '',
				headQuarter : '',
				createdBy : '',
				updatedBy : ''
			};
							self.country = '';
							self.states = [];
							self.countries = [];
  							self.filteredState = [];
			
  							self.sortKey = 'id';
  							self.reverse = true;
  							self.pageno = 1;
  							self.total_count = '';
  							self.itemsPerPage = 2; 
  							self.searchKeyword ='';
  							
  							self.sort = function(keyName) {
  								self.sortKey = keyName;
  								self.reverse = !self.reverse;
  							};
  							self.getData = function(newPageNumber,searchKeyword) {
  								self.searchKeyword =searchKeyword;
  								self.fetchAllDistricts(newPageNumber);
  							};

  							self.filterTable = function(searchKeyword) {
  				 				if (searchKeyword == null || searchKeyword.length == 0) {
  									self.fetchAllDistricts(self.pageno);
  								} else {
  									DistrictService.getFilteredDistrictList(searchKeyword, self.itemsPerPage).then(function(d) {
  										self.districts = d.data;
  										self.total_count = d.headers("X-Total-Count");
  									}, function(errResponse) {
  										console.error('Error while fetching Countries');
  									});
  								}
  							};

  						 
		      self.getStateFromCountry = function(){
	            	self.filteredState = [];
  	            	for(var i=0;i<self.states.length;i++){
  	            		if(self.states[i].country.name==self.country.name){
  	            			self.filteredState.push(self.states[i]);
  	            		}
	            		
	            	}
 	            };
       
            self.fetchAllCounty=function(){
            	DistrictService.fetchAllCountry().then(function(d){
          		  console.log(d);
          		  d.forEach(function(item){
          			  self.countries.push(item);
          		  });
          	 
          	  });
            }  ;   
          self.fetchAllStateList=function(){
        	  
         	  DistrictService.fetchAllStateList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.states.push(item);
        		  });
        	 
        	  });
          }  ;    
          self.fetchAllDistricts = function(pagenumber){
        	  DistrictService.fetchAllDistricts(self.itemsPerPage, pagenumber,self.searchKeyword)
                  .then(
      					       function(d) {
      					    	   console.log(d);
      						        self.districts = d.data;
      						      self.total_count = d.headers("X-Total-Count");
      					       },
            					function(errResponse){
            						console.error('Error while fetching Districts');
            					}
      			       );
          };
           
          self.createDistrict = function(district){
        	  DistrictService.createDistrict(district)
		              .then(
                      self.fetchAllDistricts(self.pageno), 
				              function(errResponse){
					               console.error('Error while creating District.');
				              }	
                  );
          };

         self.updateDistrict = function(district, id){
        	 DistrictService.updateDistrict(district, id)
		              .then(
				              self.fetchAllDistricts(self.pageno), 
				              function(errResponse){
					               console.error('Error while updating District.');
				              }	
                  );
          };

         self.deleteDistrict = function(id){
        	 DistrictService.deleteDistrict(id)
		              .then(
				              self.fetchAllDistricts(self.pageno), 
				              function(errResponse){
					               console.error('Error while deleting District.');
				              }	
                  );
           };

          self.fetchAllDistricts(self.pageno);
         self.fetchAllStateList();
          self.fetchAllCounty();

          self.submit = function() {
              if(self.district.id==null){
                  console.log('Saving New District', self.district);    
                  self.createDistrict(self.district);
                  self.fetchAllDistricts(self.pageno);

              }else{
                  self.updateDistrict(self.district, self.district.id);
                  console.log('District updated with id ', self.district.id);
                  self.fetchAllDistricts(self.pageno);

              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.districts.length; i++){
                  if(self.districts[i].id == id) {
                     self.district = angular.copy(self.districts[i]);
                     document.getElementById('select2-chosen-1').innerText = self.district.state.country.name;
                     document.getElementById('select2-chosen-2').innerText = self.district.state.name;

                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.districts.length; i++){
                  if(self.districts[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteDistrict(id);
          };

          
          self.reset = function(){
              document.getElementById('select2-chosen-1').innerText = 'Country Name';
              document.getElementById('select2-chosen-2').innerText = 'State Name';
		              self.district = {
			id : null,
			state : '',
			name : '',
			code : '',
			descr : '',
			latitude : '',
			longitude : '',
			headQuarter : '',
			createdBy : '',
			updatedBy : ''
		};
              $scope.myForm.$setPristine(); // reset Form
          };
          }]);
