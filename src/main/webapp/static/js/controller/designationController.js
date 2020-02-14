'use strict';

App.controller('DesignationController', [
		'$scope',
		'DesignationService','$rootScope','ngDialog',
		function($scope, DesignationService,$rootScope,ngDialog) {
			var self = this;
			self.designation = {id : null,name : '',descr : '',sortOrder : '',level : '',createdBy : '',updatedBy : ''};
			self.count =0;
			$rootScope.$on('CallParentMethod', function(){
				console.log('Designation called');
				if(self.count ===0){
					self.count+=1;
				     self.parentmethod();
				}
		      
		        });
			self.parentmethod = function () {
		          $rootScope.theme ='ngdialog-theme-default some-other-class';
		          $scope.value = true;
		          ngDialog.open({
		              template: 'designation',
		              scope: $scope,
	                    cache: false,
		              closeByDocument: false,
		              width: '1000px',
		              className: 'ngdialog-theme-default some-other-class'
		          });
		      };
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
				self.fetchAllDesignations(newPageNumber);
			};

			self.filterTable = function(searchKeyword) {
				console.log("filterkeyword: " + searchKeyword);
				if (searchKeyword == null || searchKeyword.length == 0) {
					self.fetchAllDesignations(self.pageno);
				} else {
					DesignationService.getFilteredDesignationList(searchKeyword, self.itemsPerPage).then(function(d) {
						self.countries = d.data;
						self.total_count = d.headers("X-Total-Count");
					}, function(errResponse) {
						console.error('Error while fetching Designations');
					});
				}
			};

			self.countries = [];
			self.fetchAllDesignations = function(pagenumber) {
				DesignationService.fetchAllDesignations(self.itemsPerPage, pagenumber,self.searchKeyword).then(function(d) {
					self.countries = d.data;
					self.total_count = d.headers("X-Total-Count");
				}, function(errResponse) {
					console.error('Error while fetching Designations');
				});
			};

			self.createDesignation = function(designation) {
				DesignationService.createDesignation(designation).then(self.fetchAllDesignations(self.pageno), function(errResponse) {
					console.error('Error while creating Designation.');
				});
			};

			self.updateDesignation = function(designation, id) {
				DesignationService.updateDesignation(designation, id).then(self.fetchAllDesignations(self.pageno), function(errResponse) {
					console.error('Error while updating Designation.');
				});
			};

			self.deleteDesignation = function(id) {
				DesignationService.deleteDesignation(id).then(self.fetchAllDesignations(self.pageno), function(errResponse) {
					console.error('Error while deleting Designation.');
				});
			};

			self.fetchAllDesignations(self.pageno);

			self.submit = function() {
				if (self.designation.id == null) {
					console.log('Saving New Designation', self.designation);
					self.createDesignation(self.designation);
					self.fetchAllDesignations(self.pageno);

				} else {
					self.updateDesignation(self.designation, self.designation.id);
					console.log('Designation updated with id ', self.designation.id);
					self.fetchAllDesignations(self.pageno);

				}
				self.reset();
			};

			self.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < self.countries.length; i++) {
					if (self.countries[i].id == id) {
						self.designation = angular.copy(self.countries[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				for (var i = 0; i < self.countries.length; i++) {
					if (self.countries[i].id == id) {
						self.reset();
						break;
					}
				}
				self.deleteDesignation(id);
			};

			self.reset = function() {
				self.designation = {id : null,name : '',descr : '',sortOrder : '',level: '',createdBy : '',updatedBy : ''};
				$scope.myForm.$setPristine(); 
			};
			
		
			
		} ]);
