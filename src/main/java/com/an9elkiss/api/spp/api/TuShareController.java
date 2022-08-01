package com.an9elkiss.api.spp.api;

import com.an9elkiss.api.spp.command.tushare.TushareImportCmd;
import com.an9elkiss.api.spp.command.tushare.TushareParamsCmd;
import com.an9elkiss.api.spp.command.tushare.TushareReqCmd;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RestController
public class TuShareController {

	@Resource
	private TushareClientService tushareClientService;

	@RequestMapping(value = "/tushare/fetch", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> fetch(@RequestBody TushareReqCmd<TushareParamsCmd> reqCmd) {

		return tushareClientService.fetch(reqCmd);
    }

	@RequestMapping(value = "/tushare/import", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> importData(@RequestBody TushareImportCmd<TushareParamsCmd> cmd) {

		return tushareClientService.importData(cmd);
	}

}
