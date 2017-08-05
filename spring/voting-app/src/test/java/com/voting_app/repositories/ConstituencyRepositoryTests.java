package com.voting_app.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.voting_app.VotingAppApplication;
import com.voting_app.models.Constituency;
import com.voting_app.repositories.interfaces.IConstituencyRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class ConstituencyRepositoryTests {
	
	@Autowired
	private IConstituencyRepository _constRepo;

	@Test
	public void getConstituency() {
		final int id = 2;
		final String name = "Carlow-Kilkenny";
		
		Constituency constit = _constRepo.get(id);
		assertEquals(constit.getName(), name);
	}
	
	@Test
	public void createConstituency() {
		final String name = "Dummy Name";
		
		Constituency constit = new Constituency();
		constit.setName(name);
		
		_constRepo.create(constit);
		
		assertNotEquals(0, constit.getId());
		Constituency constit2 = _constRepo.get(constit.getId());
		assertEquals(constit, constit2);
		assertEquals(constit.getName(), constit2.getName());
	}
	
	@Test
	public void deleteConstituency() {
		final int id = 1;
		
		Constituency constit = new Constituency();
		constit.setId(id);
		_constRepo.delete(constit);
		
		try {
			_constRepo.get(id);
		} catch(EmptyResultDataAccessException e) {
			return;
		}
		fail("Query should have a result size of 0");
	}
	
	@Test
	public void getAll() {
		final int constituency_count = 40;
		
		List<Constituency> constituencies = _constRepo.getAll();
		assertEquals(constituency_count, constituencies.size());
	}
	
	@Test
	public void update() {
		final int id = 1;
		final String name= "Dummy Name";
		
		Constituency constit = _constRepo.get(id);
		constit.setName(name);
		
		_constRepo.update(constit);
		Constituency constit2 = _constRepo.get(id);
		
		assertEquals(name, constit2.getName());
	}
}
