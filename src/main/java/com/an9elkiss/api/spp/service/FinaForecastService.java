package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.tushare.FinaForecastCmd;
import com.an9elkiss.commons.command.ApiResponseCmd;

public interface FinaForecastService {

	ApiResponseCmd<Integer> fetch(FinaForecastCmd cmd);

	void fetchToday();

	ApiResponseCmd<Integer> fetchMyStocksToday();

}
