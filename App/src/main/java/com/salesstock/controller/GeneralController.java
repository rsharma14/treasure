package com.salesstock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

	@RequestMapping(value="/")
	public String getHome(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
		return "redirect:a/admin";
	}
	@RequestMapping(value="a/admin")
	public String getAdminHome(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		
		return "../index";
	}
}
