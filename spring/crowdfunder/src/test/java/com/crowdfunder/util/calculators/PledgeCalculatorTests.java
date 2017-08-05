package com.crowdfunder.util.calculators;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crowdfunder.CrowdfunderApplication;
import com.crowdfunder.models.Pledge;
import com.crowdfunder.models.User;
import com.crowdfunder.util.calculators.interfaces.IPledgeCalculator;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= CrowdfunderApplication.class)
@WebAppConfiguration
@SpringBootTest
public class PledgeCalculatorTests {

	@Autowired
	private IPledgeCalculator _calculator;
	
	private List<Pledge> _pledgeList;
	
	@Before
	public void setupPledgeList() {
		_pledgeList = new ArrayList<Pledge>();
		
		for(int i=0; i < 30; i++) {
			Pledge pledge = new Pledge();
			pledge.setActive(true);
			pledge.setAmount(new BigDecimal(200));
			_pledgeList.add(pledge);
		}
		
		for(int i=0; i<20; i++) {
			Pledge pledge = new Pledge();
			pledge.setActive(false);
			pledge.setAmount(new BigDecimal(350));
			_pledgeList.add(pledge);
		}
	}

	@Test
	public void calculatePledgeAmount() {
		BigDecimal totalAmount = _calculator.calculatePledgeAmount(_pledgeList);
		assertEquals(totalAmount, new BigDecimal(6000));
	}
	
	@Test
	public void calculateInActivePledgeAmount() {
		BigDecimal totalInactiveAmount = _calculator.calculateInActivePledgeAmount(_pledgeList);
		assertEquals(totalInactiveAmount, new BigDecimal(7000));
	}
	
	@Test
	public void calculateAvailableAmountToPledge() {
		User user = new User();
		user.setCreditLimit(new BigDecimal(10000));
		user.setPledges(_pledgeList);
		BigDecimal amountAvail = _calculator.calculateAvailableAmountToPledge(user);
		assertEquals(amountAvail, new BigDecimal(4000));
	}
}
