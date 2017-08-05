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
import com.tax_calculator.models.Citizen;
import com.tax_calculator.repositories.schema.CitizensTableSchema;
import com.tax_calculator.repositories.schema.TaxationTableSchema;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class CitizensRepositoryTests {
	
	@Autowired JdbcTemplate _jdbcTemplate;

	@Autowired
	private ICitizensRepository _repo;
	
	@Test
	public void findAll() {
		List<Citizen> citizens = _repo.getAll();
		assertNotNull(citizens);
		assertTrue(!citizens.isEmpty());
	}
	
	@Test
	public void findById() {
		Citizen citizen = _repo.get(1);
		assertNotNull(citizen);
		assertTrue(citizen.getId() == 1);
		assertTrue(citizen.getName().equals("Lyndon Ceballos"));
		assertEquals(citizen.getSalary().compareTo(new BigDecimal(7000)), 0);
	}

	@Test
	public void update() {
		Citizen citizen = new Citizen();
		citizen.setId(2);
		citizen.setName("Leonarda Lesley");
		citizen.setSalary(new BigDecimal(11000));
		_repo.update(citizen);
		Citizen retrievedCitizen = _repo.get(2);
		assertNotNull(retrievedCitizen);
		assertEquals(retrievedCitizen.getName(), citizen.getName());
		assertEquals(retrievedCitizen.getSalary().compareTo(citizen.getSalary()), 0);
	}

	@Test
	public void create() {
		Citizen citizen = new Citizen();
		citizen.setName("Malcolm Tullier");
		citizen.setSalary(new BigDecimal(30000));
		_repo.create(citizen);
		
		assertNotEquals(citizen.getId(), 0);
		
		Citizen savedCitizen = _repo.get(citizen.getId());
		assertNotNull(savedCitizen);
		assertEquals(savedCitizen.getName(), citizen.getName());
		assertEquals(savedCitizen.getSalary().compareTo(citizen.getSalary()), 0);
	}

	@After
	public void after() throws ScriptException, SQLException {
		JdbcTestUtils.dropTables(_jdbcTemplate, CitizensTableSchema.CITIZENS_TABLE_SCHEMA, TaxationTableSchema.TAXATION_TABLE);
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("schema.sql"));
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
	}
}
 