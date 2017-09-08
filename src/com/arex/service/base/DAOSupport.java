package com.arex.service.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arex.bean.PageInfo;

@Transactional
public abstract class DAOSupport<T> implements DAO<T> {

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public void save(Object entity) {
		em.persist(entity);
	}

	@Override
	public void delete(Object entityid) {
		Class<T> entityClass = this.getGenericClass();
		em.remove(em.getReference(entityClass, entityid));
	}

	@Override
	public void delete(Object[] entityids) {
		for (Object entityid : entityids) {
			this.delete(entityid);
		}
	}
	
	@Override
	public void deleteBySetVisible(Object entityid) {
		this.deleteBySetVisible(new Object[]{entityid});
	}

	@Override
	public void deleteBySetVisible(Object[] entityids) {
		
		Class<T> entityClass = this.getGenericClass();
		String entityClassName = this.getEntityClassName(entityClass);
		
		String idName = entityClassName.toLowerCase() + "id";
		
		//prepare in condition
		StringBuffer buffer = new StringBuffer();
		for (Object entityid : entityids) {
			buffer.append("?").append(",");
		}
		buffer.deleteCharAt(buffer.length()-1);
		String inCondition = buffer.toString();
		
		Query query = em.createQuery("update " + entityClassName + " o set o.visible = ? where o." + idName + " in (" + inCondition + ")")
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
	public T find(Object entityid) {
		Class<T> entityClass = this.getGenericClass();
		T entity = em.find(entityClass, entityid);
		
		return entity;
	}

	@Transactional(readOnly=true, propagation=Propagation.NOT_SUPPORTED)
	@Override
	public List<T> find(Object[] entityids) {
		
		List<T> entityList = new ArrayList<T>();
		for (Object entityid : entityids) {
			T entity = this.find(entityid);
			entityList.add(entity);
		}
		return entityList;
	}

	@Override
	public PageInfo<T> findWithPage(int firstResult, int maxResults, LinkedHashMap<String, String> orderby) {
		return this.findWithPage(firstResult, maxResults, null, null, orderby);
	}

	@Override
	public PageInfo<T> findWithPage(int firstResult, int maxResults, String hqlWhere, Object[] params) {
		return this.findWithPage(firstResult, maxResults, hqlWhere, params, null);
	}

	@Override
	public PageInfo<T> findWithPage(int firstResult, int maxResults) {
		return this.findWithPage(firstResult, maxResults, null, null, null);
	}

	@Override
	public PageInfo<T> findWithPage() {
		return this.findWithPage(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<T> findWithPage(int firstResult, int maxResults, String hqlWhere, Object[] params, LinkedHashMap<String, String> orderby) {
		
		Class<T> entityClass = this.getGenericClass();
		PageInfo<T> pageInfo = new PageInfo<T>();
		String entityClassName = getEntityClassName(entityClass);
		String orderStr = getOrderBy(orderby);
		
		Query query = em.createQuery("select o from " + entityClassName + " o " + (hqlWhere==null?"":hqlWhere) + orderStr);
		this.setQueryWhereParams(query, params);
		if (firstResult!=-1 && maxResults!=-1) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
		}
		pageInfo.setEntityList(query.getResultList());
		query = em.createQuery("select count(o) from " + entityClassName + " o");
		pageInfo.setTotalResults((long)query.getSingleResult());
		
		return pageInfo;
	}
	
	private Class<T> getGenericClass() {
		
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>)type.getActualTypeArguments()[0];
		
		return clazz;
	}
	
	private String getEntityClassName(Class<T> entityClass) {
		
		String entityClassName = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity.name() !=null && !"".equals(entity.name())) {
			entityClassName = entity.name();
		}
		
		return entityClassName;
	}
	
	private void setQueryWhereParams(Query query, Object[] params) {
		for (int i=0; params!=null && i<params.length; ++i) {
			query.setParameter(i, params[i]);
		}
	}

	private String getOrderBy(LinkedHashMap<String, String> orderby) {
		
		StringBuffer buffer = new StringBuffer();
		if (orderby != null && !orderby.isEmpty()) {
			buffer.append(" order by ");
			for (String key : orderby.keySet()) {
				buffer.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		
		return buffer.toString();
	}
}
