package com.circle.dao;

import java.util.List;

import com.circle.model.RoleModel;

public interface RoleDao {

	RoleModel findById(long id);

	List<RoleModel> findAllRoles();

}
