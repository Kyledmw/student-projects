package com.tax_calculator.services;

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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.tax_calculator.TaxCalculatorApplication;
import com.tax_calculator.models.Tax;
import com.tax_calculator.repositories.ITaxationRepository;
import com.tax_calculator.repositories.schema.CitizensTableSchema;
import com.tax_calculator.repositories.schema.TaxationTableSchema;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class TaxationServiceTests {
	
	@Autowired
	private JdbcTemplate _jdbcTemplate;

	@Autowired
	private ITaxationService _service;
	
	@Autowired
	private ITaxationRepository _repo;
	
	@Test
	public void saveNewTax() {
		Tax tax = new Tax();
		tax.setTaxFrom(new BigDecimal(26000));
		tax.setAmountEligableForTax(new BigDecimal(10000));
		tax.setTaxPercent(0.3);
		tax.setTaxLimited(true);
		
		_service.save(tax);

		assertNotEquals(tax.getId(), 0);
		
		Tax savedTax = _repo.get(tax.getId());
		assertNotNull(savedTax);
		assertEquals(savedTax.getTaxFrom().compareTo(tax.getTaxFrom()), 0);
		assertEquals(savedTax.getAmountEligableForTax().compareTo(tax.getAmountEligableForTax()), 0);
		assertTrue(savedTax.getTaxPercent() == tax.getTaxPercent());
		assertTrue(savedTax.isTaxLimited() == tax.isTaxLimited());
		
	}
	
	@Test
	public void saveExistingTax() {
		Tax tax = new Tax();
		tax.setId(2);
		tax.setTaxFrom(new BigDecimal(26000));
		tax.setAmountEligableForTax(new BigDecimal(10000));
		tax.setTaxPercent(0.3);
		tax.setTaxLimited(true);
		
		_service.save(tax);
		Tax retrievedTax = _repo.get(2);
		assertNotNull(retrievedTax);
		assertEquals(retrievedTax.getTaxFrom().compareTo(tax.getTaxFrom()), 0);
		assertEquals(retrievedTax.getAmountEligableForTax().compareTo(tax.getAmountEligableForTax()), 0);
		assertTrue(retrievedTax.getTaxPercent() == tax.getTaxPercent());
		assertTrue(retrievedTax.isTaxLimited() == tax.isTaxLimited());
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void delete() throws ScriptException, SQLException {
		Tax tax = new Tax();
		tax.setId(1);
		
		_service.delete(tax);
		_repo.get(1);
	}
	
	@Test
	public void getAll() {
		List<Tax> serviceAll = _service.getAll();
		assertNotNull(serviceAll);
		assertTrue(!serviceAll.isEmpty());
		
		List<Tax> repoAll = _repo.getAll();
		
		for(int i=0; i < repoAll.size(); i++) {
			Tax service = serviceAll.get(i);
			Tax repo = repoAll.get(i);
			assertEquals(service.getTaxFrom().compareTo(repo.getTaxFrom()), 0);
			assertEquals(service.getAmountEligableForTax().compareTo(repo.getAmountEligableForTax()), 0);
			assertTrue(service.getTaxPercent() == repo.getTaxPercent());
			assertTrue(service.isTaxLimited() == repo.isTaxLimited());
		}
		
	}
	
	@After
	public void after() throws ScriptException, SQLException {
		JdbcTestUtils.dropTables(_jdbcTemplate, CitizensTableSchema.CITIZENS_TABLE_SCHEMA, TaxationTableSchema.TAXATION_TABLE);
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("schema.sql"));
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
	}
	
}
