/**
 * 
 */
package ddd.zjw.support.repository;


import ddd.zjw.support.dao.BasicDao;
import ddd.zjw.support.dao.impl.factory.Join;
import ddd.zjw.support.entity.Entity;
import ddd.zjw.support.utils.BeanUtils;
import ddd.zjw.support.utils.EntityUtils;

import java.io.Serializable;
import java.util.List;

/**
 * The generic ddd factory to load and assemble domain objects together, 
 * according to vObj.xml 
 * @author zjw
 */
public class GenericEntityFactory<S extends Serializable> {
	private Join join;
	private Entity<S> entity;
	private BasicDao dao;

	/**
	 * load and assemble domain objects together.
	 * @param join the join between domain objects.
	 * @param entity the value object
	 * @param dao the data access object
	 */
	public void build(Join join, Entity<S> entity, BasicDao dao) {
		this.join = join;
		this.entity = entity;
		this.dao = dao;
		
		String joinType = join.getJoinType();
		Entity<S> template;
		if("oneToOne".equals(joinType)) {
			template = loadOfOneToOne(join);
			setValueOfJoinToVo(template);
			return;
		}
		if("manyToOne".equals(joinType)) {
			template = loadOfManyToOne(join);
			setValueOfJoinToVo(template);
			return;
		}
		if("oneToMany".equals(joinType)) {
			List<Entity<S>> list = loadOfOneToMany(join);
			setValueOfJoinToVo(list);
			return;
		}
		if("manyToMany".equals(joinType)) {
			throw new RuntimeException("Don't support the many to many relation now!");
		}
	}
	
	/**
	 * load data of the one to one relation.
	 * @param join the join information
	 * @return the entity need to join, if no data then return null.
	 */
	private Entity<S> loadOfOneToOne(Join join) {
		S id = entity.getId();
		String clazz = join.getClazz();
		Entity<S> template = EntityUtils.createEntity(clazz, id);
		return dao.load(id, template);
	}
	
	/**
	 * load data of the many to one relation.
	 * @param join the join information
	 * @return the entity need to join, if no data then return null.
	 */
	private Entity<S> loadOfManyToOne(Join join) {
		String joinKey = join.getJoinKey();
		@SuppressWarnings("unchecked")
		S id = (S) BeanUtils.getValueByField(entity, joinKey);
		if(id==null) return null;
		String clazz = join.getClazz();
		Entity<S> template = EntityUtils.createEntity(clazz, id);
		return dao.load(id, template);
	}
	
	/**
	 * load data of the one to many relation.
	 * @param join the join information
	 * @return the entity need to join, if no data then return null.
	 */
	private List<Entity<S>> loadOfOneToMany(Join join) {
		S id = entity.getId();
		String clazz = join.getClazz();
		Entity<S> template = EntityUtils.createEntity(clazz, null);
		String joinKey = join.getJoinKey();
		BeanUtils.setValueByField(template, joinKey, id);
		return dao.loadAll(template);
	}
	
	/**
	 * set value of the join to the value object.
	 * @param value
	 */
	private void setValueOfJoinToVo(Entity<S> value) {
		String name = join.getName();
		BeanUtils.setValueByField(entity, name, value);
	}
	
	/**
	 * set value of the join to the value object.
	 * @param list the list of value.
	 */
	private void setValueOfJoinToVo(List<Entity<S>> list) {
		String name = join.getName();
		BeanUtils.setValueByField(entity, name, list);
	}
}
