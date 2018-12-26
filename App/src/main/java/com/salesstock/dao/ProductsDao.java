package com.salesstock.dao;

import java.util.List;

import com.salesstock.entity.Products;
import com.salesstock.entity.views.ProductsView;

public interface ProductsDao extends GenericDao<Products>{
	List<ProductsView> getProductsView();
}
