package com.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.CustomerListResponseBean;
import com.demo.bean.CustomerRequestBean;
import com.demo.bean.CustomerResponseBean;
import com.demo.bean.ResponseHeaderBean;
import com.demo.dao.CustomerProfileDAOImpl;
import com.demo.entity.CustomerProfileEntity;

import jakarta.transaction.Transactional;

@Service
public class CustomerProfileServiceImpl {

	@Autowired
	private CustomerProfileDAOImpl customerProfileDAOImpl;
	
	public CustomerResponseBean getCustomerProfileById(Integer customerId) {
		CustomerResponseBean customerResponseBean = new CustomerResponseBean();
		customerResponseBean.setResponseHeaderBean(new ResponseHeaderBean());
		try {
			CustomerProfileEntity cp = customerProfileDAOImpl.getCustomerProfileById(customerId);
			
			if(cp != null) {
				customerResponseBean.setCustomerId(cp.getCustomerId());
				customerResponseBean.setCustomerName(cp.getCustomerName());
				customerResponseBean.setUserId(cp.getUserId());
				customerResponseBean.setStatus(cp.getStatus());
			}
			customerResponseBean.getResponseHeaderBean().setStatusCode("00000");
			customerResponseBean.getResponseHeaderBean().setStatusMsg("Success");
		}catch (Exception ex) {
			customerResponseBean.getResponseHeaderBean().setStatusCode("99999");
			customerResponseBean.getResponseHeaderBean().setStatusMsg("Error");
		}
		
		return customerResponseBean;
	}
	
	public CustomerListResponseBean listAllCustomerDetailsByPage(Integer pageNo) {
		CustomerListResponseBean customerListResponseBean = new CustomerListResponseBean();
		customerListResponseBean.setResponseHeaderBean(new ResponseHeaderBean());
		
		try {
			List<CustomerProfileEntity> cpList = customerProfileDAOImpl.listAllCustomerDetailsByPage(pageNo);
			
			if(cpList != null && cpList.size() > 0) {
				List<CustomerResponseBean> customerResponseBeanList = cpList.stream()
						.map(Mapper::toResponseBean)
						.collect(Collectors.toList());
				customerListResponseBean.setCustomerResponseBeanList(customerResponseBeanList);
			}
			customerListResponseBean.getResponseHeaderBean().setStatusCode("00000");
			customerListResponseBean.getResponseHeaderBean().setStatusMsg("Success");
		}catch (Exception ex) {
			customerListResponseBean.getResponseHeaderBean().setStatusCode("99999");
			customerListResponseBean.getResponseHeaderBean().setStatusMsg("Error");
		}
		
		return customerListResponseBean;
	}
	
	@Transactional
	public ResponseHeaderBean addCustomerDetails(CustomerRequestBean customerRequestBean) {
		ResponseHeaderBean responseHeaderBean = new ResponseHeaderBean();
		try {
			Boolean isExists = customerProfileDAOImpl.checkIsUserIdExists(customerRequestBean.getUserId(), null);
			
			if(isExists) {
				responseHeaderBean.setStatusCode("99999");
				responseHeaderBean.setStatusMsg("User ID Exists.");
				return responseHeaderBean;
			}
			
			if(customerRequestBean != null) {
				CustomerProfileEntity cp = new CustomerProfileEntity();
				cp.setCustomerName(customerRequestBean.getCustomerName());
				cp.setUserId(customerRequestBean.getUserId());
				cp.setStatus('A');
				
				customerProfileDAOImpl.saveCustomerProfileEntity(cp);
			}
			responseHeaderBean.setStatusCode("00000");
			responseHeaderBean.setStatusMsg("Success");
		}catch (Exception ex) {
			responseHeaderBean.setStatusCode("99999");
			responseHeaderBean.setStatusMsg("Error");
		}
		return responseHeaderBean;
	}
	
	@Transactional
	public ResponseHeaderBean updateCustomerDetails(CustomerRequestBean customerRequestBean) {
		ResponseHeaderBean responseHeaderBean = new ResponseHeaderBean();
		try {
			
			Boolean isExists = customerProfileDAOImpl.checkIsUserIdExists(customerRequestBean.getUserId(), customerRequestBean.getCustomerId());
			
			if(isExists) {
				responseHeaderBean.setStatusCode("99999");
				responseHeaderBean.setStatusMsg("User ID Exists.");
				return responseHeaderBean;
			}
			
			CustomerProfileEntity cp = customerProfileDAOImpl.getCustomerProfileById(customerRequestBean.getCustomerId());
			if(customerRequestBean != null) {
				cp.setCustomerName(customerRequestBean.getCustomerName());
				cp.setUserId(customerRequestBean.getUserId());
				
				customerProfileDAOImpl.updateCustomerProfileEntity(cp);
			}
			responseHeaderBean.setStatusCode("00000");
			responseHeaderBean.setStatusMsg("Success");
		}catch (Exception ex) {
			responseHeaderBean.setStatusCode("99999");
			responseHeaderBean.setStatusMsg("Error");
		}
		return responseHeaderBean;
	}
	
	public class Mapper {
	    public static CustomerResponseBean toResponseBean(CustomerProfileEntity entity) {
	        CustomerResponseBean bean = new CustomerResponseBean();
	        bean.setCustomerId(entity.getCustomerId());
	        bean.setCustomerName(entity.getCustomerName());
	        bean.setUserId(entity.getUserId());
	        bean.setStatus(entity.getStatus());
	        // map other fields
	        return bean;
	    }
	}
}
