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
import com.circle.dao.organization.ContactDetailDao;
import com.circle.model.organization.ContactDetail;
import com.circle.util.CommonConstant;
import com.circle.util.QueryConstant;

@Repository("contactDetailDao")
public class ContactDetailDaoImpl extends AbstractDao<Long, ContactDetail> implements ContactDetailDao, QueryConstant, CommonConstant {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ContactDetail findById(long id) {
		return getByKey(id);
	}

	@Override
	public ContactDetail findByName(String name) {
		Query query = getSession().createQuery(FIND_BY_NAME_CONTACT_DETAILS);
		query.setString(CONTACT_DETAIL_NAME, name);
		query.setParameter(STATUS, ZERO_CHAR);
		return (ContactDetail) query.uniqueResult();
	}

	@Override
	public void saveContactDetail(ContactDetail contactDetail) {
		persist(contactDetail);

	}

	@Override
	public void updateContactDetail(ContactDetail contactDetail) {
		update(contactDetail);

	}

	@Override
	public int deleteContactDetailById(long id) {
		//Record does not get deleted only status get updated.
		//and record will remain there. 
		//Status can be further  changed manually to make record visible.
		return updateDeleteStatusById(id,COMPONENT_CONTACT_DETAIL);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ContactDetail> findAllContactDetails(String noOfRecord,String pageNumber, String searchKey) {
		Query query = null;

		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(FILTER_QUERY_CONTACT_DETAIL);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
		} else {
			query = getSession().createQuery(" from ContactDetail where status !=:status ");
		}
		if (pageNumber != null && pageNumber.length() > 0 && noOfRecord != null && noOfRecord.length() > 0) {
			query.setFirstResult((Integer.parseInt(pageNumber) - 1) * Integer.parseInt(noOfRecord));
			query.setMaxResults(Integer.parseInt(noOfRecord));
		}
		query.setParameter(STATUS, ZERO_CHAR);
		return (List<ContactDetail>) query.list();
	}


	@Override
	public void deleteAllContactDetails() {
		deleteAllContactDetails();

	}

	@Override
	public boolean isContactDetailExist(ContactDetail contactDetail) {
		List<ContactDetail> list = new ArrayList<>();
		list = get(contactDetail);

		return list.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContactDetail> filteredContactDetail(String searchKey,String itemsPerPage) {
		Query query = null;
		List<ContactDetail> contactDetailList = new ArrayList<>();
		if (searchKey != null && searchKey.length() > ZERO) {
			query = getSession().createQuery(FILTER_QUERY_CONTACT_DETAIL);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
 			contactDetailList = query.list();
		} 
		return contactDetailList;

	}
	
	@Override
	public int countRecords(String searchKey) {
		Query query = null;
		if (!searchKey.equals("noValue")) {
			query = getSession().createQuery(COUNT_CONTACT_DETAIL_WITH_SEARCH);
			query.setParameter(SEARCH_KEY, PERCENT_SYMBOL + searchKey + PERCENT_SYMBOL);
			query.setParameter(STATUS, ZERO_CHAR);
			return ((Long) query.uniqueResult()).intValue();
		} else {
			return ((Long) getSession().createQuery(COUNT_CONTACT_DETAIL).setParameter(STATUS, ZERO_CHAR).uniqueResult()).intValue();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ContactDetail> getContactDetails() {
		 Criteria criteria = createEntityCriteria();
	        return (List<ContactDetail>) criteria.list();
	}

 
}
