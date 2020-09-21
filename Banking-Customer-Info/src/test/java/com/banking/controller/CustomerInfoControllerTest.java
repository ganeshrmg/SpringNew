package com.banking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;

import com.banking.BankingConfig;
import com.banking.entity.CustomerEntity;
import com.banking.model.Customer;
import com.banking.model.Duediligence;
import com.banking.repository.CustomerRepository;
import com.banking.service.CreateCustomerService;
import com.banking.service.DueDiligenceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerInfoController.class)
public class CustomerInfoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CreateCustomerService createCustomerService;
	
	@MockBean
	BankingConfig bankingConfig;	
	
	@MockBean
	DueDiligenceService dueDiligenceService;
	
	
	@MockBean
	CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerInfoController customerInfoController;


	@Test
	void testcustomerInformation() throws Exception {		
				
		String customerJson = "{\r\n" + 
				"  \"account\": \"Standard\",\r\n" + 
				"  \"address\": \"St jonh's street\",\r\n" + 
				"  \"contact\": \"9933992211\",\r\n" + 
				"  \"country\": \"INDIA\",\r\n" + 
				"  \"dob\": \"1993-08-13T06:21:35.746Z\",\r\n" + 
				"  \"email\": \"mike@gmail.com\",\r\n" + 
				"  \"name\": \"Mike\",\r\n" + 
				"  \"pan\": \"DDAAGAGD\",\r\n" + 
				"  \"password\": \"mike@123\",\r\n" + 
				"  \"state\": \"TAMILNADU\",\r\n" + 
				"  \"username\": \"mike@123\"\r\n" + 
				"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/customerinfo")
				.accept(MediaType.APPLICATION_JSON).content(customerJson)
				.contentType(MediaType.APPLICATION_JSON);		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();		
		
	    assertEquals(200, response.getStatus());
	}
	
	@Test
	void testcustomerInformationEmail() throws Exception {		
				
		String customerJson = "{\r\n" + 
				"  \"account\": \"Standard\",\r\n" + 
				"  \"address\": \"St jonh's street\",\r\n" + 
				"  \"contact\": \"9933992211\",\r\n" + 
				"  \"country\": \"INDIA\",\r\n" + 
				"  \"dob\": \"1993-08-13T06:21:35.746Z\",\r\n" + 
				"  \"email\": \"mike@gmail.com\",\r\n" + 
				"  \"name\": \"Mike\",\r\n" + 
				"  \"pan\": \"DDAAGAGD\",\r\n" + 
				"  \"password\": \"mike@123\",\r\n" + 
				"  \"state\": \"TAMILNADU\",\r\n" + 
				"  \"username\": \"mike@123\"\r\n" + 
				"}";
		
		CustomerEntity custinfo = new CustomerEntity();
		custinfo.setName("Test");
		custinfo.setUsername("Rmg");
		custinfo.setEmail("test@gmail.com");
		Mockito.when(createCustomerService.checkEmail(Mockito.anyString())).thenReturn(custinfo);
		Mockito.when(createCustomerService.checkUsername(Mockito.anyString())).thenReturn(custinfo);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/customerinfo")
				.accept(MediaType.APPLICATION_JSON).content(customerJson)
				.contentType(MediaType.APPLICATION_JSON);		
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();		
		
	    assertEquals(200, response.getStatus());
	}

	
	@Test
	void testgetCustomer() throws Exception {
		CustomerEntity custinfo = new CustomerEntity();
		custinfo.setName("Test");
		custinfo.setUsername("Rmg");		
		Mockito.when(createCustomerService.getCustomerinfo(Mockito.anyLong())).thenReturn(custinfo);		
		RequestBuilder builder = MockMvcRequestBuilders.get("/v1/customerinfo/53?version=v1")
                .requestAttr("customerid", 53L);
		
		MvcResult result = mockMvc.perform(builder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());		
	}
	@Test
	void testgetCustomerv2() throws Exception {
		CustomerEntity custinfo = new CustomerEntity();
		custinfo.setName("Test");
		custinfo.setUsername("Rmg");		
		Mockito.when(createCustomerService.getCustomerinfo(Mockito.anyLong())).thenReturn(custinfo);		
		RequestBuilder builder = MockMvcRequestBuilders.get("/v2/customerinfo/53?version=v2")
                .requestAttr("customerid", 53L);
		
		MvcResult result = mockMvc.perform(builder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());		
	}
	@Test
	void testgetCustomerDue() throws Exception {
		Duediligence deligence = new Duediligence();
		deligence.setCreditScore(405);
		deligence.setDueDiligenceStatus("Good");
		Mockito.when(dueDiligenceService.getDuediligenceStatus(Mockito.anyLong())).thenReturn(deligence);
		CustomerEntity custinfo = new CustomerEntity();
		custinfo.setName("Test");
		custinfo.setUsername("Rmg");		
		Mockito.when(createCustomerService.getCustomerinfo(Mockito.anyLong())).thenReturn(custinfo);
		
		RequestBuilder builder = MockMvcRequestBuilders.get("/customerdueupdate/14")
                .requestAttr("customerid", 14L);
		MvcResult result = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();		
		assertEquals(200, response.getStatus());
	}
	
//	@Test
//	void testgetCustomerFallback() {	
//		Mockito.when(bankingConfig.getName()).thenReturn("Test");
//		HashMap<String,Object> getFallback = customerInfoController.getCustomerFallback(12L);
//		System.out.println(getFallback);
//	}	

}
