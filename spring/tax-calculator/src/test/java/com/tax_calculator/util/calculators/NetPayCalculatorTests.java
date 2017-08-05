package com.tax_calculator.util.calculators;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tax_calculator.TaxCalculatorApplication;
import com.tax_calculator.models.Citizen;
import com.tax_calculator.models.Tax;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class NetPayCalculatorTests {

	@Autowired
	private INetPayCalculator _netPayCalc;
	
	private static List<Tax> _taxRates;
	
	@Before
	public void setupTaxRates() {
		_taxRates = new ArrayList<Tax>();
		
		Tax firstTax = new Tax();
		firstTax.setTaxFrom(new BigDecimal(10000));
		firstTax.setAmountEligableForTax(new BigDecimal(15000));
		firstTax.setTaxPercent(0.2);
		firstTax.setTaxLimited(true);
		_taxRates.add(firstTax);
		
		Tax secondTax = new Tax();
		secondTax.setTaxFrom(new BigDecimal(25000));
		secondTax.setAmountEligableForTax(new BigDecimal(0));
		secondTax.setTaxPercent(0.4);
		secondTax.setTaxLimited(false);
		_taxRates.add(secondTax);
		
		Tax solidarityTax = new Tax();
		solidarityTax.setTaxFrom(new BigDecimal(0));
		solidarityTax.setAmountEligableForTax(new BigDecimal(0));
		solidarityTax.setTaxPercent(0.03);
		solidarityTax.setTaxLimited(false);
		_taxRates.add(solidarityTax);
	}
	
	@Test
	public void testSalaries() {
		Citizen citizen1 = new Citizen();
		citizen1.setName("");
		citizen1.setSalary(new BigDecimal(7000));
		
		BigDecimal tax1 = _netPayCalc.calculate(_taxRates, citizen1);
		assertEquals(tax1.compareTo(new BigDecimal(7000 - 210)), 0);
		
		Citizen citizen2 = new Citizen();
		citizen2.setName("");
		citizen2.setSalary(new BigDecimal(10000));
		
		BigDecimal tax2 = _netPayCalc.calculate(_taxRates, citizen2);
		assertEquals(tax2.compareTo(new BigDecimal(10000 - 300)), 0);
		
		Citizen citizen3 = new Citizen();
		citizen3.setName("");
		citizen3.setSalary(new BigDecimal(20000));
		
		BigDecimal tax3 = _netPayCalc.calculate(_taxRates, citizen3);
		assertEquals(tax3.compareTo(new BigDecimal(20000 - 2600)), 0);
		
		Citizen citizen4 = new Citizen();
		citizen4.setName("");
		citizen4.setSalary(new BigDecimal(25000));
		
		BigDecimal tax4 = _netPayCalc.calculate(_taxRates, citizen4);
		assertEquals(tax4.compareTo(new BigDecimal(25000 - 3750)), 0);
		
		Citizen citizen5 = new Citizen();
		citizen5.setName("");
		citizen5.setSalary(new BigDecimal(40000));
		
		BigDecimal tax5 = _netPayCalc.calculate(_taxRates, citizen5);
		assertEquals(tax5.compareTo(new BigDecimal(40000 - 10200)), 0);
	}
}
