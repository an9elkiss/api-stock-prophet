package com.an9elkiss.api.spp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.tushare.FinaIndicatorCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.FinaIndicatorDao;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FinaIndicatorServiceImpl implements FinaIndicatorService {

	@Autowired
	private FinaIndicatorDao finaIndicatorDao;

	@Autowired
	private TushareClientService tushareClientService;

	@Value("#{'${spp.stock.my}'.split(',')}")
	private List<String> mStocks;

	private final static String QUERY_START_DATE = "20130101";

	private void fetch(FinaIndicatorCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.finaIndicator(cmd);


		for (Object[] item : tushareRespCmd.getData().getItems()) {
			finaIndicatorDao.save(tushareRespCmd.getData().getFields(), item);
		}
	}

	@Override
	public ApiResponseCmd<Object> fetch() {

		for (String mStock : mStocks) {
			FinaIndicatorCmd cmd = new FinaIndicatorCmd();
			cmd.setTs_code(mStock);
			cmd.setStart_date(QUERY_START_DATE);

			fetch(cmd);
		}

		return ApiResponseCmd.success();
	}

}
