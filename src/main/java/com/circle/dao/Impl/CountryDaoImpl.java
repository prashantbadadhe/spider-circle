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
import com.circle.dao.CountryDao;
import com.circle.model.Country;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("countryDao")
public class CountryDaoImpl extends AbstractDao<Long, Country> implements CountryDao, QueryConstant, CommonConstant {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Country findById(long id) {
		return getByKey(id);
	}

	@Override
	public Country findByName(String name) {
		Query query = getSession().createQuery(FIND_BY_NAME);
		query.setString(COUNTRY_NAME, name);
		query.setParameter(STATUS, ZERO_CHAR);
		return (Country) query.uniqueResult();
	}

	@Override
	public void saveCountry(Country country) {
		persist(country);

	}

	@Override
	public void updateCountry(Country country) {
		update(country);

	}

	@Override
	public int deleteCountryById(long id) {
		//Record does not get deleted only status get updated.
		//and record will remain there. 
		//Status can be further  changed manually to make record visible.
		return updateDeleteStatusById(id,COMPONENT_COUNTRY);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Country> findAllCountries(String noOfRecord,String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		} else {
			query = getSession().createQuery(" from Country where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<Country>) query.list();
	}


	@Override
	public void deleteAllCountries() {
		deleteAllCountries();

	}

	@Override
	public boolean isCountryExist(Country country) {
		List<Country> list = new ArrayList<>();
		list = get(country);

		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Country> filteredCountry(String searchKey,String itemsPerPage) {
		Query query = null;
		List<Country> countryList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
 			countryList = query.list();
		} 
		return countryList;

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
			return ((Long) getSession().createQuery(COUNT_COUNTRY).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Country> getCountries() {
		 Criteria criteria = createEntityCriteria();
	        return (List<Country>) criteria.list();
	}

 
}
