package com.an9elkiss.api.spp.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.service.FinaForecastService;
import com.an9elkiss.api.spp.service.QuotationDailyService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CronJobService {

	@Autowired
	private FinaForecastService finaForecastService;

	@Autowired
	private QuotationDailyService quotationDailyService;

//	@Scheduled(cron = "${spp.job.fetch-fina-forecast}")
	public void fetchFinaForecast() {
		log.debug("定时任务fetch-fina-forecast开始……");
		finaForecastService.fetchToday();
		log.debug("定时任务fetch-fina-forecast完成。");
	}

//	@Scheduled(cron = "${spp.job.fetch-forecast-dailys}")
	public void fetchQutationDailysByForecastAnnDate() {
		log.debug("定时任务fetch-forecast-dailys开始……");
		quotationDailyService.nextMonthDailysFromForecastAnnDate();
		log.debug("定时任务fetch-forecast-dailys完成。");
	}

}
