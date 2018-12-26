package com.salesstock.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.entity.BillInfo;
import com.salesstock.entity.BillInfoCK;
import com.salesstock.entity.views.ProductSearchView;
import com.salesstock.util.Utils;

@Repository
public class ShoppingBillDaoImpl extends AbstractGenericDao<Object> implements ShoppingBillDao{
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public List<ProductSearchView> searchByCriteria(Map<String, Object> objRequest) throws Exception {
		
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductSearchView> query=builder.createQuery(ProductSearchView.class);
		Root<ProductSearchView> root=query.from(ProductSearchView.class);
		
		List<Predicate> pl=new ArrayList<>();
		
		//pl.add(builder.equal(new Expression"1", "1"));
		
		if(!Utils.isNullOrEmpty(objRequest.get("productName"))) {
			pl.add(builder.and(builder.like(root.get("ProductName"), "%"+objRequest.get("productName")+"%")));
		}
		if(Utils.getInt(objRequest.get("filter"))==1) {
		if(!Utils.isNullOrEmptyorNot(objRequest.get("productCategoryId"),"-1")) {
			pl.add(builder.and(builder.equal(root.get("ProductCategoryId"), objRequest.get("productCategoryId"))));
		}
		if(!Utils.isNullOrEmptyorNot(objRequest.get("productTypeId"),"-1")) {
			pl.add(builder.and(builder.equal(root.get("ProductTypeId"), objRequest.get("ProductTypeId"))));		
		}
		if(!Utils.isNullOrEmptyorNot(objRequest.get("brandId"),"-1")) {
			pl.add(builder.and(builder.equal(root.get("BrandId"), objRequest.get("brandId"))));		
		}
		if(!Utils.isNullOrEmpty(objRequest.get("rangeTypeMin"))) {
			pl.add(builder.and(builder.between(root.get("PricePerUnit"), objRequest.get("rangeTypeMin").toString(),objRequest.get("rangeTypeMax").toString())));
		}
		}
		query.where(builder.and(pl.toArray(new Predicate[0])));
		
		 Query query1=entityManager.createQuery(query);
         List<ProductSearchView> list=query1.getResultList();
         System.out.println(list);
		
		return list;
	}
	
	@Transactional
	public void saveProduct(Map<String, Object> obj, HttpServletRequest request, HttpSession session) throws Exception {
		
			BillInfo bi=new BillInfo();
			bi.setBillInfoCK(new BillInfoCK(obj.get("invoiceNo").toString(), obj.get("productId").toString()));
			bi.setProductName(obj.get("productName")+"-"+obj.get("brandName"));
			bi.setTotal(Utils.getDouble(obj.get("totalPrice")));
			bi.setGrandTotal(Utils.getDouble(obj.get("grandTotal")));

			bi.setQuantity(Utils.getInt(obj.get("selectedQuantity")));
			bi.setSessionId(request.getRequestedSessionId());
			bi.setUnit((String)obj.get("qname"));
			bi.setUnitPrice(Utils.getDouble(obj.get("pricePerUnit")));
			bi.setDate((Date)obj.get("date"));
			
		    entityManager.persist(bi);;
	}
}
