'use strict';

App.controller('StateController', ['$scope', 'StateService', function($scope, StateService) {
          var self = this;
							          self.state = {
								id : null,
								country:'',
								name : '',
								code : '',
								descr : '',
								latitude : '',
								longitude : '',
								capital : '',
								district:'',
								createdBy : '',
								updatedBy : ''
							};
          
            self.countries=[];

			self.stateList = [ "Andhra Pradesh", "Arunachal Pradesh", "Assam",
					"Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana",
					"Himachal Pradesh", "Jammu and Kashmir", "Jharkhand",
					"Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra",
					"Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha",
					"Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
					"Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal",
					"Andaman and Nicobar Islands", "Chandigarh",
					"Dadar and Nagar Haveli", "Daman and Diu", "Delhi",
					"Lakshadweep", "Puducherry" ];
            
			
			// Pagintion and sorting
			
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
				self.fetchAllStates(newPageNumber);
			};

			self.filterTable = function(searchKeyword) {
 				if (searchKeyword == null || searchKeyword.length == 0) {
					self.fetchAllStates(self.pageno);
				} else {
					StateService.getFilteredStateList(searchKeyword, self.itemsPerPage).then(function(d) {
						self.states = d.data;
						self.total_count = d.headers("X-Total-Count");
					}, function(errResponse) {
						console.error('Error while fetching Countries');
					});
				}
			};

		 
			
          self.fetchAllCountry=function(){
         	  StateService.fetchAllCountry().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.countries.push(item);
        		  });
        	 
        	  });
          }  ;    
          self.fetchAllStates = function(pagenumber){
        	  StateService.fetchAllStates(self.itemsPerPage, pagenumber,self.searchKeyword)
                  .then(
      					       function(d) {
      					    	   console.log(d);
      					    	 self.states = d.data;
      							self.total_count = d.headers("X-Total-Count");
      					       },
            					function(errResponse){
            						console.error('Error while fetching States');
            					}
      			       );
          };
           
          self.createState = function(state){
        	  StateService.createState(state)
		              .then(
                      self.fetchAllStates(self.pageno), 
				              function(errResponse){
					               console.error('Error while creating State.');
				              }	
                  );
          };

         self.updateState = function(state, id){
        	 StateService.updateState(state, id)
		              .then(
				              self.fetchAllStates(self.pageno), 
				              function(errResponse){
					               console.error('Error while updating State.');
				              }	
                  );
          };

         self.deleteState = function(id){
        	 StateService.deleteState(id)
		              .then(
				              self.fetchAllStates(self.pageno), 
				              function(errResponse){
					               console.error('Error while deleting State.');
				              }	
                  );
           };

          self.fetchAllStates(self.pageno);
         self.fetchAllCountry();
          self.submit = function() {
              if(self.state.id==null){
                  console.log('Saving New State', self.state);    
                  self.createState(self.state);
                  self.fetchAllStates(self.pageno);

              }else{
                  self.updateState(self.state, self.state.id);
                  console.log('State updated with id ', self.state.id);
                  self.fetchAllStates(self.pageno);

              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.states.length; i++){
                  if(self.states[i].id == id) {
                     self.state = angular.copy(self.states[i]);
                     document.getElementById('select2-chosen-1').innerText = self.state.country.name;
                     document.getElementById('select2-chosen-2').innerText = self.state.name;

                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              for(var i = 0; i < self.states.length; i++){
                  if(self.states[i].id == id) {
                     self.reset();
                     break;
                  }
              }
              self.deleteState(id);
              self.fetchAllStates(self.pageno);
          };

          
          self.reset = function(){
           document.getElementById('select2-chosen-1').innerText = 'Country Name';
           document.getElementById('select2-chosen-2').innerText = 'State Name';
 				self.state = {
									id : null,
									country:'',
									name : '',
									code : '',
									descr : '',
									latitude : '',
									longitude : '',
									capital : '',
									district:'',
									createdBy : '',
									updatedBy : ''
								};
              $scope.myForm.$setPristine(); // reset Form
          };

      }]);
