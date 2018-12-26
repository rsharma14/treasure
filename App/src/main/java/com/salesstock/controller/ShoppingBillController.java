package com.salesstock.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesstock.dto.ProductSearchViewDto;
import com.salesstock.service.ShoppingBillService;

@Controller
@RequestMapping(value = "a/shopping-bill")
public class ShoppingBillController {

	@Autowired
	ShoppingBillService serviceShoppingBill;

	@RequestMapping(value = "/view")
	public String showView(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		return "shoppingBill/shoppingBill";
	}
	
	@RequestMapping(value = "/search-item")
	@ResponseBody
	public  ResponseEntity<List<ProductSearchViewDto>> getSearchList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		List<ProductSearchViewDto> list = serviceShoppingBill.getList(request, response, session);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/print-save",method=RequestMethod.POST,consumes={MediaType.APPLICATION_JSON})
	public ResponseEntity<Map<String,String>> printAndSave(HttpServletRequest request, HttpServletResponse response,
			HttpSession session,@RequestBody Map<String, Object> resp) {

		Map<String,String> ret=null;
		HttpStatus status =HttpStatus.OK;
		try {
			ret = serviceShoppingBill.printAndSave(request, response, session,resp);
		} catch (Exception e) {
			e.printStackTrace();
			status =HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(ret, status);
	}

}
