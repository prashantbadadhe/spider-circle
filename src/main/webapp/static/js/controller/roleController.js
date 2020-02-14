'use strict';

App.controller('RoleController', ['$scope', 'RoleService', function($scope, RoleService) {
          var self = this;
							          self.role = {
								id : null,
								name : ''
							};
          
            self.roles=[] 

            
          self.fetchAllRoles=function(){
         	  RoleService.fetchAllRoles().then(function(d){
        		  console.log(d);
        		  d.forEach(function(item){
        			  self.roles.push(item);
        		  });
        	 
        	  });
          }  ;    
         
          self.fetchAllRoles = function(){
        	  RoleService.fetchAllRoles()
                  .then(
      					       function(d) {
      					    	   console.log(d);
      						        self.roles = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Roles');
            					}
      			       );
          };
          
          self.fetchAllRoles();
           
      }]);
