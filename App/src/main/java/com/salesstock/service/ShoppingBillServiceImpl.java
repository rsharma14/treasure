package com.salesstock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.dao.ProductsDao;
import com.salesstock.dao.ShoppingBillDao;
import com.salesstock.dto.ProductSearchViewDto;
import com.salesstock.entity.Products;
import com.salesstock.entity.views.ProductSearchView;
import com.salesstock.util.Utils;

@Service
public class ShoppingBillServiceImpl implements ShoppingBillService {

	@Autowired
	ShoppingBillDao daoShoppingBill;
	
	@Autowired
	ProductsDao daoProducts;

	@Transactional
	public List<ProductSearchViewDto> getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<ProductSearchView> list = null;
		List<ProductSearchViewDto> listDto = new ArrayList<>();
		try {
			Map<String, Object> objRequest = Utils.getRequestParams(request);

			list = daoShoppingBill.searchByCriteria(objRequest);
			for (ProductSearchView pc : list) {
				listDto.add((ProductSearchViewDto) Utils.mapEntityToDTO(pc, new ProductSearchViewDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDto;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Map<String,String> printAndSave(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Map<String, Object> resp) throws Exception {
		
		 Map<String, Object> objreq=Utils.getRequestParams(request);
		 Map<String,String> ret=new HashMap<>();
		 Map<String,Object> obj=null;
		 
		 Products pro=null;
		 String invoiceNo=session.getLastAccessedTime()+"";
		 Date date=new Date();
		 Map<String,Map<String, Object>> json=(Map)resp.get("json");
		 double grandTotal=Utils.getDouble(resp.get("total"));
		 
		 for(String pId:json.keySet()) {
			 obj=json.get(pId);
			pro= daoProducts.loadById(obj.get("productId").toString());
			
			if(pro.getProductQuotation().getQuantity()>=Utils.getInt(obj.get("selectedQuantity").toString())) {
			pro.getProductQuotation().setQuantity(pro.getProductQuotation().getQuantity()-Utils.getInt(obj.get("selectedQuantity").toString()));
			obj.put("invoiceNo", invoiceNo);
			obj.put("date", date);
			obj.put("grandTotal", grandTotal);
			daoShoppingBill.saveProduct(obj,request,session);
			}else {
				throw new Exception("Stock not available!");
			}
			 
		 }
		 ret.put("invoiceNo", invoiceNo);

		return ret;
	}
	
}
