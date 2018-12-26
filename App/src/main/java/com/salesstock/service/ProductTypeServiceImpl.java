package com.salesstock.service;

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

import com.salesstock.bean.ProductTypeBean;
import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.dao.ProductTypeDao;
import com.salesstock.dto.ProductTypeDto;
import com.salesstock.entity.ProductType;
import com.salesstock.util.Utils;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	ProductTypeDao daoProductType;
	@Autowired
	ProductCategoryDao daoProductCatagory;

	@Transactional
	public List<ProductTypeDto> getList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<ProductType> list = null;
		List<ProductTypeDto> listDto = new ArrayList<>();
		try {
			list = daoProductType.findAll();
			for (ProductType pc : list) {
				listDto.add((ProductTypeDto) Utils.mapEntityToDTO(pc, new ProductTypeDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listDto = new ArrayList<>();
		}
		return listDto;
	}

	@Transactional
	public ProductTypeDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> objreq = Utils.getRequestParams(request);
		ProductType pc = null;
		ProductTypeDto pcdto = null;
		try {
			String[] selectedData = objreq.get("selectedData").toString().split("#");
			objreq.put("id", selectedData[0]);
			pc = (ProductType) daoProductType.loadById(selectedData[0]);
			pcdto = (ProductTypeDto) Utils.mapEntityToDTO(pc, new ProductTypeDto());
			pcdto.setProductCategoryId(pc.getProductCategory().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcdto;
	}

	@Transactional
	public String addDeatils(@Valid ProductTypeBean pcdto, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String msg = "SUCCESS_Saved!";
		ProductType pc = (ProductType) Utils.mapDTOToEntity(pcdto, new ProductType());
		pc.setId(Utils.getUUID());
		pc.setProductCategory(daoProductCatagory.loadById(pcdto.getProductCategoryId()));
		pc = (ProductType) daoProductType.persist(pc);
		return msg;
	}

	@Transactional
	public String editDetails(@Valid ProductTypeBean bean, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String msg = "SUCCESS_Updated!";
		ProductType pc = (ProductType) daoProductType.loadById(bean.getId());
		pc = (ProductType) Utils.mapDTOToEntity(bean, pc);
		pc.setProductCategory(daoProductCatagory.loadById(bean.getProductCategoryId()));
		return msg;
	}

	@Transactional
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		Map<String, Object> objreq = Utils.getRequestParams(request);
		String[] selectedData = objreq.get("selectedData").toString().split("#");
		objreq.put("id", selectedData[0]);
		daoProductType.delete(daoProductType.loadById(selectedData[0]));

		return "SUCCESS_Deleted!";
	}
}
