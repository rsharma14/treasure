package com.salesstock.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.salesstock.entity.views.ProductSearchView;

public interface ShoppingBillDao extends GenericDao<Object>{

	List<ProductSearchView> searchByCriteria(Map<String, Object> objRequest) throws Exception;

	void saveProduct(Map<String, Object> obj, HttpServletRequest request, HttpSession session) throws Exception;

}
