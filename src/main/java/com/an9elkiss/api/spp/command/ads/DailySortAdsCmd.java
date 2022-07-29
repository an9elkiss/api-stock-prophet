package com.an9elkiss.api.spp.command.ads;

import lombok.Data;

@Data
public class DailySortAdsCmd {
	private String market;
	private String date;
	private String timeUnit;

	private String to;

}
