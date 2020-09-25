package com.an9elkiss.api.spp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.tushare.TushareRespDataCmd;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Value("${spp.mail.to}")
	private String to;

	@Value("${spp.mail.subject.notice}")
	private String subjectNotice;

	@Value("${spp.mail.text.fina.forecast}")
	private String textFinaForecast;

	@Override
	public void send(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);

	}

	@Override
	public void noticeFinaForecast() {
		send(to, subjectNotice, textFinaForecast);
	}

	@Override
	public void noticeFinaForecast(TushareRespDataCmd data) {
		send(to, subjectNotice, finaForecastMailText(data));
	}

	private String finaForecastMailText(TushareRespDataCmd data) {
		if (data.getItems() == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(Arrays.asList(data.getFields()));
		buffer.append("\n");
		for (Object[] item : data.getItems()) {
			buffer.append(Arrays.asList(item));
			buffer.append("\n");
		}

		return buffer.toString();
	}
}
