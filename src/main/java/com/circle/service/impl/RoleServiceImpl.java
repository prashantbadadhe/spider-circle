package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.RoleDao;
import com.circle.model.RoleModel;
import com.circle.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public RoleModel findById(long id) {
		return roleDao.findById(id);
	}
	
	
	@Override
	public List<RoleModel> findAllRoles() {
		return roleDao.findAllRoles();
	}
}
