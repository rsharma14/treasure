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

import com.salesstock.bean.BrandsBean;
import com.salesstock.dto.BrandsDto;
import com.salesstock.entity.ProductCategory;
import com.salesstock.service.BrandsService;
import com.salesstock.util.Constants;
import com.salesstock.util.ModelUtilService;
import com.salesstock.util.Utils;

@Controller
@RequestMapping(value = "a/brands")
public class BrandsController {

	@Autowired
	BrandsService serviceBrands;
	@Autowired
	ModelUtilService serviceModelUtil; 

	@RequestMapping(value = "/view")
	public String getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		List<BrandsDto> list = serviceBrands.getList(request, response, session);
		request.setAttribute("LIST", list);
		return "brands/brandsList";
	}

	@RequestMapping(value = "/show-form")
	public String showBrandsForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String page = "common/genericMessage";
		request.setAttribute("message", "Try Again!");
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		switch (Utils.getInt(objRequest.get("actionId"))) {
		case Constants.ACTION_TYPES.SHOW_ADD_FORM:
			model.addAttribute("brandsBean", new BrandsBean());
			page = "brands/showBrandsForm";
			break;
		case Constants.ACTION_TYPES.SHOW_EDIT_FORM:
			BrandsDto pcdto = serviceBrands.getEditForm(request, response, session,objRequest);
			BrandsBean bean=(BrandsBean)Utils.mapEntityToDTO(pcdto, new BrandsBean());
			bean.setProductCategoryId(pcdto.getProductCategoryId());

			model.addAttribute("brandsBean", bean);
			page = "brands/showBrandsForm";
			break;
		default:
			break;
		}
		return page;
	}

	@RequestMapping(value = "/add-details")
	public String addDetails(@Valid BrandsBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String msg = null;
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		try {
			msg = serviceBrands.addDeatils(pc, request, response, session,objRequest);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "ERROR_Not added!";
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}

	@RequestMapping(value = "/edit-details")
	public String editDetails(@Valid BrandsBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String msg = null;
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		try {
			msg = serviceBrands.editDetails(pc, request, response, session,objRequest);
		} catch (Exception e) {
			msg = "ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}

	@RequestMapping(value = "/delete-details")
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String msg = null;
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		try {
			msg = serviceBrands.deleteDetails(request, response, session,objRequest);
		} catch (Exception e) {
			msg = "ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@ModelAttribute("productCatagoryList")
	public Map<String,String> getProductCategory(){ 
		List<ProductCategory> list=serviceModelUtil.getProductCategory();
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
}
