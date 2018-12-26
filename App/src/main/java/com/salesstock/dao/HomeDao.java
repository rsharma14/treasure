package com.salesstock.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.salesstock.entity.BillInfo;

public interface HomeDao {

	List<BillInfo> shopBillPerson(HttpServletRequest request, HttpSession session) throws Exception;

}
