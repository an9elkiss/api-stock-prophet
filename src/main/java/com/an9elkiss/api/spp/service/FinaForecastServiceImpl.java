package com.an9elkiss.api.spp.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.tushare.FinaForecastCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.FinaForecastDao;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import com.an9elkiss.commons.command.Status;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FinaForecastServiceImpl implements FinaForecastService {

	@Autowired
	private FinaForecastDao finaForecastDao;

	@Autowired
	private TushareClientService tushareClientService;

	@Value("#{'${spp.stock.my}'.split(',')}")
	private List<String> mStocks;

	@Override
	public ApiResponseCmd<Object> fetch(FinaForecastCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.finaForecast(cmd);

		int e = 0;

		for (Object[] item : tushareRespCmd.getData().getItems()) {
			if (cmd.getIsMyStock() != null && cmd.getIsMyStock() && !mStocks.contains(item[0])) {
				continue;
			}

			int i = finaForecastDao.save(tushareRespCmd.getData().getFields(), item);
			if (i != 1) {
				log.error("拉取fina forecast数据，存DB时异常！{}", Arrays.toString(item));
				e++;
			}
		}

		if (e > 0) {
			return new ApiResponseCmd(Status.PARTIAL_SUCCESS);
		}
		return ApiResponseCmd.success();
	}

	@Override
	public void fetchToday() {

		FinaForecastCmd cmd = new FinaForecastCmd();
		cmd.setAnn_date(today());

		fetch(cmd);
	}

	@Override
	public void fetchMyStocksToday() {
		FinaForecastCmd cmd = new FinaForecastCmd();
		cmd.setAnn_date(today());
		cmd.setIsMyStock(true);

		fetch(cmd);
	}

	private String today() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(System.currentTimeMillis());
	}
}
