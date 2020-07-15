package com.an9elkiss.api.timedo.command;

import java.util.ArrayList;
import java.util.List;

public class WeekDaysCmd   {

	private List<DailyStatusCmd> days;

	public WeekDaysCmd() {
		this.days = new ArrayList<DailyStatusCmd>();
	}

	public List<DailyStatusCmd> getDays() {
		return days;
	}

	public void setDays(List<DailyStatusCmd> days) {
		this.days = days;
	}

	public void addDailyStatus(DailyStatusCmd dailyStatus) {
		days.add(dailyStatus);
	}

}

