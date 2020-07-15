package com.an9elkiss.api.timedo.command;

import java.util.Date;

import com.an9elkiss.api.timedo.constant.TimeEntryType;
import com.fasterxml.jackson.annotation.JsonFormat;


public class TimeEntryCmd   {

	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date date;
	private Integer dateNum;
	private Integer typeId;
	private String typeName;
	private String iconName;
	private String comment;
	private Integer duration;

	public TimeEntryCmd() {
	}

	public TimeEntryCmd(TimeEntryType type) {
		this.typeId = type.getTypeId();
		this.typeName = type.getTypeName();
		this.iconName = type.getIconName();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Integer getDateNum() {
		return dateNum;
	}

	public void setDateNum(Integer dateNum) {
		this.dateNum = dateNum;
	}

}

