package com.an9elkiss.api.spp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.an9elkiss.api.spp.service.FinaIndicatorService;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class FinaIndicatorController {

	@Autowired
	private FinaIndicatorService finaIndicatorService;

	@RequestMapping(value = "/fina-indicator/fetch", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> fetch() {

		return finaIndicatorService.fetch();
    }


}
