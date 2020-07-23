package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class TushareReqCmd<T> {

	private String api_name;
	private String token;
	private T params;
	private String fields;

}
