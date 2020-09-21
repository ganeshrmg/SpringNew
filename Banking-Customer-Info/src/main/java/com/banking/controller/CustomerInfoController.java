package com.banking.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.banking.BankingConfig;
import com.banking.entity.CustomerEntity;
import com.banking.model.Customer;
import com.banking.model.Duediligence;
import com.banking.repository.CustomerRepository;
import com.banking.service.CreateCustomerService;
import com.banking.service.DueDiligenceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableHystrix
public class CustomerInfoController {
	
	private static Logger logger  = LoggerFactory.getLogger(CustomerInfoController.class);
	
	@Autowired
	CreateCustomerService createCustomerService;
	
	@Autowired
	BankingConfig bankingConfig;
	
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Autowired
	DueDiligenceService dueDiligenceService;


	@PostMapping(value="/customerinfo")
	public ResponseEntity<String> customerInformation(@Valid @RequestBody Customer customerinfo,BindingResult bindingResult) {		
		if(bindingResult.getErrorCount() > 0) {
			return ResponseEntity.ok(bindingResult.getAllErrors().toString());
		}else {
			String error;
			ModelMapper modelmapper = new ModelMapper();
			modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);			
			String encodepassword = Base64.getEncoder().encodeToString(customerinfo.getPassword().getBytes());
			customerinfo.setPassword(encodepassword);
			String email = customerinfo.getEmail();
			String uname = customerinfo.getUsername();
			CustomerEntity checkEmail = createCustomerService.checkEmail(email);
			CustomerEntity checkUsername = createCustomerService.checkUsername(uname);
			if(checkEmail == null && checkUsername == null) {
				Customer customerdata = modelmapper.map(customerinfo, Customer.class);
				customerdata.setDueapproval("Pending");
				createCustomerService.createCustomer(customerdata);
				error = "Customer is created";
			}else {
				error =  "Email id (OR) Username Already Exists";
			}
			
			return ResponseEntity.ok(error);
			 
		}
		
	}

		
		@GetMapping(value="/v1/customerinfo/{customerid}",params="version=v1")
		@HystrixCommand(fallbackMethod = "getCustomerFallback")
		public Map<String,Object> getCustomer(@PathVariable(name="customerid",required=true) Long id) {	
			logger.info("Test");
			HashMap<String,Object> getData = new HashMap<>();			
			CustomerEntity custinfo = createCustomerService.getCustomerinfo(id);
			List<CustomerEntity> customers = new ArrayList<>();
			customers.add(custinfo);
			customers.forEach(cust ->{
				String name = cust.getName();
				logger.info(name);
			});
			
			if(custinfo != null) {				
				getData.put("data", custinfo);
			}
			
			return getData;	 
	}
		
		@GetMapping(value="/v2/customerinfo/{customerid}",params="version=v2")
		@HystrixCommand(fallbackMethod = "getCustomerFallback")
		public Map<String,Object> getUser(@PathVariable(name="customerid",required=true) Long id) {					
			HashMap<String,Object> getCustomerData = new HashMap<>();			
			CustomerEntity custinfo = createCustomerService.getCustomerinfo(id);
			List<CustomerEntity> customers = new ArrayList<>();
			customers.add(custinfo);
			customers.forEach(cust ->{
				String name = cust.getName();
				logger.info(name);
			});
			
			if(custinfo != null) {				
				getCustomerData.put("data", custinfo);
			}
			
			return getCustomerData;	 
	}
		
		
		@GetMapping(value="/customerdueupdate/{customerid}")
		public Duediligence getCustomerDue(@PathVariable(name="customerid",required=true) Long id) {			
			Duediligence getStatus = dueDiligenceService.getDuediligenceStatus(id);
			CustomerEntity custinfo = createCustomerService.getCustomerinfo(id);
			custinfo.setDueapproval("Approved");
			custinfo.setDuestatus(getStatus.getDueDiligenceStatus());
			custinfo.setScore(getStatus.getCreditScore());
			createCustomerService.updateCustomer(custinfo);			
			return getStatus;	 
	}
		
		public Map<String,Object> getCustomerFallback(@PathVariable(name="customerid",required=true) Long id) {
			HashMap<String,Object> getData = new HashMap<>();
			getData.put("data", bankingConfig.getName());
			getData.put("id", id);
			return getData;
		}
		
		@GetMapping(value = "hello")
	    public ModelAndView messages() {
	            ModelAndView mav = new ModelAndView("hello");
	            mav.addObject("messages", customerRepository.findAll());
	            return mav;
	        }
}
