package com.an9elkiss.api.spp.service.tushare;

import com.an9elkiss.api.spp.model.FinaForecast;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Temp {

	public static void main(String[] args) {

		FinaForecast finaForecast = new FinaForecast();
		finaForecast.setId(123);
		finaForecast.setTs_code("aaa");
		log.debug("拉取与fina forecast关联的daily数据 {}", finaForecast);

	}

}
