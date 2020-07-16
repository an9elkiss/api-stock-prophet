package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.TimeEntryCmd;

public interface TimeEntryService {

	String QUERY_PARAM_DATE_FROM = "dateFrom";
	String QUERY_PARAM_DATE_TO = "dateTo";
	String QUERY_PARAM_TYPE_ID = "typeId";
	String QUERY_PARAM_CREATE_BY = "createBy";


	TimeEntryCmd findById(Integer id);


}
