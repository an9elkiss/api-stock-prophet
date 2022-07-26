package com.an9elkiss.api.spp.constant;

public enum TushareApiName {

	STOCK_BASIC("stock_basic", "获取基础信息数据，包括股票代码、名称、上市日期、退市日期等"),
	QUOTATION_DAILY("daily", "每日行情"),
	FINA_FORECAST("forecast","业绩预告"),
	FINA_INDICATOR("fina_indicator","财务指标");

	private String eName;
	private String chName;
	
	private TushareApiName(String eName, String chName) {
		this.eName = eName;
		this.chName = chName;
	}

	public String geteName() {
		return eName;
	}

	public String getChName() {
		return chName;
	}


}
