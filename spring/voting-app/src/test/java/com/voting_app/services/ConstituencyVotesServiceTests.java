package com.voting_app.services;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import com.voting_app.models.Constituency;
import com.voting_app.models.ConstituencyVotes;
import com.voting_app.services.interfaces.IConstituencyVotesService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class ConstituencyVotesServiceTests {

	@Autowired
	private IConstituencyVotesService _constitVotesService;
	
	@Mock
	private IConstituencyVotesService _mockConstitVotesService;
	
	@Mock
	private ConstituencyVotes _mockConstitVotes;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getVotesForConstituency() {
		final int id = 1;
		
		Constituency constit = mock(Constituency.class);
		when(constit.getId()).thenReturn(id);
		ConstituencyVotes cVotes = _constitVotesService.getVotesForConstiuency(constit);
		assertEquals(id, cVotes.getConstituency().getId());
		
	}
	
	@Test
	public void save() {
		final int id = 7;
		final int defaultId = 0;
		
		final int validVotes = 4;
		final int invalidVotes = 5;
		
		Constituency constit = mock(Constituency.class);
		when(constit.getId()).thenReturn(id);
		
		ConstituencyVotes cVotes = _constitVotesService.getVotesForConstiuency(constit);
		
		_constitVotesService.save(cVotes);
		
		ConstituencyVotes mockCVotes = mock(ConstituencyVotes.class);
		when(mockCVotes.getId()).thenReturn(defaultId);
		when(mockCVotes.getValidVotes()).thenReturn(validVotes);
		when(mockCVotes.getInvalidVotes()).thenReturn(invalidVotes);
		when(mockCVotes.getConstituency()).thenReturn(constit);
		
		_constitVotesService.save(mockCVotes);
	}
}
