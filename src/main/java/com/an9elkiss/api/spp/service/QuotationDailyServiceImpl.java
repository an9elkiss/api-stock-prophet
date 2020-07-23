package com.an9elkiss.api.spp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.FinaForecastDao;
import com.an9elkiss.api.spp.dao.FinaIndicatorDao;
import com.an9elkiss.api.spp.dao.QuotationDailyDao;
import com.an9elkiss.api.spp.exception.SppBizException;
import com.an9elkiss.api.spp.model.FinaForecast;
import com.an9elkiss.api.spp.model.FinaIndicator;
import com.an9elkiss.api.spp.model.QuotationDaily;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import com.an9elkiss.commons.command.Constant;
import com.an9elkiss.commons.command.Status;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QuotationDailyServiceImpl implements QuotationDailyService {

	@Autowired
	private QuotationDailyDao quotationDailyDao;

	@Autowired
	private FinaForecastDao finaForecastDao;

	@Autowired
	private FinaIndicatorDao finaIndicatorDao;

	@Autowired
	private TushareClientService tushareClientService;

	@Value("#{'${spp.stock.my}'.split(',')}")
	private List<String> mStocks;

	private final static String FIELD_TS_CODE = "ts_code";
	private final static String FIELD_TRADE_DATE = "trade_date";
	private final static String FIELD_FINA_FORECAST_ID = "fina_forecast_id";
	private final static String FIELD_FINA_INDICATOR_ID = "fina_indicator_id";

	@Override
	public ApiResponseCmd<Object> fetch(QuotationDailyCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.quotationDaily(cmd);

		int i = quotationDailyDao.save(tushareRespCmd.getData().getFields(), tushareRespCmd.getData().getItems()[0]);
		if (i != 1) {
			throw new SppBizException("拉取quotation daily数据，存DB时异常！");
		}

		return ApiResponseCmd.success();
	}

	@Override
	@Transactional
	public ApiResponseCmd<Object> nextMonthDailysFromForecastAnnDate() {
		
		FinaForecast finaForecast = finaForecastDao.findForQuotationDailysNextMonth(mStocks);
		if(finaForecast == null) {
			return new ApiResponseCmd(Status.NO_TARGET_DATA);
		}
		log.debug("拉取与fina forecast关联的daily数据 {}", finaForecast);
		
		TushareRespCmd tushareRespCmd = tushareClientService.quotationDailysNextMonth(finaForecast.getTs_code(),
				finaForecast.getAnn_date());
		
		int eNum = 0;
		
		for (Object[] item : tushareRespCmd.getData().getItems()) {
			String[] fields = Arrays.copyOf(tushareRespCmd.getData().getFields(),
					tushareRespCmd.getData().getFields().length + 1);
			fields[fields.length - 1] = FIELD_FINA_FORECAST_ID;

			Object[] values = Arrays.copyOf(item, item.length + 1);
			values[values.length - 1] = finaForecast.getId();

			try {
				quotationDailyDao.save(fields, values);
			} catch (DuplicateKeyException e) {
				log.error("拉取与fina forecast关联的daily数据，存DB时唯一键重复！{}", Arrays.toString(values));
				eNum++;
			}
		}

		if (eNum > 0) {
			finaForecast.setDaily_next_month(Constant.ERROR_INT);
			finaForecastDao.update(finaForecast);
			return new ApiResponseCmd(Status.PARTIAL_SUCCESS);
		} else {
			finaForecast.setDaily_next_month(Constant.YES_INT);
			finaForecastDao.update(finaForecast);
			return ApiResponseCmd.success();
		}

	}

	@Override
	@Transactional
	public ApiResponseCmd<Object> nextMonthDailysFromIndicatorAnnDate() {

		FinaIndicator finaIndicator = finaIndicatorDao.findForQuotationDailysNextMonth(mStocks);
		if (finaIndicator == null) {
			return new ApiResponseCmd(Status.NO_TARGET_DATA);
		}
		log.debug("拉取与fina indicator关联的daily数据 {}", finaIndicator);

		TushareRespCmd tushareRespCmd = tushareClientService.quotationDailysNextMonth(finaIndicator.getTs_code(),
				finaIndicator.getAnn_date());
		
		int iTsCode = Arrays.binarySearch(tushareRespCmd.getData().getFields(), FIELD_TS_CODE); 
		int iTradeDate = Arrays.binarySearch(tushareRespCmd.getData().getFields(), FIELD_TRADE_DATE);

		for (Object[] item : tushareRespCmd.getData().getItems()) {
			String[] fields = Arrays.copyOf(tushareRespCmd.getData().getFields(),
					tushareRespCmd.getData().getFields().length + 1);
			fields[fields.length - 1] = FIELD_FINA_FORECAST_ID;

			Object[] values = Arrays.copyOf(item, item.length + 1);
			values[values.length - 1] = finaIndicator.getId();

			try {
				quotationDailyDao.save(fields, values);
			} catch (DuplicateKeyException e) {
				QuotationDaily quotationDaily = new QuotationDaily();
				quotationDaily.setTs_code((String) values[iTsCode]);
				quotationDaily.setTrade_date((String) values[iTradeDate]);
				quotationDaily.setFina_indicator_id(finaIndicator.getId());
				
				quotationDailyDao.update(quotationDaily);
			}
		}

		finaIndicator.setDaily_next_month(Constant.YES_INT);
		finaIndicatorDao.update(finaIndicator);
		return ApiResponseCmd.success();

	}


}
