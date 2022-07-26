package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.tushare.FinaForecastCmd;
import com.an9elkiss.api.spp.command.tushare.StockBasicCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.FinaForecastDao;
import com.an9elkiss.api.spp.dao.StockBasicDao;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import com.an9elkiss.commons.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class StockBasicService {

	@Resource
	private StockBasicDao stockBasicDao;

	@Resource
	private TushareClientService tushareClientService;


	public ApiResponseCmd<Integer> fetch(StockBasicCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.stockBasic(cmd);

		Arrays.stream(tushareRespCmd.getData().getItems()).forEach(item-> stockBasicDao.save(tushareRespCmd.getData().getFields(), item));

		return ApiResponseCmd.success(tushareRespCmd.getData().getItems().length);
	}


}
