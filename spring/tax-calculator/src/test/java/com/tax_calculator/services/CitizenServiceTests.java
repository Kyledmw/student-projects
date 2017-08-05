package com.tax_calculator.services;

import static org.junit.Assert.assertEquals;
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
import com.tax_calculator.models.Citizen;
import com.tax_calculator.repositories.ICitizensRepository;
import com.tax_calculator.repositories.schema.CitizensTableSchema;
import com.tax_calculator.repositories.schema.TaxationTableSchema;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class CitizenServiceTests {
	
	@Autowired
	private JdbcTemplate _jdbcTemplate;
	
	@Autowired
	private ICitizensRepository _repo;
	
	@Autowired
	private ICitizensService _service;
	
	@Test
	public void saveNewCitizen() {
		Citizen newCitizen = new Citizen();
		newCitizen.setName("John Smith");
		newCitizen.setSalary(new BigDecimal(30000));
		_service.save(newCitizen);
		
		assertTrue(newCitizen.getId() != 0);
		Citizen retrievedCitizen = _repo.get(newCitizen.getId());
		
		assertNotNull(retrievedCitizen);
		assertEquals(retrievedCitizen.getName(), newCitizen.getName());
		assertEquals(retrievedCitizen.getSalary().compareTo(newCitizen.getSalary()), 0);
		
	}
	
	@Test
	public void saveExistingCitizen() {
		Citizen citizen = new Citizen();
		citizen.setId(2);
		citizen.setName("John Smith");
		citizen.setSalary(new BigDecimal(30000));
		_service.save(citizen);
		
		Citizen retrievedCitizen = _repo.get(2);
		
		assertNotNull(retrievedCitizen);
		assertEquals(retrievedCitizen.getName(), citizen.getName());
		assertEquals(retrievedCitizen.getSalary().compareTo(citizen.getSalary()), 0);
	}
	
	@Test
	public void getAll() {
		List<Citizen> serviceAll = _service.getAll();
		assertNotNull(serviceAll);
		assertTrue(!serviceAll.isEmpty());
		
		List<Citizen> repoAll = _repo.getAll();
		
		for(int i = 0; i < repoAll.size(); i++) {
			Citizen service = serviceAll.get(i);
			Citizen repo = repoAll.get(i);
			
			assertTrue(service.getId() == repo.getId());
			assertEquals(service.getSalary().compareTo(repo.getSalary()), 0);
			assertEquals(service.getName(), repo.getName());
		}
	}
	
	@After
	public void after() throws ScriptException, SQLException {
		JdbcTestUtils.dropTables(_jdbcTemplate, CitizensTableSchema.CITIZENS_TABLE_SCHEMA, TaxationTableSchema.TAXATION_TABLE);
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("schema.sql"));
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
	}
	
}
