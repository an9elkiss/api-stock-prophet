package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class TushareRespDataCmd {
	private String[] fields;
	private String[][] items;
	private boolean has_more;
}
