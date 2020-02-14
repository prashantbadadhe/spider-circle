package com.circle.dao.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.circle.dao.AbstractDao;
import com.circle.dao.DataVersionDao;
import com.circle.model.ClientModel;
import com.circle.model.DataVersionModel;
import com.circle.util.CommonConstant;

@Repository("DataVersionDao")
public class DataVersionDaoImpl extends AbstractDao<Long, DataVersionModel> implements DataVersionDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public DataVersionModel getCurrentDataVersion(ClientModel client) {
		Query query = getSession().createQuery(
				" SELECT coalesce(MAX(dvm.createdAt),0) FROM DataVersionModel dvm WHERE dvm.status='A' and dvm.client= :client ");
		DataVersionModel dvm = new DataVersionModel();
		dvm.setCreatedAt(new Date());
		return dvm;
	}

	@Override
	public Map<String, Map<String, List<Object>>> getData(ClientModel client, long lastSyncAt) {

		Map<String, Map<String, List<Object>>> result = new HashMap<String, Map<String, List<Object>>>();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getInsertData(ClientModel client, long lastSyncAt, String tableName) {

		List<Object> result = new ArrayList<Object>();
		StringBuilder sqlQuery=new StringBuilder(CommonConstant.SQL_FROM);
		sqlQuery.append(tableName);
		sqlQuery.append(CommonConstant.CHAR_SPACE);
		sqlQuery.append(CommonConstant.SQL_WHERE);
		sqlQuery.append("CREATED_AT > :lastSyncAt and CREATED_AT=UPDATED_AT");
		if(client!=null){
			//sqlQuery.append(" and client= :client");
		}
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setParameter("lastSyncAt", lastSyncAt);
		if(client!=null){
		//query.setParameter("client", client);
		}
		result=(List<Object>)query.list();
		return result;
	}

	@Override
	public List<Object> getUpdateData(ClientModel client, long lastSyncAt, String tableName) {
		List<Object> result = new ArrayList<Object>();
		return result;
	}

}
