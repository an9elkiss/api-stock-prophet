package com.an9elkiss.api.spp.service.tushare;

import com.an9elkiss.api.spp.command.tushare.*;

public interface TushareClientService {
	TushareRespCmd tushareApi(TushareReqCmd<?> reqCmd);

	TushareRespCmd stockBasic(StockBasicCmd cmd);

	TushareRespCmd quotationDaily(QuotationDailyCmd cmd);

	TushareRespCmd quotationDailysNextMonth(String tsCode, String startDate);

	TushareRespCmd finaForecast(FinaForecastCmd cmd);

	TushareRespCmd finaIndicator(FinaIndicatorCmd cmd);
}
