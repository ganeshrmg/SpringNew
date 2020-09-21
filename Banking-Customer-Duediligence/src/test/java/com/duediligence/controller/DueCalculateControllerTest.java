package com.duediligence.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.duediligence.model.Duediligence;
import com.duediligence.service.DueService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DueCalculateController.class)
class DueCalculateControllerTest {
	
	@InjectMocks
	private DueCalculateController dueCalculateController;
	
	@MockBean
	DueService dueService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testgetDuediligenceStatus() throws Exception {
		Duediligence due = new Duediligence();
		due.setCreditScore(450);
		due.setDueDiligenceStatus("Good");
		Mockito.when(dueService.getDueStatus(Mockito.anyLong())).thenReturn(due);		
		RequestBuilder builder = MockMvcRequestBuilders.get("/getDuediligenceStatus/14")
                .requestAttr("customerid", 14L);
		
		MvcResult result = mockMvc.perform(builder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());	
	}	

}
