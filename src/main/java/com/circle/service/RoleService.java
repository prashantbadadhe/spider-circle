package com.circle.service;

import java.util.List;

import com.circle.model.RoleModel;

public interface RoleService {
	
	RoleModel findById(long id);
	
	List<RoleModel> findAllRoles(); 
	
}
