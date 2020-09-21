package com.duediligence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER_DUE_STATUS")
public class CustomerDueEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8261208340366229370L;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "DUE_SEQ")
	@SequenceGenerator(name = "DUE_SEQ", sequenceName = "DUE_SEQ", allocationSize = 1)
	private Long id;
	
	@Column(name="CUSTOMERID")
    private Long customerId;	
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name="PAYMENTHISTORY")
    private Integer PaymentHistory;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Integer getPaymentHistory() {
		return PaymentHistory;
	}

	public void setPaymentHistory(Integer paymentHistory) {
		PaymentHistory = paymentHistory;
	}

	public Integer getCreditHistory() {
		return CreditHistory;
	}

	public void setCreditHistory(Integer creditHistory) {
		CreditHistory = creditHistory;
	}

	public Integer getLoanHistory() {
		return LoanHistory;
	}

	public void setLoanHistory(Integer loanHistory) {
		LoanHistory = loanHistory;
	}

	public Integer getNoofAccountsHistory() {
		return NoofAccountsHistory;
	}

	public void setNoofAccountsHistory(Integer noofAccountsHistory) {
		NoofAccountsHistory = noofAccountsHistory;
	}

	@Column(name="CREDITCARDHISTORY")
    private Integer CreditHistory;	
	
	@Column(name="LOANHISTORY")
    private Integer LoanHistory;
	
	@Column(name="NOOFACCOUNTSHISTORY")
    private Integer NoofAccountsHistory;
	
	

}
