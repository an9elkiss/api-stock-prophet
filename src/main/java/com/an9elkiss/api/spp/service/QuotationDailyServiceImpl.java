package com.an9elkiss.api.spp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.QuotationDailyDao;
import com.an9elkiss.api.spp.exception.SppBizException;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QuotationDailyServiceImpl implements QuotationDailyService {

	@Autowired
	private QuotationDailyDao quotationDailyDao;

	@Autowired
	private TushareClientService tushareClientService;

	@Override
	public ApiResponseCmd<Object> fetch(QuotationDailyCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.quotationDaily(cmd);

		int i = quotationDailyDao.save(tushareRespCmd.getData().getFields(), tushareRespCmd.getData().getItems()[0]);
		if (i != 1) {
			throw new SppBizException("拉取quotation daily数据，存DB时异常！");
		}

		return ApiResponseCmd.success();
	}


}
