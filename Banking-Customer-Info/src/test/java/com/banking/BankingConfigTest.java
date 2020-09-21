package com.banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.banking.controller.CustomerInfoController;

class BankingConfigTest {
	
	@InjectMocks
	private BankingConfig bankingConfig;

	@Test
	void testConfig() {
		BankingConfig getconfig = new BankingConfig();
		getconfig.setId(1);
		getconfig.setName("Test");
		assertNotNull(getconfig.getName());
	}

}
