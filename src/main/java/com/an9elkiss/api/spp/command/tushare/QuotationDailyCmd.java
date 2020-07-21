package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class QuotationDailyCmd {
	private String ts_code;
	private String trade_date;
	private String start_date;
	private String end_date;
}
