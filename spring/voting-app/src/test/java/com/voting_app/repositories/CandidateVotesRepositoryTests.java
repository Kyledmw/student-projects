package com.voting_app.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.voting_app.VotingAppApplication;
import com.voting_app.models.Candidate;
import com.voting_app.models.CandidateVote;
import com.voting_app.models.CandidateVotes;
import com.voting_app.repositories.interfaces.ICandidateVotesRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class CandidateVotesRepositoryTests {
	
	@Autowired
	private ICandidateVotesRepository _candVotesRepo;

	@Test
	public void addVoteForCandidate() {
		final int id = 1;
		final int rank = 2;

		CandidateVote vote = new CandidateVote();
		vote.setCandidateId(id);
		vote.setRank(rank);
		
		Candidate candidate = new Candidate();
		candidate.setId(id);
		
		_candVotesRepo.addVoteForCandidate(vote);
		CandidateVotes votes = _candVotesRepo.getVotesForCandidate(candidate);
		
		Map<Integer, Integer> integer = votes.getVotesByRank();
		assertEquals(new Integer(1), integer.get(rank));
	}
	
	@Test
	public void deleteVotesForCandidate() {
		final int id = 1;
		
		Candidate cand = new Candidate();
		cand.setId(id);
		

		 CandidateVotes candVotes = _candVotesRepo.getVotesForCandidate(cand);
		 assertTrue(candVotes.getVotesByRank().isEmpty());
	}
}
