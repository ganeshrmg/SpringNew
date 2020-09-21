package com.banking.repository;

import java.util.List;

import org.springframework.data.repository.*;

import com.banking.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity,Long> {

	List<CustomerEntity> findByEmail(String email);

	List<CustomerEntity> findByUsername(String uname);

}
