package com.salesstock.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.salesstock.dto.BillInfoDto;
import com.salesstock.entity.BillInfo;

public interface HomeService {

	Map<String, List<BillInfoDto>> shopBillPerson(HttpServletRequest request, HttpSession session) throws Exception;

	List<BillInfo> shopBillAdmin(HttpServletRequest request, HttpSession session) throws Exception;

}
