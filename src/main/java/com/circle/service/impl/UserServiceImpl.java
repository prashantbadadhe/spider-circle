package com.circle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.circle.dao.UserDao;
import com.circle.model.UserModel;
import com.circle.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public List<UserModel> findAllUsers() {
		return userDao.findAllUsers();
	}
	
	public UserModel findById(long id) {
		
		return userDao.findById(id);
	}
	
	public UserModel findByName(String userName) {
		return userDao.findByName(userName);
	}
	
	public void saveUser(UserModel user) {
		userDao.saveUser(user);
	}

	public void updateUser(UserModel user) {
		userDao.updateUser(user);
	}

	public void deleteUserById(long id) {
		
	userDao.deleteUserById(id);
	}

	public boolean isUserExist(UserModel user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		userDao.deleteAllUsers();
	}

	@Override
	public UserModel findUserFromCredentials(UserModel user) {
		return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	 
}
