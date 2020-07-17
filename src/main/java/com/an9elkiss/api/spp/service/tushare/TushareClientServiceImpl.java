package com.an9elkiss.api.spp.service.tushare;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.QutationDailyFetchCmd;
import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareReqCmd;
import com.an9elkiss.commons.util.JsonUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TushareClientServiceImpl implements TushareClientService {

	private String url = "http://api.tushare.pro";
	
//	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	private RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).setSocketTimeout(10000)
			.build();

	private static final CloseableHttpClient httpClient = HttpClients.createDefault();

	@Override
	public String tushareApi(QutationDailyFetchCmd params) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		httpPost.setConfig(requestConfig);

		String jsonReq = JsonUtils.toString(buildQuotationDailyCmd(params));
//		se.setContentType(CONTENT_TYPE_TEXT_JSON);

		try {
			httpPost.setEntity(new StringEntity(jsonReq));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity httpEntity = response.getEntity();
			return EntityUtils.toString(httpEntity, "UTF-8");
		} catch (ParseException | IOException e) {
			log.warn("请求 Tushare异常！", e);
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}
	}

	private TushareReqCmd<QuotationDailyCmd> buildQuotationDailyCmd(QutationDailyFetchCmd params) {
		QuotationDailyCmd cmd = new QuotationDailyCmd();
		cmd.setTs_code(params.getTsCode());
		cmd.setTrade_date(params.getTradeDate());

		TushareReqCmd<QuotationDailyCmd> req = new TushareReqCmd<QuotationDailyCmd>();
		req.setParams(cmd);
		req.setApi_name("daily");

		return req;
	}
}
