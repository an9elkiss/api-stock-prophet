package com.an9elkiss.api.spp.service.tushare;

import com.an9elkiss.api.spp.command.QutationDailyFetchCmd;

public interface TushareClientService {
	String tushareApi(QutationDailyFetchCmd params);
}
