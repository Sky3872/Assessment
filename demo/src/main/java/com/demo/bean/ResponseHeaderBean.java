package com.demo.bean;

import java.io.Serializable;

public class ResponseHeaderBean implements Serializable {

	private static final long serialVersionUID = -4684864124922714999L;
	
	private String statusCode;
	
	private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
}
