package com.salesstock.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesstock.dto.BillInfoDto;
import com.salesstock.entity.BillInfo;
import com.salesstock.service.HomeService;

@Controller
@RequestMapping(value="a/home")
public class HomeController {

	@Autowired
	HomeService serviceHome;
	
	@RequestMapping(value="/view")
	public String getHome(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
		String homePage="home/homePage";
		
		//retrive from DB session
		int key=1;
		Object home=null;
		
		switch (key) {
		case 1:
			home=shopBillPerson(request, session);
			request.setAttribute("DATA", home);
			homePage="home/shopBillPerson";
			break;
		case 2:
			home=shopBillAdmin(request, session);
			homePage="";
			break;
		default:
			break;
		}
		return homePage;
	}

	private Map<String, List<BillInfoDto>> shopBillPerson(HttpServletRequest request,HttpSession session) {
		Map<String, List<BillInfoDto>> ret=null;
		try {
			ret= serviceHome.shopBillPerson(request,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	private List<BillInfo> shopBillAdmin(HttpServletRequest request, HttpSession session) {
		List<BillInfo> ret=null;
		try {
			ret= serviceHome.shopBillAdmin(request,session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
