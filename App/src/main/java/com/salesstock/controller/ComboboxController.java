package com.salesstock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesstock.dao.BrandsDao;
import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.dao.ProductSubTypeDao;
import com.salesstock.dao.ProductTypeDao;
import com.salesstock.dao.QuantityUnitDao;
import com.salesstock.entity.Brands;
import com.salesstock.entity.ProductCategory;
import com.salesstock.entity.ProductSubType;
import com.salesstock.entity.ProductType;
import com.salesstock.entity.QuantityUnit;
import com.salesstock.util.Utils;

@RestController
@Service
@RequestMapping(value="combobox")
public class ComboboxController {

	@Autowired
	ProductCategoryDao daoProductCategory; 
	@Autowired
	ProductTypeDao daoProductType; 
	@Autowired
	ProductSubTypeDao daoProductSubType;
	@Autowired
	BrandsDao daoBrands;
	@Autowired
	QuantityUnitDao daoQuantityUnit;
	
	Map<String,String> blankOption=new HashMap<>();
	public ComboboxController() {
		blankOption.put("Select", "");
	}
	
	
	@RequestMapping(value="product-category")
	public Map<String,String> getProductCategory(){ 
		List<ProductCategory> list=null;
		try {
			list=	daoProductCategory.findAllByCondition("from ProductCategory pc WHERE pc.status=1");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
	
	@RequestMapping(value="product-type/{id}")
	public Map<String,String> getProductType(@PathVariable("id") String id){ 
		List<ProductType> list=null;
		try {
			list=	daoProductType.findAllByCondition("from ProductType pt WHERE pt.status=1 AND pt.productCategory='"+id+"'");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
	
	@RequestMapping(value="product-subtype/{id}")
	public Map<String,String> getProductSubType(@PathVariable("id") String id){ 
		List<ProductSubType> list=null;
		try {
			list=	daoProductSubType.findAllByCondition("from ProductSubType pst WHERE pst.status=1 AND pst.productType='"+id+"'");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
	
	@RequestMapping(value="brands/{id}")
	public Map<String,String> getBrands(@PathVariable("id") String id){ 
		List<Brands> list=null;
		try {
			list=	daoBrands.findAllByCondition("from Brands b join fetch b.productCategory pc where b.status=1 AND pc.id ='"+id+"'");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
	
	@RequestMapping(value="quantity-unit")
	public Map<String,String> getQuantityUnits(){ 
		List<QuantityUnit> list=null;
		try {
			list=	daoQuantityUnit.findAllByCondition("from QuantityUnit qu where qu.status=1");
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
}
