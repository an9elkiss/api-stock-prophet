package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.tushare.TushareRespDataCmd;

public interface EmailService {

	void send(String to, String subject, String text);

	void noticeFinaForecast();

	void noticeFinaForecast(TushareRespDataCmd data);
}
