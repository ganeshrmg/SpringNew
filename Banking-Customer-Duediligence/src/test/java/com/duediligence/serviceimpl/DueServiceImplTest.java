package com.duediligence.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.duediligence.entity.CustomerDueEntity;
import com.duediligence.model.Duediligence;
import com.duediligence.repository.DueRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DueServiceImplTest {
	
	@Mock
	DueRepository dueRepository;
	
	@InjectMocks
	private DueServiceImpl dueServiceImpl;

	@Test
	void testgetDueStatus() {				
		when(dueRepository.findByCustomerId(anyLong())).thenReturn(Optional.empty());
		Duediligence getStatus =  dueServiceImpl.getDueStatus(12L);
		assertNotNull(getStatus.getDueDiligenceStatus());
	}
	
	@Test
	void testgetDueStatuswithValue() {
		CustomerDueEntity getDue = new CustomerDueEntity();
		getDue.setCustomerId(12L);
		getDue.setCreditHistory(150);
		getDue.setLoanHistory(170);
		getDue.setNoofAccountsHistory(145);
		getDue.setPaymentHistory(189);
		when(dueRepository.findByCustomerId(anyLong())).thenReturn(Optional.of(getDue));
		Duediligence getStatus =  dueServiceImpl.getDueStatus(12L);
		assertEquals("Excellent",getStatus.getDueDiligenceStatus());		
	}
	
	@Test
	void testgetDueStatuswithValueLow() {
		CustomerDueEntity getDue = new CustomerDueEntity();
		getDue.setCustomerId(12L);
		getDue.setCreditHistory(20);
		getDue.setLoanHistory(20);
		getDue.setNoofAccountsHistory(100);
		getDue.setPaymentHistory(30);
		when(dueRepository.findByCustomerId(anyLong())).thenReturn(Optional.of(getDue));
		Duediligence getStatus =  dueServiceImpl.getDueStatus(12L);
		assertEquals("Poor",getStatus.getDueDiligenceStatus());		
	}
	
	@Test
	void testgetDueStatuswithValueAvg() {
		CustomerDueEntity getDue = new CustomerDueEntity();
		getDue.setCustomerId(12L);
		getDue.setCreditHistory(120);
		getDue.setLoanHistory(20);
		getDue.setNoofAccountsHistory(100);
		getDue.setPaymentHistory(30);
		when(dueRepository.findByCustomerId(anyLong())).thenReturn(Optional.of(getDue));
		Duediligence getStatus =  dueServiceImpl.getDueStatus(12L);
		assertEquals("Average",getStatus.getDueDiligenceStatus());		
	}

}
