package com.an9elkiss.api.spp.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.service.EmailService;
import com.an9elkiss.api.spp.service.FinaForecastService;
import com.an9elkiss.api.spp.service.QuotationDailyService;
import com.an9elkiss.commons.command.ApiResponseCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CronJobService {

	@Autowired
	private FinaForecastService finaForecastService;

	@Autowired
	private QuotationDailyService quotationDailyService;

	@Autowired
	private EmailService emailService;

	@Scheduled(cron = "${spp.job.fetch-fina-forecast}")
	public void fetchFinaForecast() {
		log.debug("定时任务fetch-fina-forecast开始……");
		ApiResponseCmd<Integer> resp = finaForecastService.fetchMyStocksToday();

		if (resp.getData() > 0) {
			emailService.noticeFinaForecast();
		}
		log.debug("定时任务fetch-fina-forecast完成。");
	}

//	@Scheduled(cron = "${spp.job.fetch-forecast-dailys}")
	public void fetchQutationDailysByForecastAnnDate() {
		log.debug("定时任务fetch-forecast-dailys开始……");
		quotationDailyService.nextMonthDailysFromForecastAnnDate();
		log.debug("定时任务fetch-forecast-dailys完成。");
	}

//	@Scheduled(cron = "${spp.job.fetch-indicator-dailys}")
	public void fetchQutationDailysByIndicatorAnnDate() {
		log.debug("定时任务fetch-indicator-dailys开始……");
		quotationDailyService.nextMonthDailysFromIndicatorAnnDate();
		log.debug("定时任务fetch-indicator-dailys完成。");
	}

}
