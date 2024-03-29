package com.an9elkiss.api.spp.api;

import com.an9elkiss.api.spp.command.DailyImportCmd;
import com.an9elkiss.api.spp.command.StkHolderNumberImportCmd;
import com.an9elkiss.api.spp.command.tushare.StockBasicCmd;
import com.an9elkiss.api.spp.service.StockBasicService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RestController
public class StockBasicController {

	@Resource
	private StockBasicService stockBasicService;

	@RequestMapping(value = "/stock-basic/fetch", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> fetch(@RequestBody StockBasicCmd cmd) {

		return stockBasicService.fetch(cmd);
    }

	@RequestMapping(value = "/stock-basic/daily/import", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> importDaily(@RequestBody DailyImportCmd cmd) {

		return stockBasicService.importDaily(cmd);
	}

	@RequestMapping(value = "/stock-basic/stk-holdernumber/import", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> importStkHolderNumber(@RequestBody StkHolderNumberImportCmd cmd) {

		return stockBasicService.importStkHolderNumber(cmd);
	}


}
