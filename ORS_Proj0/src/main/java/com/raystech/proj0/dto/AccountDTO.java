package com.raystech.proj0.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Account POJO class. It is persistent object.
 *
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@Entity

@Table(name = "st_Account")
public class AccountDTO extends BaseDTO {

	/**
	 * Id of Account
	 */
	@Column(name ="ACCOUNT_ID")
	private long accountId;
	
	/**
	 * 1ST Month Fees of Student
	 */
	@Column(name="1ST_MONTH_FEES")
	private Integer firstMonthFees;
	
	/**
	 * 2ND Month Fees of Student
	 */
	@Column(name="2ND_MONTH_FEES")
	private Integer secondMonthFees;
	
	/**
	 * 3ED Month Fees of Student
	 */
	@Column(name="3RD_MONTH_FEES")
	private Integer thirdMonthFees;
	
	/**
	 * 4TH Month Fees of Student
	 */
	@Column(name="4TH_MONTH_FEES")
	private Integer fourthMonthFees;
	
	/**
	 * 5TH Month Fees of Student
	 */
	@Column(name="5TH_MONTH_FEES")
	private Integer fiveMonthFees;
	
	/**
	 * 6TH Month Fees of Student
	 */
	@Column(name="6TH_MONTH_FEES")
	private Integer sixMonthFees;
	
	/**
	 * Total Fees
	 */
	private Integer total;
	
	
	
	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Integer getFirstMonthFees() {
		return firstMonthFees;
	}

	public void setFirstMonthFees(Integer firstMonthFees) {
		this.firstMonthFees = firstMonthFees;
	}

	public Integer getSecondMonthFees() {
		return secondMonthFees;
	}

	public void setSecondMonthFees(Integer secondMonthFees) {
		this.secondMonthFees = secondMonthFees;
	}

	public Integer getThirdMonthFees() {
		return thirdMonthFees;
	}

	public void setThirdMonthFees(Integer thirdMonthFees) {
		this.thirdMonthFees = thirdMonthFees;
	}

	public Integer getFourthMonthFees() {
		return fourthMonthFees;
	}

	public void setFourthMonthFees(Integer fourthMonthFees) {
		this.fourthMonthFees = fourthMonthFees;
	}

	public Integer getFiveMonthFees() {
		return fiveMonthFees;
	}

	public void setFiveMonthFees(Integer fiveMonthFees) {
		this.fiveMonthFees = fiveMonthFees;
	}

	public Integer getSixMonthFees() {
		return sixMonthFees;
	}

	public void setSixMonthFees(Integer sixMonthFees) {
		this.sixMonthFees = sixMonthFees;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
