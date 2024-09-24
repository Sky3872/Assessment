package com.demo.bean;

import java.io.Serializable;

public class CustomerRequestBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8025939713190883112L;
	
	private Integer customerId;
	
	private Integer pageNo;
	
	private String customerName;
	
	private String userId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
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
}
