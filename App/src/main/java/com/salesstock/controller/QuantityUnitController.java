package com.salesstock.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesstock.bean.QuantityUnitBean;
import com.salesstock.dto.QuantityUnitDto;
import com.salesstock.service.QuantityUnitService;
import com.salesstock.util.Constants;
import com.salesstock.util.Utils;

@Controller
@RequestMapping(value = "a/quantity-unit")
public class QuantityUnitController {

	@Autowired
	QuantityUnitService serviceQuantityUnit;

	@RequestMapping(value = "/view")
	public String getList(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		List<QuantityUnitDto> list = serviceQuantityUnit.getList(request, response, session);
		request.setAttribute("LIST", list);
		return "quantityUnit/quantityUnitList";
	}

	@RequestMapping(value = "/show-form")
	public String showQuantityUnitForm(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String page = "common/genericMessage";
		request.setAttribute("message", "Try Again!");
		Map<String, Object> objRequest = Utils.getRequestParams(request);

		switch (Utils.getInt(objRequest.get("actionId"))) {
		case Constants.ACTION_TYPES.SHOW_ADD_FORM:
			model.addAttribute("quantityUnitBean", new QuantityUnitBean());
			page = "quantityUnit/showQuantityUnitForm";
			break;
		case Constants.ACTION_TYPES.SHOW_EDIT_FORM:
			QuantityUnitDto pcdto = serviceQuantityUnit.getEditForm(request, response, session);
			QuantityUnitBean bean=(QuantityUnitBean)Utils.mapEntityToDTO(pcdto, new QuantityUnitBean());

			model.addAttribute("quantityUnitBean", bean);
			page = "quantityUnit/showQuantityUnitForm";
			break;
		default:
			break;
		}
		return page;
	}

	@RequestMapping(value = "/add-details")
	public String addDetails(@Valid QuantityUnitBean quantityUnitBean, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String msg = null;
		try {
			msg = serviceQuantityUnit.addDeatils(quantityUnitBean, request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "ERROR_Not added!";
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}

	@RequestMapping(value = "/edit-details")
	public String editDetails(@Valid QuantityUnitBean pc, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String msg = null;
		try {
			msg = serviceQuantityUnit.editDetails(pc, request, response, session);
		} catch (Exception e) {
			msg = "ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}

	@RequestMapping(value = "/delete-details")
	public String deleteDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String msg = null;
		try {
			msg = serviceQuantityUnit.deleteDetails(request, response, session);
		} catch (Exception e) {
			msg = "ERROR_Not Updated!";
			e.printStackTrace();
		}
		request.setAttribute("message", msg);
		return "common/genericMessage";
	}
}
