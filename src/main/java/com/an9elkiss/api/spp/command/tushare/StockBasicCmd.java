package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class StockBasicCmd {
	private String list_status;
	private String market;

	private Integer offSet;
	private Integer limit;

}
