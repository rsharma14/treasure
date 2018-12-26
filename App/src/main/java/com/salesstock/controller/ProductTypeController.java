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

import com.salesstock.bean.ProductTypeBean;
import com.salesstock.dto.ProductTypeDto;
import com.salesstock.entity.ProductCategory;
import com.salesstock.service.ProductTypeService;
import com.salesstock.util.Constants;
import com.salesstock.util.ModelUtilService;
import com.salesstock.util.Utils;

@Controller
@RequestMapping(value="a/product-type")
public class ProductTypeController {

	@Autowired
	ProductTypeService serviceProductType;
	@Autowired
	ModelUtilService serviceModelUtil;
	
	@RequestMapping(value="/view")
	public String getList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	
		List<ProductTypeDto> list=serviceProductType.getList(request, response, session);
		request.setAttribute("LIST", list);
		return "productType/productTypeList";
	}
	
	@RequestMapping(value = "/show-form")
	public String showProductTypeForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String page = "common/genericMessage";
		request.setAttribute("message", "Try Again!");
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		switch (Utils.getInt(objRequest.get("actionId"))) {
		case Constants.ACTION_TYPES.SHOW_ADD_FORM:
			model.addAttribute("productTypeBean", new ProductTypeBean());
			page = "productType/showProductTypeForm";
			break;
		case Constants.ACTION_TYPES.SHOW_EDIT_FORM:
			ProductTypeDto pcdto = serviceProductType.getEditForm(request, response, session);
			ProductTypeBean bean=(ProductTypeBean)Utils.mapEntityToDTO(pcdto, new ProductTypeBean());

			model.addAttribute("productTypeBean", bean);
			page = "productType/showProductTypeForm";
			break;
		default:
			break;
		}
		return page;
	}
	
	@RequestMapping(value="/add-details")
	public String addDetails(@Valid ProductTypeBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=serviceProductType.addDeatils(pc,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR_Not added!";
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@RequestMapping(value="/edit-details")
	public String editDetails(@Valid ProductTypeBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=serviceProductType.editDetails(pc,request, response, session);
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
			msg=serviceProductType.deleteDetails(request, response, session);
		} catch (Exception e) {
			msg="ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@ModelAttribute("productCatagoryList")
	public Map<String,String> getProductCategory(){ 
		List<ProductCategory> list=	serviceModelUtil.getProductCategory();
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
}
