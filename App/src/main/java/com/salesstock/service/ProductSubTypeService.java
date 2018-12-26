package com.salesstock.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesstock.bean.ProductSubTypeBean;
import com.salesstock.dto.ProductSubTypeDto;

public interface ProductSubTypeService {

	List<ProductSubTypeDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	ProductSubTypeDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	String addDeatils(ProductSubTypeBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String editDetails(ProductSubTypeBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
}
