package com.salesstock.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.salesstock.bean.QuantityUnitBean;
import com.salesstock.dto.QuantityUnitDto;

public interface QuantityUnitService {

	List<QuantityUnitDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	QuantityUnitDto getEditForm(HttpServletRequest request, HttpServletResponse response, HttpSession session);

	String addDeatils(@Valid QuantityUnitBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String editDetails(@Valid QuantityUnitBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception;

	String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
	
}
