package com.duediligence.serviceimpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duediligence.model.Duediligence;
import com.duediligence.repository.DueRepository;
import com.duediligence.service.DueService;
import com.duediligence.entity.CustomerDueEntity;


@Service
public class DueServiceImpl implements DueService {
	
	@Autowired
	DueRepository dueRepository;
	
	public Random getValues() {		 
		return new Random();
	}
	
	public Duediligence getDueStatus(Long id) {
		Optional<CustomerDueEntity> getStatus = dueRepository.findByCustomerId(id);
		Random rand = getValues();
		Duediligence getDiligence;
		
		if(getStatus.isEmpty()) {
			CustomerDueEntity setValues = new CustomerDueEntity();
			setValues.setCreditHistory(rand.nextInt(200));
			setValues.setCustomerId(id);
			setValues.setLoanHistory(rand.nextInt(200));
			setValues.setNoofAccountsHistory(rand.nextInt(200));
			setValues.setPaymentHistory(rand.nextInt(200));
			dueRepository.save(setValues);
			 getDiligence = findDuediligence(setValues);			
		}else {
			 getDiligence = findDuediligence(getStatus.get());
		}
		
		return getDiligence;
		
	}

	private Duediligence findDuediligence(CustomerDueEntity setValues) {
		int creditScore = (setValues.getCreditHistory()+setValues.getLoanHistory()+setValues.getNoofAccountsHistory()+setValues.getPaymentHistory());
		String status = "Need to find";
		
		DueStatus calculateScore=(a,b,c)->(b <= a && a <= c);
			
		if (calculateScore.getStaus(creditScore, 1, 199)) {
			status = "Poor";
		} else if (calculateScore.getStaus(creditScore, 200, 399)) {
			status = "Average";
		} else if (calculateScore.getStaus(creditScore, 400, 599)) {
			status = "Good";
		} else if (calculateScore.getStaus(creditScore, 600, 799)) {
			status = "Excellent";
		}
		Duediligence getDiligence = new Duediligence();
		getDiligence.setCreditScore(creditScore);
		getDiligence.setDueDiligenceStatus(status);
		return getDiligence;
	}		

}
interface DueStatus{  
    boolean getStaus(int a,int b,int c);  
}  
