package com.circle.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.circle.model.ClientModel;
import com.circle.model.Country;
import com.circle.model.DataVersionModel;
import com.circle.model.District;
import com.circle.model.State;
import com.circle.service.ClientService;
import com.circle.service.DataVersionService;
 
@RestController
@RequestMapping(value="/api")
public class DataVersionController {
 
    @Autowired
    DataVersionService dataVersionService;
    
    @Autowired
    ClientService clientService;
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/syncData", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Map<String,List<Object>>>> listAllUsers(@RequestParam("clientId") long clientId, @RequestParam("lastSyncAt") long lastSyncAt) {

    	
    	List<String> tableNames=new ArrayList<String>();
    	tableNames.add(Country.class.getSimpleName());
    	tableNames.add(State.class.getSimpleName());
    	tableNames.add(District.class.getSimpleName());
    	
    	Map<String,Map<String,List<Object>>> data=new HashMap<String,Map<String,List<Object>>>();
    	ClientModel client=clientService.findById(clientId);
    	DataVersionModel dataVersionModel=dataVersionService.getCurrentDataVersion(client);
    	if(dataVersionModel!=null && dataVersionModel.getCreatedAt().getTime() > lastSyncAt){
    		
    		data=dataVersionService.getData(client,lastSyncAt,tableNames);
    	}
    	
        return new ResponseEntity<Map<String,Map<String,List<Object>>>>(data, HttpStatus.OK);
    }
}
