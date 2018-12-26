package com.salesstock.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesstock.bean.ProductCategoryBean;
import com.salesstock.rest.bean.MessageRestBean;
import com.salesstock.rest.bean.ProductCategoryRestBean;
import com.salesstock.rest.service.ProductCategoryRestService;

@RestController
@RequestMapping(value="api/v1/product-catagory")
public class ProductCategoryRestController {

	@Autowired
	ProductCategoryRestService productCategoryService;
	
	@GetMapping
	public ResponseEntity<List<ProductCategoryRestBean>> getProductList(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	
		List<ProductCategoryRestBean> list=productCategoryService.getList(request, response, session);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProductCategoryRestBean> addDetails(@RequestBody ProductCategoryRestBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		ProductCategoryRestBean msg=null;
		try {
			msg=productCategoryService.addDeatils(pc,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping(value="edit")
	public ResponseEntity<ProductCategoryRestBean> getProduct(@QueryParam("id") String id, HttpServletRequest request,HttpServletResponse response,HttpSession session) {
	
		ProductCategoryRestBean pcdto =null;
		try {
			pcdto = productCategoryService.getEditForm(id,request, response, session);
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(pcdto,HttpStatus.OK);
	}
	
	@PutMapping
	public  ResponseEntity<ProductCategoryRestBean> editDetails(@RequestBody ProductCategoryRestBean pc,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		ProductCategoryRestBean resp=null;
		try {
			resp=productCategoryService.editDetails(pc,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<MessageRestBean> deleteDetails(@QueryParam("id") String id,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		MessageRestBean mrb=new MessageRestBean("key", "Success");
		HttpStatus status=HttpStatus.OK;
		try {
			productCategoryService.deleteDetails(id,request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			mrb.setValue("error");
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(mrb,status);
	}
}
