package com.an9elkiss.api.spp.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.service.FinaForecastService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CronJobService {

	@Autowired
	private FinaForecastService finaForecastService;

	@Scheduled(cron = "${spp.job.fetch-fina-forecast}")
	public void fetchFinaForecast() {
		log.debug("定时任务fetch-fina-forecast开始……");
		finaForecastService.fetchToday();
		log.debug("定时任务fetch-fina-forecast完成。");
	}

}
