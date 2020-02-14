package com.circle.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.circle.dao.AbstractDao;
import com.circle.dao.UserDao;
import com.circle.model.UserModel;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao<Long, UserModel> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserModel findById(long id) {
		return getByKey(id);
	}

	@Override
	public UserModel findByName(String name) {
		Query query = getSession().createQuery("from User where username=:username");
		query.setString("username", name);
		return (UserModel) query.uniqueResult();
	}

	@Override
	public void saveUser(UserModel user) {
		persist(user);

	}

	@Override
	public void updateUser(UserModel user) {
		update(user);

	}

	@Override
	public void deleteUserById(long id) {
		delete(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		return (List<UserModel>) criteria.list();
	}

	@Override
	public void deleteAllUsers() {
		deleteAllUsers();

	}

	@Override
	public boolean isUserExist(UserModel user) {
		List<UserModel> list = new ArrayList<>();
		list = get(user);

		return list.isEmpty();
	}

	@Override
	public UserModel findUserFromCredentials(UserModel user) {
		return findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public UserModel findByUsernameAndPassword(String username, String password) {
		Query query = getSession().createQuery("from UserModel where username=:username and password=:password");
		query.setString("username", username);
		query.setString("password", password);
		return (UserModel) query.uniqueResult();
	}

}
