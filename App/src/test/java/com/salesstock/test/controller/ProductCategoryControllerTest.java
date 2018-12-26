package com.salesstock.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import com.salesstock.controller.ProductCategoryController;
import com.salesstock.dto.ProductCategoryDto;
import com.salesstock.service.ProductCategoryService;

@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryControllerTest {

	@Mock
	ProductCategoryService productCategoryService;
	@Mock
	HttpServletRequest request;
	@Mock
	HttpSession session;
	@Mock
	ModelMap model;
	
	@InjectMocks
	ProductCategoryController productCategoryController;
	
	private MockMvc mockMvc;
	
	static List<ProductCategoryDto> list=new ArrayList<>();
	
	@Before
	public void before() {
		list.add(new ProductCategoryDto("a", "N1", "D1", 1));
		list.add(new ProductCategoryDto("a", "N2", "D2", 0));
		
        mockMvc = MockMvcBuilders.standaloneSetup(productCategoryController).build();

	}
	
	
	@Test
	public void getProductList() {
		when(productCategoryService.getList(request, null, session)).thenReturn(list);
		assertEquals("productCategory/productCategoryList", productCategoryController.getProductList(request, null, session));
		
		verify(productCategoryService).getList(request, null, session);
		
	}

	@Test
	public void showProductCategoryForm() throws Exception{
		 mockMvc.perform(get("a/product-catagory/show-form")
			        .param("actionId", "1"))
			      .andExpect(status().isOk());
//		request.setAttribute("actionId", "1");
		//when(productCategoryService.getList(request, null, session)).thenReturn(list);
//		assertEquals("productCategory/showProductCategoryForm", productCategoryController.showProductCategoryForm(model,request, null, session));
		
		//verify(productCategoryService).getList(request, null, session);
		
	}
	
}
