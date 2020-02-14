/*package com.circle.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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
public class GenericRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements GenericRepository<T, ID>,
		Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	protected EntityManager entityManager;
	protected Class<T> persistentClass;
	protected CriteriaBuilder cb;
	protected EntityType<T> entityType;
	String idProperty = null;

	// There are two constructors to choose from, either can be used.
	public GenericRepositoryImpl(Class<T> domainClass,
			EntityManager entityManager) {
		super(domainClass, entityManager);

		// This is the recommended method for accessing inherited class
		// dependencies.
		this.entityManager = entityManager;
		this.persistentClass = domainClass;
		cb = entityManager.getCriteriaBuilder();
		entityType = entityManager.getMetamodel().entity(persistentClass);
	}

	
	 * public GenericRepositoryImpl( JpaEntityInformation<?, ? extends
	 * Serializable> entityInformation, EntityManager entityManager2, Class<?>
	 * repositoryInterface) { super(entityInformation, entityManager2); }
	 
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected String getIdProperty() {
		if (idProperty == null) {
			Set<SingularAttribute<? super T, ?>> singularAttributes = entityType
					.getSingularAttributes();
			for (SingularAttribute<? super T, ?> singularAttribute : singularAttributes) {
				if (singularAttribute.isId()) {
					idProperty = singularAttribute.getName();
					break;
				}
			}
		}
		if (idProperty == null)
			throw new RuntimeException("id field not found");
		return idProperty;
	}

	// ///////////////////////////////////// Methods from
	// GenericServiceImpl.java
	// //////////////////////////////////////////////////////////////////

	@Override
	public List<T> findMany(final int firstResult, int maxResults) {
		Pageable pageSpecification = new PageRequest(firstResult,
				maxResults > 0 ? maxResults : NUMBER_OF_PERSONS_PER_PAGE);
		return findAll(pageSpecification).getContent();
	}

	@Override
	public T findById(ID id) {
		return findOne(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findIfObjectWithIdExists(Serializable id) {
		if (id == null)
			return false;
		return exists((ID) id);
	}

	@Override
	public long getRowCount() {
		return super.count();
	}

	
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
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> root = criteriaQuery.from(type);

		for (String relation : fetchRelations) {
			FetchParent<T, T> fetch = root;
			for (String pathSegment : relation.split(Pattern.quote("."))) {
				fetch = fetch.fetch(pathSegment, JoinType.LEFT);
			}
		}

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return getSingleOrNoneResult(entityManager.createQuery(criteriaQuery));
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
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();

		@SuppressWarnings("unchecked")
		Class<T> ret = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(ret);
		Root<T> root = criteriaQuery.from(ret);

		for (String relation : fetchRelations) {
			FetchParent<T, T> fetch = root;
			for (String pathSegment : relation.split(Pattern.quote("."))) {
				fetch = fetch.fetch(pathSegment, JoinType.LEFT);
			}
		}

		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

		return getSingleOrNoneResult(entityManager.createQuery(criteriaQuery));
	}

	@SuppressWarnings("hiding")
	private <T> T getSingleOrNoneResult(TypedQuery<T> query) {
		query.setMaxResults(1);
		List<T> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		}

		return result.get(0);
	}

	public void flush() {
		this.entityManager.flush();
	}

	public void clear() {
		this.entityManager.clear();
	}

	// ///////////// Methods from GenericServiceImpl.java ends
	// //////////////////////////////////////////////

	*//**
	 * @see GenericRepository#countByExample(java.lang.Object)
	 *//*
	@Override
	public long countByExample(final T exampleInstance) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());
		crit.add(Example.create(exampleInstance));

		return (long) crit.list().get(0);
	}

	*//**
	 * @see GenericRepository#findAll()
	 *//*
	@Override
	public List<T> findAll() {
		return super.findAll();
	}

	*//**
	 * @see GenericRepository#findAll(int, int)
	 *//*
	@Override
	public List<T> findAll(final int firstResult, final int maxResults) {
		return findByCriteria(firstResult, maxResults);
	}

	*//**
	 * @see GenericRepository#findAll(int, int, Sort)
	 *//*
	@Override
	public List<T> findAll(final int firstResult, final int maxResults,
			final Sort sort) {
		return findByCriteria(firstResult, maxResults, sort);
	}

	*//**
	 * @see GenericRepository#findByExample(java.lang.Object)
	 *//*
	@Override
	public List<T> findByExample(final T exampleInstance) {
		return findByExample(exampleInstance, -1, -1);
	}

	*//**
	 * @see GenericRepository#findByExample(java.lang.Object, Sort)
	 *//*
	@Override
	public List<T> findByExample(final T exampleInstance, final Sort sort) {
		return findByExample(exampleInstance, -1, -1, sort);
	}

	*//**
	 * @see GenericRepository#findByExample(java.lang.Object, int, int)
	 *//*
	@Override
	public List<T> findByExample(final T exampleInstance,
			final int firstResult, final int maxResults) {
		return findByExample(exampleInstance, firstResult, maxResults, null);
	}

	*//**
	 * @see GenericRepository#findByExample(java.lang.Object, int, int, Sort)
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(final T exampleInstance,
			final int firstResult, final int maxResults, final Sort sort) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		addOrder(crit, sort);

		crit.add(Example.create(exampleInstance));

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		final List<T> result = crit.list();
		return result;
	}

	*//**
	 * @see GenericRepository#findById(java.io.Serializable, boolean)
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public T findById(final ID id, final boolean lock) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.add(Restrictions.idEq(id));
		crit.setLockMode(LockMode.PESSIMISTIC_WRITE);

		final T result = (T) crit.uniqueResult();
		return result;
	}

	*//**
	 * @see GenericRepository#findByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(final String name, Object... params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	*//**
	 * @see GenericRepository#findByNamedQueryAndNamedParams(java.lang.String,
	 *      java.util.Map)
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ? extends Object> params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	*//**
	 * @see GenericRepository
	 *//*
	@Override
	public List<T> findall(final org.springframework.data.domain.Sort sort) {
		return super.findAll(sort);
	}

	*//**
	 * @see GenericRepository#getEntityClass()
	 *//*
	@Override
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	*//**
	 * Use this inside subclasses as a convenience method.
	 *//*
	protected List<T> findByCriteria(final Criterion... criterion) {
		return findByCriteria(-1, -1, null, criterion);
	}

	*//**
	 * Use this inside subclasses as a convenience method.
	 *//*
	protected List<T> findByCriteria(final Sort sort,
			final Criterion... criterion) {
		return findByCriteria(-1, -1, sort, criterion);
	}

	*//**
	 * Use this inside subclasses as a convenience method.
	 *//*
	protected List<T> findByCriteria(final int firstResult,
			final int maxResults, final Criterion... criterion) {
		return findByCriteria(firstResult, maxResults, null, criterion);
	}

	*//**
	 * Use this inside subclasses as a convenience method.
	 *//*
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final int firstResult,
			final int maxResults, final Sort sort, final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		addOrder(crit, sort);

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		final List<T> result = crit.list();
		return result;
	}

	protected long countByCriteria(Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return (long) crit.list().get(0);
	}

	private void addOrder(final Criteria criteria, final Sort sort) {
		if ((criteria == null) || (sort == null) || sort.getFields().isEmpty()) {
			return;
		}

		for (final SortField field : sort.getFields()) {
			switch (field.getDirection()) {
			case ASC:
				criteria.addOrder(Order.asc(field.getPropertyName()));

				break;

			case DESC:
				criteria.addOrder(Order.desc(field.getPropertyName()));

				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> find(javax.persistence.Query query, int pageSize,
			int currentPage, Object... parameters) {
		for (int i = 0; i < parameters.length; i++) {
			query.setParameter(i + 1, parameters[i]);
		}

		if (currentPage > 0) {
			query.setFirstResult(pageSize * currentPage);
		}
		if (pageSize > 0) {
			query.setMaxResults(pageSize);
		}
		final List<T> result = (List<T>) query.getResultList();
		return result;
	}

	public Long count(javax.persistence.Query query, Object... parameters) {
		for (int i = 0; i < parameters.length; i++) {
			query.setParameter(i + 1, parameters[i]);
		}
		return (Long) query.getSingleResult();
	}

	*//**
	 * @see GenericRepository#delete(java.io.Serializable)
	 *//*
	@Override
	public void delete(ID id) {
		T entity = findById(id);
		delete(entity);
	}

	*//**
	 * @see GenericRepository#delete(java.lang.Object)
	 *//*
	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	protected int arrayContains(String[] arr, String val) {
		int retval = -1;
		if (arr != null) {
			for (retval = 0; retval < arr.length; retval++) {
				if (val == null && arr[retval] == null) {
					break;
				}
				if (val != null && arr[retval].equals(val)) {
					break;
				}
			}
		}
		return retval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMany(String qlQuery, List<Object> params,
			int firstResult, int maxResult) {
		
		 * I haven't tested this obviously but if user provides query like
		 * Select templateId, name from Template where status = ?1 then this
		 * should return Template objects with templateId and name populated and
		 * other values set to null or default
		 
		Query q = entityManager.createQuery(qlQuery, persistentClass);
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				Object objParam = params.get(i);
				if (objParam != null)
					q = q.setParameter(i + 1, params.get(i));
			}
		}
		q.setFirstResult(firstResult);
		if (maxResult > 0)
			q.setMaxResults(maxResult);
		return q.getResultList();
	}

	@Override
	public List<T> findMany(String qlQuery, List<Object> params) {
		return findMany(qlQuery, params, 0, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMany(String qlQuery, Object[] params, int firstResult,
			int maxResult) {
		
		 * I haven't tested this obviously but if user provides query like
		 * Select templateId, name from Template where status = ?1 then this
		 * should return Template objects with templateId and name populated and
		 * other values set to null or default
		 
		Query q = entityManager.createQuery(qlQuery, persistentClass);
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				q = q.setParameter(i + 1, params[i]);
		}
		q.setFirstResult(firstResult);
		if (maxResult > 0)
			q.setMaxResults(maxResult);
		return q.getResultList();
	}

	@Override
	public List<T> findMany(String qlQuery, Object[] params) {
		return findMany(qlQuery, params, 0, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMany(String qlQuery, Map<String, Object> params,
			int firstResult, int maxResult) {
		
		 * I haven't tested this obviously but if user provides query like
		 * Select templateId, name from Template where status = ?1 then this
		 * should return Template objects with templateId and name populated and
		 * other values set to null or default
		 
		Query q = entityManager.createQuery(qlQuery, persistentClass);
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet())
				q = q.setParameter(e.getKey(), e.getValue());
		}
		q.setFirstResult(firstResult);
		if (maxResult > 0)
			q.setMaxResults(maxResult);
		return q.getResultList();
	}

	@Override
	public List<T> findMany(String qlQuery, Map<String, Object> params) {
		return findMany(qlQuery, params, 0, 0);
	}

	@Override
	public List<T> findByIDs(List<ID> ids, int firstResult, int maxResult) {
		CriteriaQuery<T> cq = cb.createQuery(persistentClass);
		Root<T> root = cq.from(persistentClass);

		Path<Object> path = root.get(getIdProperty());
		In<Object> in = cb.in(path);
		in.value(ids);
		cq = cq.where(in);

		TypedQuery<T> typedQuery = entityManager.createQuery(cq);
		typedQuery.setFirstResult(firstResult);
		if (maxResult > 0)
			typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ID> findIDs(String whereClause, Object[] params,
			int firstResult, int maxResult) {
		String typedQuery = "Select " + getIdProperty() + " from "
				+ persistentClass.getSimpleName();
		if (whereClause != null)
			typedQuery = typedQuery + " where " + whereClause;

		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				q = q.setParameter(i + 1, params[i]);
		}
		q.setFirstResult(firstResult);
		if (maxResult > 0)
			q.setMaxResults(maxResult);
		return q.getResultList();
	}

	public Object executeSingleQuery(String typedQuery, List<Object> params) {
		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (int i = 0; i < params.size(); i++)
				q = q.setParameter(i, params.get(i));
		}
		return q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeQuery(String typedQuery, Object[] params,
			int firstResult, int maxResult) {
		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				q = q.setParameter(i + 1, params[i]);
		}
		q.setFirstResult(firstResult);
		if (maxResult > 0)
			q.setMaxResults(maxResult);
		return q.getResultList();
	}

	@Override
	public List<Object[]> executeQuery(String typedQuery, Object[] params) {
		return executeQuery(typedQuery, params, 0, 0);
	}

	@Override
	public int executeUpdate(String typedQuery, Object[] params) {
		// EntityTransaction tx = null;
		// if (entityManager.getTransaction() == null) {
		// tx = entityManager.getTransaction();
		// tx.begin();
		// }
		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				q = q.setParameter(i + 1, params[i]);
		}
		int retval = q.executeUpdate();
		// if (tx != null)
		// tx.commit();
		return retval;
	}

	public Object executeSingleQuery(String typedQuery,
			Map<String, Object> params) {
		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet())
				q = q.setParameter(e.getKey(), e.getValue());
		}
		return q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeQuery(String typedQuery,
			Map<String, Object> params, int firstResult, int maxResult) {
		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet())
				q = q.setParameter(e.getKey(), e.getValue());
		}
		q.setFirstResult(firstResult);
		if (maxResult > 0)
			q.setMaxResults(maxResult);
		return q.getResultList();
	}

	@Override
	public List<Object[]> executeQuery(String typedQuery,
			Map<String, Object> params) {
		return executeQuery(typedQuery, params, 0, 0);
	}

	@Override
	public int executeUpdate(String typedQuery, Map<String, Object> params) {
		// EntityTransaction tx = null;
		// if (entityManager.getTransaction() == null) {
		// tx = entityManager.getTransaction();
		// tx.begin();
		// }
		Query q = entityManager.createQuery(typedQuery);
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet())
				q = q.setParameter(e.getKey(), e.getValue());
		}
		int retval = q.executeUpdate();
		// if (tx != null)
		// tx.commit();
		return retval;
	}

}
*/