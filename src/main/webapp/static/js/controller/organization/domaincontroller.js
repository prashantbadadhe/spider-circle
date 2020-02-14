'use strict';

App.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});
App.controller('DomainController', [ '$scope', 'DomainService','ngDialog', '$rootScope',function($scope, DomainService,ngDialog,$rootScope) {
	var self = this;
	self.domain = {
		id : null,
		name : '',
		descr : ''
	};


	// Pagintion and sorting

	self.sortKey = 'id';
	self.reverse = false;
	self.pageno = 1;
	self.total_count = '';
	self.itemsPerPage = 5;
	self.searchKeyword = '';
	self.newPageNumber = '';
	self.sort = function(keyName) {
		self.sortKey = keyName;
		self.reverse = !self.reverse;
	};
	self.getData = function(newPageNumber, searchKeyword) {
		self.searchKeyword = searchKeyword;
		self.newPageNumber = newPageNumber;
		self.fetchAllDomains(newPageNumber);
	};

	// add new record
	self.addUser = function() {
		self.inserted = {
			id : 'new*',
			name : '',
			descr : ''
		};
		self.domains.splice(0, 0, self.inserted);
	};

	self.filterTable = function(searchKeyword) {
		console.log("filterkeyword: " + searchKeyword);
		if (searchKeyword == null || searchKeyword.length == 0) {
			self.fetchAllDomains(self.pageno);
		} else {
			DomainService.getFilteredDomainList(searchKeyword, self.itemsPerPage).then(function(d) {
				self.domains = d.data;
				self.total_count = d.headers("X-Total-Count");
			}, function(errResponse) {
				console.error('Error while fetching Domains');
			});
		}
	};

	self.domains = [];
	self.fetchAllDomains = function(pagenumber) {
		DomainService.fetchAllDomains(self.itemsPerPage, pagenumber, self.searchKeyword).then(function(d) {
			self.domains = d.data;
			self.total_count = d.headers("X-Total-Count");
		}, function(errResponse) {
			console.error('Error while fetching Domains');
		});
	};

	self.createDomain = function(domain) {
		DomainService.createDomain(domain).then(self.fetchAllDomains(self.pageno), function(errResponse) {
			console.error('Error while creating Domain.');
		});
	};

	self.updateDomain = function(domain, id) {
		DomainService.updateDomain(domain, id).then(self.fetchAllDomains(self.pageno), function(errResponse) {
			console.error('Error while updating Domain.');
		});
	};

	self.deleteDomain = function(id) {
		DomainService.deleteDomain(id).then(self.fetchAllDomains(self.pageno), function(errResponse) {
			console.error('Error while deleting Domain.');
		});
	};

	self.fetchAllDomains(self.pageno);

	self.submit = function(data, id) {

		if (id == 'new*') {
			console.log('Saving New Domain', data);
			self.createDomain(data);
			self.fetchAllDomains(self.pageno);

		} else {
			self.updateDomain(data, id);
			console.log('Domain updated with id ', id);
			self.fetchAllDomains(self.pageno);

		}
	};

	self.edit = function(id) {
		console.log('id to be edited', id);
		for (var i = 0; i < self.domains.length; i++) {
			if (self.domains[i].id == id) {
				self.domain = angular.copy(self.domains[i]);
				document.getElementById('select2-chosen-1').innerText = self.domain.name;
				break;
			}
		}
	};

	self.remove = function(id) {
		console.log('id to be deleted', id);
		for (var i = 0; i < self.domains.length; i++) {
			if (self.domains[i].id == 'new*') {
				self.domains.splice(i, 1);
				break;
			}
		}
		self.deleteDomain(id);
		self.fetchAllDomains(self.pageno);
	};

	 self.clickToOpen = function () {
		 $rootScope.$emit('CallParentMethod');
		 /*
          $rootScope.theme = 'ngdialog-theme-plain custom-width';

          ngDialog.open({
              template: 'domain',
              controller: 'DomainController',
              closeByDocument: false,
              width: 'auto'
          });*/
      };
	 
} ]);
