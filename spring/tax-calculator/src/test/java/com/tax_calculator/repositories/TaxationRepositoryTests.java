package com.tax_calculator.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.tax_calculator.TaxCalculatorApplication;
import com.tax_calculator.models.Tax;
import com.tax_calculator.repositories.schema.CitizensTableSchema;
import com.tax_calculator.repositories.schema.TaxationTableSchema;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class TaxationRepositoryTests {
	
	@Autowired JdbcTemplate _jdbcTemplate;
	
	@Autowired
	private ITaxationRepository _repo;
	
	@Test
	public void findAll() {
		List<Tax> taxRates = _repo.getAll();
		assertNotNull(taxRates);
		assertTrue(!taxRates.isEmpty());
	}
	
	@Test
	public void findById() {
		Tax tax = _repo.get(1);
		assertNotNull(tax);
		assertTrue(tax.getId() == 1);
		assertEquals(tax.getTaxFrom().compareTo(new BigDecimal(10000)), 0);
	}

	@Test
	public void update() {
		Tax tax = new Tax();
		tax.setId(2);
		tax.setTaxFrom(new BigDecimal(26000));
		tax.setAmountEligableForTax(new BigDecimal(10000));
		tax.setTaxPercent(0.3);
		tax.setTaxLimited(true);
		
		_repo.update(tax);
		Tax retrievedTax = _repo.get(2);
		assertNotNull(retrievedTax);
		assertEquals(retrievedTax.getTaxFrom().compareTo(tax.getTaxFrom()), 0);
		assertEquals(retrievedTax.getAmountEligableForTax().compareTo(tax.getAmountEligableForTax()), 0);
		assertTrue(retrievedTax.getTaxPercent() == tax.getTaxPercent());
		assertTrue(retrievedTax.isTaxLimited() == tax.isTaxLimited());
	}

	@Test
	public void create() {
		Tax tax = new Tax();
		tax.setTaxFrom(new BigDecimal(26000));
		tax.setAmountEligableForTax(new BigDecimal(10000));
		tax.setTaxPercent(0.3);
		tax.setTaxLimited(true);
		
		_repo.create(tax);
		assertNotEquals(tax.getId(), 0);
		
		Tax savedTax = _repo.get(tax.getId());
		assertNotNull(savedTax);
		assertEquals(savedTax.getTaxFrom().compareTo(tax.getTaxFrom()), 0);
		assertEquals(savedTax.getAmountEligableForTax().compareTo(tax.getAmountEligableForTax()), 0);
		assertTrue(savedTax.getTaxPercent() == tax.getTaxPercent());
		assertTrue(savedTax.isTaxLimited() == tax.isTaxLimited());
	}

	@After
	public void after() throws ScriptException, SQLException {
		JdbcTestUtils.dropTables(_jdbcTemplate, CitizensTableSchema.CITIZENS_TABLE_SCHEMA, TaxationTableSchema.TAXATION_TABLE);
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("schema.sql"));
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
	}
}
