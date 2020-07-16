package com.an9elkiss.api.spp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.an9elkiss.api.spp.command.TimeEntryCmd;
import com.an9elkiss.api.spp.service.TimeEntryService;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-28T09:59:07.066Z")

@Controller
public class TimeEntryApiController implements TimeEntryApi {

    private static final Logger log = LoggerFactory.getLogger(TimeEntryApiController.class);

	@Autowired
	private TimeEntryService timeEntryService;

    @Override
	@RequestMapping(value = "/time-entry/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<TimeEntryCmd> findTimeEntryById(
			@ApiParam(value = "ID of time entry that needs to be fetched", required = true) @PathVariable("id") Integer id) {


		return ResponseEntity.ok(timeEntryService.findById(id));
    }


}
