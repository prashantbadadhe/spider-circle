package com.circle.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.circle.dao.AbstractDao;
import com.circle.dao.UrlDao;
import com.circle.model.UrlModel;

@Repository("UrlDao")
public class UrlDaoImpl  extends AbstractDao<Long, UrlModel> implements UrlDao{

	
	 @Autowired
	    private SessionFactory sessionFactory;
	 protected Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	@Override
	public List<UrlModel> findUrlsByGroupId(long urlGroupId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UrlModel> findChildUrls(long parentUrlId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UrlModel> findUrlGroupsByRole(long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UrlModel> findMenusforGroup(long roleId, long urlGroupId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Object> findUrlAndGroupUrlWithRoleAccess(long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UrlModel findLaunchUrl(long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
