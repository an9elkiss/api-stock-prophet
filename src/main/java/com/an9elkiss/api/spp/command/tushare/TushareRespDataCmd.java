package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class TushareRespDataCmd {
	private String[] fields;
	private Object[][] items;
	private boolean has_more;
}
