package com.framework.loippi.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;

/**
 * Service - 基类
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface GenericService<T extends GenericEntity, PK extends Serializable> {

	/**
	 * 保存
	 */
	abstract Long saveEntity(T param);

	/**
	 * 更新
	 */
	abstract Long updateEntity(T param);

	/**
	 * 删除
	 */
	abstract Long deleteEntity(T param);
	/****************************************************/
	/**
	 * 保存
	 */
	abstract Long save(T param);

	/**
	 * 更新
	 */
	abstract Long update(T param);

	/**
	 * 通过id删除单条记录
	 */
	abstract Long delete(PK pk);

	/**
	 * 统计记录数
	 */
	abstract Long count();

	/**
	 * 根据条件查询实体对象数量
	 */
	abstract Long count(Map<String, Object> params);
	
	/****************************************************/
	/**
	 * 根据主键查询数据
	 */
	abstract T find(PK pk);

	/**
	 * 查询所有
	 */
	abstract List<T> findAll();

	/**
	 * 根据条件查询数据
	 */
	abstract T find(String propertyName, Object propertyValue);

	/**
	 * 根据属性名和属性值获取实体对象集合.
	 */
	abstract List<T> findList(String propertyName, Object propertyValue);

	/**
	 * 根据条件查询数据
	 */
	abstract List<T> findList(Map<String, Object> params);
	
	/**
	 * 分页查询列表(添加分页)
	 */
	abstract Page<T> findByPage(Pageable pageable);
}
