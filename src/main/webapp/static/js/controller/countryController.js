'use strict';

App.controller('CountryController', [
		'$scope',
		'CountryService','$rootScope','ngDialog',
		function($scope, CountryService,$rootScope,ngDialog) {
			var self = this;
			self.country = {id : null,name : '',code : '',isocode : '',descr : '',latitude : '',longitude : '',capital : '',state : '',createdBy : '',updatedBy : ''};
			self.count =0;
			$rootScope.$on('CallParentMethod', function(){
				console.log('Country called');
				if(self.count ===0){
					self.count+=1;
				     self.parentmethod();
				}
		      
		        });
			self.parentmethod = function () {
		          $rootScope.theme ='ngdialog-theme-default some-other-class';
		          $scope.value = true;
		          ngDialog.open({
		              template: 'country',
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
				self.fetchAllCountries(newPageNumber);
			};

			self.filterTable = function(searchKeyword) {
				console.log("filterkeyword: " + searchKeyword);
				if (searchKeyword == null || searchKeyword.length == 0) {
					self.fetchAllCountries(self.pageno);
				} else {
					CountryService.getFilteredCountryList(searchKeyword, self.itemsPerPage).then(function(d) {
						self.countries = d.data;
						self.total_count = d.headers("X-Total-Count");
					}, function(errResponse) {
						console.error('Error while fetching Countries');
					});
				}
			};

			self.countries = [];
			self.fetchAllCountries = function(pagenumber) {
				CountryService.fetchAllCountries(self.itemsPerPage, pagenumber,self.searchKeyword).then(function(d) {
					self.countries = d.data;
					self.total_count = d.headers("X-Total-Count");
				}, function(errResponse) {
					console.error('Error while fetching Countries');
				});
			};

			self.createCountry = function(country) {
				CountryService.createCountry(country).then(self.fetchAllCountries(self.pageno), function(errResponse) {
					console.error('Error while creating Country.');
				});
			};

			self.updateCountry = function(country, id) {
				CountryService.updateCountry(country, id).then(self.fetchAllCountries(self.pageno), function(errResponse) {
					console.error('Error while updating Country.');
				});
			};

			self.deleteCountry = function(id) {
				CountryService.deleteCountry(id).then(self.fetchAllCountries(self.pageno), function(errResponse) {
					console.error('Error while deleting Country.');
				});
			};

			self.fetchAllCountries(self.pageno);

			self.submit = function() {
				if (self.country.id == null) {
					console.log('Saving New Country', self.country);
					self.createCountry(self.country);
					self.fetchAllCountries(self.pageno);

				} else {
					self.updateCountry(self.country, self.country.id);
					console.log('Country updated with id ', self.country.id);
					self.fetchAllCountries(self.pageno);

				}
				self.reset();
			};

			self.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < self.countries.length; i++) {
					if (self.countries[i].id == id) {
						self.country = angular.copy(self.countries[i]);
						document.getElementById('select2-chosen-1').innerText = self.country.name;
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
				self.deleteCountry(id);
			};

			self.reset = function() {
				document.getElementById('select2-chosen-1').innerText = 'Country Name';
				self.country = {id : null,name : '',code : '',isocode : '',descr : '',latitude : '',longitude : '',capital : '',state : '',createdBy : '',updatedBy : ''};
				$scope.myForm.$setPristine(); 
			};
			
		
			self.countryNames = [ "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
					"Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
					"Bosnia &amp; Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon",
					"Cape Verde", "Cayman Islands", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cruise Ship", "Cuba",
					"Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Estonia",
					"Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia",
					"Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras",
					"Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan",
					"Kazakhstan", "Kenya", "Kuwait", "Kyrgyz Republic", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
					"Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mauritania", "Mauritius", "Mexico", "Moldova", "Monaco", "Mongolia",
					"Montenegro", "Montserrat", "Morocco", "Mozambique", "Namibia", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua",
					"Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal",
					"Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre &amp; Miquelon", "Samoa", "San Marino", "Satellite", "Saudi Arabia", "Senegal",
					"Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "South Africa", "South Korea", "Spain", "Sri Lanka", "St Kitts &amp; Nevis",
					"St Lucia", "St Vincent", "St. Lucia", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
					"Timor L'Este", "Togo", "Tonga", "Trinidad &amp; Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks &amp; Caicos", "Uganda", "Ukraine",
					"United Arab Emirates", "United Kingdom", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe" ];
		} ]);
