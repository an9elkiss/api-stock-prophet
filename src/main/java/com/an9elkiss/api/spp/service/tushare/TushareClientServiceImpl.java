package com.an9elkiss.api.spp.service.tushare;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.tushare.FinaForecastCmd;
import com.an9elkiss.api.spp.command.tushare.FinaIndicatorCmd;
import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareReqCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.constant.TushareApiName;
import com.an9elkiss.api.spp.exception.SppBizException;
import com.an9elkiss.commons.util.JsonUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TushareClientServiceImpl implements TushareClientService {

	private RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(10000)
			.build();

	private CloseableHttpClient httpClient = HttpClients.createDefault();

	@Value("${spp.tushare.token}")
	private String token;

	@Value("${spp.tushare.api.url}")
	private String url;

	private final static String FIELDS_FINA_INDICATOR = "ts_code,ann_date,end_date,op_income,ebit,netprofit_margin,op_yoy,or_yoy";

	@Override
	public TushareRespCmd tushareApi(TushareReqCmd<?> reqCmd) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		httpPost.setConfig(requestConfig);

		String jsonReq = JsonUtils.toString(reqCmd);

		try {
			httpPost.setEntity(new StringEntity(jsonReq));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity httpEntity = response.getEntity();
			String json = EntityUtils.toString(httpEntity, "UTF-8");

			return JsonUtils.parse(json, TushareRespCmd.class);
		} catch (ParseException | IOException e) {
			throw new SppBizException("请求 Tushare异常！", e);
		}
	}

	@Override
	public TushareRespCmd quotationDaily(QuotationDailyCmd cmd) {

		TushareReqCmd<QuotationDailyCmd> req = new TushareReqCmd<QuotationDailyCmd>();
		req.setApi_name(TushareApiName.QUOTATION_DAILY.geteName());
		req.setToken(token);
		req.setParams(cmd);

		return tushareApi(req);
	}

	@Override
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

	@Override
	public TushareRespCmd finaForecast(FinaForecastCmd cmd) {

		TushareReqCmd<FinaForecastCmd> req = new TushareReqCmd<FinaForecastCmd>();
		req.setApi_name(TushareApiName.FINA_FORECAST.geteName());
		req.setToken(token);
		req.setParams(cmd);

		return tushareApi(req);
	}

	@Override
	public TushareRespCmd finaIndicator(FinaIndicatorCmd cmd) {

		TushareReqCmd<FinaIndicatorCmd> req = new TushareReqCmd<FinaIndicatorCmd>();
		req.setApi_name(TushareApiName.FINA_INDICATOR.geteName());
		req.setToken(token);
		req.setParams(cmd);
		req.setFields(this.FIELDS_FINA_INDICATOR);

		return tushareApi(req);
	}
}
