package com.an9elkiss.api.spp.command;

import lombok.Data;

@Data
public class StockBasicFetchCmd   {

	private String tsCode;
	private String tradeDate;

}

