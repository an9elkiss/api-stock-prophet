package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class TushareRespCmd {
	private String request_id;
	private String code;
	private String msg;
	private TushareRespDataCmd data;
}
