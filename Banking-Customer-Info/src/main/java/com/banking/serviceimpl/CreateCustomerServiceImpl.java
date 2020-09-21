package com.banking.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entity.CustomerEntity;
import com.banking.model.Customer;
import com.banking.repository.CustomerRepository;
import com.banking.service.CreateCustomerService;

@Service
public class CreateCustomerServiceImpl implements CreateCustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public void createCustomer(Customer customerinfo) {		
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);		
		CustomerEntity customerEntity = modelmapper.map(customerinfo, CustomerEntity.class);		
		customerRepository.save(customerEntity);	
		
		
	}
	public CustomerEntity getCustomerinfo(Long id) {
		Optional<CustomerEntity> getinfo = customerRepository.findById(id);
		if(getinfo.isEmpty()) {
			return null;
		}else {			
			return getinfo.get();
		}
				
	}
	
	public void updateCustomer(CustomerEntity custinfo) {
		customerRepository.save(custinfo);
	}	
	
	public CustomerEntity checkEmail(String email) {
		List<CustomerEntity> getCustomer = customerRepository.findByEmail(email);		
		CustomerEntity customer = null;
		if(!getCustomer.isEmpty()) {
			customer = getCustomer.get(0);
		}
		return customer;
	}
	
	public CustomerEntity checkUsername(String uname) {
		List<CustomerEntity> getCustomer = customerRepository.findByUsername(uname);		
		CustomerEntity customer = null;
		if(!getCustomer.isEmpty()) {
			customer = getCustomer.get(0);
		}
		return customer;
	}
}
