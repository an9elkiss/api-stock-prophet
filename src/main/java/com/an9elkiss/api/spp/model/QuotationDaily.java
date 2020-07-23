package com.an9elkiss.api.spp.model;

import lombok.Data;

@Data
public class QuotationDaily {
	private Integer id;
	private String ts_code;
	private String trade_date;
	private Integer fina_indicator_id;

}
