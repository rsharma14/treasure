package com.salesstock.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.entity.ProductCategory;
import com.salesstock.rest.bean.ProductCategoryRestBean;
import com.salesstock.util.Utils;

@Service
public class ProductCategoryRestServiceImpl implements ProductCategoryRestService {

	@Autowired
	ProductCategoryDao daoProductCatagory;
	
	@Transactional
	public List<ProductCategoryRestBean> getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<ProductCategory> list=null;
		List<ProductCategoryRestBean> list1=new ArrayList<>();
		try {
			list = daoProductCatagory.findAll();
			for(ProductCategory pc:list) {
				list1.add((ProductCategoryRestBean)Utils.mapEntityToDTO(pc, new ProductCategoryRestBean()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		return list1;
	}
	
	@Transactional
	public ProductCategoryRestBean getEditForm(String id,HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		ProductCategory pc=null;
		ProductCategoryRestBean pcrb=new ProductCategoryRestBean();
		try {
			pc=(ProductCategory) daoProductCatagory.loadById(id);
			pcrb=(ProductCategoryRestBean)Utils.mapEntityToDTO(pc, pcrb);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcrb;
	}
	
	@Transactional
	public ProductCategoryRestBean addDeatils(@Valid ProductCategoryRestBean pcdto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String msg="SUCCESS_Saved!";
		ProductCategory pc=(ProductCategory) Utils.mapDTOToEntity(pcdto, new ProductCategory());
		pc.setId(Utils.getUUID());
		pc =(ProductCategory) daoProductCatagory.persist(pc);
		pcdto.setId(pc.getId());
		return pcdto;
	}
	@Transactional
	public ProductCategoryRestBean editDetails(@Valid ProductCategoryRestBean pcdto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String msg="SUCCESS_Updated!";
		ProductCategory pc=(ProductCategory) daoProductCatagory.loadById(pcdto.getId());
		pc=(ProductCategory) Utils.mapDTOToEntity(pcdto, pc);
		pcdto=(ProductCategoryRestBean) Utils.mapDTOToEntity(pc, pcdto);

		return pcdto;
	}
	
	@Transactional
	public boolean deleteDetails(String id,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		 Map<String, Object> objreq=Utils.getRequestParams(request);
		daoProductCatagory.delete(daoProductCatagory.loadById(id));

		return true;
	}
	
	
}
