package com.an9elkiss.api.spp.command.tushare;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class FinaForecastCmd {
	private List<Long> shopIds;
	@URL
	private String ann_date;
	private Boolean isMyStock;
}
