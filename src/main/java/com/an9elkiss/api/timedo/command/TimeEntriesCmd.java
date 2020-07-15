package com.an9elkiss.api.timedo.command;

import java.util.ArrayList;
import java.util.List;

public class TimeEntriesCmd   {

	private List<TimeEntryCmd> timeEntries;

	public TimeEntriesCmd() {
		this.timeEntries = new ArrayList<TimeEntryCmd>();
	}

	public List<TimeEntryCmd> getTimeEntries() {
		return timeEntries;
	}

	public void setTimeEntries(List<TimeEntryCmd> timeEntries) {
		this.timeEntries = timeEntries;
	}

	public void addTimeEntry(TimeEntryCmd timeEntryCmd) {
		timeEntries.add(timeEntryCmd);
	}

}

