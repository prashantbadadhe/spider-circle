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

import com.circle.model.State;
import com.circle.service.StateService;
import com.circle.util.CommonConstant;
 
@RestController
public class StateController implements CommonConstant {
 
    @Autowired
    StateService stateService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All State--------------------------------------------------------
     
    
    @RequestMapping(value = "/state/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<State>> getStates() {
        HttpHeaders headers = new HttpHeaders();
        
        List<State> state = stateService.getStates();
        if(state.isEmpty()){
            return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<State>>(state, headers,HttpStatus.OK);
    }
 
    
    //-------------------Retrieve Single State--------------------------------------------------------
     
    @RequestMapping(value = "/state/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<State> getState(@PathVariable("id") long id) {
        System.out.println("Fetching State with id " + id);
        State state = stateService.findById(id);
        if (state == null) {
            System.out.println("State with id " + id + " not found");
            return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<State>(state, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a State--------------------------------------------------------
     
    @RequestMapping(value = "/state/", method = RequestMethod.POST)
    public ResponseEntity<Void> createState(@RequestBody State state,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating State " + state.getName());
 
        if (state.getName()==null || stateService.isStateExist(state)) {
            System.out.println("A State with name " + state.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        state.setStatus('1');
        stateService.saveState(state);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/state/{id}").buildAndExpand(state.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a State --------------------------------------------------------
     
    @RequestMapping(value = "/state/{id}", method = RequestMethod.PUT)
    public ResponseEntity<State> updateState(@PathVariable("id") long id, @RequestBody State state) {
        System.out.println("Updating State " + id);
         
        State currentState = stateService.findById(id);
         
        if (currentState==null) {
            System.out.println("State with id " + id + " not found");
            return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
        }
 
        currentState.setName(state.getName());
        currentState.setCode(  state.getCode());
        currentState.setCountry( state.getCountry());

        currentState.setDescr(state.getDescr());
        currentState.setLatitude(state.getLatitude());
        currentState.setLongitude(state.getLongitude());
        currentState.setCapital(state.getCapital());
        currentState.setCreatedAt(new Date());
        currentState.setUpdatedAt(new Date());
        currentState.setCreatedBy("Abhishek");
         //Set user datails
        stateService.updateState(currentState);
        return new ResponseEntity<State>(currentState, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a State --------------------------------------------------------
     
    @RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<State> deleteState(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting State with id " + id);
 
        State state = stateService.findById(id);
        if (state == null) {
            System.out.println("Unable to delete. State with id " + id + " not found");
            return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
        }
        int deleted =   stateService.deleteStateById(id);
        if(deleted>0){
            return new ResponseEntity<State>(HttpStatus.OK);
        }
        stateService.deleteStateById(id);
        return new ResponseEntity<State>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All States --------------------------------------------------------
     
    @RequestMapping(value = "/state/", method = RequestMethod.DELETE)
    public ResponseEntity<State> deleteAllStates() {
        System.out.println("Deleting All States");
 
        stateService.deleteAllStates();
        return new ResponseEntity<State>(HttpStatus.NO_CONTENT);
    }
    

    @RequestMapping(value = "/state/{noOfRecord}/{pageNumber}/{searchKeyword}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<State>> listAllState(@PathVariable("noOfRecord") String noOfRecord, @PathVariable("pageNumber") String pageNumber,	@PathVariable("searchKeyword") String searchKeyword) {
        HttpHeaders headers = new HttpHeaders();
        
        List<State> state = stateService.findAllStates(noOfRecord,pageNumber,searchKeyword);
        state.removeIf(Objects::isNull);
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  stateService.countRecord(searchKeyword));

        if(state.isEmpty()){
            return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<State>>(state, headers,HttpStatus.OK);
    }
 
    @RequestMapping(value = "state/search/{searchKey}/{itemsPerPage}", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<State>> filteredState(@PathVariable String  searchKey ,@PathVariable String  itemsPerPage ) {
        HttpHeaders headers = new HttpHeaders();
        List<State> state = stateService.filteredState(searchKey,itemsPerPage);
		List<State> filterStrings = state.stream().filter(p -> p != null).collect(Collectors.toList());
        headers.add(X_TOTAL_COUNT, EMPTY_DOUBLE_QUOTE +  filterStrings.size());
        if(state.isEmpty()){
            return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<State>>(state, headers, HttpStatus.OK);
    }
 
}