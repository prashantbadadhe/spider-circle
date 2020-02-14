package com.circle.controller;
 
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

import com.circle.model.Designation;
import com.circle.service.DesignationService;
import com.circle.util.CommonConstant;
 
@RestController
public class DesignationController implements CommonConstant{
 
    @Autowired
    DesignationService designationService; 

    @RequestMapping(value = "/designation/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Designation>> getDesignations() {
        HttpHeaders headers = new HttpHeaders();
        
    	List<Designation> designation = designationService.getDesignations();
        if(designation.isEmpty()){
            return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Designation>>(designation, headers,HttpStatus.OK);
    }
    
    //-------------------Retrieve Single Designation--------------------------------------------------------
     
    @RequestMapping(value = "/designation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Designation> getDesignation(@PathVariable("id") long id) {
         Designation designation = designationService.findById(id);
        if (designation == null) {
            return new ResponseEntity<Designation>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Designation>(designation, HttpStatus.OK);
    }
    //-------------------Create a Designation--------------------------------------------------------
     
    @RequestMapping(value = "/designation/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDesignation(@RequestBody Designation designation,    UriComponentsBuilder ucBuilder) {
        if (designation.getName()==null || designationService.isDesignationExist(designation)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //Set status to 1 identify active record
        designation.setStatus('1');
        designationService.saveDesignation(designation);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/designation/{id}").buildAndExpand(designation.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Designation --------------------------------------------------------
     
    @RequestMapping(value = "/designation/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Designation> updateDesignation(@PathVariable("id") long id, @RequestBody Designation designation) {
        Designation currentDesignation = designationService.findById(id);
        if (currentDesignation==null) {
            return new ResponseEntity<Designation>(HttpStatus.NOT_FOUND);
        }
        currentDesignation.setName(designation.getName());
        currentDesignation.setDescr(designation.getDescr());
        currentDesignation.setSortOrder(designation.getSortOrder());
        currentDesignation.setLevel(designation.getLevel());
        
        currentDesignation.setCreatedAt(new Date());
        currentDesignation.setUpdatedAt(new Date());
        currentDesignation.setCreatedBy("Abhishek");
        designationService.updateDesignation(currentDesignation);
        return new ResponseEntity<Designation>(currentDesignation, HttpStatus.OK);
    }
 
    //------------------- Delete a Designation --------------------------------------------------------
    //Record does not get deleted only status or the record changed
     
    @RequestMapping(value = "/designation/{id}", method = RequestMethod.DELETE)
    
    public ResponseEntity<Designation> deleteDesignation(@PathVariable("id") long id) {
        Designation designation = designationService.findById(id);
        if (designation == null) {
            return new ResponseEntity<Designation>(HttpStatus.NOT_FOUND);
        }
        int deleted =   designationService.deleteDesignationById(id);
        if(deleted>0){
            return new ResponseEntity<Designation>(HttpStatus.OK);
        }
            return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Delete All Designations --------------------------------------------------------
     
    @RequestMapping(value = "/designation/", method = RequestMethod.DELETE)
    public ResponseEntity<Designation> deleteAllDesignations() {
        designationService.deleteAllDesignations();
        return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
    }
   
    @RequestMapping(value = "/designation/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Designation>> listAllDesignation(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,	@PathVariable("searchKeyword") String searchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        
    	List<Designation> designation = designationService.findAllDesignations(noOfRecord,pageNumber, searchKeyword);
    	designation.removeIf(Objects::isNull);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  designationService.countRecord(searchKeyword));
        if(designation.isEmpty()){
            return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Designation>>(designation, headers,HttpStatus.OK);
    }
    
    @RequestMapping(value = "designation/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Designation>> filteredDesignation(@PathVariable String  searchKey ,@PathVariable String  itemsPerPage ) {
        HttpHeaders headers = new HttpHeaders();
        List<Designation> designation = designationService.filteredDesignation(searchKey,itemsPerPage);
		List<Designation> filterStrings = designation.stream().filter(p -> p != null).collect(Collectors.toList());
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  filterStrings.size());
        if(designation.isEmpty()){
            return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Designation>>(designation, headers, HttpStatus.OK);
    }
 
   
}