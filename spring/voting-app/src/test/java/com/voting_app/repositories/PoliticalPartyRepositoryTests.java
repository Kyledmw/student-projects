package com.voting_app.repositories;

import static org.junit.Assert.assertEquals;
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
import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.interfaces.IPoliticalPartyRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class PoliticalPartyRepositoryTests {
	
	@Autowired
	private IPoliticalPartyRepository _ppartyRepo;

	@Test
	public void getPoliticalParty() {
		final int id = 4;
		final String name = "Direct Democracy Ireland";
		
		PoliticalParty pparty = _ppartyRepo.get(id);
		
		assertEquals(name, pparty.getName());
	}
	
	@Test
	public void createPoliticalParty() {
		final String name = "Dummy Name";
		
		PoliticalParty pparty = new PoliticalParty();
		pparty.setName(name);
		
		_ppartyRepo.create(pparty);
		
		PoliticalParty pparty2 = _ppartyRepo.get(pparty.getId());
		
		assertEquals(name, pparty2.getName());
	}
	
	@Test
	public void deletePoliticalParty() {
		final int id = 2;
		
		PoliticalParty pparty = new PoliticalParty();
		pparty.setId(id);
		_ppartyRepo.delete(pparty);
		
		try {
			_ppartyRepo.get(id);
		} catch(EmptyResultDataAccessException e) {
			return;
		}
		fail("Query should have a result size of 0");
	}
	
	@Test
	public void getAllPParties() {
		final int totalPoliticalParties = 15;
		
		List<PoliticalParty> pparties = _ppartyRepo.getAll();
		assertEquals(totalPoliticalParties, pparties.size());
	}
	
	@Test
	public void updatePParty() {
		final int id = 2;
		final String name = "Dummy Name";
		
		PoliticalParty pparty = _ppartyRepo.get(id);
		pparty.setName(name);
		
		_ppartyRepo.update(pparty);
		PoliticalParty pparty2 = _ppartyRepo.get(id);
		
		assertEquals(pparty, pparty2);
		assertEquals(name, pparty2.getName());
	}
}
