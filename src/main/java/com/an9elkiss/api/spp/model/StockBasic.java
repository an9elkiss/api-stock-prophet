package com.an9elkiss.api.spp.model;

import lombok.Data;

@Data
public class StockBasic {
	private String tsCode;
	private String market;
	private String industry;

}
//  `ts_code` varchar(20) DEFAULT NULL,
//  `symbol` varchar(20) DEFAULT NULL,
//  `name` varchar(10) DEFAULT NULL,
//  `area` varchar(10) DEFAULT NULL,
//  `industry` varchar(10) DEFAULT NULL,
//  `market` varchar(10) DEFAULT NULL,
//  `list_date` varchar(10) DEFAULT NULL