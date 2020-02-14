package com.circle.dao.Impl.organization;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.circle.dao.AbstractDao;
import com.circle.dao.organization.DomainDao;
import com.circle.model.Domain;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("domainDao")
public class DomainDaoImpl extends AbstractDao<Long, Domain> implements DomainDao, QueryConstant, CommonConstant {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Domain findById(long id) {
		return getByKey(id);
	}

	@Override
	public Domain findByName(String name) {
		Query query = getSession().createQuery(FIND_BY_NAME_DOMAIN);
		query.setString(DOMAIN_NAME, name);
		query.setParameter(STATUS, ZERO_CHAR);
		return (Domain) query.uniqueResult();
	}

	@Override
	public void saveDomain(Domain domain) {
		persist(domain);

	}

	@Override
	public void updateDomain(Domain domain) {
		update(domain);

	}

	@Override
	public int deleteDomainById(long id) {
		//Record does not get deleted only status get updated.
		//and record will remain there. 
		//Status can be further  changed manually to make record visible.
		return updateDeleteStatusById(id,COMPONENT_DOMAIN);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> findAllDomains(String noOfRecord,String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY_DOMAIN);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		} else {
			query = getSession().createQuery(" from Domain where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<Domain>) query.list();
	}


	@Override
	public void deleteAllDomains() {
		deleteAllDomains();

	}

	@Override
	public boolean isDomainExist(Domain domain) {
		List<Domain> list = new ArrayList<>();
		list = get(domain);

		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> filteredDomain(String searchKey,String itemsPerPage) {
		Query query = null;
		List<Domain> domainList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY_DOMAIN);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
 			domainList = query.list();
		} 
		return domainList;

	}
	
	@Override
	public int countRecords(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_DOMAIN_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_DOMAIN).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> getDomains() {
		 Criteria criteria = createEntityCriteria();
	        return (List<Domain>) criteria.list();
	}

 
}
