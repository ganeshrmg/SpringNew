package com.duediligence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BankingCustomerDuediligenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCustomerDuediligenceApplication.class, args);
	}

}
