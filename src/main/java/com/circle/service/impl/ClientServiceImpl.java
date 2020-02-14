package com.circle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.ClientDao;
import com.circle.model.ClientModel;
import com.circle.service.ClientService;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;
	
	@Override
	public ClientModel findById(long id) {
		return clientDao.findById(id);
	}

}
