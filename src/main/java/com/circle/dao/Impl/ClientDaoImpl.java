package com.circle.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.circle.dao.AbstractDao;
import com.circle.dao.ClientDao;
import com.circle.model.ClientModel;

@Repository("ClientDao")
public class ClientDaoImpl extends AbstractDao<Long, ClientModel> implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ClientModel findById(long id) {
		return getByKey(id);
	}
}
