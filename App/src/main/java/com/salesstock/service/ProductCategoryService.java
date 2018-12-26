package com.salesstock.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.salesstock.bean.ProductCategoryBean;
import com.salesstock.dto.ProductCategoryDto;

public interface ProductCategoryService {

	List<ProductCategoryDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	ProductCategoryDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	String addDeatils(@Valid ProductCategoryBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String editDetails(@Valid ProductCategoryBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
}
