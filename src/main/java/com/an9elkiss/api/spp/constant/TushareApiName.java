package com.an9elkiss.api.spp.constant;

public enum TushareApiName {

	QUOTATION_DAILY("daily", "每日行情");

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
