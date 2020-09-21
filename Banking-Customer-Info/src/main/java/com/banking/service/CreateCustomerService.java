package com.banking.service;

import com.banking.entity.CustomerEntity;
import com.banking.model.Customer;


public interface CreateCustomerService {
	void createCustomer(Customer customerinfo);
	CustomerEntity getCustomerinfo(Long id);
	void updateCustomer(CustomerEntity custinfo);
	CustomerEntity checkEmail(String email);
	CustomerEntity checkUsername(String uname);	
}
