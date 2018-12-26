package com.salesstock.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesstock.bean.ProductCategoryBean;
import com.salesstock.dto.ProductCategoryDto;
import com.salesstock.service.ProductCategoryService;
import com.salesstock.util.Constants;
import com.salesstock.util.Utils;

@Controller
@RequestMapping(value="a/product-catagory")
public class ProductCategoryController {

	@Autowired
	ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/view")
	public String getProductList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	
		List<ProductCategoryDto> list=productCategoryService.getList(request, response, session);
		request.setAttribute("LIST", list);
		return "productCategory/productCategoryList";
	}
	
	@RequestMapping(value = "/show-form")
	public String showProductCategoryForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String page = "common/genericMessage";
		request.setAttribute("message", "Try Again!");
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		switch (Utils.getInt(objRequest.get("actionId"))) {
		case Constants.ACTION_TYPES.SHOW_ADD_FORM:
			model.addAttribute("productCategoryBean", new ProductCategoryBean());
			page = "productCategory/showProductCategoryForm";
			break;
		case Constants.ACTION_TYPES.SHOW_EDIT_FORM:
			ProductCategoryDto pcdto = productCategoryService.getEditForm(request, response, session);
			ProductCategoryBean bean=(ProductCategoryBean)Utils.mapEntityToDTO(pcdto, new ProductCategoryBean());

			model.addAttribute("productCategoryBean", bean);
			page = "productCategory/showProductCategoryForm";
			break;
		default:
			break;
		}
		return page;
	}
	
	@RequestMapping(value="/add-details")
	public String addDetails(@Valid ProductCategoryBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=productCategoryService.addDeatils(pc,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			msg="ERROR_Not added!";
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
	
	@RequestMapping(value="/edit-details")
	public String editDetails(@Valid ProductCategoryBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String msg=null;
		try {
			msg=productCategoryService.editDetails(pc,request, response, session);
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
			msg=productCategoryService.deleteDetails(request, response, session);
		} catch (Exception e) {
			msg="ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
}
