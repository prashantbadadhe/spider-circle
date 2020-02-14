package com.circle.controller.organization;
 
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

import com.circle.model.Domain;
import com.circle.service.organization.DomainService;
import com.circle.util.CommonConstant;
 
@RestController
public class DomainController implements CommonConstant{
 
    @Autowired
    DomainService domainService; 

    @RequestMapping(value = "/domain/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Domain>> getDomains() {
        HttpHeaders headers = new HttpHeaders();
        
    	List<Domain> domain = domainService.getDomains();
        if(domain.isEmpty()){
            return new ResponseEntity<List<Domain>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Domain>>(domain, headers,HttpStatus.OK);
    }
    
    //-------------------Retrieve Single Domain--------------------------------------------------------
     
    @RequestMapping(value = "/domain/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Domain> getDomain(@PathVariable("id") long id) {
         Domain domain = domainService.findById(id);
        if (domain == null) {
            return new ResponseEntity<Domain>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Domain>(domain, HttpStatus.OK);
    }
    //-------------------Create a Domain--------------------------------------------------------
     
    @RequestMapping(value = "/domain/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDomain(@RequestBody Domain domain,    UriComponentsBuilder ucBuilder) {
        if (domain.getName()==null || domainService.isDomainExist(domain)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //Set status to 1 identify active record
        domain.setStatus('1');
        domain.setCreatedAt(new Date());
        domain.setCreatedBy("Abhishek Gautam");
        domainService.saveDomain(domain);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/domain/{id}").buildAndExpand(domain.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Domain --------------------------------------------------------
     
    @RequestMapping(value = "/domain/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Domain> updateDomain(@PathVariable("id") long id, @RequestBody Domain domain) {
        Domain currentDomain = domainService.findById(id);
        if (currentDomain==null) {
            return new ResponseEntity<Domain>(HttpStatus.NOT_FOUND);
        }
        currentDomain.setName(domain.getName());
        currentDomain.setDescr(domain.getDescr());
        currentDomain.setUpdatedAt(new Date());
        currentDomain.setUpdatedBy("Abhishek");
        domainService.updateDomain(currentDomain);
        return new ResponseEntity<Domain>(currentDomain, HttpStatus.OK);
    }
 
    //------------------- Delete a Domain --------------------------------------------------------
    //Record does not get deleted only status or the record changed
     
    @RequestMapping(value = "/domain/{id}", method = RequestMethod.DELETE)
    
    public ResponseEntity<Domain> deleteDomain(@PathVariable("id") long id) {
        Domain domain = domainService.findById(id);
        if (domain == null) {
            return new ResponseEntity<Domain>(HttpStatus.NOT_FOUND);
        }
        int deleted =   domainService.deleteDomainById(id);
        if(deleted>0){
            return new ResponseEntity<Domain>(HttpStatus.OK);
        }
            return new ResponseEntity<Domain>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Delete All Domains --------------------------------------------------------
     
    @RequestMapping(value = "/domain/", method = RequestMethod.DELETE)
    public ResponseEntity<Domain> deleteAllDomains() {
        domainService.deleteAllDomains();
        return new ResponseEntity<Domain>(HttpStatus.NO_CONTENT);
    }
   
    @RequestMapping(value = "/domain/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Domain>> listAllDomain(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,	@PathVariable("searchKeyword") String searchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        
    	List<Domain> domain = domainService.findAllDomains(noOfRecord,pageNumber, searchKeyword);
    	domain.removeIf(Objects::isNull);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  domainService.countRecord(searchKeyword));
        if(domain.isEmpty()){
            return new ResponseEntity<List<Domain>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Domain>>(domain, headers,HttpStatus.OK);
    }
    
    @RequestMapping(value = "domain/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<Domain>> filteredDomain(@PathVariable String  searchKey ,@PathVariable String  itemsPerPage ) {
        HttpHeaders headers = new HttpHeaders();
        List<Domain> domain = domainService.filteredDomain(searchKey,itemsPerPage);
		//List<Domain> filterStrings = domain.stream().filter(p -> p != null).collect(Collectors.toList());
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  domain.size());
        if(domain.isEmpty()){
            return new ResponseEntity<List<Domain>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Domain>>(domain, headers, HttpStatus.OK);
    }
 
   
}