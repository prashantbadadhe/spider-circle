'use strict';

App.controller('TalukaController', ['$scope', 'TalukaService', function($scope, TalukaService) {
	 var self = this;
			self.taluka = {
				id : null,
				district : '',
				name : '',
				code : '',
				descr : '',
				latitude : '',
				longitude : '',
				city : '',
				createdBy : '',
				updatedBy : ''
			};
							self.country = '';
							self.state = '';
							self.states = [];
							self.countries = [];
							self.districts = [];
							self.filteredDistrictList=[];
							self.filteredState = [];
			
					//pagination
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
  							self.getData = function(newPageNumber,searchKey) {
  							self.searchKeyword =searchKey;
  								self.fetchAllTalukas(newPageNumber);
  							};

  							self.filterTable = function(searchKeyword) {
  				 				if (searchKeyword == null || searchKeyword.length == 0) {
  									self.fetchAllTalukas(self.pageno);
  								} else {
  									TalukaService.getFilteredTalukaList(searchKeyword, self.itemsPerPage).then(function(d) {
  										self.talukas = d.data;
  										self.total_count = d.headers("X-Total-Count");
  									}, function(errResponse) {
  										console.error('Error while fetching Countries');
  									});
  								}
  							};

  						 //
							
							
		      self.getStateFromCountry = function(){
	            	self.filteredState = []
  	            	for(var i=0;i<self.states.length;i++){
  	            		if(self.states[i].country.name==self.country.name){
  	            			self.filteredState.push(self.states[i]);
  	            		}
	            		
	            	}
 	            };
            self.getDistrictFromState = function(){
            	self.filteredDistrictList= [];
            	for(var i=0;i<self.districts.length;i++){
            		if(self.districts[i].state.name==self.state.name){
            		self.filteredDistrictList.push(self.districts[i]);
            	}
            	}
            };
        
            self.fetchAllCounty=function(){
            	TalukaService.fetchAllCountry().then(function(d){
          		  console.log(d);
          		  d.forEach(function(item){
          			  self.countries.push(item);
          		  });
          	 
          	  });
            }  ;   
          self.fetchAllStateList=function(){
         	  TalukaService.fetchAllStateList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.states.push(item);
        		  });
        	 
        	  });
          }  ;    
          self.fetchAllDistrictList=function(){
         	  TalukaService.fetchAllDistrictList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.districts.push(item);
        		  });
        	 
        	  });
          }  ;  
          self.fetchAllTalukas = function(pagenumber){
        	  TalukaService.fetchAllTalukas(self.itemsPerPage, pagenumber, self.searchKeyword)
                  .then(
      					       function(d) {
      					    	   console.log(d);
      						        self.talukas = d.data;
        						      self.total_count = d.headers("X-Total-Count");
      					       },
            					function(errResponse){
            						console.error('Error while fetching Talukas');
            					}
      			       );
          };
           
          self.createTaluka = function(taluka){
        	  TalukaService.createTaluka(taluka)
		              .then(
                      self.fetchAllTalukas(self.pageno), 
				              function(errResponse){
					               console.error('Error while creating Taluka.');
				              }	
                  );
          };

         self.updateTaluka = function(taluka, id){
        	 TalukaService.updateTaluka(taluka, id)
		              .then(
				              self.fetchAllTalukas(self.pageno), 
				              function(errResponse){
					               console.error('Error while updating Taluka.');
				              }	
                  );
          };

         self.deleteTaluka = function(id){
        	 TalukaService.deleteTaluka(id)
		              .then(
				              self.fetchAllTalukas(self.pageno), 
				              function(errResponse){
					               console.error('Error while deleting Taluka.');
				              }	
                  );
           };

         self.fetchAllTalukas(self.pageno);
           self.fetchAllCounty();
          self.fetchAllStateList();
          self.fetchAllDistrictList();

          self.submit = function() {
              if(self.taluka.id==null){
                  console.log('Saving New Taluka', self.taluka);    
                  self.createTaluka(self.taluka);
                  self.fetchAllTalukas(self.pageno);

              }else{
                  self.updateTaluka(self.taluka, self.taluka.id);
                  console.log('Taluka updated with id ', self.taluka.id);
                  self.fetchAllTalukas(self.pageno);

              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.talukas.length; i++){
                  if(self.talukas[i].id == id) {
                     self.taluka = angular.copy(self.talukas[i]);
                     document.getElementById('select2-chosen-1').innerText = self.taluka.district.state.country.name;
                     document.getElementById('select2-chosen-2').innerText = self.taluka.district.state.name;
                     document.getElementById('select2-chosen-3').innerText = self.taluka.district.name;
                     document.getElementById('select2-chosen-4').innerText = self.taluka.city;


                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.talukas.length; i++){
                  if(self.talukas[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteTaluka(id);
          };

          
          self.reset = function(){
        	  document.getElementById('select2-chosen-1').innerText = 'Country Name';
              document.getElementById('select2-chosen-2').innerText = 'State Name';
              document.getElementById('select2-chosen-3').innerText = 'District Name';

		              self.taluka = {
			id : null,
			district : '',
			name : '',
			code : '',
			descr : '',
			latitude : '',
			longitude : '',
			city : '',
			createdBy : '',
			updatedBy : ''
		};
              $scope.myForm.$setPristine(); // reset Form
          };
      
      }]);
