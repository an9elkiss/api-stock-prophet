package com.an9elkiss.api.spp.service.tushare;

import com.an9elkiss.api.spp.command.StkHolderNumberFetchCmd;
import com.an9elkiss.api.spp.command.tushare.*;
import com.an9elkiss.api.spp.constant.TushareApiName;
import com.an9elkiss.api.spp.dao.StockBasicDao;
import com.an9elkiss.api.spp.dao.TuShareDao;
import com.an9elkiss.api.spp.exception.SppBizException;
import com.an9elkiss.api.spp.model.StockBasic;
import com.an9elkiss.commons.command.ApiResponseCmd;
import com.an9elkiss.commons.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
public class TushareClientService {

	private RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(10000)
			.build();

	private CloseableHttpClient httpClient = HttpClients.createDefault();

	@Value("${spp.tushare.token}")
	private String token;

	@Value("${spp.tushare.api.url}")
	private String url;

	@Resource
	private TuShareDao tuShareDao;

	@Resource
	private StockBasicDao stockBasicDao;

	private final static String FIELDS_FINA_INDICATOR = "ts_code,ann_date,end_date,op_income,ebit,netprofit_margin,op_yoy,or_yoy";

	public TushareRespCmd tushareApi(TushareReqCmd<?> reqCmd) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		httpPost.setConfig(requestConfig);

		String jsonReq = JsonUtils.toString(reqCmd);
		log.info("ts-req {}",jsonReq);

		try {
			httpPost.setEntity(new StringEntity(jsonReq));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity httpEntity = response.getEntity();
			String json = EntityUtils.toString(httpEntity, "UTF-8");
			log.info("ts-resp {}",json);

			return JsonUtils.parse(json, TushareRespCmd.class);
		} catch (ParseException | IOException e) {
			throw new SppBizException("请求 Tushare异常！", e);
		}
	}

	public ApiResponseCmd<Integer> fetch(TushareReqCmd<?> reqCmd) {

		TushareRespCmd tushareRespCmd = tushareApi(reqCmd);

		Integer batchSize = 100;
		Integer length = tushareRespCmd.getData().getItems().length;
		for(int i = 0; i < length; i+=batchSize){
			log.info("ts-{}-批量插入 {} / {}", reqCmd.getApi_name(), i, length);
			tuShareDao.batchSave("t_ts_"+reqCmd.getApi_name(),tushareRespCmd.getData().getFields(),
					Arrays.copyOfRange(tushareRespCmd.getData().getItems(), i, i + batchSize > length ? length : i + batchSize));
		}

		return ApiResponseCmd.success(tushareRespCmd.getData().getItems().length);
	}

	public ApiResponseCmd<?> importData(TushareImportCmd<TushareParamsCmd> cmd) {
		StockBasicCmd stockBasicCmd = new StockBasicCmd();
		BeanUtils.copyProperties(cmd, stockBasicCmd);
		stockBasicCmd.setOffSet((cmd.getPage()-1)*cmd.getSize());
		stockBasicCmd.setLimit(cmd.getSize());

		List<StockBasic> stockBasics = stockBasicDao.findByPage(stockBasicCmd);
		log.info("ts-stockBasic-分页查询 第{}页，{}/{}条", cmd.getPage(), stockBasics.size(), cmd.getSize());

		stockBasics.forEach(b->{
			if (tuShareDao.count("t_ts_"+cmd.getApi_name(), b.getTsCode(), cmd.getParams().getStart_date()) > 0){
				log.info("ts-{}-已有数据，跳过 ts_code = {}", cmd.getApi_name(), b.getTsCode());
				return;
			}

			cmd.getParams().setTs_code(b.getTsCode());
			fetch(cmd);

			if(cmd.getSleep() != null) {
				try {
					log.info("线程sleep {} 秒..", cmd.getSleep()/1000.0);
					TimeUnit.MILLISECONDS.sleep(cmd.getSleep());
				} catch (InterruptedException e) {
					log.warn("线程sleep异常..",e);
				}
			}
		});

		return ApiResponseCmd.success();
	}

	public TushareRespCmd stockBasic(StockBasicCmd cmd) {
		TushareReqCmd<StockBasicCmd> req = new TushareReqCmd<>();
		req.setApi_name(TushareApiName.STOCK_BASIC.geteName());
		req.setToken(token);
		req.setParams(cmd);

		return tushareApi(req);
	}

	public TushareRespCmd quotationDaily(QuotationDailyCmd cmd) {

		TushareReqCmd<QuotationDailyCmd> req = new TushareReqCmd<QuotationDailyCmd>();
		req.setApi_name(TushareApiName.QUOTATION_DAILY.geteName());
		req.setToken(token);
		req.setParams(cmd);

		return tushareApi(req);
	}

	public TushareRespCmd quotationDailysNextMonth(String tsCode, String startDate) {
		log.debug("开始日期 {}", startDate);

		String endDate = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date start = df.parse(startDate);
			Date end = DateUtils.addMonths(start, 1);
			endDate = df.format(end);
		} catch (java.text.ParseException e) {
			throw new SppBizException("日期格式化失败！", e);
		}

		QuotationDailyCmd cmd = new QuotationDailyCmd();
		cmd.setTs_code(tsCode);
		cmd.setStart_date(startDate);
		cmd.setEnd_date(endDate);

		return quotationDaily(cmd);
	}

	public TushareRespCmd finaForecast(FinaForecastCmd cmd) {

		TushareReqCmd<FinaForecastCmd> req = new TushareReqCmd<FinaForecastCmd>();
		req.setApi_name(TushareApiName.FINA_FORECAST.geteName());
		req.setToken(token);
		req.setParams(cmd);

		return tushareApi(req);
	}

	public TushareRespCmd finaIndicator(FinaIndicatorCmd cmd) {

		TushareReqCmd<FinaIndicatorCmd> req = new TushareReqCmd<FinaIndicatorCmd>();
		req.setApi_name(TushareApiName.FINA_INDICATOR.geteName());
		req.setToken(token);
		req.setParams(cmd);
		req.setFields(TushareClientService.FIELDS_FINA_INDICATOR);

		return tushareApi(req);
	}

	public TushareRespCmd stkHolderNumber(StkHolderNumberFetchCmd cmd) {
		TushareReqCmd<StkHolderNumberFetchCmd> req = new TushareReqCmd<>();
		req.setApi_name(TushareApiName.STK_HOLDERNUMBER.geteName());
		req.setToken(token);
		req.setParams(cmd);

		return tushareApi(req);
	}
}
