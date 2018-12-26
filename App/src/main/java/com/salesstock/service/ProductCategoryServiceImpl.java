package com.salesstock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.bean.ProductCategoryBean;
import com.salesstock.dao.ProductCategoryDao;
import com.salesstock.dto.ProductCategoryDto;
import com.salesstock.entity.ProductCategory;
import com.salesstock.repository.ProductCategoryRepository;
import com.salesstock.util.Utils;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryDao daoProductCatagory;
	
	@Autowired
	ProductCategoryRepository repositoryProductCategory;
	
	@Transactional
	public List<ProductCategoryDto> getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<ProductCategory> list=null;
		List<ProductCategoryDto> listDto=new ArrayList<>();
		try {
			list = ( List<ProductCategory>)repositoryProductCategory.findAll();
			for(ProductCategory pc:list) {
				listDto.add((ProductCategoryDto)Utils.mapEntityToDTO(pc, new ProductCategoryDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listDto=new ArrayList<>();
		}
		return listDto;
	}
	
	@Transactional
	public ProductCategoryDto getEditForm(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		 Map<String, Object> objreq=Utils.getRequestParams(request);
		ProductCategory pc=null;
		ProductCategoryDto pcdto=null;
		try {
			String[] selectedData=objreq.get("selectedData").toString().split("#");
			objreq.put("id",selectedData[0]);
			pc=(ProductCategory) repositoryProductCategory.getOne(selectedData[0]);
			pcdto=(ProductCategoryDto) Utils.mapEntityToDTO(pc, new ProductCategoryDto());
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcdto;
	}
	
	@Transactional
	public String addDeatils(@Valid ProductCategoryBean pcdto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String msg="SUCCESS_Saved!";
		ProductCategory pc=(ProductCategory) Utils.mapDTOToEntity(pcdto, new ProductCategory());
		pc.setId(Utils.getUUID());
		pc =(ProductCategory) repositoryProductCategory.save(pc);
		return msg;
	}
	@Transactional
	public String editDetails(@Valid ProductCategoryBean pcdto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String msg="SUCCESS_Updated!";
		ProductCategory pc=(ProductCategory) repositoryProductCategory.getOne(pcdto.getId());
		pc=(ProductCategory) Utils.mapDTOToEntity(pcdto, pc);
		return msg;
	}
	
	@Transactional
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		 Map<String, Object> objreq=Utils.getRequestParams(request);
		 String[] selectedData=objreq.get("selectedData").toString().split("#");
			objreq.put("id",selectedData[0]);
			repositoryProductCategory.delete(daoProductCatagory.loadById(selectedData[0]));

		return "SUCCESS_Deleted!";
	}
}
