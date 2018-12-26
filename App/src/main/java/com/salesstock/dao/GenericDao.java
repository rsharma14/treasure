package com.salesstock.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E> {
	
	E persist(E entity) throws Exception;

	Serializable save(E entity) throws Exception;

	Serializable saveOrUpdate(E entity) throws Exception;

	void delete(E entity) throws Exception;

	void deleteAll() throws Exception;

	List<E> findAll() throws Exception;

	List<E> findAllByCondition(E entity) throws Exception;
	List<E> findAllByCondition(String sql) throws Exception;

	E loadById(Serializable id) throws Exception;

	E findById(Serializable id) throws Exception;

	void clear();

	void flush();
}
