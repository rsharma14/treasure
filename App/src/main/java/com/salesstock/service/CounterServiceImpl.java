package com.salesstock.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.salesstock.util.Utils;

public class CounterServiceImpl implements CounterService {

	@Transactional
	public String generateUniqueId(Map<String, String> objRequest) throws Exception
	{
		String generatedUniqueId = null;

		String cNumber = null;
		String prefix = "";
		String suffix = "";
		String parameter = "";
		int counter = 0;
		int padding = 0;
		List<Map<String, Object>> getResult = null;
		String sql = "call getCounter(" + Utils.getInt(objRequest.get("counterFeatureId")) + ",0)";

		//getResult = daoGeneric.findBySql(sql);
		//objRequest = checkCounterReset(getResult, objRequest);// check the ResetCounter 


		if (!getResult.isEmpty())
		{
			Map<String, Object> map = getResult.get(0);

			counter = Utils.getInt(Utils.clearNull(map.get("Counter")));
			padding = Utils.getInt(Utils.clearNull(map.get("Padding")));
			prefix = Utils.clearNull(map.get("Prefix"));
			suffix = Utils.clearNull(map.get("Suffix"));

			if (!Utils.isNullOrEmpty(objRequest.get("parameter")))
				parameter = objRequest.get("parameter");

			cNumber = String.format("%2$0" + padding + "d", counter, counter);
			generatedUniqueId = prefix + parameter + cNumber + suffix;
		}

		if (generatedUniqueId == null)
		{
			throw new Exception("Unable to generatedUniqueId ");
		}
		return generatedUniqueId;
	}



	int compareDates(Date date, Date resetDate)
	{
		int resp = 0;
		if (date.compareTo(resetDate) > 0)
		{
			resp = 1;
		}
		if (date.compareTo(resetDate) < 0)
		{
			resp = -1;
		}
		if (date.compareTo(resetDate) == 0)
		{
			resp = 0;
		}
		return resp;
	}


}
