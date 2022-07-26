package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class DailyImportCmd {
	private String market;
	private Integer page;
	private Integer size;

	private String start_date;
	private String end_date;

}

