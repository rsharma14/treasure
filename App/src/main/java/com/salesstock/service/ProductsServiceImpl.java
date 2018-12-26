package com.salesstock.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.bean.ProductsBean;
import com.salesstock.dao.ProductQuotationDao;
import com.salesstock.dao.ProductSubTypeDao;
import com.salesstock.dao.ProductTypeDao;
import com.salesstock.dao.ProductsDao;
import com.salesstock.dao.QuantityUnitDao;
import com.salesstock.dto.ProductsViewDto;
import com.salesstock.entity.Brands;
import com.salesstock.entity.ProductQuotation;
import com.salesstock.entity.ProductSubType;
import com.salesstock.entity.Products;
import com.salesstock.entity.views.ProductsView;
import com.salesstock.util.Utils;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsDao daoProducts;
	@Autowired
	ProductTypeDao  daoProductType;
	@Autowired
	ProductSubTypeDao  daoProductSubType;
	@Autowired
	QuantityUnitDao  daoQuantityUnit;
	@Autowired
	ProductQuotationDao  daoProductQuotation;

	@Transactional
	public List<ProductsViewDto> getList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<ProductsView> list = null;
		List<ProductsViewDto> listDto = new ArrayList<>();
		try {
			list = daoProducts.getProductsView();
			for (ProductsView pc : list) {
				listDto.add((ProductsViewDto) Utils.mapEntityToDTO(pc, new ProductsViewDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listDto = new ArrayList<>();
		}
		return listDto;
	}

	@Transactional
	public ProductsBean getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> objreq = Utils.getRequestParams(request);
		Products pc = null;
		ProductsBean pcdto = null;
		try {
			String[] selectedData = objreq.get("selectedData").toString().split("#");
			objreq.put("id", selectedData[0]);
			pc = (Products) daoProducts.loadById(selectedData[0]);
			pcdto = (ProductsBean) Utils.mapEntityToDTO(pc, new ProductsBean());
			
			pcdto.setProductCategoryId(pc.getProductType().getProductCategory().getId());
			pcdto.setProductTypeId(pc.getProductType().getId());
			List<ProductSubType> lo=(List<ProductSubType>)pc.getProductType().getProductSubType();
			
			if(lo.size()>0)
			pcdto.setProductSubTypeId(lo.get(0).getId());
			pcdto.setBrandId(pc.getBrands().stream().map(a->a.getId()).collect(Collectors.toList()));
			pcdto.setQuantityUnit(pc.getProductQuotation().getQuantityUnit().getId());
			pcdto.setQuantity(pc.getProductQuotation().getQuantity());
			pcdto.setPricePerUnit(pc.getProductQuotation().getPricePerUnit());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcdto;
	}

	@Transactional(rollbackFor=Exception.class)
	public String addDeatils(@Valid ProductsBean bean, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String msg = "SUCCESS_Saved!";
		
		ProductQuotation pq=new ProductQuotation();
		pq.setId(Utils.getUUID());
		pq.setQuantity(bean.getQuantity());
		pq.setQuantityUnit(daoQuantityUnit.loadById(bean.getQuantityUnit()));
		pq.setPricePerUnit(Utils.getDouble(bean.getPricePerUnit()));
		pq.setDiscount(0);
		pq.setVendorId(null);
		
		Products pc = (Products) Utils.mapDTOToEntity(bean, new Products());
		pc.setId(Utils.getUUID());
		pc.setProductType(daoProductType.loadById(bean.getProductTypeId()));
		if(bean.getProductSubTypeId()!=null)
			pc.setProductSubType(daoProductSubType.loadById(bean.getProductSubTypeId()));
		
		Collection<Brands> list=new ArrayList<>();
		for(String bId:bean.getBrandId()) {
			Brands obj=new Brands();
			obj.setId(bId);
			list.add(obj);
		}
		
		pc.setBrands(list);		
		
		pq=(ProductQuotation)daoProductQuotation.persist(pq);
		pc.setProductQuotation(pq);

		pc = (Products) daoProducts.persist(pc);
		return msg;
	}

	@Transactional
	public String editDetails(@Valid ProductsBean bean, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		String msg = "SUCCESS_Updated!";
		
		Products pc = daoProducts.loadById(bean.getId());
		
		pc=(Products) Utils.mapDTOToEntity(bean, pc);
		pc.setProductType(daoProductType.loadById(bean.getProductTypeId()));
		
		if(bean.getProductSubTypeId()!=null)
			pc.setProductSubType(daoProductSubType.loadById(bean.getProductSubTypeId()));
		
		Collection<Brands> list=new ArrayList<>();
		for(String bId:bean.getBrandId()) {
			Brands obj=new Brands();
			obj.setId(bId);
			list.add(obj);
		}
		
		pc.setBrands(list);		
		
		ProductQuotation pq=pc.getProductQuotation();
		pq.setQuantity(bean.getQuantity());
		pq.setQuantityUnit(daoQuantityUnit.loadById(bean.getQuantityUnit()));
		
		pq.setPricePerUnit(Utils.getDouble(bean.getPricePerUnit()));
		pq.setDiscount(0);
		pq.setVendorId(null);
		
		pc.setProductQuotation(pq);

		pc = (Products) daoProducts.persist(pc);
		return msg;
	}

	@Transactional
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		Map<String, Object> objreq = Utils.getRequestParams(request);
		String[] selectedData = objreq.get("selectedData").toString().split("#");
		objreq.put("id", selectedData[0]);
		daoProducts.delete(daoProducts.loadById(selectedData[0]));

		return "SUCCESS_Deleted!";
	}
}
