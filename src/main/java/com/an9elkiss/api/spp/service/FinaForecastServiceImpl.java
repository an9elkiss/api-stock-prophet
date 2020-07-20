package com.an9elkiss.api.spp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public ApiResponseCmd<Object> fetch(FinaForecastCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.finaForecast(cmd);

		int e = 0;

		for (Object[] item : tushareRespCmd.getData().getItems()) {
			int i = finaForecastDao.save(tushareRespCmd.getData().getFields(), item);
			if (i != 1) {
				log.error("拉取fina forecast数据，存DB时异常！{}", Arrays.toString(item));
				e++;
			}
		}

		if (e > 0) {
			ApiResponseCmd respCmd = new ApiResponseCmd();
			respCmd.setCode(Status.PARTIAL_SUCCESS_CODE);
			respCmd.setMessage(Status.PARTIAL_SUCCESS_MESSAGE);
			return respCmd;

		}
		return ApiResponseCmd.success();
	}


}