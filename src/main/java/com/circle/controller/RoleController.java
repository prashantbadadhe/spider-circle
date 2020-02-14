package com.circle.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.circle.model.RoleModel;
import com.circle.service.RoleService;
 
@RestController
public class RoleController {
 
    @Autowired
    RoleService roleService; 
 
    @RequestMapping(value = "/role/", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
    public ResponseEntity<List<RoleModel>> listAllRoles() {
        List<RoleModel> roles = roleService.findAllRoles();
        if(roles.isEmpty()){
            return new ResponseEntity<List<RoleModel>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<RoleModel>>(roles, HttpStatus.OK);
    }
     
}