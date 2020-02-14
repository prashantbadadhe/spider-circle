/*package com.circle.dao.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.annotations.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

*//**
 * JPA repository interface for CFG_APP_MODE table.
 * 
 * @author AVadhavkar
 * @unitTested: Yes
 * @reviewer: PPotnis
 * @reviewDate: 04-May-2015
 * @copyright(C) 2014-15 Bio-Analytical Technologies (India) Pvt. Ltd.
 *//*
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends
		JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	int NUMBER_OF_PERSONS_PER_PAGE = 20;

	public EntityManager getEntityManager();

	// ///////////////////////////////////////////////////////////////////////

	*//**
	 * @returnType: T
	 * @param type
	 * @param id
	 * @param fetchRelations
	 * @return
	 *//*
	T genericfindWithDepth(Class<T> type, Object id, String[] fetchRelations);

	*//**
	 * @returnType: T
	 * @param id
	 * @param fetchRelations
	 * @return
	 *//*
	T findWithDepth(Object id, String[] fetchRelations);

	// ///////////////////////////////////////
	*//**
	 * @returnType: void
	 *//*
	void clear();

	*//**
	 * @returnType: int
	 * @param exampleInstance
	 * @return
	 *//*
	long countByExample(T exampleInstance);

	*//**
	 * @returnType: List<T>
	 * @param firstResult
	 * @param maxResults
	 * @return
	 *//*
	List<T> findAll(int firstResult, int maxResults);

	*//**
	 * @returnType: List<T>
	 * @param firstResult
	 * @param maxResults
	 * @param sort
	 * @return
	 *//*
	List<T> findAll(int firstResult, int maxResults, Sort sort);

	*//**
	 * @returnType: List<T>
	 * @param exampleInstance
	 * @return
	 *//*
	List<T> findByExample(T exampleInstance);

	*//**
	 * @returnType: List<T>
	 * @param exampleInstance
	 * @param sort
	 * @return
	 *//*
	List<T> findByExample(T exampleInstance, Sort sort);

	*//**
	 * @returnType: List<T>
	 * @param exampleInstance
	 * @param firstResult
	 * @param maxResults
	 * @return
	 *//*
	List<T> findByExample(T exampleInstance, int firstResult, int maxResults);

	*//**
	 * @returnType: List<T>
	 * @param exampleInstance
	 * @param firstResult
	 * @param maxResults
	 * @param sort
	 * @return
	 *//*
	List<T> findByExample(T exampleInstance, int firstResult, int maxResults,
			Sort sort);

	*//**
	 * @returnType: T
	 * @param id
	 * @return
	 *//*
	T findById(ID id);

	*//**
	 * @returnType: T
	 * @param id
	 * @param lock
	 * @return
	 *//*
	T findById(ID id, boolean lock);

	*//**
	 * @returnType: List<T>
	 * @param name
	 * @param params
	 * @return
	 *//*
	List<T> findByNamedQuery(String name, Object[] params);

	*//**
	 * @returnType: List<T>
	 * @param name
	 * @param params
	 * @return
	 *//*
	List<T> findByNamedQueryAndNamedParams(String name,
			Map<String, ? extends Object> params);

	*//**
	 * @returnType: Class<T>
	 * @return
	 *//*
	Class<T> getEntityClass();

	*//**
	 * @returnType: List<T>
	 * @param sort
	 * @return
	 *//*
	List<T> findall(org.springframework.data.domain.Sort sort);

	// ///////////////////////////////////// Methods from
	// GenericServiceImpl.java
	*//**
	 * @returnType: List<T>
	 * @param firstResult
	 * @param maxResults
	 * @return
	 *//*
	List<T> findMany(int firstResult, int maxResults);

	*//**
	 * @returnType: boolean
	 * @param id
	 * @return
	 *//*
	boolean findIfObjectWithIdExists(ID id);

	*//**
	 * @returnType: long
	 * @return
	 *//*
	long getRowCount();

	*//**
	 * @param qlQuery
	 *            - JAP query language query
	 * @param fields
	 *            - only the fields of interest to be returned in object
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return returns list of objects for persistent class
	 *//*
	public List<T> findMany(String qlQuery, List<Object> params,
			int firstResult, int maxResult);

	*//**
	 * @returnType: List<T>
	 * @param qlQuery
	 * @param params
	 * @return
	 *//*
	public List<T> findMany(String qlQuery, List<Object> params);

	*//**
	 * @returnType: List<T>
	 * @param qlQuery
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return
	 *//*
	public List<T> findMany(String qlQuery, Object[] params, int firstResult,
			int maxResult);

	*//**
	 * @returnType: List<T>
	 * @param qlQuery
	 * @param params
	 * @return
	 *//*
	public List<T> findMany(String qlQuery, Object[] params);

	*//**
	 * @param qlQuery
	 * @param params
	 *            these are named parameters in the query
	 * @param firstResult
	 * @param maxResult
	 * @return
	 *//*
	public List<T> findMany(String qlQuery, Map<String, Object> params,
			int firstResult, int maxResult);

	*//**
	 * @returnType: List<T>
	 * @param qlQuery
	 * @param params
	 * @return
	 *//*
	public List<T> findMany(String qlQuery, Map<String, Object> params);

	*//**
	 * @param ids
	 * @param firstResult
	 * @param maxResult
	 * @return returns list of persistent class object for all the matching ids
	 *//*
	public List<T> findByIDs(List<ID> ids, int firstResult, int maxResult);

	*//**
	 * @returnType: List<ID>
	 * @param whereClause
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return
	 *//*
	public List<ID> findIDs(String whereClause, Object[] params,
			int firstResult, int maxResult);

	*//**
	 * @param typedQuery
	 *            - sql query
	 * @param params
	 *            positional parameter values for above query
	 * @param firstResult
	 * @param maxResult
	 * @return returns list of array of scalar objects
	 *//*
	public List<Object[]> executeQuery(String typedQuery, Object[] params,
			int firstResult, int maxResult);

	*//**
	 * @returnType: List<Object[]>
	 * @param typedQuery
	 * @param params
	 * @return
	 *//*
	public List<Object[]> executeQuery(String typedQuery, Object[] params);

	*//**
	 * @returnType: int
	 * @param typedQuery
	 * @param params
	 * @return
	 *//*
	public int executeUpdate(String typedQuery, Object[] params);

	*//**
	 * @returnType: Object
	 * @param typedQuery
	 * @param params
	 * @return
	 *//*
	Object executeSingleQuery(String typedQuery, List<Object> params);

	*//**
	 * @returnType: List<Object[]>
	 * @param typedQuery
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return
	 *//*
	public List<Object[]> executeQuery(String typedQuery,
			Map<String, Object> params, int firstResult, int maxResult);

	*//**
	 * @returnType: List<Object[]>
	 * @param typedQuery
	 * @param params
	 * @return
	 *//*
	public List<Object[]> executeQuery(String typedQuery,
			Map<String, Object> params);

	*//**
	 * @returnType: int
	 * @param typedQuery
	 * @param params
	 * @return
	 *//*
	public int executeUpdate(String typedQuery, Map<String, Object> params);

	*//**
	 * @returnType: Object
	 * @param typedQuery
	 * @param params
	 * @return
	 *//*
	Object executeSingleQuery(String typedQuery, Map<String, Object> params);

}
*/