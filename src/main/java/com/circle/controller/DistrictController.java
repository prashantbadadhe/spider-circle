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

import com.circle.model.Country;
import com.circle.model.District;
import com.circle.model.State;
import com.circle.service.DistrictService;
import com.circle.util.CommonConstant;
 
@RestController
public class DistrictController implements CommonConstant{
 
    @Autowired
    DistrictService districtService; 
 
    
    //-------------------Retrieve All District--------------------------------------------------------
     
    @RequestMapping(value = "/district/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<District>> listAllDistrict() {
        List<District> district = districtService.findAllDistricts();
        if(district.isEmpty()){
            return new ResponseEntity<List<District>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<District>>(district, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single District--------------------------------------------------------
     
    @RequestMapping(value = "/district/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<District> getDistrict(@PathVariable("id") long id) {
        System.out.println("Fetching District with id " + id);
        District district = districtService.findById(id);
        if (district == null) {
            System.out.println("District with id " + id + " not found");
            return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<District>(district, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a District--------------------------------------------------------
     
    @RequestMapping(value = "/district/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDistrict(@RequestBody District district,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating District " + district.getName());
 
        if (district.getName()==null || districtService.isDistrictExist(district)) {
            System.out.println("A District with name " + district.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        district.setStatus('1');
        districtService.saveDistrict(district);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/district/{id}").buildAndExpand(district.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 

     
    //------------------- Update a District --------------------------------------------------------
     
    @RequestMapping(value = "/district/{id}", method = RequestMethod.PUT)
    public ResponseEntity<District> updateDistrict(@PathVariable("id") long id, @RequestBody District district) {
        System.out.println("Updating District " + id);
         
        District currentDistrict = districtService.findById(id);
         
        if (currentDistrict==null) {
            System.out.println("District with id " + id + " not found");
            return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
        }
 
        currentDistrict.setName(district.getName());
        currentDistrict.setCode(  district.getCode());
        currentDistrict.setState( district.getState());
        currentDistrict.setHeadQuarter(district.getHeadQuarter());
        currentDistrict.setDescr(district.getDescr());
        currentDistrict.setLatitude(district.getLatitude());
        currentDistrict.setLongitude(district.getLongitude());
        currentDistrict.setCreatedAt(new Date());
        currentDistrict.setUpdatedAt(new Date());
        currentDistrict.setCreatedBy("Abhishek");
        currentDistrict.setUpdatedBy("Abhishek");

         //Set user datails
        districtService.updateDistrict(currentDistrict);
        return new ResponseEntity<District>(currentDistrict, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a District --------------------------------------------------------
     
    @RequestMapping(value = "/district/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<District> deleteDistrict(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting District with id " + id);
 
        District district = districtService.findById(id);
        if (district == null) {
            System.out.println("Unable to delete. District with id " + id + " not found");
            return new ResponseEntity<District>(HttpStatus.NOT_FOUND);
        }
        int deleted =   districtService.deleteDistrictById(id);
        if(deleted>0){
            return new ResponseEntity<District>(HttpStatus.OK);
        }
        return new ResponseEntity<District>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Districts --------------------------------------------------------
     
    @RequestMapping(value = "/district/", method = RequestMethod.DELETE)
    public ResponseEntity<District> deleteAllDistricts() {
        System.out.println("Deleting All Districts");
 
        districtService.deleteAllDistricts();
        return new ResponseEntity<District>(HttpStatus.NO_CONTENT);
    }
   

    @RequestMapping(value = "/district/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<District>> listAllDistrict(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,	@PathVariable("searchKeyword") String searchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        
        List<District> district = districtService.listAllDistrict(noOfRecord,pageNumber,searchKeyword);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  districtService.countRecords(searchKeyword));

        if(district.isEmpty()){
            return new ResponseEntity<List<District>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<District>>(district, headers,HttpStatus.OK);
    }
  
    @RequestMapping(value = "district/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<District>> filteredDistrict(@PathVariable String  searchKey ,@PathVariable String  itemsPerPage ) {
        HttpHeaders headers = new HttpHeaders();
        List<District> district = districtService.filteredDistrict(searchKey,itemsPerPage);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  district.size());
        if(district.isEmpty()){
            return new ResponseEntity<List<District>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<District>>(district, headers, HttpStatus.OK);
    }
 
}