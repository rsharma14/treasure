package com.salesstock.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.bean.BrandsBean;
import com.salesstock.dao.BrandsDao;
import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.dto.BrandsDto;
import com.salesstock.entity.Brands;
import com.salesstock.entity.ProductCategory;
import com.salesstock.util.Utils;

@Service
public class BrandsServiceImpl implements BrandsService {

	@Autowired
	BrandsDao daoBrands;
	@Autowired
	ProductCategoryDao daoProductCategory;
	
	@Transactional
	public List<BrandsDto> getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<Brands> list=null;
		List<BrandsDto> listDto=new ArrayList<>();
		try {
			list = daoBrands.findAll();
			for(Brands pc:list) {
				listDto.add((BrandsDto)Utils.mapEntityToDTO(pc, new BrandsDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listDto=new ArrayList<>();
		}
		return listDto;
	}
	
	@Transactional
	public BrandsDto getEditForm(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Map<String, Object> objRequest) {
		Brands pc=null;
		BrandsDto dto=null;
		try {
			String[] selectedData=objRequest.get("selectedData").toString().split("#");
			objRequest.put("id",selectedData[0]);
			pc=(Brands) daoBrands.loadById(selectedData[0]);
			dto=(BrandsDto) Utils.mapEntityToDTO(pc, new BrandsDto());
			dto.setProductCategoryId(pc.getProductCategory().stream().map(a->a.getId()).collect(Collectors.toList()));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Transactional
	public String addDeatils(@Valid BrandsBean bean, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Map<String, Object> objRequest) throws Exception{
		String msg="SUCCESS_Saved!";
		Brands br=(Brands) Utils.mapDTOToEntity(bean, new Brands());
		Collection<ProductCategory> list=new ArrayList<>();
		for(String pcId:bean.getProductCategoryId()) {
			ProductCategory pc=new ProductCategory();
			pc.setId(pcId);
			list.add(pc);
		}
		br.setProductCategory(list);
		br.setId(Utils.getUUID());
		br =(Brands) daoBrands.persist(br);

		//System.out.println(pc);
		return msg;
	}
	@Transactional
	public String editDetails(@Valid BrandsBean bean, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Map<String, Object> objRequest) throws Exception{
		String msg="SUCCESS_Updated!";
		Brands br=daoBrands.loadById(bean.getId());
				
		br=(Brands) Utils.mapDTOToEntity(bean, br);
		objRequest.put("selectedData", bean.getId());
		//deleteDetails(request, response, session,objRequest);
		Collection<ProductCategory> list=new ArrayList<>();
		for(String pcId:bean.getProductCategoryId()) {
			ProductCategory pc=new ProductCategory();
			pc.setId(pcId);
			list.add(pc);
		}
		br.setProductCategory(list);
		//br.setId(Utils.getUUID());
		return msg;
	}
	
	@Transactional
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session, Map<String, Object> objRequest) throws Exception{
		 String[] selectedData=objRequest.get("selectedData").toString().split("#");
		 objRequest.put("id",selectedData[0]);
		daoBrands.delete(daoBrands.loadById(selectedData[0]));

		return "SUCCESS_Deleted!";
	}

}
