package com.tax_calculator.repositories;

import java.sql.SQLException;

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
import com.tax_calculator.models.Citizen;
import com.tax_calculator.models.Tax;
import com.tax_calculator.repositories.schema.CitizensTableSchema;
import com.tax_calculator.repositories.schema.TaxationTableSchema;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= TaxCalculatorApplication.class)
@Configuration
@SpringBootTest
public class RepositoryDeleteTests {
	
	@Autowired
	private JdbcTemplate _jdbcTemplate;
	
	@Autowired
	private ICitizensRepository _citizenRepo;
	
	@Autowired
	private ITaxationRepository _taxationRepo;

	@Test(expected=EmptyResultDataAccessException.class)
	public void citizenDelete() {
		Citizen citizen = new Citizen();
		citizen.setId(1);
		
		_citizenRepo.delete(citizen);
		
		_citizenRepo.get(1);
		
	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void taxDelete() {
		Tax tax = new Tax();
		tax.setId(1);
		
		_taxationRepo.delete(tax);
		
		_taxationRepo.get(1);
	}

	@After
	public void after() throws ScriptException, SQLException {
		JdbcTestUtils.dropTables(_jdbcTemplate, CitizensTableSchema.CITIZENS_TABLE_SCHEMA, TaxationTableSchema.TAXATION_TABLE);
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("schema.sql"));
		ScriptUtils.executeSqlScript(_jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
	}
}
