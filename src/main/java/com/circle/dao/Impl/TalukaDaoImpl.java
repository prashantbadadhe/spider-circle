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
import com.circle.dao.TalukaDao;
import com.circle.model.Taluka;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("talukaDao")
public class TalukaDaoImpl extends AbstractDao<Long, Taluka> implements TalukaDao, CommonConstant, QueryConstant {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Taluka findById(long id) {
		return getByKey(id);
	}

	@Override
	public Taluka findByName(String name) {
		Query query = getSession().createQuery("from Taluka where name=:talukaName");
		query.setString("talukaName", name);
		return (Taluka) query.uniqueResult();
	}

	@Override
	public void saveTaluka(Taluka taluka) {
		persist(taluka);

	}

	@Override
	public void updateTaluka(Taluka taluka) {
		update(taluka);

	}

	@Override
	public int deleteTalukaById(long id) {
		//Record does not get deleted only status get updated.
		//and record will remain there. 
		//Status can be further  changed manually to make record visible.
		return updateDeleteStatusById(id,COMPONENT_TALUKA);


	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taluka> findAllTalukas() {
		Criteria criteria = createEntityCriteria();
		return (List<Taluka>) criteria.list();
	}

	@Override
	public void deleteAllTalukas() {
		deleteAllTalukas();

	}

	@Override
	public boolean isTalukaExist(Taluka taluka) {
		List<Taluka> list = new ArrayList<>();
		list = get(taluka);

		return list.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taluka> listAllTaluka(String noOfRecord, String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY_TALUKA);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			booleanCondition(searchKey, query);
		} else {
			query = getSession().createQuery(" from Taluka where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<Taluka>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taluka> filteredTaluka(String searchKey, String itemsPerPage) {
		Query query = null;
		List<Taluka> talukaList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY_TALUKA);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			booleanCondition(searchKey, query);
			query.setParameter(STATUS, ZERO_CHAR);
			talukaList = query.list();
		}
		return talukaList;
	}

	@Override
	public int countRecord(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_TALUKA_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			booleanCondition(searchKey, query);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_TALUKA).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}

	private void booleanCondition(String searchKey, Query query) {
		if (searchKey.charAt(0)=='t' || searchKey.charAt(0)=='T') {
			query.setParameter(BOOLEAN_SEARCH_KEY, true);
		} else if (searchKey.charAt(0)=='f' || searchKey.charAt(0)=='F') {
			query.setParameter(BOOLEAN_SEARCH_KEY, false);
		} else {
			query.setParameter(BOOLEAN_SEARCH_KEY, null);
		}
	}

}
