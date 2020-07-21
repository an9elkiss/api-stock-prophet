package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.commons.command.ApiResponseCmd;

public interface QuotationDailyService {

	ApiResponseCmd<Object> fetch(QuotationDailyCmd cmd);

	ApiResponseCmd<Object> nextMonthDailysFromForecastAnnDate();
}
