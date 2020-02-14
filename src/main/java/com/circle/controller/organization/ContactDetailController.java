package com.circle.controller.organization;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.circle.model.organization.ContactDetail;
import com.circle.model.organization.Location;
import com.circle.service.organization.ContactDetailService;
import com.circle.util.CommonConstant;
 
@RestController
public class ContactDetailController implements CommonConstant{
 
    @Autowired
    ContactDetailService contactDetailService; 

    @RequestMapping(value = "/contactDetail/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<ContactDetail>> getContactDetails() {
        HttpHeaders headers = new HttpHeaders();
        
    	List<ContactDetail> contactDetail = contactDetailService.getContactDetails();
        if(contactDetail.isEmpty()){
            return new ResponseEntity<List<ContactDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ContactDetail>>(contactDetail, headers,HttpStatus.OK);
    }
    
    //-------------------Retrieve Single ContactDetail--------------------------------------------------------
     
    @RequestMapping(value = "/contactDetail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactDetail> getContactDetail(@PathVariable("id") long id) {
         ContactDetail contactDetail = contactDetailService.findById(id);
        if (contactDetail == null) {
            return new ResponseEntity<ContactDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDetail>(contactDetail, HttpStatus.OK);
    }
    //-------------------Create a ContactDetail--------------------------------------------------------
     
    @RequestMapping(value = "/contactDetail/", method = RequestMethod.POST)
    public ResponseEntity<Void> createContactDetail(@RequestBody ContactDetail contactDetail,    UriComponentsBuilder ucBuilder) {
        if (contactDetail.getFirstPhone()==null && contactDetail.getSecondPhone()==null  || contactDetailService.isContactDetailExist(contactDetail)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //Set status to 1 identify active record
        contactDetail.setStatus('1');
        contactDetail.setCreatedAt(new Date());
        contactDetail.setCreatedBy("Abhishek Gautam");
        contactDetailService.saveContactDetail(contactDetail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/contactDetail/{id}").buildAndExpand(contactDetail.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a ContactDetail --------------------------------------------------------
     
    @RequestMapping(value = "/contactDetail/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContactDetail> updateContactDetail(@PathVariable("id") long id, @RequestBody ContactDetail contactDetail) {
        ContactDetail contact = contactDetailService.findById(id);
        if (contact==null) {
            return new ResponseEntity<ContactDetail>(HttpStatus.NOT_FOUND);
        }
        contact.setFirstName(contactDetail.getFirstName());
        contact.setMiddleName(contactDetail.getMiddleName());
        contact.setLastName(contactDetail.getLastName());
        contact.setNickName(contactDetail.getNickName());
        contact.setFirstPhone(contactDetail.getFirstPhone());
        contact.setSecondPhone(contactDetail.getSecondPhone());
        contact.setEmail(contactDetail.getEmail());
        contact.setAddress(contactDetail.getAddress());
        contact.setProfileImage(contactDetail.getProfileImage());
        //Get client id from login session or from DB to store into table
        contact.setClientId(contactDetail.getClientId());
        contact.setAdditionalInfo(contactDetail.getAdditionalInfo());
        contact.setLocationDetails(contactDetail.getLocationDetails());
        contact.setCreatedAt(new Date());
        contact.setCreatedBy("Abhishek");
        contact.setUpdatedAt(new Date());
        contact.setUpdatedBy("Abhishek");
        contactDetailService.updateContactDetail(contact);
        return new ResponseEntity<ContactDetail>(contact, HttpStatus.OK);
    }
 
    //------------------- Delete a ContactDetail --------------------------------------------------------
    //Record does not get deleted only status or the record changed
     
    @RequestMapping(value = "/contactDetail/{id}", method = RequestMethod.DELETE)
    
    public ResponseEntity<ContactDetail> deleteContactDetail(@PathVariable("id") long id) {
        ContactDetail contactDetail = contactDetailService.findById(id);
        if (contactDetail == null) {
            return new ResponseEntity<ContactDetail>(HttpStatus.NOT_FOUND);
        }
        int deleted =   contactDetailService.deleteContactDetailById(id);
        if(deleted>0){
            return new ResponseEntity<ContactDetail>(HttpStatus.OK);
        }
            return new ResponseEntity<ContactDetail>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Delete All ContactDetails --------------------------------------------------------
     
    @RequestMapping(value = "/contactDetail/", method = RequestMethod.DELETE)
    public ResponseEntity<ContactDetail> deleteAllContactDetails() {
        contactDetailService.deleteAllContactDetails();
        return new ResponseEntity<ContactDetail>(HttpStatus.NO_CONTENT);
    }
   
    @RequestMapping(value = "/contactDetail/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<ContactDetail>> listAllContactDetail(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,	@PathVariable("searchKeyword") String searchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        
    	List<ContactDetail> contactDetail = contactDetailService.findAllContactDetails(noOfRecord,pageNumber, searchKeyword);
    	contactDetail.removeIf(Objects::isNull);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  contactDetailService.countRecord(searchKeyword));
        if(contactDetail.isEmpty()){
            return new ResponseEntity<List<ContactDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ContactDetail>>(contactDetail, headers,HttpStatus.OK);
    }
    
    @RequestMapping(value = "contactDetail/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<ContactDetail>> filteredContactDetail(@PathVariable String  searchKey ,@PathVariable String  itemsPerPage ) {
        HttpHeaders headers = new HttpHeaders();
        List<ContactDetail> contactDetail = contactDetailService.filteredContactDetail(searchKey,itemsPerPage);
		//List<ContactDetail> filterStrings = contactDetail.stream().filter(p -> p != null).collect(Collectors.toList());
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  contactDetail.size());
        if(contactDetail.isEmpty()){
            return new ResponseEntity<List<ContactDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ContactDetail>>(contactDetail, headers, HttpStatus.OK);
    }
 
   
}