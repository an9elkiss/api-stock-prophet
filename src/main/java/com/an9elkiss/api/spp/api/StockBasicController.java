package com.an9elkiss.api.spp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.an9elkiss.api.spp.command.StockBasicFetchCmd;
import com.an9elkiss.api.spp.service.TimeEntryService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import com.an9elkiss.commons.util.JsonUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class StockBasicController {

	@Autowired
	private TimeEntryService timeEntryService;

	@RequestMapping(value = "/stock-basic/fetch", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> fetch(@RequestBody StockBasicFetchCmd cmd) {
		log.debug(JsonUtils.toString(cmd));

		// http client 查询Tushare

		return ApiResponseCmd.success();
    }


}
