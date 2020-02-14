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
import com.circle.dao.DistrictDao;
import com.circle.model.Country;
import com.circle.model.District;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("districtDao")
public class DistrictDaoImpl  extends AbstractDao<Long, District> implements DistrictDao, CommonConstant,QueryConstant{

	
	 @Autowired
	    private SessionFactory sessionFactory;
	 protected Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	 
	 
	@Override
	public District findById(long id) {
		return getByKey(id);
	}

	@Override
	public District findByName(String name) {
	Query query = getSession().createQuery("from District where name=:districtName");
	query.setString("districtName",name);
		return (District) query.uniqueResult();
	}

	@Override
	public void saveDistrict(District district) {
		persist(district);
		
	}

	@Override
	public void updateDistrict(District district) {
		update(district);
		
	}

	@Override
	public int deleteDistrictById(long id) {
		//Record does not get deleted only status get updated.
				//and record will remain there. 
				//Status can be further  changed manually to make record visible.
				return updateDeleteStatusById(id,COMPONENT_DISTRICT);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> findAllDistricts() {
		 Criteria criteria = createEntityCriteria();
	        return (List<District>) criteria.list();
	}

	@Override
	public void deleteAllDistricts() {
		deleteAllDistricts();
		
	}

	@Override
	public boolean isDistrictExist(District district) {
		List<District> list = new ArrayList<>();
	list= get(district);
	 
return list.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<District> listAllDistrict(String noOfRecord,String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY_DISTRICT);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		} else {
			query = getSession().createQuery(" from District where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<District>) query.list();
	}


	@Override
	public int countRecord(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_DISTRICT_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_DISTRICT).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<District> filteredDistrict(String searchKey, String itemsPerPage) {
		Query query = null;
		List<District> districtList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY_DISTRICT);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
 			districtList = query.list();
		} 
		return districtList;
	}

}
