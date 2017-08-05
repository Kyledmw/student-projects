package com.crowdfunder.control.forms;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.crowdfunder.control.validation.annotations.ProjectPledgeable;
import com.crowdfunder.control.validation.annotations.UserCanPledge;

/**
 ********************************************************************
 * Class representing a form for creating a new pledge.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class PledgeForm {

	@ProjectPledgeable
	private long projectId;
	
	@DecimalMax(value = "5000", message = "{pledgeForm.amount.minMax}")
	@DecimalMin(value = "1", message = "{pledgeForm.amount.minMax}")
	@UserCanPledge
	private BigDecimal amount;

	public long getProjectId() {
		return projectId;
	}
	
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
