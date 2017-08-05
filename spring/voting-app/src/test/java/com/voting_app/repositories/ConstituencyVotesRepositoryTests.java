package com.voting_app.repositories;

import static org.junit.Assert.assertEquals;
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
import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;
import com.voting_app.repositories.interfaces.IConstituencyVotesRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class ConstituencyVotesRepositoryTests {
	
	@Autowired
	private IConstituencyVotesRepository _constVotesRepo;

	@Test
	public void getConstituencyVotes() {
		final int id = 1;
		final int constId = 36;
		
		ConstituencyVotes cVotes = _constVotesRepo.get(id);
		assertEquals(constId, cVotes.getConstituency().getId());
	}
	
	@Test
	public void createConstituencyVotes() {
		final int constitId = 20;
		final int invalidVotes = 3;
		final int validVotes = 2;
		
		ConstituencyVotes cVotes = new ConstituencyVotes();
		Constituency constit = new Constituency();
		constit.setId(constitId);
		cVotes.setConstituency(constit);
		cVotes.setInvalidVotes(invalidVotes);
		cVotes.setValidVotes(validVotes);
		
		_constVotesRepo.create(cVotes);
		
		ConstituencyVotes cVotes2 = _constVotesRepo.get(cVotes.getId());
		
		assertEquals(constitId, cVotes2.getConstituency().getId());
		assertEquals(cVotes, cVotes2);
		assertEquals(invalidVotes, cVotes2.getInvalidVotes());
		assertEquals(validVotes, cVotes2.getValidVotes());
	}
	
	@Test
	public void updateConstituencyVotes() {
		final int id = 1;
		final int invalidVotes = 99;
		final int validVotes = 99;
		
		ConstituencyVotes cVotes = _constVotesRepo.get(id);
		cVotes.setInvalidVotes(invalidVotes);
		cVotes.setValidVotes(validVotes);
		
		_constVotesRepo.update(cVotes);
		ConstituencyVotes cVotes2 = _constVotesRepo.get(id);
		
		assertEquals(cVotes, cVotes2);
		assertEquals(validVotes, cVotes2.getValidVotes());
		assertEquals(invalidVotes, cVotes2.getInvalidVotes());
	}
	
	@Test
	public void deleteConstituencyVotes() {
		final int id = 1;
		
		ConstituencyVotes cVotes = _constVotesRepo.get(id);
		_constVotesRepo.delete(cVotes);
		
		try {
			_constVotesRepo.get(id);
		} catch(EmptyResultDataAccessException e) {
			return;
		}
		fail("Query should have a result size of 0");
	}
	
	@Test
	public void getVotesForConstituency() {
		final int constId = 25;
		
		Constituency constit = new Constituency();
		constit.setId(constId);
		
		ConstituencyVotes cVotes = _constVotesRepo.getVotesForConstituency(constit);
		assertEquals(constId, cVotes.getConstituency().getId());
	}
	
	@Test
	public void deleteVotesForConstituency() {
		final int constId = 1;
		
		Constituency constit = new Constituency();
		constit.setId(constId);
		
		_constVotesRepo.deleteVotesForConstituency(constit);
		
		try {
			_constVotesRepo.getVotesForConstituency(constit);
		}  catch(EmptyResultDataAccessException e) {
			return;
		}
		fail("Query should have a result size of 0");
	}
	
//	@Test
//	public void getAllConstituencyVotes() {
//		final int constVoteCount = 41;
//		
//		List<ConstituencyVotes> cVotesList = _constVotesRepo.getAll();
//		
//		assertEquals(constVoteCount, cVotesList.size());
//	}
}
