package com.voting_app.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;
import com.voting_app.services.interfaces.ICandidateVotesService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class CandidateVotesServiceTest {
	
	@Autowired
	private ICandidateVotesService _candVotesService;
	
	@Mock
	private Candidate _mockCandidate;
	
	@Mock
	private CandidateVote _mockCVote;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getVotesForCandidate() {
		final int candId = 19;
		final int constitId = 5;
		final int ppartyId = 5;
		PoliticalParty pparty = new PoliticalParty();
		pparty.setId(ppartyId);
		Constituency constit = new Constituency();
		constit.setId(constitId);
		
		
		when(_mockCandidate.getId()).thenReturn(candId);
		when(_mockCandidate.getConstituency()).thenReturn(constit);
		when(_mockCandidate.getPoliticalParty()).thenReturn(pparty);
		
		CandidateVotes cVotes = _candVotesService.getVotesForCandidate(_mockCandidate);
		
		assertEquals(cVotes.getCandidate(), _mockCandidate);
	}
	
	@Test
	public void vote() {
		final int candId = 19;
		final int rank = 1;
		when(_mockCVote.getCandidateId()).thenReturn(candId);
		when(_mockCVote.getRank()).thenReturn(rank);
		_candVotesService.vote(_mockCVote);
	}
	
	@Test
	public void getVotesForCandidates() {
		final int listSize = 10;
		List<Candidate> cList = new ArrayList<Candidate>();
		for(int i=0; i<listSize; i++) {
			Candidate cand = mock(Candidate.class);
			when(cand.getId()).thenReturn(i);
			cList.add(cand);
		}
		List<CandidateVotes> cvList = _candVotesService.getVotesForCandidates(cList);
		assertEquals(listSize, cvList.size());
	}
	
	@Test
	public void addVoteList() {
		final int listSize = 10;
		List<CandidateVote> cvList = new ArrayList<CandidateVote>();
		for(int i=0; i<listSize; i++) {
			CandidateVote cVote = mock(CandidateVote.class);
			Candidate c = new Candidate();
			c.setId(i);
			when(cVote.getCandidateId()).thenReturn(i + 1);
			when(cVote.getRank()).thenReturn(i + 1);
			cvList.add(cVote);
		}
		_candVotesService.addVoteList(cvList);
		
	}

}
