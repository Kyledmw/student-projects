package com.voting_app.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
import com.voting_app.models.Candidate;
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;
import com.voting_app.repositories.interfaces.ICandidateRepository;
import com.voting_app.repositories.interfaces.IConstituencyRepository;
import com.voting_app.repositories.interfaces.IPoliticalPartyRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest	
public class CandidateRepositoryTests {

	@Autowired
	private ICandidateRepository _candidateRepo;
	
	@Autowired
	private IConstituencyRepository _constRepo;
	
	@Autowired
	private IPoliticalPartyRepository _ppartyRepo;
	
	@Test
	public void getCandidateById() {
		final int id = 4;
		final String name = "Charlie McConalogue";
		
		Candidate c =_candidateRepo.get(id);
		assertNotNull(c);
		assertEquals(name, c.getName());
	}
	
	@Test
	public void createCandidate() {
		final String name = "Kyle Williamson";
		final int constId = 10;
		final int ppartyId = 10;
		
		Candidate c = new Candidate();
		c.setName(name);
		Constituency constit = _constRepo.get(constId);
		PoliticalParty pparty = _ppartyRepo.get(ppartyId);
		c.setConstituency(constit);
		c.setPoliticalParty(pparty);
		_candidateRepo.create(c);
		
		assertNotEquals(0, c.getId());
		Candidate retrievedC = _candidateRepo.get(c.getId());
		assertEquals(c, retrievedC);
		assertEquals(c.getName(), retrievedC.getName());
		assertEquals(constit, retrievedC.getConstituency());
		assertEquals(pparty, retrievedC.getPoliticalParty());
	}
	
	@Test
	public void deleteCandidate() {
		final int id = 32;
		
		Candidate c = _candidateRepo.get(id);
		_candidateRepo.delete(c);
		try {
			c = _candidateRepo.get(id);
		} catch(EmptyResultDataAccessException e) {
			return;
		}
		fail("Query should have a result size of 0");
	}
	
	@Test
	public void update() {
		final int id = 20;
		final int constId = 10;
		final int ppartyId = 10;
		final String name = "Dummy Name";
		
		Candidate c =_candidateRepo.get(id);
		Constituency constit = _constRepo.get(constId);
		PoliticalParty pparty = _ppartyRepo.get(ppartyId);
		c.setName(name);
		c.setConstituency(constit);
		c.setPoliticalParty(pparty);
		_candidateRepo.update(c);
		Candidate c2 = _candidateRepo.get(id);
		assertEquals(c.getName(), c2.getName());
		assertEquals(c.getConstituency(), c2.getConstituency());
		assertEquals(c.getPoliticalParty(), c2.getPoliticalParty());
	}
	
	
//	@Test
//	public void getAll() {
//		final int candCount = 401;
//		List<Candidate> candidates = _candidateRepo.getAll();
//		assertEquals(candCount, candidates.size());
//	}
}
