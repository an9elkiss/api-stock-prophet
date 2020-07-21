package com.an9elkiss.commons.command;

public enum Status {

	SUCCESS(200, "操作成功"),
	PARTIAL_SUCCESS(210, "部分成功"),
	NO_TARGET_DATA(241, "无目标数据");

	private Integer code;
	private String message;
	
	private Status(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}


}
