package com.an9elkiss.api.spp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.an9elkiss.api.spp.command.FinaForecastFetchCmd;
import com.an9elkiss.api.spp.service.FinaForecastService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import com.an9elkiss.commons.util.JsonUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class FinaForecastController {

	@Autowired
	private FinaForecastService finaForecastService;

	@RequestMapping(value = "/fina-forecast/fetch", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> fetch(@RequestBody FinaForecastFetchCmd cmd) {
		log.debug(JsonUtils.toString(cmd));

		return finaForecastService.fetch(cmd);
    }

	@RequestMapping(value = "/temp", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> f() {
		log.info("xxx");

		return null;
	}


}
