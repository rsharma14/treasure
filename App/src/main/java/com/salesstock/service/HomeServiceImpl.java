package com.salesstock.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salesstock.dao.HomeDao;
import com.salesstock.dto.BillInfoDto;
import com.salesstock.entity.BillInfo;
import com.salesstock.util.Utils;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeDao homeDao;

	@Override
	public List<BillInfo> shopBillAdmin(HttpServletRequest request, HttpSession session) throws Exception {
		return null;
	}

	@Transactional
	public Map<String,List<BillInfoDto>> shopBillPerson(HttpServletRequest request, HttpSession session) {

		List<BillInfo> obj = null;
		List<BillInfoDto> objRet = new ArrayList<>();
		BillInfoDto one=null;
		Map<String,List<BillInfoDto>> ret=new HashMap<>();
		
		try {
			obj = homeDao.shopBillPerson(request, session);
			for(BillInfo o:obj) {
				one=(BillInfoDto)Utils.mapFromObjToObj(o, new BillInfoDto());
				one.setInvoiceId(o.getBillInfoCK().getInvoiceId());
				one.setProductId(o.getBillInfoCK().getProductId());
				objRet.add(one);
			}
			
			if(!objRet.isEmpty()) {
				ret=objRet.stream().collect(Collectors.groupingBy(BillInfoDto::getInvoiceId));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
}
