package com.demo.bean;

import java.io.Serializable;

public class CustomerResponseBean implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4153518428720244849L;

	private ResponseHeaderBean responseHeaderBean;
	
	private Integer customerId;
	
	private String customerName;
	
	private String userId;
	
	private Character status;
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public ResponseHeaderBean getResponseHeaderBean() {
		return responseHeaderBean;
	}

	public void setResponseHeaderBean(ResponseHeaderBean responseHeaderBean) {
		this.responseHeaderBean = responseHeaderBean;
	}
	
}
