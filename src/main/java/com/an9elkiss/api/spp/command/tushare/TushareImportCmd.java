package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class TushareImportCmd<T> extends TushareReqCmd<T> {

	private String market;
	private Integer page;
	private Integer size;
	private Integer sleep;

}
