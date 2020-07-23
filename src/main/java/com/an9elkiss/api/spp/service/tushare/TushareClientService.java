package com.an9elkiss.api.spp.service.tushare;

import com.an9elkiss.api.spp.command.tushare.FinaForecastCmd;
import com.an9elkiss.api.spp.command.tushare.FinaIndicatorCmd;
import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareReqCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;

public interface TushareClientService {
	TushareRespCmd tushareApi(TushareReqCmd<?> reqCmd);

	TushareRespCmd quotationDaily(QuotationDailyCmd cmd);

	TushareRespCmd quotationDailysNextMonth(String tsCode, String startDate);

	TushareRespCmd finaForecast(FinaForecastCmd cmd);

	TushareRespCmd finaIndicator(FinaIndicatorCmd cmd);
}
