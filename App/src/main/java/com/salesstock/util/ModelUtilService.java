package com.salesstock.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.dao.ProductTypeDao;
import com.salesstock.entity.ProductCategory;
import com.salesstock.entity.ProductType;

@Service
public class ModelUtilService {
	@Autowired
	ProductCategoryDao daoProductCatagory; 
	@Autowired
	ProductTypeDao daoProductType; 
	
	
	@Transactional
	public List<ProductCategory> getProductCategory(){ 
		List<ProductCategory> list=null;
		try {
			list=	daoProductCatagory.findAllByCondition("from ProductCategory pc WHERE pc.status=1");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		return list;
	}
	
	public List<ProductType> getProductType(){ 
		List<ProductType> list=null;
		try {
			list=	daoProductType.findAllByCondition("from ProductType pt WHERE pt.status=1");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return list;
	}

}
