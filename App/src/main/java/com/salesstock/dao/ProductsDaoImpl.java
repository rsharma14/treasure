package com.salesstock.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.entity.Products;
import com.salesstock.entity.views.ProductsView;

@Repository
public class ProductsDaoImpl extends AbstractGenericDao<Products> implements ProductsDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public List<ProductsView> getProductsView() {
		return  entityManager.createQuery(" from ProductsView").getResultList();

	}
}
