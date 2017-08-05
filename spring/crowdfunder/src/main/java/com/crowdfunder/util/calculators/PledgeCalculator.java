package com.crowdfunder.util.calculators;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.User;
import com.crowdfunder.util.calculators.interfaces.IPledgeCalculator;

/**
 ********************************************************************
 * Standard implementation of IPledgeCalculator
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
public class PledgeCalculator implements IPledgeCalculator {

	@Override
	public BigDecimal calculatePledgeAmount(List<Pledge> pledges) {
		return calculateAmount(pledges, true);
	}

	@Override
	public BigDecimal calculateInActivePledgeAmount(List<Pledge> pledges) {
		return calculateAmount(pledges, false);
	}

	/**
	 * Generic method that calculates the active/inactive total based off the boolean given
	 * 
	 * @param pledges List<Pledge> list of pledge objects
	 * @param active boolean used to determine if calculating active(true) or inactive(false) pledges
	 * 
	 * @return BigDecimal total calculated amount.
	 */
	private BigDecimal calculateAmount(List<Pledge> pledges, boolean active) {
		BigDecimal amountPledged = new BigDecimal(0);
		for(Pledge pledge : pledges) {
			if(pledge.getActive() && active) {
				amountPledged = amountPledged.add(pledge.getAmount());
			} else if(!active && !pledge.getActive()) {
				amountPledged = amountPledged.add(pledge.getAmount());
			}
		}
		return amountPledged;
	}

	@Override
	public BigDecimal calculateAvailableAmountToPledge(User user) {
		BigDecimal pledgeAmount = calculatePledgeAmount(user.getPledges());
		return user.getCreditLimit().subtract(pledgeAmount);
	}

	@Override
	public BigDecimal calculateTotalAmount(List<Pledge> pledges) {
		return calculatePledgeAmount(pledges).add(calculateInActivePledgeAmount(pledges));
	}
}
