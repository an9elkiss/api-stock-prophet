package com.an9elkiss.api.timedo.api;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.an9elkiss.api.timedo.command.TimeEntryCmd;
import com.an9elkiss.api.timedo.service.TimeEntryService;
import com.an9elkiss.commons.auth.spring.Access;
import com.an9elkiss.commons.command.ApiResponseCmd;

import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-28T09:59:07.066Z")

@Controller
public class TimeEntryApiController implements TimeEntryApi {

    private static final Logger log = LoggerFactory.getLogger(TimeEntryApiController.class);

	@Autowired
	private TimeEntryService timeEntryService;

    @Override
	@Access("API_TIME_ENTRY_NEW")
	@RequestMapping(value = "/time-entry", produces = { "application/json" }, consumes = {
			"application/x-www-form-urlencoded" }, method = RequestMethod.POST)
	public ResponseEntity<ApiResponseCmd> addTimeEntry(
			@ApiParam(value = "Date of the time entry", required = true) @RequestParam(value = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
			@ApiParam(value = "Type of time entry", required = true) @RequestParam(value = "typeId", required = true) Integer typeId,
			@ApiParam(value = "Duration of time entry", required = true) @RequestParam(value = "duration", required = true) Integer duration,
			@ApiParam(value = "Comment of time entry") @RequestParam(value = "comment", required = false) String comment) {

    	TimeEntryCmd timeEntryCmd = new TimeEntryCmd();
		timeEntryCmd.setComment(comment);
		timeEntryCmd.setDuration(duration);
		timeEntryCmd.setTypeId(typeId);
		timeEntryCmd.setDate(date);

		ApiResponseCmd cmd = timeEntryService.createTimeEntry(timeEntryCmd);
    	
		return ResponseEntity.ok(cmd);
    }

    @Override
	@Access("API_TIME_ENTRY_DEL")
	@RequestMapping(value = "/time-entry/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
	public ResponseEntity<ApiResponseCmd> deleteTimeEntry(
			@ApiParam(value = "Time entry id to delete", required = true) @PathVariable("id") Integer id) {

		ApiResponseCmd cmd = timeEntryService.deleteTimeEntry(id);

		return ResponseEntity.ok(cmd);
    }

	private Logger logger = LoggerFactory.getLogger(TimeEntryApiController.class);

    @Override
	@Access("API_TIME_ENTRY_GET")
	@RequestMapping(value = "/time-entry/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ApiResponseCmd<TimeEntryCmd>> findTimeEntryById(
			@ApiParam(value = "ID of time entry that needs to be fetched", required = true) @PathVariable("id") Integer id) {

		ApiResponseCmd<TimeEntryCmd> cmd = timeEntryService.findById(id);

		return ResponseEntity.ok(cmd);
    }

    @Override
	@Access("API_TIME_ENTRY_UPDATE")
	@RequestMapping(value = "/time-entry/{id}", produces = { "application/json" }, consumes = {
			"application/x-www-form-urlencoded" }, method = RequestMethod.POST)
	public ResponseEntity<ApiResponseCmd<TimeEntryCmd>> updateTimeEntry(
			@ApiParam(value = "ID of time entry that needs to be updated", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "Updated date of the time entry", required = true) @RequestParam(value = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
			@ApiParam(value = "Updated type of time entry", required = true) @RequestParam(value = "typeId", required = true) Integer typeId,
			@ApiParam(value = "Updated duration of time entry", required = true) @RequestParam(value = "duration", required = true) Integer duration,
			@ApiParam(value = "Updated comment of time entry") @RequestParam(value = "comment", required = false) String comment) {

		TimeEntryCmd timeEntryCmd = new TimeEntryCmd();
		timeEntryCmd.setId(id);
		timeEntryCmd.setComment(comment);
		timeEntryCmd.setDuration(duration);
		timeEntryCmd.setTypeId(typeId);
		timeEntryCmd.setDate(date);

		ApiResponseCmd<TimeEntryCmd> cmd = timeEntryService.updateTimeEntry(timeEntryCmd);

		return ResponseEntity.ok(cmd);
    }

}
