package com.circle.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.circle.dao.AbstractDao;
import com.circle.dao.RoleDao;
import com.circle.model.RoleModel;

@Repository("RoleDao")
public class RoleDaoImpl  extends AbstractDao<Long, RoleModel> implements RoleDao{

	
	 @Autowired
	    private SessionFactory sessionFactory;
	 protected Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	 

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleModel> findAllRoles() {
		 Criteria criteria = createEntityCriteria();
	        return (List<RoleModel>) criteria.list();
	}


	@Override
	public RoleModel findById(long id) {
		return getByKey(id);
	}

	
	
}
