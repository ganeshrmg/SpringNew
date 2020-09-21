package com.banking;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "customerinfo")
@Component
@Data
public class BankingConfig  {
	
		private Integer id;
		public Integer getId() {
			return id;
		}
		public void setId(Integer custId) {
			id = custId;
		}		
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String custName) {
			this.name = custName;
		}		
}
