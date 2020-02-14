'use strict';

App.controller('WardController', ['$scope', 'WardService', function($scope, WardService) {
	 var self = this;
			self.ward = {
				id : null,
				name : '',
				number : '',
				address : '',
				pindcode : '',
				descr:'',
				latitude : '',
				longitude : '',
				population:'',
				area:'',
				taluka:''
			};
							self.country = '';
							self.state = '';
							self.district='';
							
							self.countries = [];
							self.states = [];
							self.districts = [];
							self.talukas=[];
							
							self.filteredState = [];
							self.filteredDistrictList=[];
							self.filteredTalukaList = [];
			
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
  								self.fetchAllWards(newPageNumber);
  							};

  							self.filterTable = function(searchKeyword) {
  				 				if (searchKeyword == null || searchKeyword.length == 0) {
  									self.fetchAllWards(self.pageno);
  								} else {
  									WardService.getFilteredWardList(searchKeyword, self.itemsPerPage).then(function(d) {
  										self.wards = d.data;
  										self.total_count = d.headers("X-Total-Count");
  									}, function(errResponse) {
  										console.error('Error while fetching Countries');
  									});
  								}
  							};

  						 //
							
							
							
		      self.getStateFromCountry = function(){
	            	self.filteredState = [];
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
            self.getTalukaFromDistrict = function(){
            	self.filteredTalukaList= [];
            	for(var i=0;i<self.talukas.length;i++){
            		if(self.talukas[i].district.name==self.district.name){
            		self.filteredTalukaList.push(self.talukas[i]);
            	}
            	}
            };
        
        
            self.fetchAllCounty=function(){
            	WardService.fetchAllCountry().then(function(d){
          		  console.log(d);
          		  d.forEach(function(item){
          			  self.countries.push(item);
          		  });
          	 
          	  });
            }  ;   
          self.fetchAllStateList=function(){
         	  WardService.fetchAllStateList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.states.push(item);
        		  });
        	 
        	  });
          }  ;    
          self.fetchAllDistrictList=function(){
         	  WardService.fetchAllDistrictList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.districts.push(item);
        		  });
        	 
        	  });
          }  ;  
          self.fetchAllTalukaList=function(){
         	  WardService.fetchAllTalukaList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.talukas.push(item);
        		  });
        	 
        	  });
          }  ;  
          self.fetchAllWards = function(pagenumber){
        	  WardService.fetchAllWards(self.itemsPerPage, pagenumber, self.searchKeyword)
                  .then(
      					       function(d) {
      					    	   console.log(d);
      					    	 self.wards = d.data;
      					    	 self.total_count = d.headers("X-Total-Count");
      					       },
            					function(errResponse){
            						console.error('Error while fetching Wards');
            					}
      			       );
          };
           
          self.createWard = function(ward){
        	  WardService.createWard(ward)
		              .then(
                      self.fetchAllWards(self.pageno), 
				              function(errResponse){
					               console.error('Error while creating Ward.');
				              }	
                  );
          };

         self.updateWard = function(ward, id){
        	 WardService.updateWard(ward, id)
		              .then(
				              self.fetchAllWards(self.pageno), 
				              function(errResponse){
					               console.error('Error while updating Ward.');
				              }	
                  );
          };

         self.deleteWard = function(id){
        	 WardService.deleteWard(id)
		              .then(
				              self.fetchAllWards(self.pageno), 
				              function(errResponse){
					               console.error('Error while deleting Ward.');
				              }	
                  );
           };

      
           self.fetchAllCounty();
          self.fetchAllStateList();
          self.fetchAllDistrictList();
          self.fetchAllTalukaList();
          self.fetchAllWards(self.pageno);
          
          self.submit = function() {
              if(self.ward.id==null){
                  console.log('Saving New Ward', self.ward);    
                  self.createWard(self.ward);
                  self.fetchAllWards(self.pageno);

              }else{
                  self.updateWard(self.ward, self.ward.id);
                  console.log('Ward updated with id ', self.ward.id);
                  self.fetchAllWards(self.pageno);

              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.wards.length; i++){
                  if(self.wards[i].id == id) {
                     self.ward = angular.copy(self.wards[i]);
                         document.getElementById('select2-chosen-1').innerText = self.ward.taluka.district.state.country.name;
                     document.getElementById('select2-chosen-2').innerText = self.ward.taluka.district.state.name;
                     document.getElementById('select2-chosen-3').innerText = self.ward.taluka.district.name;
                     document.getElementById('select2-chosen-4').innerText = self.ward.taluka.name;
                     
                     
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.wards.length; i++){
                  if(self.wards[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteWard(id);
          };

          
          self.reset = function(){
        	  document.getElementById('select2-chosen-1').innerText = 'Country Name';
              document.getElementById('select2-chosen-2').innerText = 'State Name';
  			self.ward = {
  					id : null,
  					name : '',
  					number : '',
  					address : '',
  					pindcode : '',
  					descr:'',
  					latitude : '',
  					longitude : '',
  					population:'',
  					area:'',
  					taluka:''
  				};
 
              $scope.myForm.$setPristine(); // reset Form
          };
      
      }]);
