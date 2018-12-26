package com.salesstock.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract  class AbstractGenericDao<E> implements GenericDao<E> {

	private final Class<E> entityClass;

	public AbstractGenericDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public E persist(E entity) throws Exception {
		entityManager.persist(entity);
		return entity;
	}
	
	@Transactional
	public Serializable save(E entity) throws Exception {
		return null;
	}

	@Transactional
	public Serializable saveOrUpdate(E entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void delete(E entity) throws Exception {
		entityManager.remove(entity);
		
	}

	@Transactional
	public void deleteAll() throws Exception {
		
	}

	@Transactional
	public List<E> findAll() throws Exception {
		return  entityManager.createQuery(" from "+entityClass.getName()).getResultList();
	}

	@Transactional
	public E loadById(Serializable id) throws Exception {
		return entityManager.getReference(entityClass,id);
	}

	@Transactional
	public E findById(Serializable id) throws Exception {
		return entityManager.find(entityClass,id);
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<E> findAllByCondition(E entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public List<E> findAllByCondition(String sql) throws Exception {
		return  entityManager.createQuery(sql).getResultList();
	}

}
