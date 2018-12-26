package com.salesstock.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.salesstock.bean.BrandsBean;
import com.salesstock.dto.BrandsDto;

public interface BrandsService {

	List<BrandsDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	BrandsDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session, Map<String, Object> objRequest);

	String addDeatils(@Valid BrandsBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Map<String, Object> objRequest) throws Exception;

	String editDetails(@Valid BrandsBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Map<String, Object> objRequest) throws Exception;

	String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session, Map<String, Object> objRequest) throws Exception;
	
}
