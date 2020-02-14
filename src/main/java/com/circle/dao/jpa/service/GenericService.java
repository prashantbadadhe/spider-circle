package com.circle.dao.jpa.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Sort;
import org.hibernate.type.Type;

/**
 * @author PPotnis
 * @unitTested: Yes
 * @reviewer: PBadadhe
 * @reviewDate: 08-May-2015
 * @copyright(C) 2014-15 Bio-Analytical Technologies (India) Pvt. Ltd.
 */
@SuppressWarnings("deprecation")
public interface GenericService<T, ID extends Serializable> {

	int NUMBER_OF_RECORDS_PER_PAGE = 5;
	String INCLUDE_PRIMITIVES = "includePrimitives";
	String EXCLUDE_PROPERTY = "excludeProperty";
	String ENABLE_LIKE = "enableLike";
	String IGNORE_CASE = "ignoreCase";
	String SQL_QUOTE = "'";
	String SQL_SPACE = "  ";
	String SQL_EQUALS = "=";
	String SQL_NOT_EQUALS = "<>";
	String SQL_LIKE_WILD = "%";
	String SQL_LIKE = " like ";
	String SQL_WHERE = " where ";
	String SQL_SELECT = " select ";
	String SQL_SELECT_DISTINCT = " select distinct ";
	String SQL_FROM = " from ";
	String SQL_GROUP_BY = " group by ";
	String SQL_HAVING = " having ";
	String SQL_ORDER_BY = " order by ";
	String SQL_ASC = " asc ";
	String SQL_DESC = " desc ";
	String SQL_IN = " in ";
	String SQL_AND = " and ";
	String SQL_OR = " or ";
	String SQL_NOT = " not ";
	String SQL_OPEN_PARENTHESIS = "(";
	String SQL_CLOSE_PARENTHESIS = ")";

	/**
	 * @returnType: List<T>
	 * @param exampleInstance
	 * @param sort
	 * @return
	 */
	List<T> findByExample(T exampleInstance, Sort sort);

	/**
	 * @returnType: T
	 * @param id
	 * @return Find previously persisted object by it's id
	 */
	public T findById(ID id);

	/**
	 * @returnType: List<T>
	 * @param query
	 * @param queryArgs
	 * @param firstResult
	 * @param maxResults
	 * @return Search previously persisted objects by a sql query string and
	 *         query arguments The result can be obtained in page manner by
	 *         using firstResult and maxResults
	 */
	public List<T> findByQuery(String query, final Object[] queryArgs,
			int firstResult, int maxResults);

	/**
	 * @returnType: List<T>
	 * @param query
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<T> findBySqlQuery(String query, int firstResult, int maxResults);

	/**
	 * @returnType: List<T>
	 * @return Find all
	 */
	public List<T> findAll();

	/**
	 * @returnType: List<ID>
	 * @param idattribute
	 * @return
	 */
	// public List<ID> findAllIds(Iterable<ID> idAttribute);

	/**
	 * @returnType: List<T>
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<T> findMany(int firstResult, int maxResults);

	/**
	 * @returnType: long
	 * @return Get row count of rows in table
	 */
	public long getRowCount();

	/**
	 * Use Class name as table name e.g. "from EtlSession" where EtlSession is
	 * an entity class.
	 * 
	 * @returnType: long
	 * @param query
	 * @param queryArgs
	 * @return
	 */
	public long getRowCountByQuery(String query, final Object[] queryArgs,
			boolean sqlQuery);

	/**
	 * @returnType: long
	 * @param exampleInstance
	 * @param exclusionProperties
	 * @return
	 */
	// public long getRowCountByExample(T exampleInstance, Map<Object,Object>
	// exclusionProperties);

	/**
	 * @returnType: List<HashMap>
	 * @param query
	 * @param args
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<LinkedHashMap<String, String>> findScalarBySqlQuery(
			String query, final Map<String, Object> args, int firstResult,
			int maxResults);

	/**
	 * @returnType: List<HashMap>
	 * @param query
	 * @param fields
	 * @param propTypes
	 * @param args
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<LinkedHashMap<String, String>> executeSqlQuery(String query,
			List<String> fields, HashMap<String, Type> propTypes,
			final Map<String, Object> args, int firstResult, int maxResults);

	/**
	 * @returnType: List<Object[]>
	 * @param query
	 * @param args
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public List<Object[]> findBySqlQuery(String query,
			final Map<String, Object> args, int firstResult, int maxResults);

	/**
	 * @returnType: List
	 * @param query
	 * @param args
	 * @param firstResult
	 * @param maxResults
	 * @param sql
	 * @return
	 */
	public List<Object> executeAnyQuery(String query, final Map<String, Object> args,
			int firstResult, int maxResults, boolean sql);

