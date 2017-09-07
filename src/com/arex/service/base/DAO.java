package com.arex.service.base;

import java.util.List;

public interface DAO {

	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * 删除实体
	 * @param entityid 实体id
	 */
	public <T> void delete(Class<T> entityClass, Object entityid);
	
	/**
	 * 删除实体
	 * @param entityids 实体id数组
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityids);
	
	/**
	 * 更新实体
	 * @param entity 实体
	 */
	public void update(Object entity);
	
	/**
	 * 查找实体
	 * @param entityClass  实体类
	 * @param entityid  实体id
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Object entityid);
	
	/**
	 * 查找实体
	 * @param entityClass 实体类
	 * @param entityids 实体id数组
	 * @return
	 */
	public <T> List<T> find(Class<T> entityClass, Object[] entityids);
}
