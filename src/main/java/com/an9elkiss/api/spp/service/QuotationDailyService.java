package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.commons.command.ApiResponseCmd;

public interface QuotationDailyService {

	ApiResponseCmd<Integer> fetch(QuotationDailyCmd cmd);

	ApiResponseCmd<Object> nextMonthDailysFromForecastAnnDate();
	
	ApiResponseCmd<Object> nextMonthDailysFromIndicatorAnnDate();
}
