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
import com.circle.dao.VillageDao;
import com.circle.model.Village;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("villageDao")
public class VillageDaoImpl extends AbstractDao<Long, Village> implements VillageDao, CommonConstant, QueryConstant {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Village findById(long id) {
		return getByKey(id);
	}

	@Override
	public Village findByName(String name) {
		Query query = getSession().createQuery("from Village where name=:villageName");
		query.setString("villageName", name);
		return (Village) query.uniqueResult();
	}

	@Override
	public void saveVillage(Village village) {
		persist(village);

	}

	@Override
	public void updateVillage(Village village) {
		update(village);

	}

	@Override
	public int deleteVillageById(long id) {
		//Record does not get deleted only status get updated.
				//and record will remain there. 
				//Status can be further  changed manually to make record visible.
				return updateDeleteStatusById(id,COMPONENT_TALUKA);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> findAllVillages() {
		Criteria criteria = createEntityCriteria();
		return (List<Village>) criteria.list();
	}

	@Override
	public void deleteAllVillages() {
		deleteAllVillages();

	}

	@Override
	public boolean isVillageExist(Village village) {
		List<Village> list = new ArrayList<>();
		list = get(village);

		return list.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> listAllVillage(String noOfRecord, String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = fromQuery(searchKey);
		} else {
			query = getSession().createQuery(" from Village where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<Village>) query.list();
	}

	@Override
	public int countRecord(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_VILLAGE_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_VILLAGE).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Village> filteredVillage(String searchKey, String itemsPerPage) {
		Query query = null;
		List<Village> villageList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = fromQuery(searchKey);
			villageList = query.list();
		}
		return villageList;
	}

	private Query fromQuery(String searchKey) {
		Query query;
		query = getSession().createQuery(FILTER_QUERY_VILLAGE);
		query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		query.setParameter(STATUS, ZERO_CHAR);
		return query;
	}

}
