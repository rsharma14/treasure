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

import com.salesstock.bean.ProductsBean;
import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.dto.ProductsViewDto;
import com.salesstock.entity.ProductCategory;
import com.salesstock.service.ProductsService;
import com.salesstock.util.Constants;
import com.salesstock.util.Utils;

@Controller
@RequestMapping(value="a/products")
public class ProductsController {

	@Autowired
	ProductsService serviceProducts;
	@Autowired
	ProductCategoryDao daoProductCatagory; 
	
	@RequestMapping(value="/view")
	public String getList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	
		List<ProductsViewDto> list=serviceProducts.getList(request, response, session);
		request.setAttribute("LIST", list);
		return "products/productsList";
	}
	
	@RequestMapping(value = "/show-form")
	public String showProductTypeForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String page = "common/genericMessage";
		request.setAttribute("message", "Try Again!");
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		switch (Utils.getInt(objRequest.get("actionId"))) {
		case Constants.ACTION_TYPES.SHOW_ADD_FORM:
			request.setAttribute("ProductsBean", new ProductsBean());
			page = "products/showProductsForm";
			break;
		case Constants.ACTION_TYPES.SHOW_EDIT_FORM:
			ProductsBean bean = serviceProducts.getEditForm(request, response, session);

			request.setAttribute("ProductsBean", bean);
			page = "products/showProductsForm";
			break;
		default:
			break;
		}
		return page;
	}
	
	@RequestMapping(value="/add-details")
	public String addDetails(HttpServletRequest request,HttpServletResponse response,HttpSession session,@Valid ProductsBean pc) {
		String msg=null;
		try {
			msg=serviceProducts.addDeatils(pc,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR_Not added!";
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@RequestMapping(value="/edit-details")
	public String editDetails(@Valid ProductsBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=serviceProducts.editDetails(pc,request, response, session);
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
			msg=serviceProducts.deleteDetails(request, response, session);
		} catch (Exception e) {
			msg="ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@ModelAttribute("productCatagoryList")
	public Map<String,String> getProductCategory(){ 
		List<ProductCategory> list=null;
		try {
			list=	daoProductCatagory.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			list=new ArrayList<>();
		}
		
		return Utils.getCombobox(new ArrayList<Object>(list), "getId", "", "getName", "", false, false,true);

	}
}
