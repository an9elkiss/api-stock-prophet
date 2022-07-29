package com.an9elkiss.api.spp.api.ads;

import com.an9elkiss.api.spp.command.ads.DailySortAdsCmd;
import com.an9elkiss.api.spp.service.ads.DailySortAdsService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RestController
public class DailySortAdsController {

	@Resource
	private DailySortAdsService dailySortAdsService;

	@RequestMapping(value = "/ads/daily-sort/create", produces = { "application/json" }, method = RequestMethod.POST)
	public ApiResponseCmd<?> create(@RequestBody DailySortAdsCmd cmd) {

		return dailySortAdsService.create(cmd);
    }



}
