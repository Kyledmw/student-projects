package com.tax_calculator.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tax_calculator.TaxCalculatorApplication;
import com.tax_calculator.models.CitizenWithNetPay;
import com.tax_calculator.models.util.CitizenNetPayComparatorOnId;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class CitizenNetPayCompositeServiceTests {

	@Autowired
	ICitizenNetPayCompositeService _service;
	
	@Test
	public void testCitizensWithNetPay() {
		List<CitizenWithNetPay> citizensWithNetPay = _service.getCitizensWithNetPay();
		
		Collections.sort(citizensWithNetPay, new CitizenNetPayComparatorOnId());
		
		CitizenWithNetPay citizen1 = citizensWithNetPay.get(0);
		
		assertEquals(citizen1.getCitizen().getName(), "Lyndon Ceballos");
		assertEquals(citizen1.getNetPay().compareTo(new BigDecimal(7000 - 210)), 0);
		
		CitizenWithNetPay citizen2 = citizensWithNetPay.get(1);

		assertEquals(citizen2.getCitizen().getName(), "Lori Restivo");
		assertEquals(citizen2.getNetPay().compareTo(new BigDecimal(10000 - 300)), 0);
		
		CitizenWithNetPay citizen3 = citizensWithNetPay.get(2);

		assertEquals(citizen3.getCitizen().getName(), "Pamula Lona");
		assertEquals(citizen3.getNetPay().compareTo(new BigDecimal(20000 - 2600)), 0);
		
		CitizenWithNetPay citizen4 = citizensWithNetPay.get(3);
		

		assertEquals(citizen4.getCitizen().getName(), "Kenny Randel");
		assertEquals(citizen4.getNetPay().compareTo(new BigDecimal(25000 - 3750)), 0);
		
		CitizenWithNetPay citizen5 = citizensWithNetPay.get(4);

		assertEquals(citizen5.getCitizen().getName(), "Bruno Kiernan");
		assertEquals(citizen5.getNetPay().compareTo(new BigDecimal(40000 - 10200)), 0);
	}
}
