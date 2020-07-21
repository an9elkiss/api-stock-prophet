package com.an9elkiss.api.spp.model;

import lombok.Data;

@Data
public class FinaForecast {
	private Integer id;
	private String ts_code;
	private String ann_date;
	private Integer daily_next_month;

}
