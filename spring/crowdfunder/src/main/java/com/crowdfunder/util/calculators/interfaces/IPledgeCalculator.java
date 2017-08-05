package com.crowdfunder.util.calculators.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.User;

/**
 ********************************************************************
 * Interface for calculators focused on calculating total pledge counts.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IPledgeCalculator {

	/**
	 * Calculate the total amount pledged in which all pledges are valid.
	 * 
	 * @param pledges List<Pledge> list of pledge objects
	 * 
	 * @return BigDecimal total representing the total amount pledged
	 */
	BigDecimal calculatePledgeAmount(List<Pledge> pledges);
	
	/**
	 * Calculate the total amount of cancelled/inactive pledges. 
	 * 
	 * 
	 * @param pledges List<Pledge> list of pledge objects
	 * 
	 * @return BigDecimal total representing the total amount pledged
	 */
	BigDecimal calculateInActivePledgeAmount(List<Pledge> pledges);
	
	
	/**
	 * Calculate the total amount of pledges. 
	 * 
	 * 
	 * @param pledges List<Pledge> list of pledge objects
	 * 
	 * @return BigDecimal total representing the total amount pledged
	 */
	BigDecimal calculateTotalAmount(List<Pledge> pledges);

	
	/**
	 * Calculate the amount available to a user to pledge
	 * 
	 * @param user User to check amount available to pledge
	 * @return BigDecimal amount available to pledge
	 */
	BigDecimal calculateAvailableAmountToPledge(User user);
}
