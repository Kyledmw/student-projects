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
import com.voting_app.models.PoliticalParty;
import com.voting_app.services.interfaces.IPoliticalPartyService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= VotingAppApplication.class)
@Configuration
@SpringBootTest
public class PoliticalPartyServiceTests {

	@Mock
	private IPoliticalPartyService _mockPPartyService;
	
	@Mock
	private PoliticalParty _mockPParty;
	
	@Autowired
	private IPoliticalPartyService _ppartyService;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getPParty() {
		final int id = 1;
		final String name = "Dummy Name";
		
		PoliticalParty pparty = new PoliticalParty();
		pparty.setId(id);
		pparty.setName(name);
		
		when(_mockPPartyService.get(id)).thenReturn(pparty);
		assertEquals(pparty, _mockPPartyService.get(id));
	}
	
	@Test
	public void save() {
		final int defaultId = 0;
		final int changedId = 1;
		when(_mockPParty.getId()).thenReturn(defaultId);
		_ppartyService.save(_mockPParty);
		when(_mockPParty.getId()).thenReturn(changedId);
		_ppartyService.save(_mockPParty);
	}
	
	@Test
	public void getAll() {
		final int listSize = 30;
		List<PoliticalParty> list = new ArrayList<PoliticalParty>();
		for(int i=0; i<listSize; i++) {
			PoliticalParty pparty = mock(PoliticalParty.class);
			when(pparty.getId()).thenReturn(i);
			when(pparty.getName()).thenReturn("PParty " + i);
			list.add(pparty);
		}
		when(_mockPPartyService.getAll()).thenReturn(list);
		assertEquals(list, _mockPPartyService.getAll());
	}
}
