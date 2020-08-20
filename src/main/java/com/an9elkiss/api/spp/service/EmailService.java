package com.an9elkiss.api.spp.service;

public interface EmailService {

	void send(String to, String subject, String text);

	void noticeFinaForecast();
}
