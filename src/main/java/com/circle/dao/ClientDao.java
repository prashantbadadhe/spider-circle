package com.circle.dao;

import com.circle.model.ClientModel;

public interface ClientDao {

	ClientModel findById(long id);
}
