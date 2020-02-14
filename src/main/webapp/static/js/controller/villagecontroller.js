'use strict';

App.controller('VillageController', ['$scope', 'VillageService', function($scope, VillageService) {
	 var self = this;
			self.village = {
				id : null,
				name : '',
 				address : '',
				pindcode : '',
				iscity:'',
				descr:'',
				latitude : '',
				longitude : '',
  				ward:''
			};
							self.country = '';
							self.state = '';
							self.district='';
							self.ward='';
							
							self.countries = [];
							self.states = [];
							self.districts = [];
							self.talukas=[];
							self.wards=[];
							
							self.filteredState = [];
							self.filteredDistrictList=[];
							self.filteredTalukaList = [];
							self.filteredWardList = [];
			
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
  								self.fetchAllVillages(newPageNumber);
  							};

  							self.filterTable = function(searchKeyword) {
  				 				if (searchKeyword == null || searchKeyword.length == 0) {
  									self.fetchAllVillages(self.pageno);
  								} else {
  									VillageService.getFilteredVillageList(searchKeyword, self.itemsPerPage).then(function(d) {
  										self.villages = d.data;
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
            self.getWardFromTaluka = function(){
            	self.filteredWardList= [];
            	for(var i=0;i<self.wards.length;i++){
            		if(self.wards[i].taluka.name==self.taluka.name){
            		self.filteredWardList.push(self.wards[i]);
            	}
            	}
            };
        
        
            self.fetchAllCounty=function(){
            	VillageService.fetchAllCountry().then(function(d){
          		  console.log(d);
          		  d.forEach(function(item){
          			  self.countries.push(item);
          		  });
          	 
          	  });
            }  ;   
          self.fetchAllStateList=function(){
         	  VillageService.fetchAllStateList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.states.push(item);
        		  });
        	 
        	  });
          }  ;    
          self.fetchAllDistrictList=function(){
         	  VillageService.fetchAllDistrictList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.districts.push(item);
        		  });
        	 
        	  });
          }  ;  
          self.fetchAllTalukaList=function(){
         	  VillageService.fetchAllTalukaList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.talukas.push(item);
        		  });
        	 
        	  });
          }  ;  
          self.fetchAllWardList=function(){
         	  VillageService.fetchAllWardList().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.wards.push(item);
        		  });
        	 
        	  });
          }  ;  
          self.fetchAllVillages = function(pagenumber){
        	  VillageService.fetchAllVillages(self.itemsPerPage, pagenumber, self.searchKeyword)
        	   .then(
					       function(d) {
					    	   console.log(d);
					    	 self.villages = d.data;
					    	 self.total_count = d.headers("X-Total-Count");
					       },
    					function(errResponse){
    						console.error('Error while fetching Wards');
    					}
			       );
          };
           
          self.createVillage = function(village){
        	  VillageService.createVillage(village)
		              .then(
                      self.fetchAllVillages(self.pageno), 
				              function(errResponse){
					               console.error('Error while creating Village.');
				              }	
                  );
          };

         self.updateVillage = function(village, id){
        	 VillageService.updateVillage(village, id)
		              .then(
				              self.fetchAllVillages(self.pageno), 
				              function(errResponse){
					               console.error('Error while updating Village.');
				              }	
                  );
          };

         self.deleteVillage = function(id){
        	 VillageService.deleteVillage(id)
		              .then(
				              self.fetchAllVillages(self.pageno), 
				              function(errResponse){
					               console.error('Error while deleting Village.');
				              }	
                  );
           };

      
           self.fetchAllCounty();
          self.fetchAllStateList();
          self.fetchAllDistrictList();
          self.fetchAllTalukaList();
          self.fetchAllWardList();
          self.fetchAllVillages(self.pageno);
          
          self.submit = function() {
              if(self.village.id==null){
                  console.log('Saving New Village', self.village);    
                  self.createVillage(self.village);
                  self.fetchAllVillages(self.pageno);
              }else{
                  self.updateVillage(self.village, self.village.id);
                  console.log('Village updated with id ', self.village.id);
                  self.fetchAllVillages(self.pageno);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.villages.length; i++){
                  if(self.villages[i].id == id) {
                     self.village = angular.copy(self.villages[i]);
                     document.getElementById('select2-chosen-1').innerText = self.village.ward.taluka.district.state.country.name;
                     document.getElementById('select2-chosen-2').innerText = self.village.ward.taluka.district.state.name;
                     document.getElementById('select2-chosen-3').innerText = self.village.ward.taluka.district.name;
                     document.getElementById('select2-chosen-4').innerText = self.village.ward.taluka.name;
                     document.getElementById('select2-chosen-5').innerText = self.village.ward.name;

                    

                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.villages.length; i++){
                  if(self.villages[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteVillage(id);
          };

          
          self.reset = function(){
        	  document.getElementById('select2-chosen-1').innerText = 'Country Name';
              document.getElementById('select2-chosen-2').innerText = 'State Name';
      		self.village = {
    				id : null,
    				name : '',
     				address : '',
    				pindcode : '',
    				iscity:'',
    				descr:'',
    				latitude : '',
    				longitude : '',
      				ward:''
    			};
      		
              $scope.myForm.$setPristine(); // reset Form
          };
      
      }]);
