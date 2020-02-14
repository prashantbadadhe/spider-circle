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
import com.circle.dao.StateDao;
import com.circle.model.Country;
import com.circle.model.State;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("stateDao")
public class StateDaoImpl  extends AbstractDao<Long, State> implements StateDao ,CommonConstant,QueryConstant{

	
	 @Autowired
	    private SessionFactory sessionFactory;
	 protected Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	 
	 
	@Override
	public State findById(long id) {
		return getByKey(id);
	}

	@Override
	public State findByName(String name) {
	Query query = getSession().createQuery("from State where name=:stateName");
	query.setString("stateName",name);
		return (State) query.uniqueResult();
	}

	@Override
	public void saveState(State state) {
		persist(state);
		
	}

	@Override
	public void updateState(State state) {
		update(state);
		
	}

	@Override
	public int deleteStateById(long id) {
		//Record does not get deleted only status get updated.
				//and record will remain there. 
				//Status can be further  changed manually to make record visible.
				return updateDeleteStatusById(id,COMPONENT_STATE);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> findAllStates(String noOfRecord,String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY_STATE);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		} else {
			query = getSession().createQuery(" from State where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<State>) query.list();
	}
	@Override
	public void deleteAllStates() {
		deleteAllStates();
		
	}

	@Override
	public boolean isStateExist(State state) {
		List<State> list = new ArrayList<>();
	list= get(state);
	 
return list.isEmpty();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<State> filteredState(String searchKey,String itemsPerPage) {
		Query query = null;
		List<State> stateList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY_STATE);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			stateList = query.list();
		} 
		return stateList;

	}
	@Override
	public int countRecords(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_STATE_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_STATE).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<State> getStates() {
		 Criteria criteria = createEntityCriteria();
	        return (List<State>) criteria.list();
	}

}
