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
import com.voting_app.models.Constituency;
import com.voting_app.models.PoliticalParty;
import com.voting_app.services.interfaces.ICandidateService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class CandidateServiceTests {
	
	@Mock
	private ICandidateService _mockCandidateService;
	
	@Mock
	private Candidate _mockCandidate;
	
	@Autowired
	private ICandidateService _candidateService;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getCandidate() {
		final int id = 1;
		final String name = "Dummy Name";
		final int constitId = 5;
		final int ppartyId = 5;
		PoliticalParty pparty = new PoliticalParty();
		pparty.setId(ppartyId);
		Constituency constit = new Constituency();
		constit.setId(constitId);
		Candidate cand = new Candidate();
		cand.setId(id);
		cand.setName(name);
		cand.setPoliticalParty(pparty);
		
		when(_mockCandidateService.get(id)).thenReturn(cand);
		assertEquals(cand, _mockCandidateService.get(id));
	}
	
	@Test
	public void save() {
		final int defaultId = 0;
		final int changedId = 1;
		final int constitId = 10;
		final int ppartyId = 10;
		final String name = "Dummy Name";
		PoliticalParty pparty = new PoliticalParty();
		pparty.setId(ppartyId);
		Constituency constit = new Constituency();
		constit.setId(constitId);
		
		when(_mockCandidate.getId()).thenReturn(defaultId);
		when(_mockCandidate.getName()).thenReturn(name);
		when(_mockCandidate.getPoliticalParty()).thenReturn(pparty);
		when(_mockCandidate.getConstituency()).thenReturn(constit);
		_candidateService.save(_mockCandidate);

		when(_mockCandidate.getId()).thenReturn(changedId);
		_candidateService.save(_mockCandidate);
		
	}
	
	@Test
	public void getAll() {
		final int listSize = 30;
		List<Candidate> candidates = mockCandidateList(listSize);
		
		when(_mockCandidateService.getAll()).thenReturn(candidates);
		assertEquals(listSize, _mockCandidateService.getAll().size());
	}
	
	@Test
	public void getCandidatesForConstituency() {
		final int listSize = 30;
		List<Candidate> mockList = mockCandidateList(listSize);
		Constituency constit = mock(Constituency.class);
		when(constit.getId()).thenReturn(1);
		when(_mockCandidateService.getCandidatesForConstituency(constit)).thenReturn(mockList);
		List<Candidate> candidates = _mockCandidateService.getCandidatesForConstituency(constit);
		assertEquals(mockList, candidates);
	}
	
	private List<Candidate> mockCandidateList(int listSize) {
		List<Candidate> candidates = new ArrayList<Candidate>();
		for(int i = 0; i < listSize; i++) {
			Candidate cand = mock(Candidate.class);
			when(cand.getId()).thenReturn(i);
			when(cand.getName()).thenReturn("Candidate " + i);
			candidates.add(cand);
		}
		return candidates;
	}
}
