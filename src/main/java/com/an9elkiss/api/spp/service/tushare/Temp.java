package com.an9elkiss.api.spp.service.tushare;

import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareReqCmd;
import com.an9elkiss.commons.util.JsonUtils;

public class Temp {

	public static void main(String[] args) {
		QuotationDailyCmd cmd = new QuotationDailyCmd();
		cmd.setTs_code("aaa");
		cmd.setTrade_date("bbb");
		
		TushareReqCmd<QuotationDailyCmd> req = new TushareReqCmd<QuotationDailyCmd>();
		req.setParams(cmd);
		req.setApi_name("api");
		
		System.out.println(JsonUtils.toString(req));

	}

}
