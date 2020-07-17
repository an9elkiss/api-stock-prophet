package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;

@Data
public class TushareReqCmd<T> {

	private String api_name;
	private String token = "640fd047dc9ca8079402b9d51902f27a545e1c82fd7629ac78ddda36";
	private T params;

//	{
//	    "api_name": "daily",
//	    "token": "640fd047dc9ca8079402b9d51902f27a545e1c82fd7629ac78ddda36",
//	    "params": {
//	        "ts_code": "000651.SZ",
//	        "trade_date": "20200716"
//	                },
//	    "fields": ""
//	}

}
