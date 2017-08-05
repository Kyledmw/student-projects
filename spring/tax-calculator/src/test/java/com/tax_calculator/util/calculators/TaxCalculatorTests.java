package com.tax_calculator.util.calculators;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tax_calculator.TaxCalculatorApplication;
import com.tax_calculator.models.Tax;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class TaxCalculatorTests {

	@Autowired
	private ITaxCalculator _taxCalc;
	
	@Test
	public void taxEntireSalary() {
		BigDecimal salary = new BigDecimal(10000);
		
		Tax taxRate = new Tax();
		taxRate.setTaxFrom(new BigDecimal(0));
		taxRate.setAmountEligableForTax(new BigDecimal(0));
		taxRate.setTaxLimited(false);
		taxRate.setTaxPercent(0.2);
		
		BigDecimal tax = _taxCalc.calculate(taxRate, salary);
		assertEquals(tax.compareTo(new BigDecimal(2000)), 0);
	}
	
	@Test
	public void taxRangeOfSalary() {
		BigDecimal salary1 = new BigDecimal(25000);
		BigDecimal salary2 = new BigDecimal(8000);
		BigDecimal salary3 = new BigDecimal(15000);
		BigDecimal salary4 = new BigDecimal(10000);
		
		Tax taxRate = new Tax();
		taxRate.setTaxFrom(new BigDecimal(10000));
		taxRate.setAmountEligableForTax(new BigDecimal(10000));
		taxRate.setTaxLimited(true);
		taxRate.setTaxPercent(0.2);
		
		BigDecimal tax = _taxCalc.calculate(taxRate, salary1);
		assertEquals(tax.compareTo(new BigDecimal(2000)), 0);
		
		tax = _taxCalc.calculate(taxRate, salary2);
		assertEquals(tax.compareTo(new BigDecimal(0)), 0);
		
		tax = _taxCalc.calculate(taxRate, salary3);
		assertEquals(tax.compareTo(new BigDecimal(1000)), 0);
		
		tax = _taxCalc.calculate(taxRate, salary4);
		assertEquals(tax.compareTo(new BigDecimal(0)), 0);
	}
	
	@Test
	public void taxAllFromAmount() {
		BigDecimal salary1 = new BigDecimal(20000);
		BigDecimal salary2 = new BigDecimal(10000);
		BigDecimal salary3 = new BigDecimal(5000);
		
		Tax taxRate = new Tax();
		taxRate.setTaxFrom(new BigDecimal(10000));
		taxRate.setAmountEligableForTax(new BigDecimal(0));
		taxRate.setTaxLimited(false);
		taxRate.setTaxPercent(0.2);
		
		BigDecimal tax = _taxCalc.calculate(taxRate, salary1);
		assertEquals(tax.compareTo(new BigDecimal(2000)), 0);
		
		tax = _taxCalc.calculate(taxRate, salary2);
		assertEquals(tax.compareTo(new BigDecimal(0)), 0);
		
		tax = _taxCalc.calculate(taxRate, salary3);
		assertEquals(tax.compareTo(new BigDecimal(0)), 0);		
	}
}
