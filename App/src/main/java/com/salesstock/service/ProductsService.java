package com.salesstock.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesstock.bean.ProductsBean;
import com.salesstock.dto.ProductsViewDto;

public interface ProductsService {

	List<ProductsViewDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	ProductsBean getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	String addDeatils(ProductsBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String editDetails(ProductsBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
}
