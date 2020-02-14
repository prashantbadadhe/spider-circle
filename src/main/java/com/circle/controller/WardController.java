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

import com.circle.model.Ward;
import com.circle.service.WardService;
import com.circle.util.CommonConstant;
 
@RestController
public class WardController  implements CommonConstant{
 
    @Autowired
    WardService wardService; 
 
    @RequestMapping(value = "/ward/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Ward>> listAllWard() {
        List<Ward> ward = wardService.findAllWards();
        if(ward.isEmpty()){
            return new ResponseEntity<List<Ward>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Ward>>(ward, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/ward/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ward> getWard(@PathVariable("id") long id) {
        System.out.println("Fetching Ward with id " + id);
        Ward ward = wardService.findById(id);
        if (ward == null) {
            System.out.println("Ward with id " + id + " not found");
            return new ResponseEntity<Ward>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ward>(ward, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/ward/", method = RequestMethod.POST)
    public ResponseEntity<Void> createWard(@RequestBody Ward ward,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Ward " + ward.getName());
 
        if (ward.getName()==null || wardService.isWardExist(ward)) {
            System.out.println("A Ward with name " + ward.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        ward.setStatus('1');
        wardService.saveWard(ward);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/ward/{id}").buildAndExpand(ward.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value = "/ward/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ward> updateWard(@PathVariable("id") long id, @RequestBody Ward ward) {
        System.out.println("Updating Ward " + id);
         
        Ward currentWard = wardService.findById(id);
         
        if (currentWard==null) {
            System.out.println("Ward with id " + id + " not found");
            return new ResponseEntity<Ward>(HttpStatus.NOT_FOUND);
        }
 
        currentWard.setName(ward.getName());
        currentWard.setTaluka(ward.getTaluka());
        currentWard.setNumber(ward.getNumber());
        currentWard.setAddress(ward.getAddress());
        currentWard.setPincode(ward.getPincode());
        currentWard.setLatitude(ward.getLatitude());
        currentWard.setLongitude(ward.getLongitude());
        currentWard.setPopulation(ward.getPopulation());
        currentWard.setArea(ward.getArea());
         currentWard.setDescr(ward.getDescr());
        currentWard.setCreatedAt(new Date());
        currentWard.setUpdatedAt(new Date());
        currentWard.setCreatedBy("Abhishek");
        currentWard.setUpdatedBy("Abhishek");

        wardService.updateWard(currentWard);
        return new ResponseEntity<Ward>(currentWard, HttpStatus.OK);
    }
 
    
    @RequestMapping(value = "/ward/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ward> deleteWard(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Ward with id " + id);
 
        Ward ward = wardService.findById(id);
        if (ward == null) {
            System.out.println("Unable to delete. Ward with id " + id + " not found");
            return new ResponseEntity<Ward>(HttpStatus.NOT_FOUND);
        }
        int deleted =  wardService.deleteWardById(id);
        if(deleted>0){
            return new ResponseEntity<Ward>(HttpStatus.OK);
        }
    
        return new ResponseEntity<Ward>(HttpStatus.NO_CONTENT);
    }
 
     
    @RequestMapping(value = "/ward/", method = RequestMethod.DELETE)
    public ResponseEntity<Ward> deleteAllWards() {
        System.out.println("Deleting All Wards");
 
        wardService.deleteAllWards();
        return new ResponseEntity<Ward>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/ward/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<Ward>> listAllWard(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,
			@PathVariable("searchKeyword") String searchKeyword) {
		HttpHeaders headers = new HttpHeaders();

		List<Ward> Ward = wardService.listAllWard(noOfRecord, pageNumber, searchKeyword);
		Ward.removeIf(Objects::isNull);
		headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE + wardService.countRecord(searchKeyword));

		if (Ward.isEmpty()) {
			return new ResponseEntity<List<Ward>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ward>>(Ward, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "ward/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
	public ResponseEntity<List<Ward>> filteredWard(@PathVariable String searchKey, @PathVariable String itemsPerPage) {
		HttpHeaders headers = new HttpHeaders();
		List<Ward> Ward = wardService.filteredWard(searchKey, itemsPerPage);
		List<Ward> filterStrings = Ward.stream().filter(p -> p != null).collect(Collectors.toList());

		headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE + filterStrings.size());
		
		
			if (Ward.isEmpty()) {
			return new ResponseEntity<List<Ward>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ward>>(Ward, headers, HttpStatus.OK);
	}
}