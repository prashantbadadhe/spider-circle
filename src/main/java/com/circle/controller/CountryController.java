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
import com.circle.service.CountryService;
import com.circle.util.CommonConstant;
 
@RestController
public class CountryController implements CommonConstant{
 
    @Autowired
    CountryService countryService; 

    @RequestMapping(value = "/country/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Country>> getCountries() {
        HttpHeaders headers = new HttpHeaders();
        
    	List<Country> country = countryService.getCountries();
        if(country.isEmpty()){
            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Country>>(country, headers,HttpStatus.OK);
    }
    
    //-------------------Retrieve Single Country--------------------------------------------------------
     
    @RequestMapping(value = "/country/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getCountry(@PathVariable("id") long id) {
         Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }
    //-------------------Create a Country--------------------------------------------------------
     
    @RequestMapping(value = "/country/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCountry(@RequestBody Country country,    UriComponentsBuilder ucBuilder) {
        if (country.getName()==null || countryService.isCountryExist(country)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //Set status to 1 identify active record
        country.setStatus('1');
        countryService.saveCountry(country);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/country/{id}").buildAndExpand(country.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Country --------------------------------------------------------
     
    @RequestMapping(value = "/country/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Country> updateCountry(@PathVariable("id") long id, @RequestBody Country country) {
        Country currentCountry = countryService.findById(id);
        if (currentCountry==null) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }
        currentCountry.setName(country.getName());
        currentCountry.setCode(  country.getCode());
        currentCountry.setIsocode(  country.getIsocode());
        currentCountry.setDescr(country.getDescr());
        currentCountry.setLatitude(country.getLatitude());
        currentCountry.setLongitude(country.getLongitude());
        currentCountry.setCapital(country.getCapital());
        currentCountry.setCreatedAt(new Date());
        currentCountry.setUpdatedAt(new Date());
        currentCountry.setCreatedBy("Abhishek");
        countryService.updateCountry(currentCountry);
        return new ResponseEntity<Country>(currentCountry, HttpStatus.OK);
    }
 
    //------------------- Delete a Country --------------------------------------------------------
    //Record does not get deleted only status or the record changed
     
    @RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE)
    
    public ResponseEntity<Country> deleteCountry(@PathVariable("id") long id) {
        Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }
        int deleted =   countryService.deleteCountryById(id);
        if(deleted>0){
            return new ResponseEntity<Country>(HttpStatus.OK);
        }
            return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Delete All Countries --------------------------------------------------------
     
    @RequestMapping(value = "/country/", method = RequestMethod.DELETE)
    public ResponseEntity<Country> deleteAllCountries() {
        countryService.deleteAllCountries();
        return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
    }
   
    @RequestMapping(value = "/country/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Country>> listAllCountry(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,	@PathVariable("searchKeyword") String searchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        
    	List<Country> country = countryService.findAllCountries(noOfRecord,pageNumber, searchKeyword);
    	country.removeIf(Objects::isNull);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  countryService.countRecord(searchKeyword));
        if(country.isEmpty()){
            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Country>>(country, headers,HttpStatus.OK);
    }
    
    @RequestMapping(value = "country/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Country>> filteredCountry(@PathVariable String  searchKey ,@PathVariable String  itemsPerPage ) {
        HttpHeaders headers = new HttpHeaders();
        List<Country> country = countryService.filteredCountry(searchKey,itemsPerPage);
		List<Country> filterStrings = country.stream().filter(p -> p != null).collect(Collectors.toList());
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  filterStrings.size());
        if(country.isEmpty()){
            return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Country>>(country, headers, HttpStatus.OK);
    }
 
   
}