package com.salesstock.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesstock.bean.ProductTypeBean;
import com.salesstock.dto.ProductTypeDto;

public interface ProductTypeService {

	List<ProductTypeDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	ProductTypeDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	String addDeatils(ProductTypeBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String editDetails(ProductTypeBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
}
