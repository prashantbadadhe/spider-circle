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
import com.circle.dao.DesignationDao;
import com.circle.model.Designation;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("designationDao")
public class DesignationDaoImpl extends AbstractDao<Long, Designation> implements DesignationDao, QueryConstant, CommonConstant {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Designation findById(long id) {
		return getByKey(id);
	}

	@Override
	public Designation findByName(String name) {
		Query query = getSession().createQuery(FIND_BY_NAME);
		query.setString(COUNTRY_NAME, name);
		query.setParameter(STATUS, ZERO_CHAR);
		return (Designation) query.uniqueResult();
	}

	@Override
	public void saveDesignation(Designation designation) {
		persist(designation);

	}

	@Override
	public void updateDesignation(Designation designation) {
		update(designation);

	}

	@Override
	public int deleteDesignationById(long id) {
		//Record does not get deleted only status get updated.
		//and record will remain there. 
		//Status can be further  changed manually to make record visible.
		return updateDeleteStatusById(id,COMPONENT_COUNTRY);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> findAllDesignations(String noOfRecord,String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		} else {
			query = getSession().createQuery(" from Designation where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<Designation>) query.list();
	}


	@Override
	public void deleteAllDesignations() {
		deleteAllDesignations();

	}

	@Override
	public boolean isDesignationExist(Designation designation) {
		List<Designation> list = new ArrayList<>();
		list = get(designation);

		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> filteredDesignation(String searchKey,String itemsPerPage) {
		Query query = null;
		List<Designation> designationList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY_DESIGNATION);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
 			designationList = query.list();
		} 
		return designationList;

	}
	
	@Override
	public int countRecords(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_COUNTRY_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_DESIGNATION).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Designation> getDesignations() {
		 Criteria criteria = createEntityCriteria();
	        return (List<Designation>) criteria.list();
	}

 
}
