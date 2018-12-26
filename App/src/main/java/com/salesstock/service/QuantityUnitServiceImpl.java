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

import com.salesstock.bean.QuantityUnitBean;
import com.salesstock.dao.QuantityUnitDao;
import com.salesstock.dto.QuantityUnitDto;
import com.salesstock.entity.QuantityUnit;
import com.salesstock.util.Utils;

@Service
public class QuantityUnitServiceImpl implements QuantityUnitService {

	@Autowired
	QuantityUnitDao daoQuantityUnitDao;
	
	@Transactional
	public List<QuantityUnitDto> getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		List<QuantityUnit> list=null;
		List<QuantityUnitDto> listDto=new ArrayList<>();
		try {
			list = daoQuantityUnitDao.findAll();
			for(QuantityUnit pc:list) {
				listDto.add((QuantityUnitDto)Utils.mapEntityToDTO(pc, new QuantityUnitDto()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listDto=new ArrayList<>();
		}
		return listDto;
	}
	
	@Transactional
	public QuantityUnitDto getEditForm(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		 Map<String, Object> objreq=Utils.getRequestParams(request);
		QuantityUnit pc=null;
		QuantityUnitDto pcdto=null;
		try {
			String[] selectedData=objreq.get("selectedData").toString().split("#");
			objreq.put("id",selectedData[0]);
			pc=(QuantityUnit) daoQuantityUnitDao.loadById(selectedData[0]);
			pcdto=(QuantityUnitDto) Utils.mapEntityToDTO(pc, new QuantityUnitDto());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcdto;
	}
	
	@Transactional
	public String addDeatils(@Valid QuantityUnitBean pcdto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String msg="SUCCESS_Saved!";
		QuantityUnit pc=(QuantityUnit) Utils.mapDTOToEntity(pcdto, new QuantityUnit());
		pc.setId(Utils.getUUID());
		pc =(QuantityUnit) daoQuantityUnitDao.persist(pc);
		System.out.println(pc);
		return msg;
	}
	@Transactional
	public String editDetails(@Valid QuantityUnitBean pcdto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String msg="SUCCESS_Updated!";
		QuantityUnit pc=(QuantityUnit) daoQuantityUnitDao.loadById(pcdto.getId());
		pc=(QuantityUnit) Utils.mapDTOToEntity(pcdto, pc);
		return msg;
	}
	
	@Transactional
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		 Map<String, Object> objreq=Utils.getRequestParams(request);
		 String[] selectedData=objreq.get("selectedData").toString().split("#");
			objreq.put("id",selectedData[0]);
		daoQuantityUnitDao.delete(daoQuantityUnitDao.loadById(selectedData[0]));

		return "SUCCESS_Deleted!";
	}
}
