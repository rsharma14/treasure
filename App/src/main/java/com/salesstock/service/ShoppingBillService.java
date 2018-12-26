package com.salesstock.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesstock.dto.ProductSearchViewDto;

public interface ShoppingBillService {

	List<ProductSearchViewDto> getList(HttpServletRequest request,HttpServletResponse response,HttpSession session);

	//boolean printAndSave(HttpServletRequest request, HttpServletResponse response, HttpSession session, ShoppingBill resp) throws Exception;

	Map<String, String> printAndSave(HttpServletRequest request, HttpServletResponse response, HttpSession session, Map<String, Object> resp) throws Exception;
	
}
