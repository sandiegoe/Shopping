package com.arex.service.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public <T> void deleteBySetVisible(Class<T> entityClass, Object entityid) {
		this.deleteBySetVisible(entityClass, new Object[]{entityid});
	}

	@Override
	public <T> void deleteBySetVisible(Class<T> entityClass, Object[] entityids) {
		
		String className = entityClass.getSimpleName();
		
		String idName = className.toLowerCase() + "id";
		
		//prepare in condition
		StringBuffer buffer = new StringBuffer();
		for (Object entityid : entityids) {
			buffer.append("?").append(",");
		}
		buffer.deleteCharAt(buffer.length()-1);
		String inCondition = buffer.toString();
		
		Query query = em.createQuery("update " + className + " o set o.visible = ? where o." + idName + " in (" + inCondition + ")")
			.setParameter(0, false);
		for (int i=0; i<entityids.length; ++i) {
			query.setParameter(i+1, entityids[i]);
		}
		
		query.executeUpdate();
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
