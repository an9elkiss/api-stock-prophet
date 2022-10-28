package com.an9elkiss.api.spp.command.tushare;

import com.an9elkiss.api.spp.validator.TempVal;
import lombok.Data;

@Data
public class TempCmd {
//	@URL
	@TempVal
	private String url;
	private Boolean isMyStock;
}
