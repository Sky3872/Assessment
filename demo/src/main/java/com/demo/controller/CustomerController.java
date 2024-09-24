package com.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.bean.CustomerListResponseBean;
import com.demo.bean.CustomerRequestBean;
import com.demo.bean.CustomerResponseBean;
import com.demo.bean.ResponseHeaderBean;
import com.demo.service.CustomerProfileServiceImpl;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerProfileServiceImpl customerProfileServiceImpl;
	
	private static final String API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
	
	@GetMapping("/listAllCustomerDetailsByPage")
	@ResponseBody
	public CustomerListResponseBean listAllCustomerDetailsByPage(@RequestBody CustomerRequestBean customerRequestBean) {
		CustomerListResponseBean customerListResponseBean = new CustomerListResponseBean();
		customerListResponseBean = customerProfileServiceImpl.listAllCustomerDetailsByPage(customerRequestBean.getPageNo());

		return customerListResponseBean;
	}
	
	@PostMapping("/getCustomerDetails")
	@ResponseBody
	public CustomerResponseBean getCustomerDetails(@RequestBody CustomerRequestBean customerRequestBean) {
		CustomerResponseBean customerResponseBean = new CustomerResponseBean();
		customerResponseBean = customerProfileServiceImpl.getCustomerProfileById(customerRequestBean.getCustomerId());

		return customerResponseBean;
	}
	
	@PostMapping("/addCustomerDetails")
	@ResponseBody
	public ResponseHeaderBean addCustomerDetails(@RequestBody CustomerRequestBean customerRequestBean) {
		ResponseHeaderBean responseHeaderBean = new ResponseHeaderBean();
		responseHeaderBean = customerProfileServiceImpl.addCustomerDetails(customerRequestBean);

		return responseHeaderBean;
	}
	
	@PostMapping("/updateCustomerDetails")
	@ResponseBody
	public ResponseHeaderBean updateCustomerDetails(@RequestBody CustomerRequestBean customerRequestBean) {
		ResponseHeaderBean responseHeaderBean = new ResponseHeaderBean();
		responseHeaderBean = customerProfileServiceImpl.updateCustomerDetails(customerRequestBean);

		return responseHeaderBean;
	}
	
	@PostMapping("/thirdApiCall")
	@ResponseBody
	public ResponseHeaderBean thirdApiCall(@RequestBody CustomerRequestBean customerRequestBean) {
		ResponseHeaderBean responseHeaderBean = new ResponseHeaderBean();
		try {
            String response = sendGetRequest(API_URL);
            logger.info("Bitcoin Price: " + response);
            responseHeaderBean.setStatusMsg("00000");
            responseHeaderBean.setStatusMsg(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

		return responseHeaderBean;
	}
	
	public static String sendGetRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
