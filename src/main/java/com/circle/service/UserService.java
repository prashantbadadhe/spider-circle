package com.circle.service;

import java.util.List;

import com.circle.model.UserModel;



public interface UserService {
	
	UserModel findById(long id);
	
	UserModel findByName(String name);
	
	void saveUser(UserModel user);
	
	void updateUser(UserModel user);
	
	void deleteUserById(long id);

	List<UserModel> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(UserModel user);
	
	public UserModel findUserFromCredentials(UserModel user);
	
}
