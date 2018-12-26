package com.salesstock.rest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.salesstock.bean.ProductCategoryBean;
import com.salesstock.rest.bean.ProductCategoryRestBean;

public interface ProductCategoryRestService {

	List<ProductCategoryRestBean> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	ProductCategoryRestBean getEditForm(String id, HttpServletRequest request, HttpServletResponse response, HttpSession session);

	ProductCategoryRestBean addDeatils(@Valid ProductCategoryRestBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	ProductCategoryRestBean editDetails(@Valid ProductCategoryRestBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	boolean deleteDetails(String id,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;

	
}
