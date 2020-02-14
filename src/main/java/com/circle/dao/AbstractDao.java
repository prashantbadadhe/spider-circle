package com.circle.dao;

 
	import java.io.Serializable;
	 
	import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	 
	public abstract class AbstractDao<PK extends Serializable, T> {
	     
	    private final Class<T> persistentClass;
	     
	    @SuppressWarnings("unchecked")
	    public AbstractDao(){
	        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	    }
	     
	    @Autowired
	    private SessionFactory sessionFactory;
	 
	    protected Session getSession(){
	        return sessionFactory.getCurrentSession();
	    }
	 
	    @SuppressWarnings("unchecked")
	    public T getByKey(PK key) {
	        return (T) getSession().get(persistentClass, key);
	    }
	 
	    public void persist(T entity) {
	        getSession().persist(entity);
	    }
	 
	    public void delete(T entity) {
	        getSession().delete(entity);
	    }
	     
	    protected Criteria createEntityCriteria(){
	        return getSession().createCriteria(persistentClass);
	    }

	    @SuppressWarnings("unchecked")
	    public void delete(PK key) {
	    	 T entityClass = (T) getSession().get(persistentClass,key);
	    	 getSession().delete(entityClass);
 	    }
	    public void update(Object entityClass) {
	    	getSession().update(entityClass);
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<T> get(T entity) {
	        return getSession().createQuery("from " + entity ).list();
	    }

	    //Status is updated to 0 is consider as deleted record
		public int updateDeleteStatusById(long id, String screen) {
			Query query =  getSession().createQuery("update "+screen+" s set s.status = 0 where s.id = :id");
	 		query.setLong("id", id);
	 		int update = query.executeUpdate();
			return update;
		}
	}
	

