package com.demo.bean;

import java.io.Serializable;
import java.util.List;

public class CustomerListResponseBean implements Serializable {

	private static final long serialVersionUID = 790674583383386087L;
	
	private ResponseHeaderBean responseHeaderBean;
	
	private List<CustomerResponseBean> customerResponseBeanList;

	public List<CustomerResponseBean> getCustomerResponseBeanList() {
		return customerResponseBeanList;
	}

	public void setCustomerResponseBeanList(List<CustomerResponseBean> customerResponseBeanList) {
		this.customerResponseBeanList = customerResponseBeanList;
	}

	public ResponseHeaderBean getResponseHeaderBean() {
		return responseHeaderBean;
	}

	public void setResponseHeaderBean(ResponseHeaderBean responseHeaderBean) {
		this.responseHeaderBean = responseHeaderBean;
	}
}