	/**
	 * @returnType: Map<String,String>
	 * @param keyProperty
	 * @param otherProps
	 * @param whereClause
	 * @param delim
	 * @return
	 */
	public Map<String, String> findPropertyMap(String keyProperty,
			String[] otherProps, String whereClause, String delim);

	/**
	 * @returnType: T
	 * @param newInstance
	 * @return Persist the newInstance object into database
	 */
	public T create(T newInstance);

	/**
	 * @returnType: T
	 * @param instance
	 * @return Save changes made to a persistent object.
	 */
	public T update(T instance);

	/**
	 * @returnType: void
	 * @param persistentObject
	 *            Remove an object from persistent storage in the database
	 */
	public void delete(T persistentObject);

	/**
	 * @returnType: void
	 * @param id
	 */
	void delete(ID id);

	/**
	 * @returnType: void Remove an object from persistent storage in the
	 *              database
	 */
	public void deleteAll();

	/**
	 * @returnType: void
	 * @param newInstances
	 */
	public void createMany(Collection<T> newInstances);

	/** Save changes made to a persistent object. */
	/**
	 * @returnType: void
	 * @param instances
	 */
	public void updateMany(Collection<T> instances);

	/**
	 * @returnType: T
	 * @param type
	 * @param id
	 * @param fetchRelations
	 * @return
	 */
	T genericfindWithDepth(Class<T> type, Object id, String... fetchRelations);

	/**
	 * @returnType: T
	 * @param type
	 * @param id
	 * @param fetchRelations
	 * @return
	 */
	T findWithDepth(Object id, String... fetchRelations);

	/**
	 * @returnType: void
	 */
	public void flush();

	/**
	 * @returnType: void
	 */
	public void clear();

	/**
	 * @returnType: String
	 * @param clauses
	 * @param orderBy
	 * @param params
	 * @return
	 */
	String prepareWhereClauseAndParams(Map<String, Object[]> clauses,
			String orderBy, Map<String, Object> params);

	/**
	 * @returnType: boolean
	 * @param id
	 * @return
	 */
	boolean findIfObjectWithIdExists(ID id);

	/**
	 * @param typedQuery
	 *            - sql query
	 * @param params
	 *            positional parameter values for above query
	 * @param firstResult
	 * @param maxResult
	 * @return returns list of array of scalar objects
	 */
	public List<Object[]> executeQuery(String typedQuery,
			Map<String, Object> params, int firstResult, int maxResult);

	/**
	 * @returnType: List<Object[]>
	 * @param typedQuery
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<Object[]> executeQuery(String typedQuery, Object[] params,
			int firstResult, int maxResult);

	/**
	 * @returnType: List<Object[]>
	 * @param typedQuery
	 * @param params
	 * @return
	 */
	public List<Object[]> executeQuery(String typedQuery, Object[] params);

	/**
	 * @param qlQuery
	 *            - JAP query language query
	 * @param fields
	 *            - only the fields of interest to be returned in object
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return returns list of objects for persistent class
	 */
	public List<T> findMany(String qlQuery, List<Object> params,
			int firstResult, int maxResult);

	/**
	 * @returnType: List<T>
	 * @param qlQuery
	 * @param params
	 * @return
	 */
	public List<T> findMany(String qlQuery, List<Object> params);

	public List<T> findMany(String qlQuery, Object[] params, int firstResult,
			int maxResult);

	public List<T> findMany(String qlQuery, Object[] params);

	/**
	 * @param qlQuery
	 * @param params
	 *            these are named parameters in the query
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findMany(String qlQuery, Map<String, Object> params,
			int firstResult, int maxResult);

	/**
	 * @returnType: List<T>
	 * @param qlQuery
	 * @param params
	 * @return
	 */
	public List<T> findMany(String qlQuery, Map<String, Object> params);

	/**
	 * @returnType: int
	 * @param typedQuery
	 * @param params
	 * @return
	 */
	public int executeUpdate(String typedQuery, Object[] params);

	/**
	 * @param ids
	 * @param firstResult
	 * @param maxResult
	 * @return returns list of persistent class object for all the matching ids
	 */
	public List<T> findByIDs(List<ID> ids, int firstResult, int maxResult);

	/**
	 * @returnType: List<ID>
	 * @param whereClause
	 * @param params
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<ID> findIDs(String whereClause, Object[] params,
			int firstResult, int maxResult);

}
