package com.an9elkiss.api.timedo.command;

import java.util.Date;

import com.an9elkiss.commons.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DailyStatusCmd   {

	public static final Integer DONE = 1;
	public static final Integer TODAY = 2;
	public static final Integer FUTURE = 3;
	public static final Integer UNDO = 4;

	private Integer dateNum;
	private Integer type;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date date;

	public DailyStatusCmd(Date date, Integer type) {
		this.dateNum = date.getDate();
		this.type = type;
		this.date = date;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getDateNum() {
		return dateNum;
	}

	public void setDateNum(Integer dateNum) {
		this.dateNum = dateNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return JsonUtils.toString(this);
	}

}

