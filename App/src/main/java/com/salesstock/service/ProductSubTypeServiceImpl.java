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

import com.salesstock.bean.ProductSubTypeBean;
import com.salesstock.dao.ProductSubTypeDao;
import com.salesstock.dao.ProductTypeDao;
import com.salesstock.dto.ProductSubTypeDto;
import com.salesstock.entity.ProductSubType;
import com.salesstock.util.Utils;

@Service
public class ProductSubTypeServiceImpl implements ProductSubTypeService {

	@Autowired
	ProductSubTypeDao daoProductSubType;
	@Autowired
	ProductTypeDao daoProductType;

	@Transactional
	public List<ProductSubTypeDto> getList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		List<ProductSubType> list = null;
		List<ProductSubTypeDto> listDto = new ArrayList<>();
		try {
			list = daoProductSubType.findAll();
			for (ProductSubType pc : list) {
				listDto.add((ProductSubTypeDto) Utils.mapEntityToDTO(pc, new ProductSubTypeDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listDto = new ArrayList<>();
		}
		return listDto;
	}

	@Transactional
	public ProductSubTypeDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> objreq = Utils.getRequestParams(request);
		ProductSubType pc = null;
		ProductSubTypeDto pcdto = null;
		try {
			String[] selectedData = objreq.get("selectedData").toString().split("#");
			objreq.put("id", selectedData[0]);
			pc = (ProductSubType) daoProductSubType.loadById(selectedData[0]);
			pcdto = (ProductSubTypeDto) Utils.mapEntityToDTO(pc, new ProductSubTypeDto());
			//pcdto.setProductCategoryId(pc.getProductCategory().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcdto;
	}

	@Transactional
	public String addDeatils(@Valid ProductSubTypeBean bean, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String msg = "SUCCESS_Saved!";
		ProductSubType pc = (ProductSubType) Utils.mapDTOToEntity(bean, new ProductSubType());
		pc.setId(Utils.getUUID());
		pc.setProductType(daoProductType.loadById(bean.getProductTypeId()));
		pc = (ProductSubType) daoProductSubType.persist(pc);
		return msg;
	}

	@Transactional
	public String editDetails(@Valid ProductSubTypeBean bean, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String msg = "SUCCESS_Updated!";
		ProductSubType pc = (ProductSubType) daoProductSubType.loadById(bean.getId());
		pc = (ProductSubType) Utils.mapDTOToEntity(bean, pc);
		pc.setProductType(daoProductType.loadById(bean.getProductTypeId()));

		return msg;
	}

	@Transactional
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		Map<String, Object> objreq = Utils.getRequestParams(request);
		String[] selectedData = objreq.get("selectedData").toString().split("#");
		objreq.put("id", selectedData[0]);
		daoProductSubType.delete(daoProductSubType.loadById(selectedData[0]));

		return "SUCCESS_Deleted!";
	}
	
	
}
