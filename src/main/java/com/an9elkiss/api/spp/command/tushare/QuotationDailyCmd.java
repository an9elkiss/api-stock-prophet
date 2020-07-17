package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class QuotationDailyCmd {
//    "ts_code": "000651.SZ",
//    "trade_date": "20200716"

	private String ts_code;
	private String trade_date;
}
