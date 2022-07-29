package com.an9elkiss.api.spp.model;

import lombok.Data;

@Data
public class DailySortAds {
	private String ts_code;
	private String industry;
	private String date;
	private Integer time_num;
	private String time_unit;
	private Double pct_chg;
	private String market;
	private Integer sort;

}
