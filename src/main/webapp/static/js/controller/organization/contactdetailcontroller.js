'use strict';

App.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});
App.controller('ContactDetailController', [ '$scope', 'ContactDetailService', 'ngDialog', '$rootScope', function($scope, ContactDetailService, ngDialog, $rootScope) {
	var self = this;
	self.contactDetail = {
		id : null,
		firstName : '',
		middleName : '',
		lastName : '',
		firstPhone : '',
		secondPhone:'',
		email:'',
		address:'',
		additionalInfo : '',
		locationDetails:''
		};
	
//------------------------------Religion--------------------------------------------------------------------
	self.religions=['Hindu','Muslim','Shikh','Jain','Baudh','Christian '];
	
//---------------------------------------------------------------------------------------------------------------	
	
	// Pagintion and sorting
	
	self.sortKey = 'id';
	self.reverse = true;
	self.pageno = 1;
	self.total_count = '';
	self.itemsPerPage = 10; 
	self.searchKeyword ='';
	self.locationSelected  = false;
	self.designations = ["first","second","third","four"];
	self.organizations = ["Congress","BJP","Cognizant","HDFC"];
	self.locationType= ["Please select","Country","State","District","Takula","Ward","Village"];
	self.country='';
	self.state='';
	self.district='';
	self.taluka='';
	self.ward='';
	self.village='';
	self.otherType='';
	self.otherlocation='';
	self.countries = [];
	self.states = [];
	self.districts = [];
	self.talukas = [];
	self.wards = [];
	self.villages = [];
	//filtering locations
	self.filteredState = [];
	self.filteredDistrictList=[];
	self.filteredTalukaList = [];
 	self.filteredWardList = [];
	self.filteredVillageList= [];
	
//---------------------------------------------------------------------------	
	
	self.selectionCheck = function(location){
		if(location!="Please select"){
			self.locationSelected = true;
		}else{
			self.locationSelected = false;
		}
	};
	//////
	self.sort = function(keyName) {
		self.sortKey = keyName;
		self.reverse = !self.reverse;
	};
	self.getData = function(newPageNumber,searchKeyword) {
		self.searchKeyword =searchKeyword;
		self.fetchAllContactDetails(newPageNumber);
	};

	self.filterTable = function(searchKeyword) {
			if (searchKeyword == null || searchKeyword.length == 0) {
			self.fetchAllContactDetails(self.pageno);
		} else {
			ContactDetailService.getFilteredStateList(searchKeyword, self.itemsPerPage).then(function(d) {
				self.states = d.data;
				self.total_count = d.headers("X-Total-Count");
			}, function(errResponse) {
				console.error('Error while fetching Countries');
			});
		}
	};

	///
	
	//Show selected value on the dropdown button
	self.checkValue = function(value,id ){
		$("#"+id)[0].innerText = value;
	}
	self.setLocationValues = function(value,id,componentName){
		$("#"+id)[0].innerText = value;
		switch(componentName) {
	    case "country":
	         self.country = value;
	        break;
	    case "state":
	    	self.state = value;
	        break;
	    case "district":
	    	self.district = value;
	        break;
	    case "taluka":
	    	self.taluka = value; 
	        break;
	    case "ward":
	    	self.ward = value;
	        break;
	    case "village":
	    	self.country = value;
	        break;
	    case "locationType":
	         self.otherType = value;
	        break;
	}
	
	}
	//-------------------------------------------------------
	self.filterTable = function(searchKeyword) {
		console.log("filterkeyword: " + searchKeyword);
		if (searchKeyword == null || searchKeyword.length == 0) {
			self.fetchAllContactDetails(self.pageno);
		} else {
			ContactDetailService.getFilteredContactDetailList(searchKeyword, self.itemsPerPage).then(function(d) {
				self.contactDetails = d.data;
				self.total_count = d.headers("X-Total-Count");
			}, function(errResponse) {
				console.error('Error while fetching ContactDetails');
			});
		}
	};

	self.contactDetails = [];
	self.fetchAllContactDetails = function(pagenumber) {
		ContactDetailService.fetchAllContactDetails(self.itemsPerPage, pagenumber, self.searchKeyword).then(function(d) {
			self.contactDetails = d.data;
			self.total_count = d.headers("X-Total-Count");
		}, function(errResponse) {
			console.error('Error while fetching ContactDetails');
		});
	};

	self.createContactDetail = function(contactDetail) {
		ContactDetailService.createContactDetail(contactDetail).then(self.fetchAllContactDetails(self.pageno), function(errResponse) {
			console.error('Error while creating ContactDetail.');
		});
	};

	self.updateContactDetail = function(contactDetail, id) {
		ContactDetailService.updateContactDetail(contactDetail, id).then(self.fetchAllContactDetails(self.pageno), function(errResponse) {
			console.error('Error while updating ContactDetail.');
		});
	};

	self.deleteContactDetail = function(id) {
		ContactDetailService.deleteContactDetail(id).then(self.fetchAllContactDetails(self.pageno), function(errResponse) {
			console.error('Error while deleting ContactDetail.');
		});
	};
	
	///Location fetching services..
	 self.fetchAllCountry = function() {
		ContactDetailService.fetchAllCountry().then(function(d) {d.forEach(function(item) {self.countries.push(item);	});
		});
	};
	   self.fetchAllStateList = function() {
			ContactDetailService.fetchAllStateList().then(function(d) {	d.forEach(function(item) {self.states.push(item);});
			});
		};   
	 
	     self.fetchAllDistrictList=function(){
	    	 ContactDetailService.fetchAllDistrictList().then(function(d){  d.forEach(function(item){ self.districts.push(item);  });
	   	 
	   	  });
	     }  ;  
	     self.fetchAllTalukaList=function(){
	    	 ContactDetailService.fetchAllTalukaList().then(function(d){d.forEach(function(item){self.talukas.push(item); });
	   	 
	   	  });
	     }  ;  
	     self.fetchAllWardList=function(){
	    	 ContactDetailService.fetchAllWardList().then(function(d){ d.forEach(function(item){ self.wards.push(item); });
	   	  });
	     }  ;  
	     self.fetchAllVillages = function(){
	    	 ContactDetailService.fetchAllVillages().then(function(d) { d.forEach(function(item){self.villages.push(item);});
	   	  });
	     }  ;  
    self.getStateFromCountry = function(countryName){
    	self.filteredState = [];
      	for(var i=0;i<self.states.length;i++){
      		if(self.states[i].country.name==countryName){
      			self.filteredState.push(self.states[i]);}
    	}
      	if(self.filteredState.length==0){
      		self.filteredState.push({"name":"No state found"});
      	}
     };
     self.getDistrictFromState = function(state){
     	self.filteredDistrictList= [];
     	for(var i=0;i<self.districts.length;i++){
     		if(self.districts[i].state.name==state){
     		self.filteredDistrictList.push(self.districts[i]);
     	}
     	}
     	if(self.filteredDistrictList.length==0){
      		self.filteredDistrictList.push({"name":"No District found"});
      	}
     };

     self.getTalukaFromDistrict = function(district){
     	self.filteredTalukaList= [];
     	for(var i=0;i<self.talukas.length;i++){
     		if(self.talukas[i].district.name==district){
     		self.filteredTalukaList.push(self.talukas[i]);
     	}
     	}
    	if(self.filteredTalukaList.length==0){
      		self.filteredTalukaList.push({"name":"No Taluka found"});
      	}
     };
     self.getWardFromTaluka = function(taluka){
     	self.filteredWardList= [];
     	for(var i=0;i<self.wards.length;i++){
     		if(self.wards[i].taluka.name==taluka){
     		self.filteredWardList.push(self.wards[i]);
     	}
     	}
     	if(self.filteredWardList.length==0){
      		self.filteredWardList.push({"name":"No Ward found"});
      	}
     };
     self.getVillageFromWard = function(ward){
      	self.filteredVillageList= [];
      	for(var i=0;i<self.villages.length;i++){
      		if(self.villages[i].ward.name==ward){
      		self.filteredVillageList.push(self.villages[i]);
      	}
      	}
      	if(self.filteredVillageList.length==0){
       		self.filteredVillageList.push({"name":"No Village found"});
       	}
      };
	  
     
     
     
   ///Location fetching services..ends here
	self.fetchAllContactDetails(self.pageno);
	  self.fetchAllCountry();
	  self.fetchAllStateList ();
	  self.fetchAllDistrictList();
	  self.fetchAllTalukaList();
	  self.fetchAllWardList();
	  self.fetchAllVillages();
	  
	self.submit = function() {
		if(self.otherType.length>0){
			self.contactDetail.locationDetails = [{
				"locationType":self.otherType,"locationValue":self.otherlocation
			}];
		}else{
			self.contactDetail.locationDetails = [{
				"locationType":"country","locationValue":self.country
			},{
				"locationType":"state","locationValue":self.state
			},{
				"locationType":"district","locationValue":self.district
			},{
				"locationType":"taluka","locationValue":self.taluka
			},{
				"locationType":"ward","locationValue":self.ward
			},{
				"locationType":"village","locationValue":self.village
			} 
	];
		}
		
		   if(self.contactDetail.id==null){
			console.log('Saving New ContactDetail', self.contactDetail);
			self.createContactDetail(self.contactDetail);
			self.fetchAllContactDetails(self.pageno);
			alert("Record Saved Successfully");
		} else {
			self.updateContactDetail(self.contactDetail, self.contactDetail.id);
			console.log('ContactDetail updated with id ', self.contactDetail.id);
			self.fetchAllContactDetails(self.pageno);
			alert("Record updated Successfully");
		}
		   
		   self.reset();
	};

	self.edit = function(id) {
		console.log('id to be edited', id);
		for (var i = 0; i < self.contactDetails.length; i++) {
			if (self.contactDetails[i].id == id) {
				self.contactDetail = angular.copy(self.contactDetails[i]);
				break;
			}
		}
	};

	self.remove = function(id) {
		console.log('id to be deleted', id);
		self.deleteContactDetail(id);
	//	self.fetchAllContactDetails(self.pageno);
	}
	  
    self.reset = function(){
    	self.contactDetail = {
    			id : null,
    			firstName : '',
    			middleName : '',
    			lastName : '',
    			firstPhone : '',
    			secondPhone:'',
    			email:'',
    			address:'',
    			additionalInfo : '',
    			};
        $scope.myForm.$setPristine(); // reset Form
    };
	 
} ]);
