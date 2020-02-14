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

import com.circle.model.Village;
import com.circle.service.VillageService;
import com.circle.util.CommonConstant;
 
@RestController
public class VillageController implements CommonConstant {
 
    @Autowired
    VillageService villageService; 
 
    @RequestMapping(value = "/village/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Village>> listAllVillage() {
        List<Village> village = villageService.findAllVillages();
        if(village.isEmpty()){
            return new ResponseEntity<List<Village>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Village>>(village, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/village/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Village> getVillage(@PathVariable("id") long id) {
        System.out.println("Fetching Village with id " + id);
        Village village = villageService.findById(id);
        if (village == null) {
            System.out.println("Village with id " + id + " not found");
            return new ResponseEntity<Village>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Village>(village, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/village/", method = RequestMethod.POST)
    public ResponseEntity<Void> createVillage(@RequestBody Village village,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Village " + village.getName());
 
        if (village.getName()==null || villageService.isVillageExist(village)) {
            System.out.println("A Village with name " + village.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        village.setStatus('1');
        villageService.saveVillage(village);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/village/{id}").buildAndExpand(village.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    @RequestMapping(value = "/village/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Village> updateVillage(@PathVariable("id") long id, @RequestBody Village village) {
        System.out.println("Updating Village " + id);
         
        Village currentVillage = villageService.findById(id);
         
        if (currentVillage==null) {
            System.out.println("Village with id " + id + " not found");
            return new ResponseEntity<Village>(HttpStatus.NOT_FOUND);
        }
 
        currentVillage.setName(village.getName());
        currentVillage.setWard(village.getWard());
        currentVillage.setCity(village.isCity());
        currentVillage.setAddress(village.getAddress());
        currentVillage.setPincode(village.getPincode());
        currentVillage.setLatitude(village.getLatitude());
        currentVillage.setLongitude(village.getLongitude());
         currentVillage.setDescr(village.getDescr());
        currentVillage.setCreatedAt(new Date());
        currentVillage.setUpdatedAt(new Date());
        currentVillage.setCreatedBy("Abhishek");
        currentVillage.setUpdatedBy("Abhishek");

        villageService.updateVillage(currentVillage);
        return new ResponseEntity<Village>(currentVillage, HttpStatus.OK);
    }
 
    
    @RequestMapping(value = "/village/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Village> deleteVillage(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Village with id " + id);
 
        Village village = villageService.findById(id);
        if (village == null) {
            System.out.println("Unable to delete. Village with id " + id + " not found");
            return new ResponseEntity<Village>(HttpStatus.NOT_FOUND);
        }
        int deleted =      villageService.deleteVillageById(id);
        if(deleted>0){
            return new ResponseEntity<Village>(HttpStatus.OK);
        }
     
        return new ResponseEntity<Village>(HttpStatus.NO_CONTENT);
    }
 
     
    @RequestMapping(value = "/village/", method = RequestMethod.DELETE)
    public ResponseEntity<Village> deleteAllVillages() {
        System.out.println("Deleting All Villages");
 
        villageService.deleteAllVillages();
        return new ResponseEntity<Village>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(value = "/village/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
   	public ResponseEntity<List<Village>> listAllVillage(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,
   			@PathVariable("searchKeyword") String searchKeyword) {
   		HttpHeaders headers = new HttpHeaders();

   		List<Village> village = villageService.listAllVillage(noOfRecord, pageNumber, searchKeyword);
   		village.removeIf(Objects::isNull);
   		headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE + villageService.countRecord(searchKeyword));

   		if (village.isEmpty()) {
   			return new ResponseEntity<List<Village>>(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<List<Village>>(village, headers, HttpStatus.OK);
   	}

   	@RequestMapping(value = "village/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = { "Accept=text/xml, application/json" })
   	public ResponseEntity<List<Village>> filteredVillage(@PathVariable String searchKey, @PathVariable String itemsPerPage) {
   		HttpHeaders headers = new HttpHeaders();
   		List<Village> village = villageService.filteredVillage(searchKey, itemsPerPage);
   		List<Village> filterStrings = village.stream().filter(p -> p != null).collect(Collectors.toList());

   		headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE + filterStrings.size());
   		
   		
   			if (village.isEmpty()) {
   			return new ResponseEntity<List<Village>>(HttpStatus.NO_CONTENT);
   		}
   		return new ResponseEntity<List<Village>>(village, headers, HttpStatus.OK);
   	}
}