package com.arex.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import com.arex.bean.PageInfo;

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
	 * 删除实体（设置实体中的visible为false）
	 * @param entityClass
	 * @param entityid
	 */
	public <T> void deleteBySetVisible(Class<T> entityClass, Object entityid);
	
	/**
	 * 删除实体（设置实体中的visible为false）
	 * @param entityClass
	 * @param entityids
	 */
	public <T> void deleteBySetVisible(Class<T> entityClass, Object[] entityids);
	
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
	
	/**
	 * 分页查询实体
	 * @param entityClass 实体类
	 * @param firstResult 第一条记录
	 * @param maxResults 一页显示的记录数
	 * @param hqlWhere 筛选条件
	 * @param params  筛选参数
	 * @param orderby  排序
	 * @return
	 */
	public <T> PageInfo<T> findWithPage(Class<T> entityClass, int firstResult, int maxResults, String hqlWhere, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 分页查询实体
	 * @param entityClass 实体类
	 * @param firstResult 第一条记录
	 * @param maxResults 一页显示的记录数
	 * @param orderby  排序
	 * @return
	 */
	public <T> PageInfo<T> findWithPage(Class<T> entityClass, int firstResult, int maxResults, LinkedHashMap<String, String> orderby);
	
	/**
	 * 分页查询实体
	 * @param entityClass 实体类
	 * @param firstResult 第一条记录
	 * @param maxResults 一页显示的记录数
	 * @param hqlWhere 筛选条件
	 * @param params  筛选参数
	 * @return
	 */
	public <T> PageInfo<T> findWithPage(Class<T> entityClass, int firstResult, int maxResults, String hqlWhere, Object[] params);
	
	/**
	 * 分页查询实体
	 * @param entityClass 实体类
	 * @param firstResult 第一条记录
	 * @param maxResults 一页显示的记录数
	 * @return
	 */
	public <T> PageInfo<T> findWithPage(Class<T> entityClass, int firstResult, int maxResults);
	
	/**
	 * 查询所有实体
	 * @param entityClass 实体类
	 * @return
	 */
	public <T> PageInfo<T> findWithPage(Class<T> entityClass);
}
