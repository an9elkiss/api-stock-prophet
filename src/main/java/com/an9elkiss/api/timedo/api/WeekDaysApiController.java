package com.an9elkiss.api.timedo.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.an9elkiss.api.timedo.command.WeekDaysCmd;
import com.an9elkiss.api.timedo.service.TimeEntryService;
import com.an9elkiss.commons.auth.spring.Access;
import com.an9elkiss.commons.command.ApiResponseCmd;

@Controller
public class WeekDaysApiController implements WeekDaysApi {

	private static final Logger log = LoggerFactory.getLogger(WeekDaysApiController.class);

	@Autowired
	private TimeEntryService timeEntryService;


    @Override
	@Access("API_WEEK_DAYS")
	@RequestMapping(value = "/week-days", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ApiResponseCmd<WeekDaysCmd>> getWeekDays() {

		ApiResponseCmd<WeekDaysCmd> cmd = timeEntryService.getWeekDays();

		return ResponseEntity.ok(cmd);
    }


}
