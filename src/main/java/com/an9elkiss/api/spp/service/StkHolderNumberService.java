package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.StkHolderNumberFetchCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.StkHolderNumberDao;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Log4j2
@Service
public class StkHolderNumberService {

	@Resource
	private StkHolderNumberDao stkHolderNumberDao;

	@Resource
	private TushareClientService tushareClientService;


	public ApiResponseCmd<Integer> fetch(StkHolderNumberFetchCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.stkHolderNumber(cmd);

		Integer batchSize = 100;
		Integer length = tushareRespCmd.getData().getItems().length;
		for(int i = 0; i < length; i+=batchSize){
			log.info("ts-tsk-holdernumber-批量插入 {} / {}", i, length);
			stkHolderNumberDao.batchSave(tushareRespCmd.getData().getFields(),
					Arrays.copyOfRange(tushareRespCmd.getData().getItems(), i, i + batchSize > length ? length : i + batchSize));
		}

		return ApiResponseCmd.success(tushareRespCmd.getData().getItems().length);
	}



}
