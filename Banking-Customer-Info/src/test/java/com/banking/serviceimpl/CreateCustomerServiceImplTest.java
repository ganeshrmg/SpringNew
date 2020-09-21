package com.banking.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import com.banking.entity.CustomerEntity;
import com.banking.model.Customer;
import com.banking.repository.CustomerRepository;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreateCustomerServiceImplTest {
	
	@InjectMocks
	private CreateCustomerServiceImpl createCustomerServiceImpl;
	
	@Mock
	private CustomerRepository customerRepository;
	
	
	@Before
    public void setUp() throws Exception {
          MockitoAnnotations.initMocks(this);
    }	

	@Test
	void testcreateCustomer() {		
		Customer customerinfo = new Customer();
		customerinfo.setName("Test");
		customerinfo.setUsername("Rmg");
		customerinfo.setEmail("tt@gmail.com");
		customerinfo.setAccount("Standard");
		customerinfo.setAddress("Test");
		customerinfo.setContact("2323342312");
		customerinfo.setCountry("India");
		customerinfo.setState("TamilNadu");
		customerinfo.setDob(new Date(0));
		customerinfo.setScore(100);
		customerinfo.setDueapproval("Pending");
		customerinfo.setDuestatus("Good");
		createCustomerServiceImpl.createCustomer(customerinfo);
		assertNotNull(customerinfo);
	}
	
	@Test
	void testgetCustomerinfo() {
		CustomerEntity customerinfo = getData();
		 when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerinfo));
		 CustomerEntity getCust = createCustomerServiceImpl.getCustomerinfo(1L);
		 assertEquals("test@gmail.com",getCust.getEmail());
	}
	
	@Test
	void testcheckEmail() {
		CustomerEntity customerinfo = getData();
		List<CustomerEntity> customerinfolist = new ArrayList<CustomerEntity>();
		customerinfolist.add(customerinfo);
		  when(customerRepository.findByEmail(anyString())).thenReturn(customerinfolist);
		  CustomerEntity getmail =  createCustomerServiceImpl.checkEmail("test@gmail.com");
		 assertEquals("test@gmail.com",getmail.getEmail());
		 System.out.println(getmail);
	}
	
	@Test
	void testcheckUsername() {
		CustomerEntity customerinfo = getData();
		List<CustomerEntity> customerinfolist = new ArrayList<CustomerEntity>();
		customerinfolist.add(customerinfo);
		  when(customerRepository.findByUsername(anyString())).thenReturn(customerinfolist);
		  CustomerEntity getmail =  createCustomerServiceImpl.checkUsername("test");
		 assertEquals("test",getmail.getName());		 
	}
	
	public CustomerEntity getData() {
		CustomerEntity customerinfo = new CustomerEntity();
		customerinfo.setName("test");
		customerinfo.setUsername("Rmg");
		customerinfo.setEmail("test@gmail.com");
		customerinfo.setAccount("Standard");
		customerinfo.setAddress("Test");
		customerinfo.setContact("2323342312");
		customerinfo.setCountry("India");
		customerinfo.setState("TamilNadu");
		customerinfo.setDob(new Date(0));
		customerinfo.setScore(100);
		customerinfo.setDueapproval("Pending");
		customerinfo.setDuestatus("Good");	
		return customerinfo;	
		
	}

}
