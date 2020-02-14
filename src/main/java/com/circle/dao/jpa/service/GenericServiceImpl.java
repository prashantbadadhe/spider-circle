/*package com.circle.dao.jpa.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.annotations.Sort;
import org.hibernate.criterion.Example;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.circle.dao.jpa.GenericRepository;



*//**
 * @author PPotnis
 * @unitTested: Yes
 * @reviewer: PBadadhe
 * @reviewDate: 08-May-2015
 * @copyright(C) 2014-15 Bio-Analytical Technologies (India) Pvt. Ltd.
 *//*
// @Transactional
public class GenericServiceImpl<T, ID extends Serializable> implements
		GenericService<T, ID> {
	protected static final Logger log = LoggerFactory
			.getLogger(GenericServiceImpl.class);

	protected static String[] ALL_STATUSES;// = new
											// String[]{ObjectStatusValues.STATUS_ACTIVE,ObjectStatusValues.STATUS_APPROVED,ObjectStatusValues.STATUS_CANCELLED,ObjectStatusValues.STATUS_COMPLETED,ObjectStatusValues.STATUS_EXCEPTION,ObjectStatusValues.STATUS_INELIGIBLE,ObjectStatusValues.STATUS_INVALID,ObjectStatusValues.STATUS_PROPOSED,ObjectStatusValues.STATUS_REJECTED,ObjectStatusValues.STATUS_SUSPENDED,ObjectStatusValues.STATUS_SUBMITTED,ObjectStatusValues.STATUS_TERMINATED,ObjectStatusValues.STATUS_VERIFIED};

	protected GenericRepository<T, ID> repository;
	protected EntityManager entityManager;
	protected Class<T> clazz;

	public GenericServiceImpl(GenericRepository<T, ID> repository,
			Class<T> clazz) {
		this.repository = repository;
		this.clazz = clazz;
		this.entityManager = repository.getEntityManager();
	}

	*//** JPA specific Queries starts here. *//*
	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public List<T> findMany(final int firstResult, int maxResults) {
		return repository.findMany(firstResult, maxResults);
	}

	@Override
	public List<T> findByExample(T exampleInstance, Sort sort) {
		return repository.findByExample(exampleInstance, sort);
	}

	@Override
	public T findById(ID id) {
		return repository.findOne(id);
	}

	@Override
	public boolean findIfObjectWithIdExists(ID id) {
		return repository.findIfObjectWithIdExists(id);
	}

	@Override
	public long getRowCount() {
		return repository.count();
	}

	@Override
	public T create(T newInstance) {
		return repository.save(newInstance);
	}

	@Override
	public T update(T transientObject) {
		return repository.save(transientObject);
	}

	@Override
	public void delete(T persistentObject) {
		repository.delete(persistentObject);
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public void createMany(Collection<T> newInstances) {
		repository.save(newInstances);
	}

	@Override
	public void updateMany(Collection<T> instances) {
		repository.save(instances);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see com.opencombine.service.generic.GenericService#flush()
	 
	@Override
	public void flush() {
		repository.flush();
	}

	
	 * (non-Javadoc)
	 * 
	 * @see com.opencombine.service.generic.GenericService#clear()
	 
	@Override
	public void clear() {
		repository.clear();
	}

	*//**
	 * Not working for single or multiple properties. Only work when all
	 * properties are set by using setters.
	 * 
	 * @see repository.GenericRepository#findByExample(java.lang.Object)
	 *//*
	
	 * @Override public List<T> findByExample(final T exampleInstance) { return
	 * repository.findByExample(exampleInstance, -1, -1); }
	 

	*//**
	 * Not working for single or multiple properties. Only work when all
	 * properties are set by using setters.
	 * 
	 * @see repository.GenericRepository#findByExample(java.lang.Object,
	 *      repository.Sort)
	 *//*
	
	 * @Override public List<T> findByExample(final T exampleInstance, final
	 * Sort sort) { return repository.findByExample(exampleInstance, -1, -1,
	 * sort); }
	 

	*//**
	 * Not working for single or multiple properties. Only work when all
	 * properties are set by using setters.
	 * 
	 * @see repository.GenericRepository#findByExample(java.lang.Object, int,
	 *      int)
	 *//*
	
	 * @Override public List<T> findByExample(final T exampleInstance, final int
	 * firstResult, final int maxResults) { return
	 * repository.findByExample(exampleInstance, firstResult, maxResults, null);
	 * }
	 

	
	 * The genericfindWithDepth method can now be called with one or more path
	 * expressions, where each path is just a chain of properties separated by a
	 * dot (like in expression language). E.g.:
	 * 
	 * AnyEntityClass user = someRepository.findWithDepth( AnyEntityClass.class,
	 * 15, "addresses", "friends.addresses" ); The above line would fetch the
	 * user with â€œidâ€� 15, and pre-fetches the addresses associated with that
	 * user, as well as the friends and their addresses. (Note that the @Id
	 * field is hardcoded to be called â€œidâ€� here. A more fancy implementation
	 * could query the object for it)
	 
	@Override
	public T genericfindWithDepth(Class<T> type, Object id,
			String... fetchRelations) {
		return repository.genericfindWithDepth(type, id, fetchRelations);
	}

	
	 * The findWithDepth method can now be called with one or more path
	 * expressions, where each path is just a chain of properties separated by a
	 * dot (like in expression language). E.g.:
	 * 
	 * User user = userRepository.findWithDepth( 15, "addresses",
	 * "friends.addresses" ); The above line would fetch the user with â€œidâ€� 15,
	 * and pre-fetches the addresses associated with that user, as well as the
	 * friends and their addresses. (Note that the @Id field is hardcoded to be
	 * called â€œidâ€� here. A more fancy implementation could query the object for
	 * it)
	 
	@Override
	public T findWithDepth(Object id, String... fetchRelations) {
		return findWithDepth(id, fetchRelations);
	}

	*//** JPA specific Queries end here. *//*
	*//**
	 * JPA specific Queries end here. JPA specific Queries end here. Hibernate
	 * specific Queries starts here. Hibernate specific Queries starts here.
	 *//*
	*//** Hibernate specific Queries starts here. *//*

	public static Map<String, List<Object>> scalarListToMap(String[] fields,
			List<Object[]> result) {
		Map<String, List<Object>> retval = null;
		if (result != null && !result.isEmpty()) {
			retval = new HashMap<String, List<Object>>();
			for (int r = 0; r < result.size(); r++) {
				for (int f = 0; f < fields.length; f++) {
					if (r == 0) {
						retval.put(fields[f], new ArrayList<Object>());
					}
					List<Object> l = retval.get(fields[f]);
					if (fields.length > 1) {
						Object[] vals = (Object[]) result.get(r);
						l.add(vals[f]);
					} else {
						l.add(result.get(r));
					}
				}
			}
		}
		return retval;
	}

	*//**
	 * Use Class name as table name e.g. "from EtlSession" where EtlSession is
	 * an entity class. Map <String,Object[]>clauses; StringBuffer qry = new
	 * StringBuffer();
	 * qry.append("from com.opencombine.domain.Transaction tx where ");
	 * Map<String,Object> params = new HashMap<String,Object>();
	 * qry.append(transactionService.prepareWhereClauseAndParams(clauses, null,
	 * params)); getRowCountByQuery(qry.toString(), new Object[]{params});
	 * 
	 * e.g params.put("fName", "CAL CURVE_001.lcb"); params.put("status", 'C');
	 * getRowCountByQuery
	 * ("from EtlSession where fileName = :fName and status = :status", new
	 * Object[]{params});
	 * 
	 * e.g
	 * getRowCountByQuery("from EtlSession where fileName = ? and status = ?",
	 * new Object[]{"CAL CURVE_001.lcb",ch});
	 * 
	 * @see com.opencombine.dao.jpa.service.GenericService#getRowCountByQuery(java.lang.String,
	 *      java.lang.Object[])
	 *//*
	public long getRowCountByQuery(String query, final Object[] queryArgs,
			boolean sqlQuery) {
		long retval = 0;
		Query q = prepareQuery( "select count(*) " + query, queryArgs,
				sqlQuery);
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) q.list();
		if ((result != null) && !result.isEmpty()) {
			if (result.get(0) instanceof Number)
				retval = ((Number) result.get(0)).longValue();
		}
		if (result != null)
			result.clear();
		return retval;
	}

	@Override
	public List<T> findByQuery(String query, Object[] queryArgs,
			int firstResult, int maxResults) {
		
		 * Pageable pageSpecification = new PageRequest(firstResult, maxResults
		 * > 0 ? maxResults : NUMBER_OF_PERSONS_PER_PAGE); return
		 * repository.findAll(new Specification<T>() {
		 * 
		 * @Override public Predicate toPredicate(Root<T> root, CriteriaQuery<?>
		 * q, CriteriaBuilder cb) { q.distinct(true); root.fetch("permissions",
		 * JoinType.LEFT);
		 * 
		 * // EntityType<T> metamodel= root.getModel(); // Predicate start =
		 * cb.equal(root.get("id"), (long)firstResult); // Predicate start =
		 * cb.greaterThan(root.get("id").as(Long.class), // (long)firstResult);
		 * 
		 * return cb.equal(root.get("id"), 1l); } },
		 * pageSpecification).getContent();
		 

		Query q = prepareQuery(query, queryArgs, false);
		if (firstResult > 0)
			q.setFirstResult(firstResult);
		if (maxResults > 0)
			q.setMaxResults(maxResults);
		List<T> retval = q.list();
		q = null;
		return retval;
	}

	@Transactional
	@Override
	public List<T> findBySqlQuery(String query, int firstResult, int maxResults) {
		Session session = (Session) this.entityManager.getDelegate();
		SQLQuery q = session.createSQLQuery(query);
		q.addEntity(clazz);
		if (firstResult > 0)
			q.setFirstResult(firstResult);
		if (maxResults > 0)
			q.setMaxResults(maxResults);
		return q.list();
	}

	@Override
	public List<LinkedHashMap<String, String>> findScalarBySqlQuery(
			String query, final Map<String, Object> args, int firstResult,
			int maxResults) {
		String p = query.toLowerCase();
		// if(p.startsWith(SQL_SELECT_DISTINCT))
		// p = query.substring(p.indexOf(SQL_SELECT_DISTINCT) + 16,
		// p.indexOf(SQL_FROM));
		// else
		p = query.substring(p.indexOf(SQL_SELECT) + 6, p.indexOf(SQL_FROM));

		ArrayList<String> fields = new ArrayList<String>();
		for (String fld : StringUtils.split(p, ",")) {
			String[] f = fld.trim().split(" ");
			fields.add(f[f.length - 1]);
		}
		List<LinkedHashMap<String, String>> retval = executeSqlQuery(query,
				fields, null, args, firstResult, maxResults);
		fields.clear();
		return retval;
	}

	@Override
	public List<LinkedHashMap<String, String>> executeSqlQuery(String query,
			List<String> fields, HashMap<String, Type> propTypes,
			final Map<String, Object> args, int firstResult, int maxResults) {
		ArrayList<LinkedHashMap<String, String>> result = new ArrayList<>();
		Session session = (Session) this.entityManager.getDelegate();
		SQLQuery q = session.createSQLQuery(query);
		if (fields != null) {
			for (String f : fields) {
				if (propTypes != null)
					q.addScalar(f, (Type) propTypes.get(f));
				else
					q.addScalar(f, StringType.INSTANCE);
			}
		}
		if (firstResult > 0)
			q.setFirstResult(firstResult);
		if (maxResults > 0)
			q.setMaxResults(maxResults);
		if (args != null) {
			for (String param : args.keySet()) {
				Object val = args.get(param);
				if (java.util.Collection.class.isAssignableFrom(val.getClass())) {
					q.setParameterList(param, (Collection) val);
				} else if (val.getClass().isArray()) {
					q.setParameterList(param, (Object[]) val);
				} else {
					q.setParameter(param, val);
				}
			}
		}
		log.debug("@GenericServiceImpl executeSqlQuery Query: " + q);
		List<Object> l = q.list(); // returns array of objects
		for (Object a : l) {
			int i = 0;
			LinkedHashMap<String, String> row = new LinkedHashMap<String, String>();
			if (a instanceof Object[]) {
				for (Object v : (Object[]) a) {
					row.put(fields.get(i), (String) v);
					i++;
				}
			} else {
				row.put(fields.get(i), (String) a);
			}
			result.add(row);
		}
		l.clear();
		return result;
	}

	@Override
	public List<Object[]> findBySqlQuery(String query,
			final Map<String, Object> args, int firstResult, int maxResults) {

		String p = query.toLowerCase();
		p = query.substring(p.indexOf(SQL_SELECT) + 6, p.indexOf(SQL_FROM));
		ArrayList<String> fields = new ArrayList<String>();
		for (String fld : StringUtils.split(p, ",")) {
			String[] f = fld.trim().split(" ");
			fields.add(f[f.length - 1]);
		}

		Session session = (Session) this.entityManager.getDelegate();
		SQLQuery q = session.createSQLQuery(query);
		if (fields != null) {
			for (String f : fields) {
				q.addScalar(f, StringType.INSTANCE);
			}
		}

		if (firstResult > 0)
			q.setFirstResult(firstResult);
		if (maxResults > 0)
			q.setMaxResults(maxResults);
		if (args != null) {
			for (String param : args.keySet()) {
				Object val = args.get(param);
				if (java.util.Collection.class.isAssignableFrom(val.getClass())) {
					q.setParameterList(param, (Collection) val);
				} else if (val.getClass().isArray()) {
					q.setParameterList(param, (Object[]) val);
				} else {
					q.setParameter(param, val);
				}
			}
		}
		return q.list(); // returns array of objects
	}

	@Override
	public List executeAnyQuery(String query, Map<String, Object> args,
			int firstResult, int maxResults, boolean sql) {
		Session session = (Session) this.entityManager.getDelegate();
		Query q = sql ? session.createSQLQuery(query) : session
				.createQuery(query);
		if (firstResult > 0)
			q.setFirstResult(firstResult);
		if (maxResults > 0)
			q.setMaxResults(maxResults);
		if (args != null) {
			for (String param : args.keySet()) {
				Object val = args.get(param);
				if (val != null) {
					if (java.util.Collection.class.isAssignableFrom(val
							.getClass())) {
						q.setParameterList(param, (Collection<?>) val);
					} else if (val.getClass().isArray()) {
						q.setParameterList(param, (Object[]) val);
					} else {
						q.setParameter(param, val);
					}
				} else {
					q.setParameter(param, val);
				}
			}
		}
		return q.list();
	}

	
	 * @Override public List<ID> findAllIds(Iterable<ID> idattribute) { List<ID>
	 * ids = new ArrayList<ID>(); for (T t :
	 * repository.findAll((Iterable<ID>)idattribute)) ids.add((ID) t.getId());
	 * return ids; }
	 
	@Override
	public Map<String, String> findPropertyMap(String keyProperty,
			String[] otherProps, String whereClause, String delim) {
		String qry = SQL_SELECT + SQL_SPACE + keyProperty;
		for (String p : otherProps) {
			qry += "," + p;
		}
		qry += SQL_SPACE + SQL_FROM + SQL_SPACE + clazz.getName();
		if (StringUtils.isNotBlank(whereClause))
			qry += SQL_SPACE + whereClause;
		List l = this.executeAnyQuery(qry, null, 0, 0, false);
		Map<String, String> retval = new HashMap<String, String>();
		if (StringUtils.isBlank(delim))
			delim = "-";
		if (l != null) {
			for (Object r : l) {
				Object[] ra = (Object[]) r;
				String v = "";
				for (int i = 1; i < ra.length; i++) {
					if (i > 1)
						v += delim;
					v += ra[i];
				}
				retval.put(String.valueOf(ra[0]), v);
			}
		}
		return retval;
	}

	*//**
	 * Search previously persisted objects by a named query
	 *//*
	private Query prepareQuery(String query, final Object[] queryArgs,
			boolean sql) {
		Session session = (Session) this.entityManager.getDelegate();
		Query q = sql ? session.createSQLQuery(query) : session
				.createQuery(query);
		if (queryArgs != null && queryArgs.length > 0) {
			if (queryArgs[0] instanceof java.util.Map) { // named parameters
				@SuppressWarnings("unchecked")
				Map<String, Object> params = (Map<String, Object>) queryArgs[0];
				for (String pname : params.keySet()) {
					Object val = params.get(pname);
					if (val != null) {
						if (val instanceof java.util.Collection) {
							q.setParameterList(pname, (Collection) val);
						} else if (val.getClass().isArray()) {
							q.setParameterList(pname, (Object[]) val);
						} else {
							q.setParameter(pname, val);
						}
					} else {
						q.setParameter(pname, val);
					}
				}
			} else {
				for (int i = 0; i < queryArgs.length; i++) { // positional
																// parameters
					q.setParameter(i, queryArgs[i]);
				}
			}
		}
		return q;
	}

	
	 * Not working for single or multiple properties. Only work when all
	 * properties are set by using setters.
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#getRowCountByExample(java
	 * .lang.Object, java.util.Map)
	 
	
	 * @Override public long getRowCountByExample(T exampleInstance,
	 * Map<Object,Object> exclusionProperties) { long retval = 0; Criteria
	 * criteria = prepareExampleCriteria(exampleInstance,exclusionProperties);
	 * criteria.setProjection(Projections.rowCount());
	 * 
	 * @SuppressWarnings("unchecked") List<Object> result = (List<Object>)
	 * criteria.list(); if ((result != null) && !result.isEmpty()) { if
	 * (criteria.uniqueResult() instanceof Long) retval = ((Long)
	 * criteria.uniqueResult()).longValue(); if (criteria.uniqueResult()
	 * instanceof Integer) retval = ((Integer)
	 * criteria.uniqueResult()).longValue(); } if (result != null)
	 * result.clear(); return retval; }
	 

	
	 * Not working for single or multiple properties. Only work when all
	 * properties are set by using setters.
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findByExample(java.lang
	 * .Object, java.util.Map, int, int)
	 
	
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<T> findByExample(T exampleInstance,
	 * Map<Object,Object> exclusionProperties, int firstResult, int maxResults)
	 * { Criteria criteria =
	 * prepareExampleCriteria(exampleInstance,exclusionProperties); if
	 * (firstResult > 0) criteria.setFirstResult(firstResult); if (maxResults >
	 * 0) criteria.setMaxResults(maxResults); return criteria.list(); }
	 

	*//**
	 * Find previously persisted objects by example instance TO BE enhanced to
	 * handle collections
	 *//*
	protected static class ExamplePreparationHelper implements FieldCallback {

		Session session;
		Map<Object, Object> exclusionProperties;
		Map<String, Object> eps = null;
		Map<String, Object> geps = null;
		List<String> excludedFields;
		boolean includePrimitives = false;
		Criteria criteria = null;
		Criteria parentCriteria = null;
		Object target;
		Example example;

		@Override
		public void doWith(Field f) throws IllegalArgumentException,
				IllegalAccessException {
			if (!java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
				if (excludedFields != null
						&& excludedFields.contains(f.getName())) {
					example.excludeProperty(f.getName());
				} else {
					f.setAccessible(true);
					Object fvalue = f.get(target);
					// System.out.println("......doWith " + f.getName() +
					// " has value " + fvalue);
					if (fvalue != null) {
						if (fvalue.getClass().isPrimitive()) {
							// System.out.println(" is primitive...includePrimitives="
							// + includePrimitives);
							if (!includePrimitives) {
								example.excludeProperty(f.getName());
							}
						} else if (!fvalue.getClass().getName()
								.startsWith("java")) {
							// System.out.println("....procesing child");
							ExamplePreparationHelper cfc = new ExamplePreparationHelper();
							cfc.target = fvalue;
							cfc.exclusionProperties = this.exclusionProperties;
							cfc.session = this.session;
							cfc.geps = this.geps;
							cfc.parentCriteria = criteria;
							cfc.prepareExampleCriteria(f.getName());
						}
					}
				}
			}
		}

		public Criteria prepareExampleCriteria(String criteriaName) {
			// System.out.println("....prepareExampleCriteria for " +
			// target.getClass());
			if (exclusionProperties != null) {
				if (exclusionProperties.containsKey(target.getClass())) {
					eps = (Map<String, Object>) exclusionProperties.get(target
							.getClass());
					if (eps.containsKey(ENABLE_LIKE))
						example.enableLike();
					if (eps.containsKey(IGNORE_CASE))
						example.ignoreCase();
					if (eps.containsKey(EXCLUDE_PROPERTY))
						excludedFields = (List<String>) eps
								.get(EXCLUDE_PROPERTY);
				}
				if (geps == null
						&& exclusionProperties.containsKey(Object.class)) {
					geps = (Map<String, Object>) exclusionProperties
							.get(Object.class);
				}
				if (geps != null) {
					if (geps.containsKey(ENABLE_LIKE))
						example.enableLike();
					if (geps.containsKey(IGNORE_CASE))
						example.ignoreCase();
					if (geps.containsKey(INCLUDE_PRIMITIVES))
						includePrimitives = true;
				}
				if (!includePrimitives && eps.containsKey(INCLUDE_PRIMITIVES))
					includePrimitives = true;
			}
			if (StringUtils.isNotBlank(criteriaName)) {
				criteria = parentCriteria.createCriteria(criteriaName);
			} else {
				criteria = session.createCriteria(target.getClass());
			}
			Example example = Example.create(target);
			criteria.add(example);
			ReflectionUtils.doWithFields(target.getClass(), this);
			return criteria;
		}

	}

	private Criteria prepareExampleCriteria(Object obj,
			Map<Object, Object> exclusionProperties) {
		ExamplePreparationHelper cfc = new ExamplePreparationHelper();
		cfc.target = obj;
		cfc.exclusionProperties = exclusionProperties;
		Session session = (Session) this.entityManager.getDelegate();
		cfc.session = session;
		return cfc.prepareExampleCriteria(null);
	}

	@Override
	public String prepareWhereClauseAndParams(Map<String, Object[]> clauses,
			String orderBy, Map<String, Object> params) {
		StringBuffer whereClause = new StringBuffer();
		boolean first = true;
		for (String param : clauses.keySet()) {
			if (!first)
				whereClause.append(" and ");
			first = false;
			// qry.append(param);
			whereClause.append(clauses.get(param)[0]);
			Object val = clauses.get(param).length == 1 ? null : clauses
					.get(param)[1];
			if (val != null)
				params.put(param, clauses.get(param)[1]);
		}
		if (StringUtils.isNotBlank(orderBy)) {
			whereClause.append(" order by ");
			whereClause.append(orderBy);
		}
		return whereClause.toString();
	}

	*//** Hibernate specific Queries end here. *//*

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#executeQuery(java.lang
	 * .String, java.lang.Object[], int, int)
	 
	@Override
	public List<Object[]> executeQuery(String typedQuery, Object[] params,
			int firstResult, int maxResult) {
		return repository.executeQuery(typedQuery, params, firstResult,
				maxResult);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#executeQuery(java.lang
	 * .String, java.lang.Object[])
	 
	@Override
	public List<Object[]> executeQuery(String typedQuery, Object[] params) {
		return repository.executeQuery(typedQuery, params);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findMany(java.lang.String,
	 * java.util.List, int, int)
	 
	@Override
	public List<T> findMany(String qlQuery, List<Object> params,
			int firstResult, int maxResult) {
		return repository.findMany(qlQuery, params, firstResult, maxResult);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findMany(java.lang.String,
	 * java.util.List)
	 
	@Override
	public List<T> findMany(String qlQuery, List<Object> params) {
		return repository.findMany(qlQuery, params);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findMany(java.lang.String,
	 * java.lang.Object[], int, int)
	 
	@Override
	public List<T> findMany(String qlQuery, Object[] params, int firstResult,
			int maxResult) {
		return repository.findMany(qlQuery, params, firstResult, maxResult);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findMany(java.lang.String,
	 * java.lang.Object[])
	 
	@Override
	public List<T> findMany(String qlQuery, Object[] params) {
		return repository.findMany(qlQuery, params);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findMany(java.lang.String,
	 * java.util.Map, int, int)
	 
	@Override
	public List<T> findMany(String qlQuery, Map<String, Object> params,
			int firstResult, int maxResult) {
		return repository.findMany(qlQuery, params, firstResult, maxResult);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findMany(java.lang.String,
	 * java.util.Map)
	 
	@Override
	public List<T> findMany(String qlQuery, Map<String, Object> params) {
		return repository.findMany(qlQuery, params);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#executeUpdate(java.lang
	 * .String, java.lang.Object[])
	 
	@Override
	public int executeUpdate(String typedQuery, Object[] params) {
		return repository.executeUpdate(typedQuery, params);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findByIDs(java.util.List,
	 * int, int)
	 
	@Override
	public List<T> findByIDs(List<ID> ids, int firstResult, int maxResult) {
		return repository.findByIDs(ids, firstResult, maxResult);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opencombine.service.generic.GenericService#findIDs(java.lang.String,
	 * java.lang.Object[], int, int)
	 
	@Override
	public List<ID> findIDs(String whereClause, Object[] params,
			int firstResult, int maxResult) {
		return repository.findIDs(whereClause, params, firstResult, maxResult);
	}

	@Override
	public List<Object[]> executeQuery(String typedQuery,
			Map<String, Object> params, int firstResult, int maxResult) {
		return repository.executeQuery(typedQuery, params, firstResult,
				maxResult);
	}

}
*/