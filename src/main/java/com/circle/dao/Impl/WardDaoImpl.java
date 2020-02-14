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
import com.circle.dao.WardDao;
import com.circle.model.Ward;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("wardDao")
public class WardDaoImpl  extends AbstractDao<Long, Ward> implements WardDao  , CommonConstant, QueryConstant{

	
	 @Autowired
	    private SessionFactory sessionFactory;
	 protected Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	 
	 
	@Override
	public Ward findById(long id) {
		return getByKey(id);
	}

	@Override
	public Ward findByName(String name) {
	Query query = getSession().createQuery("from Ward where name=:wardName");
	query.setString("wardName",name);
		return (Ward) query.uniqueResult();
	}

	@Override
	public void saveWard(Ward ward) {
		persist(ward);
		
	}

	@Override
	public void updateWard(Ward ward) {
		update(ward);
		
	}

	@Override
	public int deleteWardById(long id) {
		//Record does not get deleted only status get updated.
				//and record will remain there. 
				//Status can be further  changed manually to make record visible.
				return updateDeleteStatusById(id,COMPONENT_WARD);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ward> findAllWards() {
		 Criteria criteria = createEntityCriteria();
	        return (List<Ward>) criteria.list();
	}

	@Override
	public void deleteAllWards() {
		deleteAllWards();
		
	}

	@Override
	public boolean isWardExist(Ward ward) {
		List<Ward> list = new ArrayList<>();
	list= get(ward);
	 
return list.isEmpty();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Ward> listAllWard(String noOfRecord, String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = fromQuery(searchKey);
		} else {
			query = getSession().createQuery(" from Ward where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<Ward>) query.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Ward> filteredWard(String searchKey, String itemsPerPage) {
		Query query = null;
		List<Ward> wardList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = fromQuery(searchKey);
			wardList = query.list();
		}
		return wardList;
	}


	private Query fromQuery(String searchKey) {
		Query query;
		query = getSession().createQuery(FILTER_QUERY_WARD);
		query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		query.setParameter(STATUS, ZERO_CHAR);
		return query;
	}


	@Override
	public int countRecord(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_WARD_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_WARD).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}

}
