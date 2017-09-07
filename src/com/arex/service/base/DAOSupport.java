package com.arex.service.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class DAOSupport implements DAO {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void save(Object entity) {
		em.persist(entity);
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object entityid) {
		em.remove(em.getReference(entityClass, entityid));
	}

	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for (Object entityid : entityids) {
			this.delete(entityClass, entityid);
		}
	}

	@Override
	public void update(Object entity) {
		em.merge(entity);
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> T find(Class<T> entityClass, Object entityid) {
		T entity = em.find(entityClass, entityid);
		
		return entity;
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	@Override
	public <T> List<T> find(Class<T> entityClass, Object[] entityids) {
		List<T> entityList = new ArrayList<T>();
		for (Object entityid : entityids) {
			T entity = this.find(entityClass, entityid);
			entityList.add(entity);
		}
		return entityList;
	}

}
