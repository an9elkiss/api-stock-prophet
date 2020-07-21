package com.an9elkiss.commons.command;

import java.io.Serializable;

public class ApiResponseCmd<T> implements Serializable{
	private static final long serialVersionUID = -2865055966239830849L;

	private Integer code;
	private String message;
	private T data;

	public ApiResponseCmd() {
	}

	public ApiResponseCmd(Status status) {
		setStatus(status);
	}

	public ApiResponseCmd(Status status, Object... params) {
		setStatus(status, params);
	}

	public static ApiResponseCmd success() {
		ApiResponseCmd apiResponseCmd = new ApiResponseCmd();
		apiResponseCmd.setCode(Status.SUCCESS.getCode());
		apiResponseCmd.setMessage(Status.SUCCESS.getMessage());
		return apiResponseCmd;
	}

	public static <D> ApiResponseCmd<D> success(D t) {
		ApiResponseCmd<D> apiResponseCmd = new ApiResponseCmd<D>();
		apiResponseCmd.setCode(Status.SUCCESS.getCode());
		apiResponseCmd.setMessage(Status.SUCCESS.getMessage());
		apiResponseCmd.setData(t);
		return apiResponseCmd;
	}

	public void setStatus(Status status) {
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	public void setStatus(Status status, Object... params) {
		this.code = status.getCode();
		this.message = String.format(status.getMessage(), params);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
