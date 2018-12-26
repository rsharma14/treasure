package com.salesstock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesstock.bean.ProductSubTypeBean;
import com.salesstock.dto.ProductSubTypeDto;
import com.salesstock.entity.ProductType;
import com.salesstock.service.ProductSubTypeService;
import com.salesstock.util.Constants;
import com.salesstock.util.ModelUtilService;
import com.salesstock.util.Utils;

@Controller
@RequestMapping(value="a/product-subtype")
public class ProductSubTypeController {

	@Autowired
	ProductSubTypeService serviceProductSubType;
	@Autowired
	ModelUtilService serviceModelUtil;
	
	@RequestMapping(value="/view")
	public String getList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	
		List<ProductSubTypeDto> list=serviceProductSubType.getList(request, response, session);
		request.setAttribute("LIST", list);
		return "productSubType/productSubTypeList";
	}
	
	@RequestMapping(value = "/show-form")
	public String showProductSubTypeForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String page = "common/genericMessage";
		request.setAttribute("message", "Try Again!");
		
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		switch (Utils.getInt(objRequest.get("actionId"))) {
		case Constants.ACTION_TYPES.SHOW_ADD_FORM:
			request.setAttribute("productSubTypeBean", new ProductSubTypeBean());
			page = "productSubType/showProductSubTypeForm";
			break;
		case Constants.ACTION_TYPES.SHOW_EDIT_FORM:
			ProductSubTypeDto pcdto = serviceProductSubType.getEditForm(request, response, session);
			ProductSubTypeBean bean=(ProductSubTypeBean)Utils.mapEntityToDTO(pcdto, new ProductSubTypeBean());

			request.setAttribute("productSubTypeBean", bean);
			page = "productSubType/showProductSubTypeForm";
			break;
		default:
			break;
		}
		return page;
	}
	
	@RequestMapping(value="/add-details")
	public String addDetails(@Valid ProductSubTypeBean bean,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=serviceProductSubType.addDeatils(bean,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR_Not added!";
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@RequestMapping(value="/edit-details")
	public String editDetails(@Valid ProductSubTypeBean bean,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=serviceProductSubType.editDetails(bean,request, response, session);
		} catch (Exception e) {
			msg="ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@RequestMapping(value="/delete-details")
	public String deleteDetails(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=serviceProductSubType.deleteDetails(request, response, session);
		} catch (Exception e) {
			msg="ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@ModelAttribute("productTypeList")
	public Map<String,String> getProductCategory(){ 
		List<ProductType> list=serviceModelUtil.getProductType();
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
}
